package com.sample.app.controller;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.smallrye.openapi.runtime.OpenApiDocumentService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
@Slf4j
public class CPStatusController {

    @Inject
    OpenApiDocumentService openApiDocumentService;
    private boolean isReady = false;

    void onStart(@Observes StartupEvent ev) {
        log.info("Setting the app ready");
        this.isReady = true;
    }

    @GET
    @Path("/-/healthz")
    public Response healthz() {
        if (isReady) {
            return Response.ok().build();
        } else {
            return Response.status(503).build();
        }
    }

    @GET
    @Path("/-/ready")
    public Response ready() {
        return Response.ok().build();
    }

    @GET
    @Path("/-/check-up")
    public Response checkUp() {
        return Response.ok().build();
    }

    @GET
    @Path("/documentation/json")
    public Response getDocumentation() {
        return Response.ok(openApiDocumentService.getJsonDocument()).build();
    }
}
