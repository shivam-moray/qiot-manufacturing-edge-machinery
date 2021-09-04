package io.qiot.manufacturing.edge.machinery.service.registration;

import java.util.UUID;

import io.qiot.manufacturing.all.commons.exception.SubscriptionException;

public interface RegistrationService {

    UUID register(String serial, String name, String ksPassword)
            throws SubscriptionException;

    void resetFactoryData();

}