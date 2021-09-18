package io.qiot.manufacturing.edge.machinery.service.registration;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.qiot.manufacturing.all.commons.domain.landscape.SubscriptionResponse;
import io.qiot.manufacturing.factory.commons.domain.registration.EdgeSubscriptionRequest;

/**
 * @author andreabattaglia
 *
 */
@Path("/v1/machinery")
@RegisterRestClient(configKey = "facility-manager-api")
public interface FacilityManagerClient {

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SubscriptionResponse subscribeMachinery(
            EdgeSubscriptionRequest request);

}