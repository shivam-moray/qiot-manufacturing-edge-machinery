package io.qiot.manufacturing.edge.machinery.service.production;

import io.qiot.manufacturing.edge.machinery.domain.ItemDTO;

public interface ValidationService {
    void validateItem(ItemDTO item);
}
