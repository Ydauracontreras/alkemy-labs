package ar.com.api.alkemy.labs.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.alkemy.labs.entities.Enrollment;
import ar.com.api.alkemy.labs.entities.Student;
import ar.com.api.alkemy.labs.entities.Subject;
import ar.com.api.alkemy.labs.repositories.StudentRepository;
import ar.com.api.alkemy.labs.services.base.GenericService;

@Service
public class StudentService extends GenericService<Student> {

    @Autowired
    SubjectService subjectService;

    public StudentRepository repo() {
        return (StudentRepository) repository;
    }

    public Student registerStudent(String name, String lastName, Integer dni) {
        if (!existStudent(dni)) {
            Student student = new Student();
            student.setName(name);
            student.setLastName(lastName);
            student.setDni(dni);
            student.setFile("s" + dni + name.charAt(0) + lastName.charAt(0));
            create(student);
            return student;
        } else {
            return null;
        }
    }

    public Student updateStudent(Student student, String name, String lastName, Integer dni) {
        student.setName(name);
        student.setLastName(lastName);
        student.setDni(dni);
        student.setFile("s" + dni + name.charAt(0) + lastName.charAt(0));
        update(student);
        return student;

    }

    public boolean existStudent(Integer dni) {
        return (this.repo().findByDni(dni) != null);
    }

    public boolean findSubject(Student student, Subject subject) {
        Optional<Subject> o = student.getEnrolledSubjects().stream()
                .filter(sb -> sb.getName().equals(subject.getName())).findAny();
        return (o.isPresent() ? true : false);
    }

    public boolean findTimeFree(Student student, Subject subject) {
        Optional<Subject> o = student.getEnrolledSubjects().stream()
                .filter(sb -> sb.getSchedule().equals(subject.getSchedule())).findAny();
        return (!o.isPresent() ? true : false);
    }

    public Student enrollStudent(Integer id, Integer subjectId) {
        Student student = this.findById(id);
        Subject subject = subjectService.findById(subjectId);
        Enrollment enrollment = new Enrollment();
        if (this.findTimeFree(student, subject) && subjectService.isFull(subject) > 0) {
            subject.AddStudent(student);

            enrollment.setStudent(student);
            enrollment.setSubject(subject);

            subject.AddEnrollment(enrollment);

            update(student);
            return student;
        }

        return null;

    }

}
