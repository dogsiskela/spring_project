package mk.finki.ukim.mk.lab.service.impl;

import java.util.*;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryManufacturerRepository;
import mk.finki.ukim.mk.lab.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final InMemoryManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(InMemoryManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public ArrayList<Manufacturer> findAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer getManufacturerById(Long id) {
        Optional<Manufacturer> manufacturerById = findAll().stream().filter(p -> p.getId() == id).findFirst();
        if (manufacturerById.isPresent()) {
            return manufacturerById.get();
        } else
            return findAll().get(0);
    }

    @Override
    public void addManufacturer(String name, String country, String address) {
        Long lastId = findAll().get(findAll().size() - 1).getId();
        Long id = Long.valueOf(lastId + 1);
        manufacturerRepository.addManufacturer(id, name, country, address);
    }

    @Override
    public void editManufacturer(Long id, String name, String country, String address) {
        getManufacturerById(id).setName(name);
        getManufacturerById(id).setCountry(country);
        getManufacturerById(id).setAddress(address);
    }

    @Override
    public void removeManufacturer(Long id) {
        manufacturerRepository.deleteManufacturer(id);
    }

}
