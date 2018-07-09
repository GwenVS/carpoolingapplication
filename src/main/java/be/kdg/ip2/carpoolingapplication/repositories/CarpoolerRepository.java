package be.kdg.ip2.carpoolingapplication.repositories;

import be.kdg.ip2.carpoolingapplication.domain.Carpooler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolerRepository extends JpaRepository<Carpooler, Long> {
}
