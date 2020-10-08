package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.api.alkemy.labs.entities.Student;
import ar.com.api.alkemy.labs.models.requests.StundentRequest;
import ar.com.api.alkemy.labs.models.responses.GenericResponse;
import ar.com.api.alkemy.labs.services.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/students")
    public ResponseEntity<?> registeStudents(@RequestBody StundentRequest studentR) {
        GenericResponse gr = new GenericResponse();
        Student student = studentService.registerStudent(studentR.name, studentR.lastName, studentR.dni);
        if (student != null) {
            gr.isOk = true;
            gr.id = student.getStudentId();
            gr.message = "Student successfully registered";
            return ResponseEntity.ok().body(gr);
        } else {
            gr.isOk = false;
            gr.message = "Student did not register";
            return ResponseEntity.badRequest().body(gr);
        }

    }

    @GetMapping("students")
    public ResponseEntity<?> lisstudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("students/{id}")
    public ResponseEntity<?> findTeacher(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

}
