package be.kdg.ip2.carpoolingapplication.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Primary
public class RideService {

    @Autowired

    public RideService() {
    }
}
