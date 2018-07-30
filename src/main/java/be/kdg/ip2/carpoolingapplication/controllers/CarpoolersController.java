package be.kdg.ip2.carpoolingapplication.controllers;

import be.kdg.ip2.carpoolingapplication.domain.Carpooler;
import be.kdg.ip2.carpoolingapplication.services.CarpoolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carpoolers")
public class CarpoolersController {

    @Autowired
    private CarpoolerService carpoolerService;

    @GetMapping
    public List<Carpooler> findAllCarpoolers() {
        return carpoolerService.findAllCarpoolers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCarpooler(@RequestBody Carpooler carpooler){
        carpoolerService.createCarpooler(carpooler);
    }

    @GetMapping("/{carpooler_id}")
    public Carpooler get(@PathVariable("carpooler_id") long carpoolerId){
        return carpoolerService.getCarpooler(carpoolerId);
    }
}
