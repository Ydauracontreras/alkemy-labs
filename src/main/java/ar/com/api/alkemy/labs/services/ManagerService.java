package ar.com.api.alkemy.labs.services;

import org.springframework.stereotype.Service;

import ar.com.api.alkemy.labs.entities.Manager;
import ar.com.api.alkemy.labs.repositories.ManagerRepository;
import ar.com.api.alkemy.labs.services.base.GenericService;

@Service
public class ManagerService extends GenericService<Manager> {

    public ManagerRepository repo() {
        return (ManagerRepository) repository;
    }

    public Manager registerManagers(String name, String lastName, Integer dni) {
        if (!existManager(dni)) {
            Manager manager = new Manager();
            manager.setName(name);
            manager.setLastName(lastName);
            manager.setDni(dni);
            manager.setFile("M" + dni + name.charAt(0) + lastName.charAt(0));
            create(manager);
            return manager;
        } else {
            return null;
        }
    }

    public boolean existManager(Integer dni) {
        return (this.repo().findByDni(dni) != null);
    }
}
