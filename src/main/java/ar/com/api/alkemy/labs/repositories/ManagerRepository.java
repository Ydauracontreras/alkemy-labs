package ar.com.api.alkemy.labs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.alkemy.labs.entities.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findByDni(Integer dni);

}
