package ar.com.api.alkemy.labs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.alkemy.labs.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByDni(Integer dni);

}
