package com.example.crudexampletennissqlite;

public interface TennisDetailsListener {
    void deleteTennis(long id);
    void updateTennis(TennisModel tennisModel);
}
