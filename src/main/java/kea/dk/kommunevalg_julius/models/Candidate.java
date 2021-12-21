package kea.dk.kommunevalg_julius.models;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

/**
 * @author Julius Panduro
 */
@Data
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double votes;

    @ManyToOne
    @JoinColumn(name = "parties_id")
    @Nullable
    private Party party;

}
