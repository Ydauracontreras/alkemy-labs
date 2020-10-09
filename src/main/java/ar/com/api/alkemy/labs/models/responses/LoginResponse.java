package ar.com.api.alkemy.labs.models.responses;

import ar.com.api.alkemy.labs.entities.User.TypeUserEnum;

public class LoginResponse {
    public Integer id;
    public String username;
    public String token;
    public TypeUserEnum userType;
    public Integer entityId; // Si es un Docente, va el Id de Docente, si es estudiante Id Estudiante

}