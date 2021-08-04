package com.example.mockserver.server;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.stereotype.Service;

@Service
public class MockServer {
   /* private final ClientAndServer mockServer;


    public MockServer() {
        mockServer = ClientAndServer.startClientAndServer(8086);
    }

    public void expectations() {

        mockServer
                .when(
                        HttpRequest.request()
                                .withMethod("GET")
                                .withPath("/ms/getMessage")
                )
                .respond(
                        HttpResponse.response()
                                .withBody("Hola Mundo")
                );


    }

    public void tearDown() {
        mockServer.stop();
    }*/


}
