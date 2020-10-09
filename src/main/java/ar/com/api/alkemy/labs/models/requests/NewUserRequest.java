package ar.com.api.alkemy.labs.models.requests;

import ar.com.api.alkemy.labs.entities.User.TypeUserEnum;

public class NewUserRequest {
    public TypeUserEnum userType;
    public String name;
    public String lastName;
    public Integer dni;

}
