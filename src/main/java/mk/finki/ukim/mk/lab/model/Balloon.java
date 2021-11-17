package mk.finki.ukim.mk.lab.model;

import javax.persistence.*;

@Entity
public class Balloon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String description;

    @ManyToOne(targetEntity = Manufacturer.class)
    private Manufacturer manufacturer;

    public Balloon() {

    }

    public Balloon(Long id, String name, String description, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return this.name + " " + this.description + " ";
    }
}
