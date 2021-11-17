package mk.finki.ukim.mk.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;

@Controller
public class BalloonController {

    BalloonService balloonService;
    ManufacturerService manufacturerService;

    BalloonController(ManufacturerService manufacturerService, BalloonService balloonService) {
        this.manufacturerService = manufacturerService;
        this.balloonService = balloonService;
    }

    @GetMapping("/balloons")
    public String getBalloonsPage(@RequestParam(required = false) String error, Model model) {
        List<Balloon> balloons = balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return ("listBalloons.html");
    }

    @GetMapping("/balloons/add-form")
    public String getBalloonsAddFormPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return ("addBalloonForm.html");
    }

    @PostMapping("/balloons/add-form")
    public String addBaloonPost(@RequestParam(required = true) String balloonName,
            @RequestParam(required = true) String balloonDescription,
            @RequestParam(required = true) Long manufacturerId) {
        Manufacturer manufacturerById = manufacturerService.getManufacturerById(manufacturerId);
        balloonService.addBalloon(balloonName, balloonDescription, manufacturerById);
        return "redirect:/";
    }

    @GetMapping("balloons/edit/{id}")
    public String editBalloonForm(@PathVariable(required = true) Long id, Model model) {
        Balloon balloonFromId = balloonService.getBalloonById(id);
        model.addAttribute("balloonName", balloonFromId.getName());
        model.addAttribute("balloonDescription", balloonFromId.getDescription());
        model.addAttribute("manufacturers", manufacturerService.findAll());
        return ("addBalloonForm.html");
    }

    @PostMapping("balloons/edit/{id}")
    public String editBalloonForm(@PathVariable(required = true) Long id,
            @RequestParam(required = true) String balloonName, @RequestParam(required = true) String balloonDescription,
            @RequestParam(required = true) Long manufacturerId) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(manufacturerId);
        balloonService.editBalloon(id, balloonName, balloonDescription, manufacturer);
        return ("redirect:/");
    }

    @PostMapping("balloons/delete/{id}")
    public String deleteBalloon(@PathVariable(required = true) Long id) {
        balloonService.deleteBalloon(id);
        return "redirect:/";
    }

}
