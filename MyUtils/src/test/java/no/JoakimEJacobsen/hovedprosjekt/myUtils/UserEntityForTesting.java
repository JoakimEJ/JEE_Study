package no.JoakimEJacobsen.hovedprosjekt.myUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Joakim on 24.01.2017.
 */
@Entity
public class UserEntityForTesting {
    @Id @GeneratedValue
    private Long userId;

    private String name;
    private String surname;

    public void UserEntityForTesting(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "UserEntityForTesting{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
