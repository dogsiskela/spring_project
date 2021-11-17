package mk.finki.ukim.mk.lab.service;

import java.util.List;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;

public interface BalloonService {
    List<Balloon> listAll();

    Balloon getBalloonById(Long id);

    void addBalloon(String name, String description, Manufacturer manufacturer);

    void editBalloon(Long id, String name, String description, Manufacturer manufacturer);

    void deleteBalloon(Long id);
}