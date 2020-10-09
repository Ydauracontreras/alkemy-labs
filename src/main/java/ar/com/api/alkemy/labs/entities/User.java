package ar.com.api.alkemy.labs.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String username;
    private String password;
    private Integer typeUserId;
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;
    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the manager
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public enum TypeUserEnum {
        MANAGER(0), STUDENT(1);

        private final Integer value;

        private TypeUserEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TypeUserEnum parse(Integer id) {
            TypeUserEnum status = null; // Default
            for (TypeUserEnum item : TypeUserEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    public Integer getEntityId() {

        switch (this.getTypeUserId()) {
            case STUDENT:
                return this.getStudent().getStudentId();

            case MANAGER:
                return this.getManager().getManagerId();

            default:
                break;
        }
        return null;
    }

    /**
     * @return the tipoUsuarioId
     */
    public TypeUserEnum getTypeUserId() {
        return TypeUserEnum.parse(this.typeUserId);
    }

    /**
     * @param tipoUsuarioId the tipoUsuarioId to set
     */
    public void setTipoUsuarioId(TypeUserEnum typeUserId) {
        this.typeUserId = typeUserId.getValue();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public void setTipoUsuarioId(Integer value) {
    }

}
