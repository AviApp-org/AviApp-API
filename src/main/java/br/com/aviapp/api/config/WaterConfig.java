package br.com.aviapp.api.config;

import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.gateways.IWater;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.WaterMapperBO;
import br.com.aviapp.api.application.usecases.water.*;
import br.com.aviapp.api.infra.mappers.WaterMapperEntity;
import br.com.aviapp.api.infra.mysql.repository.EntityLookupRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WaterConfig {

    @Bean
    public CreateWaterRecordUseCase createWaterRecordUseCase(IWater waterRepository, WaterMapperBO mapperBO) {
        return new CreateWaterRecordUseCase(waterRepository, mapperBO);
    }

    @Bean
    public DeleteWaterRecordUseCase deleteWaterRecordUseCase(IWater waterRepository) {
        return new DeleteWaterRecordUseCase(waterRepository);
    }

    @Bean
    public GetWaterRecordByDateUseCase getWaterRecordByDateUseCase(IWater waterRepository, WaterMapperBO mapperBO) {
        return new GetWaterRecordByDateUseCase(waterRepository, mapperBO);
    }

    @Bean
    public ListAllWaterRecordsUseCase listAllWaterRecordsUseCase(IWater waterRepository, WaterMapperBO mapperBO) {
        return new ListAllWaterRecordsUseCase(waterRepository, mapperBO);
    }

    @Bean
    public ListWaterRecordsByAviaryUseCase listWaterRecordsByAviaryUseCase(IWater waterRepository, WaterMapperBO mapperBO) {
        return new ListWaterRecordsByAviaryUseCase(waterRepository, mapperBO);
    }

    @Bean
    public ListWaterRecordsByDateAndAviaryUseCase listWaterRecordsByDateAndAviaryUseCase(IWater waterRepository, WaterMapperBO mapperBO) {
        return new ListWaterRecordsByDateAndAviaryUseCase(waterRepository, mapperBO);
    }

    @Bean
    public WaterMapperBO waterMapperBO(AviaryMapperBO aviaryMapperBO, ILookUp repository) {
        return new WaterMapperBO(aviaryMapperBO, repository);
    }

    @Bean
    public WaterMapperEntity waterMapperEntity(EntityLookupRepository entityLookupRepository) {
        return new WaterMapperEntity(entityLookupRepository);
    }
}
