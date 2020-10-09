package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import ar.com.api.alkemy.labs.entities.Teacher;
import ar.com.api.alkemy.labs.entities.Teacher.TeacherStatusEnum;
import ar.com.api.alkemy.labs.models.requests.*;
import ar.com.api.alkemy.labs.models.responses.*;
import ar.com.api.alkemy.labs.services.TeacherService;

@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/teachers")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> registerTeachers(@RequestBody TeacherRequest teacherR) {
        GenericResponse gr = new GenericResponse();
        Teacher teacher = teacherService.registerTeachers(teacherR.name, teacherR.lastName, teacherR.dni);
        if (teacher != null) {
            gr.isOk = true;
            gr.id = teacher.getTeacherId();
            gr.message = "Teacher successfully registered";
            return ResponseEntity.ok().body(gr);
        } else {
            gr.isOk = false;
            gr.message = "Teacher did not register";
            return ResponseEntity.badRequest().body(gr);
        }

    }

    @GetMapping("/teachers")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> listTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> findTeacher(@PathVariable Integer id) {
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacher);
    }

    @DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> deleteTeacher(@PathVariable Integer id) {
        GenericResponse gr = new GenericResponse();
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            return ResponseEntity.notFound().build();
        }
        teacher.setStatus(TeacherStatusEnum.INACTIVE);
        this.teacherService.update(teacher);
        gr.isOk = true;
        gr.id = teacher.getTeacherId();
        gr.message = "Teacher " + teacher.getName() + " " + teacher.getLastName() + "successfully deleted";
        return ResponseEntity.ok().body(gr);
    }

}
