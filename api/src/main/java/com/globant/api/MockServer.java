package com.globant.api;

import com.globant.api.dao.ExceptionHandlerDAO;
import com.globant.api.entity.Customer;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Body;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import static com.globant.api.utils.Utils.convertObjectToJson;

@Service
public class MockServer {

    private final ClientAndServer mockServer;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ExceptionHandlerDAO handlerDAO;

    public MockServer() {
        mockServer = ClientAndServer.startClientAndServer(9015);
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
                                .withBody("Hello Mundo")
                );

        mockServer
                .when(
                        HttpRequest.request()
                                .withMethod("GET")
                                .withPath("/ms/get")
                )
                .respond(
                        HttpResponse.response()
                                .withBody("Jejeje")
                );

    /*    mockServer
                .when(
                        HttpRequest.request()
                                .withMethod("GET")
                                .withPath("/ms/hello/getMessage")
                )
                .respond(
                        HttpResponse.response()
                                .withBody("Sample Text")
                );*/
        mockServer.when(
                HttpRequest.request()
                        .withMethod("POST")
                        .withPath("/insertCustomer")
        ).respond((res) -> {
                    try {
                        Body body = HttpRequest.request().getBody();

                        if (body == null) {
                            HttpStatus status = HttpStatus.BAD_REQUEST;
                            handlerDAO = new ExceptionHandlerDAO(
                                    "Empty request", String.valueOf(status.value()), status.getReasonPhrase());
                            return HttpResponse
                                    .response()
                                    .withStatusCode(
                                            status.value()
                                    ).withBody(convertObjectToJson(handlerDAO));
                        } else {

                            Object o = body.getValue();
                            if (o instanceof Customer) {
                                Customer customer = (Customer) o;

                                return HttpResponse.response(String.valueOf(insertCustomer(customer)));
                            }
                            return HttpResponse.response().withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                        }
                    } catch (Exception ex) {
                        handlerDAO.setMessage(ex.getMessage());
                        handlerDAO.setCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
                        handlerDAO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
                        return HttpResponse.response(convertObjectToJson(handlerDAO));
                    }
                }
        );

    }

    public void tearDown() {
        mockServer.stop();
    }

    private long insertCustomer(Customer customer) {
        try {

            jdbcTemplate
                    .update("INSERT INTO customer(forename, surname, " +
                                    "identification_number,identification_type_id) Values(?,?,?,?)",
                            customer.getCustomerId(),
                            customer.getForename(),
                            customer.getSurname(),
                            customer.getIdentificationNumber(),
                            customer.getIdentificationType().getIdentificationTypeId()
                    );
            return 1L;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }


}
