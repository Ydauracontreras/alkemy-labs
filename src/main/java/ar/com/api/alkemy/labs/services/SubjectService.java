package ar.com.api.alkemy.labs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.alkemy.labs.entities.Subject;
import ar.com.api.alkemy.labs.entities.Teacher;
import ar.com.api.alkemy.labs.repositories.SubjectRepository;
import ar.com.api.alkemy.labs.services.base.GenericService;

@Service
public class SubjectService extends GenericService<Subject> {

    @Autowired
    TeacherService teacherService;

    public SubjectRepository repo() {
        return (SubjectRepository) repository;
    }

    public Subject registerSubjects(String name, String schedule, Integer maxQuota) {
        if (!existSubject(name)) {
            Subject subject = new Subject();
            subject.setName(name);
            subject.setSchedule(schedule);
            subject.setMaxQuota(maxQuota);
            create(subject);
            return subject;
        } else {
            return null;
        }
    }

    public boolean existSubject(String name) {
        return (this.repo().findByName(name) != null);
    }

    public Integer isFull(Subject subject) {
        if (subject.getEnrollments().size() >= subject.getMaxQuota())
            return 0;
        return subject.getMaxQuota() - subject.getEnrollments().size();
    }

    public Subject addTeacher(Integer subjectId, Integer teacherId) {
        Subject subject = this.findById(subjectId);
        Teacher teacher = teacherService.findById(teacherId);
        if (subject.getTeacher() == null) {
            subject.setTeacher(teacher);
            this.update(subject);
            return subject;
        }
        return null;
    }

}
