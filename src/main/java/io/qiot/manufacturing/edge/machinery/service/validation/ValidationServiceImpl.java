package io.qiot.manufacturing.edge.machinery.service.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import io.qiot.manufacturing.edge.machinery.domain.event.chain.AbstractValidationRequestEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.RequestColoringValidationEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.RequestPackagingValidationEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.RequestPrintingValidationEvent;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.RequestWeavingValidationEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.service.machinery.MachineryService;

@ApplicationScoped
public class ValidationServiceImpl implements ValidationService {

    @Inject
    MachineryService machineryService;

    @Inject
    Event<RequestWeavingValidationEvent> weavingValidationEvent;
    @Inject
    Event<RequestColoringValidationEvent> coloringValidationEvent;
    @Inject
    Event<RequestPrintingValidationEvent> printingValidationEvent;
    @Inject
    Event<RequestPackagingValidationEvent> packagingValidationEvent;

    @Override
    public void validateItem(ItemDTO item) {
        switch (item.stage) {
        case WEAVING:
            RequestWeavingValidationEvent wev = new RequestWeavingValidationEvent();
            abstractPopulate(wev, item);
            wev.data = item.sizeMetrics;
            weavingValidationEvent.fire(wev);
            return;
        case COLORING:
            RequestColoringValidationEvent cev = new RequestColoringValidationEvent();
            abstractPopulate(cev, item);
            cev.data = item.colorMetrics;
            coloringValidationEvent.fire(cev);
            return;
        case PRINTING:
            RequestPrintingValidationEvent prev = new RequestPrintingValidationEvent();
            abstractPopulate(prev, item);
            prev.data = item.printingMetrics;
            printingValidationEvent.fire(prev);
            return;
        case PACKAGING:
            RequestPackagingValidationEvent paev = new RequestPackagingValidationEvent();
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
