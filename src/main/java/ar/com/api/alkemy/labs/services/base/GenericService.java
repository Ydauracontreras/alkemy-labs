package ar.com.api.alkemy.labs.services.base;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T> implements IService<T> {

    @Autowired
    protected JpaRepository<T, Integer> repository;

    @Override
    public boolean create(T entity) {
        repository.save(entity);
        return true;
    }

    @Override
    public T update(T entity) {
        repository.save(entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Integer id) {
        Optional<T> t = repository.findById(id);
        return (t.isPresent() ? t.get() : null);
    }

    @Override
    public boolean delete(T entity) {
        repository.delete(entity);
        return true;
    }

}
