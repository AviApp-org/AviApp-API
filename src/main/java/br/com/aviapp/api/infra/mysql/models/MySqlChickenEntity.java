package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chickens")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MySqlChickenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer currentRoosters;

    private Integer currentChickens;



}
