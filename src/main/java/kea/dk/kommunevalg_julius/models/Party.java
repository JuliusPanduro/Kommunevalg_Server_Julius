package kea.dk.kommunevalg_julius.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Julius Panduro
 */
@Data
@Entity
@Table(name = "parties")
public class Party {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String letter;

    @Column
    private String color;

    @Column
    private double totalVotes;


    @JsonIgnore
    @OneToMany(mappedBy = "party", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Candidate> candidates;


}
/*
    A.Socialdemokratiet Color: #b50f05
    B.Radikale Venstre Color: #7d1f91
    C.Det Konservative Folkeparti Color: #a6cc05
    D.Nye Borgerlige Color: #008c8c
    F.SF - Socialistisk Folkeparti Color: #f578b0
    G.Veganerpartiet Color: #beaa64
    I.Liberal Alliance Color: #24c7d6
    K.Kristendemokraterne Color: #8c826e
    O.Dansk Folkeparti Color: #ffd129
    V. Venstre, Danmarks Liberale Parti Color: #1a406e
    Ø. Enhedslisten - De Rød-Grønne Color: #ff8200
    Å.Alternativet Color: #1a9629
 */