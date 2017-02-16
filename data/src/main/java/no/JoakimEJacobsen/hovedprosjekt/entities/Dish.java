package no.JoakimEJacobsen.hovedprosjekt.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Min(2)
    @Max(128)
    private String name;

    // Type of dish (breakfast, lunch, dinner) TODO: change this to use enum later
    @NotNull
    @Min(3)
    @Max(128)
    private String type;

    @NotNull
    @Min(16)
    @Max(280)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (getDishId() != null ? !getDishId().equals(dish.getDishId()) : dish.getDishId() != null) return false;
        if (getName() != null ? !getName().equals(dish.getName()) : dish.getName() != null) return false;
        if (getType() != null ? !getType().equals(dish.getType()) : dish.getType() != null) return false;
        return getDescription() != null ? getDescription().equals(dish.getDescription()) : dish.getDescription() == null;
    }
}
