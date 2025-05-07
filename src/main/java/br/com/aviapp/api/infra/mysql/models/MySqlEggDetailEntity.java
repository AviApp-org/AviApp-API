package br.com.aviapp.api.infra.mysql.models;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "egg_details")
@Data
@NoArgsConstructor

public class MySqlEggDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "egg_collection_id", nullable = false)
    private MySqlCollectEggDataEntity eggCollection;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EggType type;

    @Column(nullable = false)
    private Integer quantity;
}
