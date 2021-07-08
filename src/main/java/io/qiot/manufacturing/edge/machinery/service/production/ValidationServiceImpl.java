package io.qiot.manufacturing.edge.machinery.service.production;

import javax.enterprise.context.ApplicationScoped;

import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;

@ApplicationScoped
public class ValidationServiceImpl implements ValidationService {

    @Override
    public void validateItem(ItemDTO item) {
        switch (item.stage) {
        case WEAVING:
            break;
        case COLORING:
            break;
        case PRINTING:
            break;
        case PACKAGING:
            break;
        default:
            throw new RuntimeException("Not yet implemented");
        }        
    }

}
