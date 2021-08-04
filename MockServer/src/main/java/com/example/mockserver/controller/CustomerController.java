package com.example.mockserver.controller;

import com.example.mockserver.entity.Customer;
import com.example.mockserver.error.CustomException;
import com.example.mockserver.error.validation.customer.IValidCustomer;
import com.example.mockserver.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController("/customer")
@Validated
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/getOne/{customerId}")
    public ResponseEntity<?> getOneByID(
            @NotNull(message = "Could not validate request due to missing information.")
            @PathVariable Long customerId) throws CustomException {
        Customer customer = customerService.getOneByID(customerId);
        return ResponseEntity.ok(Objects.requireNonNullElse(customer, "No results found for this request."));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() throws CustomException {
        List<Customer> customers = customerService.getAll();
        if (customers.isEmpty())
            return ResponseEntity.ok("No results found for this request.");
        return ResponseEntity.ok().body(customers);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@IValidCustomer @RequestBody Customer customer) throws CustomException {

        if (!isValidIdentification(customer.getIdentificationNumber(), customer.getIdentificationType().getIdentificationTypeId())) {
            throw new CustomException("Invalid data provided.", "00");
        }

        Long customerId = customerService.insert(customer);
        return ResponseEntity.created(
                URI.create(
                        String.format("/customer/%s", customer.getIdentificationNumber()))
        ).body(customerId);

    }

    public boolean isValidIdentification(Long idNumber, Integer typeId) {
        if (idNumber == null || typeId == null) return false;

        String idNumberStr = String.valueOf(idNumber);

        if (typeId < 1 || typeId > 5) return false;
        if (typeId == 1 && (idNumberStr.length() != 8 && idNumberStr.length() != 7)) return false;
        return typeId == 1 || idNumberStr.length() == 5;
    }

}
