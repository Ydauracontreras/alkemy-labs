package ar.com.api.alkemy.labs.entities;

import javax.persistence.CascadeType;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "managers")
@Entity
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Integer managerId;
    private String file;

    @JsonIgnore
    @OneToOne(mappedBy = "manager", cascade = CascadeType.ALL)
    private User user;

    /**
     * @return the managerId
     */
    public Integer getManagerId() {
        return managerId;
    }

    /**
     * @param managerId the managerId to set
     */
    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    /**
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}
