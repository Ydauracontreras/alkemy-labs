package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.api.alkemy.labs.entities.Teacher;
import ar.com.api.alkemy.labs.models.requests.TeacherRequest;
import ar.com.api.alkemy.labs.models.responses.GenericResponse;
import ar.com.api.alkemy.labs.services.TeacherService;

@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping("/teachers")
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
    public ResponseEntity<?> listTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<?> findTeacher(@PathVariable Integer id) {
        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacher);
    }

}
