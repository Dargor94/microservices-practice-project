package com.globant.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.api.entity.Customer;
import com.globant.api.entity.IdentificationType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsertionsTest {

    private static Customer customer;
    @Autowired
    private MockServer mockServer;

    @BeforeAll
    static void init() {
        customer = new Customer();
        customer.setForename("Francisco");
        customer.setSurname("Masera");
        customer.setIdentificationNumber(38395932L);
        customer.setIdentificationType(new IdentificationType(1L));
    }

    @AfterAll
    void tearDown() {
        mockServer.tearDown();
    }

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void initServer() {
        mockServer.expectations();
    }

    @Test
    @Order(4)
    void canInsert_Test() {

        try {
            //   var client = HttpClient.newHttpClient();
     /*       var req = HttpRequest.newBuilder(
                            URI.create("http://localhost:9015/insertCustomer")
                    ).POST(HttpRequest.BodyPublishers.ofString(
                            "{\"forename\":\"Francisco\"," +
                                    " \"surname\":\"Masera\"," +
                                    " \"identification_number\":\"333\"," +
                                    "\"identification_type\":{" +
                                    " \"identification_type_id\":1" +
                                    "}" +
                                    "}")).header("Accept", "application/json")
                    .build();*/

            var values = new Customer(
                    "Francisco",
                    "Masera",
                    333L,
                    new IdentificationType(1L));

            var objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writeValueAsString(values);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:9015/insertCustomer"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            assertNotNull(response.body());
            assertEquals(1L, Long.valueOf(response.body()));
        } catch (Exception e) {
            System.out.println("MSG = " + e.getMessage());
        }

    }

    @Test
    @Order(1)
    void bodyIsNotNull_Test() {
        assertNotNull(customer);
        assertNotNull(customer.getIdentificationType());
    }

    @Test
    @Order(2)
    void ifDniIsValid_Test() {
        if (customer.getIdentificationType().getIdentificationTypeId() != 1)
            return;
        Long dni = customer.getIdentificationNumber();
        assertNotNull(dni);
        assertTrue(dni.toString().length() == 7 || dni.toString().length() == 8);
    }

    @Test
    @Order(3)
    void ifOtherIdentificationIsValid_Test() {
        if (customer.getIdentificationType().getIdentificationTypeId() == 1)
            return;
        Long idNumber = customer.getIdentificationNumber();
        assertNotNull(idNumber);
        assertEquals(5, idNumber.toString().length());
    }

}
