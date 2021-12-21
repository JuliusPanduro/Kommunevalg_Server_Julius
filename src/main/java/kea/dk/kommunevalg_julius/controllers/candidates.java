package kea.dk.kommunevalg_julius.controllers;

import kea.dk.kommunevalg_julius.exceptions.ResourceNotFoundException;
import kea.dk.kommunevalg_julius.models.Candidate;
import kea.dk.kommunevalg_julius.repositories.CandidatesRepository;
import kea.dk.kommunevalg_julius.repositories.PartiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Julius Panduro
 */
@RestController
public class candidates {

    @Autowired
    CandidatesRepository candidates;

    @Autowired
    PartiesRepository parties;

    //--- List's all the candidates ---\\
    @GetMapping("/candidates")
    List<Candidate> getCandidates() {
        return candidates.findAll();
    }

    //--- Returns a single candidate based on the ID ---\\
    @GetMapping("/candidates/{candidateID}")
    Candidate getCandidateById(@PathVariable Long candidateID) {
        return candidates.findById(candidateID).orElseThrow(() -> new ResourceNotFoundException("Candidate Does Not Exist"));
    }

    //--- Returns a list of candidates based on the party ID ---\\
    @GetMapping("/candidates/parties/{partyID}")
    List<Candidate> getCandidatesByPartyId(@PathVariable Long partyID) {
        return candidates.findAllByPartyId(partyID);
    }

    //--- Add's an Candidate ---\\
    @PostMapping("/candidates")
    String addCandidate(@RequestBody Candidate newCandidate) {
        newCandidate.setId(null);
        candidates.save(newCandidate);
        return "Candidate added";
    }


    //--- Add's an Candidate, and link it to a party ---\\
    @PostMapping("/candidates/parties/{partyID}")
    String addCandidateToParty(@PathVariable Long partyID, @RequestBody Candidate newCandidate) {
        newCandidate.setId(null);
        newCandidate.setParty(parties.findById(partyID).orElseThrow(() -> new ResourceNotFoundException("Party does not exist")));
        candidates.save(newCandidate);
        return "Candidate Added To party";
    }

    //--- Updates the entire candidate based on candidate id ---\\
    @PutMapping("/candidates/{candidateID}")
    String updateCandidateById(@PathVariable Long candidateID, @RequestBody Candidate candidateToUpdateWith) {
        if (candidates.existsById(candidateID)) {
            candidateToUpdateWith.setId(candidateID);
            candidates.save(candidateToUpdateWith);
            return "Candidate is now updated";
        } else {
            return "Candidate id Could not be found";
        }
    }

    //--- Updates the parts of the candidate based on candidate id ---\\
    @PatchMapping("/candidates/{candidatesID}")
    String patchCandidateById(@PathVariable Long candidatesID, @RequestBody Candidate candidateToPatchWith) {
        return candidates.findById(candidatesID).map(foundCandidate -> {
            if (candidateToPatchWith.getName() != null) foundCandidate.setName(candidateToPatchWith.getName());
            if (candidateToPatchWith.getParty() != null) foundCandidate.setParty(candidateToPatchWith.getParty());
            if (candidateToPatchWith.getVotes() != 0) foundCandidate.setVotes(candidateToPatchWith.getVotes());
            candidates.save(foundCandidate);
            return "Candidate Updated";
        }).orElse("Candidate not found");
    }

    //--- Delete the candidate based on candidate id ---\\
    @DeleteMapping("/candidates/{candidateID}")
    void deleteCandidateById(@PathVariable Long candidateID) {
        candidates.deleteById(candidateID);
    }

}
