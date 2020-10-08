package ar.com.api.alkemy.labs.services;

import org.springframework.stereotype.Service;

import ar.com.api.alkemy.labs.entities.Subject;
import ar.com.api.alkemy.labs.repositories.SubjectRepository;
import ar.com.api.alkemy.labs.services.base.GenericService;

@Service
public class SubjectService extends GenericService<Subject> {

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

}
