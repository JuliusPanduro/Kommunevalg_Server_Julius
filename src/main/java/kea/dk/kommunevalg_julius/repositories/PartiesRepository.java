package kea.dk.kommunevalg_julius.repositories;


import kea.dk.kommunevalg_julius.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Julius Panduro
 */
@Repository
public interface PartiesRepository extends JpaRepository<Party, Long> {

}
