/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.machinery;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.edge.machinery.domain.MachineryDataDTO;
import io.qiot.manufacturing.edge.machinery.service.registration.RegistrationService;
import io.qiot.manufacturing.edge.machinery.util.exception.DataValidationException;

/**
 * @author abattagl
 *
 */
public class MachineryServiceImpl implements MachineryService {

    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.datafile.path")
    String dataFilePathString;

    @Inject
    ObjectMapper MAPPER;

    @Inject
    RegistrationService registrationService;

    @ConfigProperty(name = "qiot.station.serial")
    String STATION_SERIAL;
    @ConfigProperty(name = "qiot.station.address")
    String STATION_ADDRESS;
    @ConfigProperty(name = "qiot.station.name")
    String STATION_NAME;

    @ConfigProperty(name = "qiot.mqtts.ks.password")
    String ksPassword;
    @ConfigProperty(name = "qiot.mqtts.ts.password")
    String tsPassword;

    private MachineryDataDTO stationData;

    public MachineryDataDTO checkRegistration() throws DataValidationException {
        Path dataFilePath = Paths.get(dataFilePathString);
        if (Files.exists(dataFilePath)) {
            LOGGER.info(
                    "Device is already registered. Loading data from persistent volume...");
            try {
                String datafileContent = Files.readString(dataFilePath);
                stationData = MAPPER.readValue(datafileContent,
                        MachineryDataDTO.class);
            } catch (Exception e) {
                LOGGER.error("An error occurred loading the station data file.",
                        e);
                throw new DataValidationException(e);
            }
            LOGGER.info("Data loaded successfully: {}", stationData);
        } else {
            LOGGER.info(
                    "Device is not registered. Stepping through the registration process...");

            stationData = new MachineryDataDTO();
            stationData.serial = STATION_SERIAL;
            stationData.name = STATION_NAME;
            try {
                String stationId = registrationService.register(
                        stationData.serial, stationData.name, ksPassword);

                LOGGER.info("Received station ID: {}", stationId);
                stationData.id = stationId;
                Files.createFile(dataFilePath);

                String stationDataString = MAPPER
                        .writeValueAsString(stationData);
                Files.writeString(dataFilePath, stationDataString);

                LOGGER.info("Data Created successfully: {}", stationData);
            } catch (Exception e) {
                LOGGER.error(
                        "An error occurred registering the measurement station.",
                        e);
                throw new DataValidationException(e);
            }
        }
        return stationData;
    }

    public String getStationId() {
        return stationData.id;
    }

    public String getStationSerial() {
        return stationData.serial;
    }

    public String getStationName() {
        return stationData.name;
    }

    public String getTrustStorePassword() {
        return tsPassword;
    }

    public String getKeyStorePassword() {
        return ksPassword;
    }

}
