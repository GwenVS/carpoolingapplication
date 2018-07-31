package be.kdg.ip2.carpoolingapplication.services;

import be.kdg.ip2.carpoolingapplication.domain.Carpooler;
import be.kdg.ip2.carpoolingapplication.repositories.CarpoolerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Primary
public class CarpoolerService {

    @Autowired
    private CarpoolerRepository carpoolerRepository;

    public CarpoolerService(CarpoolerRepository carpoolerRepository) {
        this.carpoolerRepository = carpoolerRepository;
    }

    public List<Carpooler> findAllCarpoolers() {
        List<Carpooler> carpoolers = carpoolerRepository.findAll();
        if (carpoolers == null) {
            return new ArrayList<>();
        } else {
            return carpoolers;
        }
    }

    public Carpooler createCarpooler(Carpooler carpooler) {
        Carpooler c = carpoolerRepository.save(carpooler);
        if (c == null) {
            throw new CarpoolerServiceException("Carpooler not saved");
        }
        return c;
    }

    public Carpooler getCarpooler(long carpoolerId) {
        Carpooler carpooler = carpoolerRepository.getOne(carpoolerId);
        if (carpooler == null) {
            throw new CarpoolerServiceException("Carpooler not found");
        }
        return carpooler;
    }
}
