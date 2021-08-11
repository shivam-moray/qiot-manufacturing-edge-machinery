/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import org.slf4j.Logger;

import io.qiot.manufacturing.commons.domain.event.BootstrapCompletedEventDTO;
import io.qiot.manufacturing.edge.machinery.service.machinery.MachineryService;
import io.qiot.manufacturing.edge.machinery.service.production.ProductionChainService;
import io.qiot.manufacturing.edge.machinery.util.exception.DataValidationException;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.scheduler.Scheduler;

/**
 * @author andreabattaglia
 *
 */
//@Startup(1)
@ApplicationScoped
public class CoreServiceImpl implements CoreService {

    @Inject
    Logger LOGGER;

    @Inject
    Scheduler scheduler;

    @Inject
    MachineryService machineryService;

    @Inject
    ProductionChainService productionChainService;
    
    @Inject
    ConnectionFactory connectionFactory;
    
    @Inject
    Event<BootstrapCompletedEventDTO> event;

    void onStart(@Observes StartupEvent ev) throws DataValidationException {
        LOGGER.debug("The application is starting...{}");
        // stationData =
        scheduler.pause(); 
        machineryService.checkRegistration();
        event.fire(new BootstrapCompletedEventDTO());
        scheduler.resume(); 
    }

    void ping() {
       scheduler.pause(); 
       scheduler.pause("myIdentity"); 
       if (scheduler.isRunning()) {
          throw new IllegalStateException("This should never happen!");
       }
       scheduler.resume("myIdentity"); 
       scheduler.resume(); 
    }
}
