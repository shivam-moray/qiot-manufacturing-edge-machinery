/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.production.chain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

import org.slf4j.Logger;

import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;
import io.qiot.manufacturing.edge.machinery.util.qualifier.chain.PackagingChainQualifier;
import io.quarkus.scheduler.Scheduled;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
@Typed(PackagingChainServiceImpl.class)
@PackagingChainQualifier
public class PackagingChainServiceImpl implements ChainService{

    @Inject
Logger LOGGER;

    @Scheduled(every = "5s")
    @Override
    public void simulate(ItemDTO product)
             {
    }

}
