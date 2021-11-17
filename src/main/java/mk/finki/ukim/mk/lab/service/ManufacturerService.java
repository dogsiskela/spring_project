package mk.finki.ukim.mk.lab.service;

import java.util.*;

import mk.finki.ukim.mk.lab.model.Manufacturer;

public interface ManufacturerService {
    ArrayList<Manufacturer> findAll();

    Manufacturer getManufacturerById(Long id);

    void addManufacturer(String name, String country, String address);

    void editManufacturer(Long id, String name, String country, String address);

    void removeManufacturer(Long id);

}
