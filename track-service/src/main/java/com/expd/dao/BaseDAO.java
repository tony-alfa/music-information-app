package com.expd.dao;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public interface BaseDAO<T> {

    public boolean update(T updateObject);

    public boolean delete(T deleteObject);

    public T create(T newObject);

    public T get(int id);

    public List<T> getAll();

    public void deleteStore();

    public void createStore();

    default List<T> findByExample(T example) {
        throw new UnsupportedOperationException("Needs Implementing");
    }

    default public List<T> findBy(Predicate<T> pred) {
        List<T> x = getAll();
        return getAll().stream()
                .filter(pred)
                .collect(toList());
    }
}
