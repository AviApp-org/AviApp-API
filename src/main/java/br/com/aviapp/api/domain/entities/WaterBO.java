package br.com.aviapp.api.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WaterBO {

    private Long id;

    private AviaryBO aviary;

    private float volume;


    public WaterBO(Long id, AviaryBO aviary, float volume ) {
        this.id = id;
        this.aviary = aviary;
        this.volume = volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }
}
