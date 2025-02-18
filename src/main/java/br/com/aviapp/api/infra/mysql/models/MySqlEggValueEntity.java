package br.com.aviapp.api.infra.mysql.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import br.com.aviapp.api.infra.mysql.enums.EggType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
@Table(name = "egg_value")
public class MySqlEggValueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EggType egg;

    @CreatedDate
    @Column(name = "timestamp", updatable = false)
    private LocalDateTime timestamp;

    private BigDecimal value;
}
