package io.qiot.manufacturing.edge.machinery.service.validation;

import io.qiot.manufacturing.commons.domain.production.ItemDTO;

/**
 * @author andreabattaglia
 *
 */
public interface ValidationService {
    void validateItem(ItemDTO item);
}
