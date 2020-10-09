package ar.com.api.alkemy.labs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.api.alkemy.labs.entities.Student;
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

    public boolean existStudent(Integer dni) {
        return (this.repo().findByDni(dni) != null);
    }

    /*
     * public Student enrollStudent(Integer id, Integer subjectId) {
     * 
     * Subject subject = subjectService.findById(subjectId); Enrollment enrollment =
     * new Enrollment(); this.findById(id).getEnrolledSubjects().stream().forEach(s
     * -> { Student s = findById(id); (s.getSubjectId().equals(subjectId) ||
     * subject.getEnrollments().size() >= subject.getMaxQuota()).map(
     * subject.AddStudent(s); enrollment.setStudent(s);
     * enrollment.setSubject(subject); subjectService.update(subject); update(s);
     * return s; )}
     * 
     * ); }
     */

}
