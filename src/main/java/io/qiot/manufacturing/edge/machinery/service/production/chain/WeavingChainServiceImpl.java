/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.production.chain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.ProductionChainStageEnum;
import io.qiot.manufacturing.edge.machinery.domain.event.chain.StageCompletedEvent;
import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.util.qualifier.chain.WeavingChainQualifier;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
@Typed(WeavingChainServiceImpl.class)
@WeavingChainQualifier
public class WeavingChainServiceImpl extends AbstractChainService {

    @Inject
    Logger LOGGER;

    @Override
    protected ProductionChainStageEnum getStage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void doSimulate(ItemDTO product) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected Event<StageCompletedEvent> getEvent() {
        // TODO Auto-generated method stub
        return null;
    }

    

    
}
