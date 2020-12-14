package com.example.crudexampletennissqlite;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> getAll();
    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
}
