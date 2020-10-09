package ar.com.api.alkemy.labs.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import ar.com.api.alkemy.labs.entities.Manager;
import ar.com.api.alkemy.labs.entities.Student;
import ar.com.api.alkemy.labs.entities.User;
import ar.com.api.alkemy.labs.entities.User.TypeUserEnum;
import ar.com.api.alkemy.labs.repositories.UserRepository;
import ar.com.api.alkemy.labs.security.Crypto;
import ar.com.api.alkemy.labs.services.base.GenericService;

@Service
public class UserService extends GenericService<User> {

    @Autowired
    ManagerService managerService;

    @Autowired
    StudentService studentService;

    public UserRepository repo() {
        return (UserRepository) repository;
    }

    public User addUser(TypeUserEnum userType, String name, String lastName, Integer dni) {
        User user = new User();
        user.setTipoUsuarioId(userType);
        user.setUsername(dni.toString());
        if (!existUser(user.getUsername())) {
            switch (userType) {
                case STUDENT:
                    Student student = new Student();
                    student.setName(name);
                    student.setLastName(lastName);
                    student.setDni(dni);
                    student.setFile("s" + dni + name.charAt(0) + lastName.charAt(0));
                    user.setPassword(Crypto.encrypt(student.getFile(), user.getUsername()));
                    student.setUser(user);
                    studentService.create(student);

                    student.setUser(user);
                    break;
                case MANAGER:
                    Manager manager = new Manager();
                    manager.setName(name);
                    manager.setLastName(lastName);
                    manager.setDni(dni);
                    manager.setFile("M" + dni + name.charAt(0) + lastName.charAt(0));
                    user.setPassword(Crypto.encrypt(manager.getFile(), dni.toString()));
                    manager.setUser(user);
                    managerService.create(manager);
                    break;

                default:

                    break;
            }

            return user;
        } else {
            return null;
        }
    }

    public User findByUsername(String username) {
        return this.repo().findByUsername(username);
    }

    public boolean existUser(String username) {
        return (this.repo().findByUsername(username) != null);
    }

    public User login(String username, String password) {

        User u = findByUsername(username);

        if (u == null || !u.getPassword().equals(Crypto.encrypt(password, u.getUsername()))) {

            throw new BadCredentialsException("Invalid DNI or password");
        }

        return u;
    }

    public User findById(Integer id) {
        User userO = findById(id);
        return userO;
    }

    public Map<String, Object> getUserClaims(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("userType", user.getTypeUserId());

        if (user.getEntityId() != null)
            claims.put("entityId", user.getEntityId());

        return claims;
    }

    public UserDetails getUserAsUserDetail(User user) {
        UserDetails uDetails;

        uDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));

        return uDetails;
    }

    Set<? extends GrantedAuthority> getAuthorities(User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        TypeUserEnum userType = user.getTypeUserId();

        authorities.add(new SimpleGrantedAuthority("CLAIM_userType_" + userType.toString()));

        if (user.getEntityId() != null)
            authorities.add(new SimpleGrantedAuthority("CLAIM_entityId_" + user.getEntityId()));
        return authorities;
    }

}
