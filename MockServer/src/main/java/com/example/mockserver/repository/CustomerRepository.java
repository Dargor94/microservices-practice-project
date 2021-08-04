package com.example.mockserver.repository;

import com.example.mockserver.entity.Customer;
import com.example.mockserver.entity.IdentificationType;
import com.example.mockserver.error.CustomDataAccessException;
import com.example.mockserver.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Customer getOneById(Long customerId) throws CustomException {
        Customer customer = new Customer();
        try {

            if (existsCustomerByID(customerId))
                customer = jdbcTemplate.queryForObject("SELECT * FROM customer WHERE customer_id = ?",
                        (rs, rowNum) ->
                                new Customer(
                                        rs.getLong("customer_id"),
                                        rs.getString("forename"),
                                        rs.getString("surname"),
                                        rs.getLong("identification_number"),
                                        new IdentificationType(rs.getInt("identification_type_id"))
                                ),
                        customerId);

        } catch (CustomDataAccessException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new CustomDataAccessException("There was an error while retrieving data.", "01.02.02");
        } catch (Exception ex) {
            throw new CustomDataAccessException("Unexpected error.", "01.02.01");
        }
        return customer;
    }

    public List<Customer> getAll() throws CustomException {
        try {
            return jdbcTemplate.query("SELECT * FROM customer",
                    (rs, rowNum) ->
                            new Customer(
                                    rs.getLong("customer_id"),
                                    rs.getString("forename"),
                                    rs.getString("surname"),
                                    rs.getLong("identification_number"),
                                    new IdentificationType(rs.getInt("identification_type_id"))
                            ));
        } catch (DataAccessException ex) {
            throw new CustomDataAccessException("There was an error while retrieving data.", "01.02.02");
        } catch (Exception ex) {
            throw new CustomException("Unexpected error.", "01.02.01");
        }
    }


    public Long insert(Customer customer) throws CustomException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            AtomicInteger idx = new AtomicInteger(1);
            jdbcTemplate.update(connection -> {
                try (PreparedStatement ps = connection.
                        prepareStatement(
                                "INSERT INTO customer (forename, surname, identification_number,identification_type_id) " +
                                        "VALUES (?,?,?,?)",
                                Statement.RETURN_GENERATED_KEYS
                        )) {

                    ps.setString(idx.getAndIncrement(), customer.getForename());
                    ps.setString(idx.getAndIncrement(), customer.getSurname());
                    ps.setLong(idx.getAndIncrement(), customer.getIdentificationNumber());
                    ps.setInt(idx.getAndIncrement(), customer.getIdentificationType().getIdentificationTypeId());
                    return ps;
                }
            }, keyHolder);

            return Objects.requireNonNull(keyHolder.getKey()).longValue();
        } catch (DataAccessException ex) {
            throw new CustomDataAccessException("There was an error while inserting data.", "01.02.02");
        } catch (Exception ex) {
            throw new CustomException("Unexpected error.", "01.02.01");
        }
    }


    public boolean existsCustomerByID(long customerId) throws CustomException {
        try {
            List<Long> count = jdbcTemplate.query(
                    "SELECT COUNT(identification_number) as count FROM customer where customer_id = ?",
                    (rs, rowNum) -> rs.getLong("count"),
                    customerId
            );
            return count.size() == 1 && count.get(0) != null;
        } catch (DataAccessException ex) {
            throw new CustomDataAccessException("Requested data not found.", "01.02.03");
        } catch (Exception ex) {
            throw new CustomException("Unexpected error", "01.02.01");
        }
    }

}
