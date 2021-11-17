package mk.finki.ukim.mk.lab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.impl.InMemoryBalloonRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;

@Service
public class BalloonServiceImpl implements BalloonService {
    private final InMemoryBalloonRepository balloonRepository;

    public BalloonServiceImpl(InMemoryBalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return this.balloonRepository.findAllBalloons();
    };

    @Override
    public Balloon getBalloonById(Long id) {
        Optional<Balloon> balloonById = listAll().stream().filter(p -> p.getId() == id).findFirst();
        if (balloonById.isPresent()) {
            return balloonById.get();
        } else
            // should returne rror
            return listAll().get(0);
    }

    @Override
    public void addBalloon(String name, String description, Manufacturer manufacturer) {
        balloonRepository.addBalloon(name, description, manufacturer);
    }

    @Override
    public void editBalloon(Long id, String name, String description, Manufacturer manufacturer) {
        balloonRepository.editBalloon(id, name, description, manufacturer);
    }

    @Override
    public void deleteBalloon(Long id) {
        balloonRepository.deleteBalloon(id);
    }

}
