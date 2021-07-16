/**
 * 
 */
package io.qiot.manufacturing.edge.machinery.service.machinery;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.qiot.manufacturing.edge.machinery.domain.MachineryDataDTO;
import io.qiot.manufacturing.edge.machinery.util.exception.DataValidationException;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.configuration.ProfileManager;

/**
 * @author andreabattaglia
 *
 */
@ApplicationScoped
public class MachineryServiceImpl implements MachineryService {

    @Inject
    Logger LOGGER;

    @ConfigProperty(name = "qiot.datafile.path")
    String dataFilePathString;

    @Inject
    ObjectMapper MAPPER;

//    @Inject
//    RegistrationService registrationService;

    @ConfigProperty(name = "qiot.machinery.serial")
    String MACHINERY_SERIAL;
    @ConfigProperty(name = "qiot.machinery.name")
    String MACHINERY_NAME;

    @ConfigProperty(name = "qiot.mqtts.ks.password")
    String ksPassword;
    @ConfigProperty(name = "qiot.mqtts.ts.password")
    String tsPassword;

    private MachineryDataDTO machineryData;

    public MachineryDataDTO checkRegistration() throws DataValidationException {
        Path dataFilePath = Paths.get(dataFilePathString);
        if (Files.exists(dataFilePath)) {
            LOGGER.info(
                    "Device is already registered. Loading data from persistent volume...");
            try {
                String datafileContent = Files.readString(dataFilePath);
                machineryData = MAPPER.readValue(datafileContent,
                        MachineryDataDTO.class);
            } catch (Exception e) {
                LOGGER.error(
                        "An error occurred loading the machinery data file.",
                        e);
                throw new DataValidationException(e);
            }
            LOGGER.info("Data loaded successfully: {}", machineryData);
        } else {
            LOGGER.info(
                    "Device is not registered. Stepping through the registration process...");

            machineryData = new MachineryDataDTO();
            machineryData.serial = MACHINERY_SERIAL;
            machineryData.name = MACHINERY_NAME;
            try {
                String machineryId = null;
                if (ProfileManager.getActiveProfile()
                        .equals(LaunchMode.DEVELOPMENT.getDefaultProfile())) {
                    machineryId = UUID.randomUUID().toString();
                }
//                else
//                    machineryId = registrationService.register(
//                            machineryData.serial, machineryData.name,
//                            ksPassword);
//                }

                LOGGER.info("Received machinery ID: {}", machineryId);
                machineryData.id = machineryId;
                Files.createFile(dataFilePath);

                String machineryDataString = MAPPER
                        .writeValueAsString(machineryData);
                Files.writeString(dataFilePath, machineryDataString);

                LOGGER.info("Data Created successfully: {}", machineryData);
            } catch (Exception e) {
                LOGGER.error(
                        "An error occurred registering the measurement machinery.",
                        e);
                throw new DataValidationException(e);
            }
        }
        return machineryData;
    }

    @Override
    public String getMachineryId() {
        return machineryData.id;
    }

    @Override
    public String getMachinerySerial() {
        return machineryData.serial;
    }

    @Override
    public String getMachineryName() {
        return machineryData.name;
    }

    public String getTrustStorePassword() {
        return tsPassword;
    }

    public String getKeyStorePassword() {
        return ksPassword;
    }

}
