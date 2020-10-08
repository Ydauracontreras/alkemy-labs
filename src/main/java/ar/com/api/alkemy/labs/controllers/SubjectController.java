package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.api.alkemy.labs.entities.Subject;
import ar.com.api.alkemy.labs.models.requests.SubjectRequest;
import ar.com.api.alkemy.labs.models.responses.GenericResponse;
import ar.com.api.alkemy.labs.services.SubjectService;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("/subjects")
    public ResponseEntity<?> registerSubjects(@RequestBody SubjectRequest subjectR) {
        GenericResponse gr = new GenericResponse();
        Subject subject = subjectService.registerSubjects(subjectR.name, subjectR.schedule, subjectR.maxQuota);
        if (subject != null) {
            gr.isOk = true;
            gr.id = subject.getSubjectId();
            gr.message = "Subject successfully registered";
            return ResponseEntity.ok().body(gr);
        } else {
            gr.isOk = false;
            gr.message = "subject did not register";
            return ResponseEntity.badRequest().body(gr);
        }
    }

    @GetMapping("/subjects")
    public ResponseEntity<?> listSubjects() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<?> findSubject(@PathVariable Integer id) {
        Subject subject = subjectService.findById(id);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

}
