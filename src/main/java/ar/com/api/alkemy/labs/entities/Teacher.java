package ar.com.api.alkemy.labs.entities;

import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer teacherId;
    @Column(name = "status_id")
    private Integer status;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Subject> subjectsTeach = new ArrayList<>();

    public enum TeacherStatusEnum {
        INACTIVE(0), ACTIVE(1);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TeacherStatusEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TeacherStatusEnum parse(Integer id) {
            TeacherStatusEnum status = null; // Default
            for (TeacherStatusEnum item : TeacherStatusEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    /**
     * @return the teacherId
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(TeacherStatusEnum status) {
        this.status = status.getValue();
    }

    public TeacherStatusEnum getStatus() {
        return TeacherStatusEnum.parse(this.status);
    }

    /**
     * @return the subjectsTeach
     */
    public List<Subject> getSubjectsTeach() {
        return subjectsTeach;
    }

    /**
     * @param subjectsTeach the subjectsTeach to set
     */
    public void setSubjectsTeach(List<Subject> subjectsTeach) {
        this.subjectsTeach = subjectsTeach;
    }

}
