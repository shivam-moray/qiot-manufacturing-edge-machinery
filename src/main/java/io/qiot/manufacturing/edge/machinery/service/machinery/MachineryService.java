package io.qiot.manufacturing.edge.machinery.service.machinery;

import io.qiot.manufacturing.edge.machinery.domain.MachineryDataDTO;
import io.qiot.manufacturing.edge.machinery.util.exception.DataValidationException;

/**
 * @author andreabattaglia
 *
 */
public interface MachineryService {

    MachineryDataDTO checkRegistration() throws DataValidationException;

    String getMachineryId();

    String getMachinerySerial();

    String getMachineryName();

}
