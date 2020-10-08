package ar.com.api.alkemy.labs.services.base;

import java.util.List;

public interface IService<T> {
    boolean create(T entity);

    T update(T entity);

    List<T> findAll();

    T findById(Integer id);

    boolean delete(T entity);

}
