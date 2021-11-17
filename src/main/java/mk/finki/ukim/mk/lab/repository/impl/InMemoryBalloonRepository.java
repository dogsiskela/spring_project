package mk.finki.ukim.mk.lab.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;

@Repository
public class InMemoryBalloonRepository {
    List<Balloon> balloons = new ArrayList<Balloon>();

    public List<Balloon> findAllBalloons() {
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text) {
        return balloons.stream().filter(p -> p.getDescription().contains(text) || p.getName().contains(text))
                .collect(Collectors.toList());


    }

    public void addBalloon(String name, String description, Manufacturer manufacturer) {
        Long index;
        if (findAllBalloons().size() == 0)
            index = Long.valueOf(1);
        else {
            index = findAllBalloons().get(findAllBalloons().size() - 1).getId() + 1;
        }
        Balloon balloonInstance = new Balloon(index, name, description, manufacturer);
        balloons.add(balloonInstance);
    }

    public void editBalloon(Long id, String name, String description, Manufacturer manufacturer) {
        Optional<Balloon> balloonInstanceOptional = findAllBalloons().stream().filter(e -> e.getId() == id).findFirst();
        if (!balloonInstanceOptional.isPresent()) {
            return;
        }
        Balloon balloonInstance = balloonInstanceOptional.get();
        balloonInstance.setName(name);
        balloonInstance.setDescription(description);
        balloonInstance.setManufacturer(manufacturer);
    }

    public void deleteBalloon(Long id) {
        balloons.removeIf(p -> p.getId() == id);
    }
}
