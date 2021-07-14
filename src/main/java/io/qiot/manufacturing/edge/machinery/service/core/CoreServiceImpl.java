/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.event.BootstrapCompletedEvent;
import io.qiot.manufacturing.edge.machinery.service.machinery.MachineryService;
import io.qiot.manufacturing.edge.machinery.service.production.ProductionChainService;
import io.qiot.manufacturing.edge.machinery.util.exception.DataValidationException;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;

/**
 * @author andreabattaglia
 *
 */
@Startup
@ApplicationScoped
public class CoreServiceImpl implements CoreService {

    @Inject
    Logger LOGGER;

    @Inject
    MachineryService machineryService;

    @Inject
    ProductionChainService productionChainService;
    
    @Inject
    Event<BootstrapCompletedEvent> event;

    // private StationDataBean stationData;

    void onStart(@Observes StartupEvent ev) throws DataValidationException {
        LOGGER.info("The application is starting...{}");
        // stationData =
        machineryService.checkRegistration();
        event.fire(new BootstrapCompletedEvent());
    }

//    @Scheduled(every = "5s", delayed = "5s")
//    void startProduction() {
//        productionChainService.produce();
//    }
}
