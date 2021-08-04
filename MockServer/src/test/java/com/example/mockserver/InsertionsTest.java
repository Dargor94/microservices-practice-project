package com.example.mockserver;

import com.example.mockserver.controller.CustomerController;
import com.example.mockserver.entity.Customer;
import com.example.mockserver.entity.IdentificationType;
import com.example.mockserver.error.CustomException;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsertionsTest {

    private static final Customer customer = new Customer();
    @Autowired
    private CustomerController customerController;

    @BeforeAll
    static void init() {

    }

    @Test
    void contextLoads() {
    }

    @Test
    void ifIdentificationIsValid_shouldReturnTrue() {
        assertTrue(customerController.isValidIdentification(customer.getIdentificationNumber(),
                customer.getIdentificationType().getIdentificationTypeId()));
    }

    @Test
    void whenPostCustomer_thenCreated_shouldReturnLong() throws CustomException {
        customer.setForename("Francisco");
        customer.setSurname("Masera");
        customer.setIdentificationNumber(38356L);
        customer.setIdentificationType(new IdentificationType(2));
        var response = customerController.insert(customer).getBody();
        Assumptions.assumeTrue(response != null, () -> "Cuerpo de petición sin contenido.");
        assertTrue(response instanceof Long);

    }
}

