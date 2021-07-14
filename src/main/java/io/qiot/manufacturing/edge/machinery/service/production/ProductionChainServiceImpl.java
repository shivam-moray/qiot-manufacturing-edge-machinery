/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.production;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.ValidationFailedEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.ValidationSuccessfullEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.domain.productline.ProductLineDTO;
import io.qiot.manufacturing.edge.machinery.service.productline.ProductLineService;
import io.qiot.manufacturing.edge.machinery.service.validation.ValidationService;
import io.quarkus.scheduler.Scheduled;

/**
 * @author andreabattaglia
 *
 */
public class ProductionChainServiceImpl implements ProductionChainService {

    @Inject
    Logger LOGGER;

    /*
     * SERVICES
     */
    @Inject
    ConveyorBeltService conveyorBeltService;

    @Inject
    ProductLineService productLineService;

    @Inject
    ValidationService validationService;

    @Inject
    CountersService countersService;

    /*
     * EVENTS
     */

    @Scheduled(every = "10s")
    // @Override
    public void produce() {
        if (!productLineService.hasProductLine()) {
            LOGGER.warn("No product Liner available");
            return;
        }

        // setup product line
        ProductLineDTO productLineDTO = productLineService
                .getCurrentProductLine();

        int itemId = countersService.recordNewItem(productLineDTO.productLineId);

        // setup new item
        conveyorBeltService.createNewItem(productLineDTO.productLineId, itemId);

    }

    void onStageCompleted(@Observes StageCompletedEvent event) {
        ItemDTO item = event.item;
        countersService.recordStageEnd(item.id, item.productLineId, item.stage);
        conveyorBeltService.moveToWaitingQueue(item);
        validationService.validateItem(item);
    };

    void onValidationSuccessfull(@Observes ValidationSuccessfullEvent event) {
        ItemDTO item = conveyorBeltService.moveToNextStage(event.itemId,
                event.stage);
        countersService.recordStageSuccess(item.id, item.productLineId, item.stage);
        conveyorBeltService.moveToNextStage(item.id, item.stage);
    };

    void onValidationFailed(@Observes ValidationFailedEvent event) {
        ItemDTO item = conveyorBeltService.dropItem(event.itemId,
                event.stage);
        countersService.recordStageFailure(item.id, item.productLineId, item.stage);
    };

}
