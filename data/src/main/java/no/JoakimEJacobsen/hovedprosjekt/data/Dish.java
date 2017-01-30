package no.JoakimEJacobsen.hovedprosjekt.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Joakim on 12.01.2017.
 */
@Entity
@Access(AccessType.FIELD)
public class Dish implements Serializable
{
    @Id @GeneratedValue
    private Long dishId;

    private String name;

    // Type of dish (breakfast, lunch, dinner) TODO: change this to use enum later
    private String type;
    private String description;

    // Empty Constructor
    public Dish() {}

    // Full Constructor
    public Dish(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nDish{" +
                "dishId = " + dishId +
                ", name = " + name +
                ", type = " + type +
                ", description = " + description +
                "}" ;
    }
}
