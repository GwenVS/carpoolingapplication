package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.domain.Carpooler;
import be.kdg.ip2.carpoolingapplication.repositories.CarpoolerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carpoolers")
public class CarpoolersController {

    @Autowired
    private CarpoolerRepository carpoolerRepository;

    @GetMapping
    public List<Carpooler> findAllCarpoolers() {
        return carpoolerRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCarpooler(@RequestBody Carpooler carpooler){
        carpoolerRepository.save(carpooler);
    }

    @GetMapping("/{carpooler_id}")
    public Carpooler get(@PathVariable("carpooler_id") long carpoolerId){
        return carpoolerRepository.getOne(carpoolerId);
    }
}
