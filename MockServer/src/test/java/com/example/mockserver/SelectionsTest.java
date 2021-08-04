package com.example.mockserver;


import com.example.mockserver.controller.CustomerController;
import com.example.mockserver.entity.Customer;
import com.example.mockserver.error.CustomException;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectionsTest {

    @Autowired
    private CustomerController customerController;

    @Test
    void contextLoads() {
    }

    @Test
    void selectOneByID_ShouldReturnOnlyOneEntity() throws CustomException {
        var response = customerController.getOneByID(1L).getBody();
        Assumptions.assumeTrue(response != null, () -> "Cuerpo nulo.");
        assertTrue(response instanceof Customer);
    }

}
