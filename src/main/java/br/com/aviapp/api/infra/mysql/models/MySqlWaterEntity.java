package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "water")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySqlWaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MySqlAviaryEntity aviaryId;

    private float volume;

}
