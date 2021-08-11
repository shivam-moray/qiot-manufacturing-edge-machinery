//package io.qiot.manufacturing.edge.machinery.service.registration;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Base64;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//
//import org.eclipse.microprofile.config.inject.ConfigProperty;
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//import org.slf4j.Logger;
//
//import io.qiot.manufacturing.edge.machinery.service.machinery.MachineryService;
//import io.qiot.manufacturing.edge.machinery.util.exception.RegistrationException;
//
//@ApplicationScoped
//public class RegistrationService {
//    @Inject
//    Logger LOGGER;
//
//    @ConfigProperty(name = "qiot.mqtts.ks.path")
//    String ksPath;
//    @ConfigProperty(name = "qiot.mqtts.ts.path")
//    String tsPath;
//
//    @Inject
//    @RestClient
//    RegistrationServiceClient registrationClient;
//
//    @Inject
//    MachineryService machineryService;
//
//    public String register(String serial, String name, String ksPassword)
//            throws RegistrationException {
//        RegisterRequest registerRequest = null;
//        RegisterResponse registerResponse = null;
//
//        registerRequest = new RegisterRequest();
//        registerRequest.serial = serial;
//        registerRequest.name = name;
//        registerRequest.keyStorePassword = ksPassword;
//        try {
//            LOGGER.debug(
//                    "Attempting registration process with the following data: {}",
//                    registerRequest);
//            registerResponse = registrationClient.register(registerRequest);
//            LOGGER.debug("Registratior process results: {}", registerResponse);
//
//            LOGGER.debug("Acquired stationID: {}", registerResponse.id);
//            String encodedTSString = registerResponse.truststore;
//            String encodedKSString = registerResponse.keystore;
//
//            writeTS(encodedTSString);
//            writeKS(encodedKSString);
//
//            return registerResponse.id;
//        } catch (Exception e) {
//            LOGGER.error(
//                    "An error occurred registering the device to the data hub.",
//                    e);
//            throw new RegistrationException(e);
//        }
//    }
//
//    private void writeKS(String encodedKSString) throws IOException {
//        byte[] content = Base64.getDecoder()
//                .decode(encodedKSString.getBytes(StandardCharsets.UTF_8));
//        writeToFile(content, ksPath);
//    }
//
//    private void writeTS(String encodedTSString) throws IOException {
//        byte[] content = Base64.getDecoder()
//                .decode(encodedTSString.getBytes(StandardCharsets.UTF_8));
//        writeToFile(content, tsPath);
//    }
//
//    private void writeToFile(byte[] content, String destination)
//            throws IOException {
//        Path file = Paths.get(destination);
//        Files.createDirectories(file.getParent());
//        Files.createFile(file);
//        try (OutputStream outputStream = Files.newOutputStream(file);) {
//            outputStream.write(content);
//        }
//    }
//
//}
