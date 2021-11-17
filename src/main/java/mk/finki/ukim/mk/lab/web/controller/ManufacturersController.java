package mk.finki.ukim.mk.lab.web.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.ManufacturerService;

@Controller
public class ManufacturersController {
    ManufacturerService manufacturerService;

    ManufacturersController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("/manufacturers")
    public String getAllManufacturers(Model model) {
        ArrayList<Manufacturer> allManufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", allManufacturers);
        return ("manufacturersList.html");
    }

    @GetMapping("/manufacturers/add")
    public String getManufacturersFormPage(Model model) {
        return ("addManufacturerForm.html");
    }

    @PostMapping("/manufacturers/add")
    public String addNewManufacturer(@RequestParam(required = true) String manufacturerName,
            @RequestParam(required = true) String manufacturerCountry,
            @RequestParam(required = true) String manufacturerAddress, Model model) {
        manufacturerService.addManufacturer(manufacturerName, manufacturerCountry, manufacturerAddress);
        return ("redirect:/manufacturers");
    }

    @GetMapping("/manufacturers/edit/{id}")
    public String editManufacturerFormPage(@PathVariable(required = true) Long id, Model model) {
        Manufacturer manufacturerFromId = manufacturerService.getManufacturerById(id);
        model.addAttribute("manufacturerName", manufacturerFromId.getName());
        model.addAttribute("manufacturerCountry", manufacturerFromId.getCountry());
        model.addAttribute("manufacturerAddress", manufacturerFromId.getAddress());
        return ("addManufacturerForm.html");
    }

    @PostMapping("/manufacturers/edit/{id}")
    public String editManufacturerFormPost(@PathVariable(required = true) Long id,
            @RequestParam(required = true) String manufacturerName,
            @RequestParam(required = true) String manufacturerCountry,
            @RequestParam(required = true) String manufacturerAddress, Model model) {
        manufacturerService.editManufacturer(id, manufacturerName, manufacturerCountry, manufacturerAddress);
        return ("redirect:/manufacturers");
    }

    @PostMapping("/manufacturers/delete/{id}")
    public String deleteManufacturer(@PathVariable(required = true) Long id) {
        manufacturerService.removeManufacturer(id);
        return ("redirect:/manufacturers");
    }
}
