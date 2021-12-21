package kea.dk.kommunevalg_julius.repositories;

import kea.dk.kommunevalg_julius.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * @author Julius Panduro
 */
@Repository
public interface CandidatesRepository extends JpaRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM candidates WHERE parties_id = ?", nativeQuery = true)
    List<Candidate> findAllByPartyId(Long id);

    @Query(value = "SELECT * FROM candidates WHERE parties_id = ?", nativeQuery = true)
    List<Candidate> setPartyIdToNullByPartyId(Long id);

    @Query(value = "SELECT * FROM candidates WHERE vote_amount > ?", nativeQuery = true)
    List<Candidate> findCandidateAboveCertainVoteAmount(Long id);

    @Query(value = "SELECT * FROM candidates WHERE vote_amount < ?", nativeQuery = true)
    List<Candidate> findCandidateUnderCertainVoteAmount(Long id);
}
