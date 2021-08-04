package com.example.mockserver.service.impl;

import com.example.mockserver.entity.Customer;
import com.example.mockserver.error.CustomException;
import com.example.mockserver.repository.CustomerRepository;
import com.example.mockserver.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements IService<Customer> {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    CustomerRepository repository;

    @Override
    public Customer getOneByID(Long customerId) throws CustomException {
        return repository.getOneById(customerId);
    }

    @Override
    public List<Customer> getAll() throws CustomException {
        return repository.getAll();
    }

    @Override
    public Long insert(Customer customer) throws CustomException {
        return repository.insert(customer);
    }

}
