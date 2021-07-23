package io.qiot.manufacturing.edge.machinery.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import io.qiot.manufacturing.commons.domain.production.ItemDTO;
import io.qiot.manufacturing.commons.domain.productionvalidation.AbstractValidationRequestEvent;
import io.qiot.manufacturing.commons.domain.productionvalidation.ColoringValidationRequestEvent;
import io.qiot.manufacturing.commons.domain.productionvalidation.PackagingValidationRequestEvent;
import io.qiot.manufacturing.commons.domain.productionvalidation.PrintingValidationRequestEvent;
import io.qiot.manufacturing.commons.domain.productionvalidation.WeavingValidationRequestEvent;
import io.qiot.manufacturing.edge.machinery.service.machinery.MachineryService;

@ApplicationScoped
public class ValidationServiceImpl implements ValidationService {

    @Inject
    MachineryService machineryService;

    @Inject
    Event<WeavingValidationRequestEvent> weavingValidationEvent;
    @Inject
    Event<ColoringValidationRequestEvent> coloringValidationEvent;
    @Inject
    Event<PrintingValidationRequestEvent> printingValidationEvent;
    @Inject
    Event<PackagingValidationRequestEvent> packagingValidationEvent;

    @Override
    public void validateItem(ItemDTO item) {
        switch (item.stage) {
        case WEAVING:
            WeavingValidationRequestEvent wev = new WeavingValidationRequestEvent();
            abstractPopulate(wev, item);
            wev.data = item.sizeMetrics;
            weavingValidationEvent.fire(wev);
            return;
        case COLORING:
            ColoringValidationRequestEvent cev = new ColoringValidationRequestEvent();
            abstractPopulate(cev, item);
            cev.data = item.colorMetrics;
            coloringValidationEvent.fire(cev);
            return;
        case PRINTING:
            PrintingValidationRequestEvent prev = new PrintingValidationRequestEvent();
            abstractPopulate(prev, item);
            prev.data = item.printingMetrics;
            printingValidationEvent.fire(prev);
            return;
        case PACKAGING:
            PackagingValidationRequestEvent paev = new PackagingValidationRequestEvent();
            abstractPopulate(paev, item);
            paev.data = item.packagingMetrics;
            packagingValidationEvent.fire(paev);
            return;
        default:
            throw new RuntimeException("Not yet implemented");
        }
    }

    private void abstractPopulate(AbstractValidationRequestEvent ev,
            ItemDTO item) {
        ev.machineryId = machineryService.getMachineryId();
        ev.itemId = item.id;
        ev.productLineId = item.productLineId;
        ev.stage = item.stage;
    }

}
