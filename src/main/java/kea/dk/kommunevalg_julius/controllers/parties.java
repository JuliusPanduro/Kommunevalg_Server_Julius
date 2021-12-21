package kea.dk.kommunevalg_julius.controllers;

import kea.dk.kommunevalg_julius.exceptions.ResourceNotFoundException;
import kea.dk.kommunevalg_julius.models.Candidate;
import kea.dk.kommunevalg_julius.models.Party;
import kea.dk.kommunevalg_julius.repositories.CandidatesRepository;
import kea.dk.kommunevalg_julius.repositories.PartiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Julius Panduro
 */
@RestController
public class parties {

    @Autowired
    PartiesRepository parties;

    @Autowired
    CandidatesRepository candidates;

    //--- Returns a List of all the parties ---\\
    @GetMapping("/parties")
    List<Party> getParties() {
        return parties.findAll();
    }

    //--- Returns a single party based on the ID ---\\
    @GetMapping("/parties/{partyID}")
    Party getPartyById(@PathVariable Long partyID) {
        return parties.findById(partyID).orElseThrow(() -> new ResourceNotFoundException("Party Does Not Exist"));
    }

    //--- Returns a List of all the candidates with the same partyID ---\\
    @GetMapping("/parties/{partyID}/candidates")
    List<Candidate> getCandidatesByPartyId(@PathVariable Long partyID) {
        return candidates.findAllByPartyId(partyID);
    }


    //--- Add's an Party ---\\
    @PostMapping("/parties")
    String addParty(@RequestBody Party newParty) {
        newParty.setId(null);
        parties.save(newParty);
        return "Party added";
    }


    //--- Updates the entire party based on party id ---\\
    @PutMapping("/parties/{partyID}")
    String updatePartyById(@PathVariable Long partyID, @RequestBody Party partyToUpdateWith) {
        if (parties.existsById(partyID)) {
            partyToUpdateWith.setId(partyID);
            parties.save(partyToUpdateWith);
            return "Party is now updated";
        } else {
            return "Party id Could not be found";
        }
    }


    //--- Updates the parts of the party based on party id ---\\
    @PatchMapping("/parties/{partyID}")
    String patchPartyById(@PathVariable Long partyID, @RequestBody Party partyToPatchWith) {
        return parties.findById(partyID).map(foundParty -> {
            if (partyToPatchWith.getName() != null) foundParty.setName(partyToPatchWith.getName());
            if (partyToPatchWith.getCandidates() != null) foundParty.setCandidates(partyToPatchWith.getCandidates());
            if (partyToPatchWith.getColor() != null) foundParty.setColor(partyToPatchWith.getColor());
            if (partyToPatchWith.getTotalVotes() != 0) foundParty.setTotalVotes(partyToPatchWith.getTotalVotes());
            parties.save(foundParty);
            return "Party Updated";
        }).orElse("Party not found");
    }

    //--- Delete the party and candidates based on party id ---\\
    @DeleteMapping("/parties/{partyID}")
    void deletePartyById(@PathVariable Long partyID) {
        candidates.findAllByPartyId(partyID).forEach(candidate -> candidate.setParty(null));
        parties.deleteById(partyID);
    }
}


