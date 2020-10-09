package ar.com.api.alkemy.labs.models.responses;

import java.util.*;

import ar.com.api.alkemy.labs.entities.Teacher;

public class SubjectResponse {
    public Integer id;
    public String name;
    public String schedule;
    public Integer maxQuota;
    public Teacher teacher;
    public Integer freeSpots;
    public List<StudentResponse> students = new ArrayList<>();
}
