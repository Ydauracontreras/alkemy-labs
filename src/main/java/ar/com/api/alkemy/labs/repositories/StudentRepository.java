package ar.com.api.alkemy.labs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.alkemy.labs.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByDni(Integer dni);

}
