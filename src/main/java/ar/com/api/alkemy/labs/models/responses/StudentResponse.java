package ar.com.api.alkemy.labs.models.responses;

import java.util.*;

public class StudentResponse {
    public String name;
    public String lastName;
    public Integer dni;
    public List<SubjectResponse> subjects = new ArrayList<>();
}
