package com.example.mockserver.service;

import com.example.mockserver.error.CustomException;

import java.util.List;

public interface IService<T> {

    T getOneByID(Long id) throws CustomException;

    List<T> getAll() throws CustomException;

    Long insert(T entity) throws CustomException;

}
