package com.example.mockserver.error.validation.customer;

import com.example.mockserver.entity.Customer;
import com.example.mockserver.error.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class CustomerNotNullFields implements ConstraintValidator<IValidCustomer, Customer> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerNotNullFields.class);

    @Override
    public boolean isValid(Customer customer, ConstraintValidatorContext context) {
        try {
            if (customer == null)
                throw new CustomException("Empty request.", "01.03.04");
            if (customer.getSurname() == null || customer.getForename() == null
                    || customer.getIdentificationNumber() == null || customer.getIdentificationType() == null
                    || Arrays.stream(customer.getIdentificationType().getClass().getDeclaredFields()).anyMatch(Objects::isNull))
                throw new CustomException("Invalid data provided. Check and try again.", "01.03.04");
            if (customer.getIdentificationNumber() <= 0)
                throw new CustomException("Identification number is invalid. Check and try again.", "01.03.04");
            if (customer.getForename().isBlank() || customer.getSurname().isBlank())
                throw new CustomException("Name and surname must be filled up. Check and try again.", "01.03.04");
            return true;
        } catch (CustomException ex) {
            logger.error(ex.getMessage(), ex.getCode());
        }
        return false;
    }

}
