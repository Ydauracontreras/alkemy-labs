package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.com.api.alkemy.labs.entities.Student;
import ar.com.api.alkemy.labs.models.requests.*;
import ar.com.api.alkemy.labs.models.responses.*;
import ar.com.api.alkemy.labs.services.StudentService;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/students")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
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
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> lisstudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("students/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> findTeacher(@PathVariable Integer id) {
        Student student = studentService.findById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    /*
     * @PostMapping("students/{id}/enrollements") public ResponseEntity<?>
     * enrollToSubjec(@PathVariable Integer id, @RequestBody EnrollRequest enrrolR)
     * { GenericResponse gr = new GenericResponse();
     * 
     * Student result = studentService.enrollStudent(id, enrrolR.subjectId); if
     * (result != null) { gr.isOk = true; gr.id =
     * studentService.findById(id).getStudentId(); gr.message =
     * "Student successfully enrolled"; return ResponseEntity.ok().body(gr); } else
     * { gr.isOk = false; gr.message = "Student did not enrolled"; return
     * ResponseEntity.badRequest().body(gr); }
     * 
     * }
     */

}
