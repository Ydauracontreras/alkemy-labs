package ar.com.api.alkemy.labs.services;

import org.springframework.stereotype.Service;

import ar.com.api.alkemy.labs.entities.Teacher;
import ar.com.api.alkemy.labs.entities.Teacher.TeacherStatusEnum;
import ar.com.api.alkemy.labs.repositories.TeacherRepository;
import ar.com.api.alkemy.labs.services.base.GenericService;

@Service
public class TeacherService extends GenericService<Teacher> {

    public TeacherRepository repo() {
        return (TeacherRepository) repository;
    }

    public Teacher registerTeachers(String name, String lastName, Integer dni) {
        if (!existTeacher(dni)) {
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setLastName(lastName);
            teacher.setDni(dni);
            teacher.setStatus(TeacherStatusEnum.ACTIVE);
            create(teacher);
            return teacher;
        } else {
            return null;
        }
    }

    public Teacher updateTeachers(Teacher teacher, String name, String lastName, Integer dni) {
        teacher.setName(name);
        teacher.setLastName(lastName);
        teacher.setDni(dni);
        teacher.setStatus(TeacherStatusEnum.ACTIVE);
        update(teacher);
        return teacher;
    }

    public boolean existTeacher(Integer dni) {
        return (this.repo().findByDni(dni) != null);
    }
}
