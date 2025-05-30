package br.com.aviapp.api.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class WaterBO {

    private Long id;

    private AviaryBO aviary;

    private float volume;

    private LocalDateTime collectionDate;

    public WaterBO(Long id, AviaryBO aviary, float volume, LocalDateTime collectionDate) {
        this.id = id;
        this.aviary = aviary;
        this.volume = volume;
        this.collectionDate = collectionDate;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
