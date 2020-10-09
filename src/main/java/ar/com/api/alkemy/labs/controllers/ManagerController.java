package ar.com.api.alkemy.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ar.com.api.alkemy.labs.entities.Manager;
import ar.com.api.alkemy.labs.models.requests.*;
import ar.com.api.alkemy.labs.models.responses.*;
import ar.com.api.alkemy.labs.services.ManagerService;

@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/Managers")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> registerManagers(@RequestBody ManagerRequest managerR) {
        GenericResponse gr = new GenericResponse();
        Manager manager = managerService.registerManagers(managerR.name, managerR.lastName, managerR.dni);
        if (manager != null) {
            gr.isOk = true;
            gr.id = manager.getManagerId();
            gr.message = "Manager successfully registered";
            return ResponseEntity.ok().body(gr);
        } else {
            gr.isOk = false;
            gr.message = "Manager did not register";
            return ResponseEntity.badRequest().body(gr);
        }
    }

    @GetMapping("/Managers")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> listManagers() {
        return ResponseEntity.ok(managerService.findAll());
    }

    @GetMapping("/Managers/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_MANAGER')")
    public ResponseEntity<?> findManager(@PathVariable Integer id) {
        Manager manager = managerService.findById(id);
        if (manager == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(manager);
    }
}
