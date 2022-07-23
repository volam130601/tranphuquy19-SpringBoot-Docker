package com.example.demo.shared.interfaces;

import java.util.List;

public interface IBaseService<T> {
    T create(T entity);
    T getById(String id);
    List<T> getAll();
    T update(T entity);
    void delete(String id);
}
