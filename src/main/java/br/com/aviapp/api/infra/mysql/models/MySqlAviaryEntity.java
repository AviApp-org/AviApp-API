package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "aviary")
@NoArgsConstructor
@AllArgsConstructor
public class MySqlAviaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer initialAmountOfRoosters;

    private Integer initialAmountOfChickens;

    private Integer currentAmountOfRoosters;

    private Integer currentAmountOfChickens;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private MySqlBatchEntity batchId;


    @OneToMany(mappedBy = "aviary", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MySqlAnomalyEntity> anomalies;


    @PrePersist
    public void prePersist() {
        if (currentAmountOfRoosters == null) {
            currentAmountOfRoosters = initialAmountOfRoosters;
        }
        if (currentAmountOfChickens == null) {
            currentAmountOfChickens = initialAmountOfChickens;
        }
    }
}
