package br.com.aviapp.api.infra.mysql.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime collectionDate;

}
