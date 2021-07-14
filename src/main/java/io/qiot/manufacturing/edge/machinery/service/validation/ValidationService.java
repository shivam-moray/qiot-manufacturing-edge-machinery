package io.qiot.manufacturing.edge.machinery.service.validation;

import io.qiot.manufacturing.edge.machinery.domain.production.ItemDTO;

public interface ValidationService {
    void validateItem(ItemDTO item);
}
