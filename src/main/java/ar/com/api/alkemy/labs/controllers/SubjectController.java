package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.com.api.alkemy.labs.entities.Subject;
import ar.com.api.alkemy.labs.models.requests.*;
import ar.com.api.alkemy.labs.models.responses.*;
import ar.com.api.alkemy.labs.services.SubjectService;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("/subjects")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
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
        return ResponseEntity.ok(subjectService.listAllSubject());
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<?> findSubject(@PathVariable Integer id) {
        Subject subject = subjectService.findById(id);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/subjects/{id}/teachers")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> addTeacherToSubject(@PathVariable Integer id, @RequestBody AddTeacherRequest teacherR) {
        GenericResponse gr = new GenericResponse();
        Subject subject = subjectService.addTeacher(id, teacherR.teacherId);
        if (subject != null) {
            gr.isOk = true;
            gr.id = subject.getSubjectId();
            gr.message = "Teacher  " + subject.getTeacher().getName() + " " + subject.getTeacher().getLastName()
                    + " successfully registered";
            return ResponseEntity.ok().body(gr);
        } else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/subjects/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> deleteSubject(@PathVariable Integer id) {
        GenericResponse gr = new GenericResponse();
        Subject subject = subjectService.findById(id);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }
        this.subjectService.delete(subject);
        gr.isOk = true;
        gr.id = subject.getSubjectId();
        gr.message = "Subject successfully deleted";
        return ResponseEntity.ok().body(gr);
    }

    @PutMapping("/subjects/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> updateSubjects(@PathVariable Integer id, @RequestBody SubjectRequest subjectR) {
        GenericResponse gr = new GenericResponse();
        Subject subject = subjectService.updateSubjects(subjectService.findById(id), subjectR.name, subjectR.schedule,
                subjectR.maxQuota);
        if (subject != null) {
            gr.isOk = true;
            gr.id = subject.getSubjectId();
            gr.message = "Subject successfully Update";
            return ResponseEntity.ok().body(gr);
        } else {
            gr.isOk = false;
            gr.message = "subject did not Update";
            return ResponseEntity.badRequest().body(gr);
        }
    }

}
