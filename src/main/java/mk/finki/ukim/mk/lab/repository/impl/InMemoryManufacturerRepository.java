package mk.finki.ukim.mk.lab.repository.impl;

import java.util.*;

import org.springframework.stereotype.Repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;

@Repository
public class InMemoryManufacturerRepository {
    ArrayList<Manufacturer> manufacturers = new ArrayList<Manufacturer>(
            Arrays.asList(new Manufacturer(Long.valueOf(1), "Creators1", "Macedonia", "JaneSandanski"),
                    new Manufacturer(Long.valueOf(2), "Creators2", "Macedonia", "JaneSandanski"),
                    new Manufacturer(Long.valueOf(3), "Creators3", "Macedonia", "JaneSandanski"),
                    new Manufacturer(Long.valueOf(4), "Creators4", "Macedonia", "JaneSandanski"),
                    new Manufacturer(Long.valueOf(5), "Creators5", "Macedonia", "JaneSandanski")));

    public ArrayList<Manufacturer> findAll() {
        return manufacturers;
    }

    public void addManufacturer(Long id, String name, String country, String address) {
        manufacturers.add(new Manufacturer(id, name, country, address));
    }


    public void deleteManufacturer(Long id) {
        manufacturers.removeIf(p -> p.getId() == id);
    }
}
