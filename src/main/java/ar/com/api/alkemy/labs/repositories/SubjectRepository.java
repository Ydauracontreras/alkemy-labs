package ar.com.api.alkemy.labs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.alkemy.labs.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findByName(String name);

    List<Subject> findByOrderByNameAsc();
}
