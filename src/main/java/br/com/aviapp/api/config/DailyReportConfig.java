package br.com.aviapp.api.config;

import br.com.aviapp.api.application.gateways.ILookUp;
import br.com.aviapp.api.application.mappers.*;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.batch.FindBatchByIdUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListChickenCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.report.GenerateAviaryReportUseCase;
import br.com.aviapp.api.application.usecases.report.GenerateDailyReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DailyReportConfig {

    @Bean
    public GenerateDailyReportUseCase generateDailyReportUseCase(GenerateAviaryReportUseCase generateAviaryReportUseCase, ListAviariesByBatchUseCase listAviariesByBatchUseCase, AviaryMapperBO aviaryMapperBO, DailyReportMapperBO dailyReportMapperBO, AviaryReportMapperBO aviaryReportMapperBO, FindBatchByIdUseCase findBatchByIdUseCase) {
        return new GenerateDailyReportUseCase(generateAviaryReportUseCase, listAviariesByBatchUseCase, aviaryMapperBO, dailyReportMapperBO, aviaryReportMapperBO, findBatchByIdUseCase);
    }

    @Bean
    public GenerateAviaryReportUseCase generateAviaryReportUseCase(ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase, ListChickenCollectsByDateAndAviaryUseCase listCollectChickensByDateAndAviaryUseCase, FindAviaryByIdUseCase findAviaryByIdUseCase, CollectEggMapperBO collectEggMapperBO, CollectChickenMapperBO collectChickenMapperBO, AviaryMapperBO aviaryMapperBO, AviaryReportMapperBO aviaryReportMapperBO) {
        return new GenerateAviaryReportUseCase(listEggCollectsByDateAndAviaryUseCase, listCollectChickensByDateAndAviaryUseCase, findAviaryByIdUseCase, collectEggMapperBO, collectChickenMapperBO, aviaryMapperBO, aviaryReportMapperBO);
    }

    @Bean
    public DailyReportMapperBO dailyReportMapperBO(AviaryReportMapperBO aviaryReportMapperBO, EggDetailMapperBO eggDetailMapperBO) {
        return new DailyReportMapperBO(aviaryReportMapperBO, eggDetailMapperBO);
    }

    @Bean
    public AviaryReportMapperBO aviaryReportMapperBO(ILookUp lookUpRepository, EggDetailMapperBO eggDetailMapperBO, CollectEggMapperBO collectEggMapperBO, CollectChickenMapperBO collectChickenMapperBO) {
        return new AviaryReportMapperBO(lookUpRepository, collectEggMapperBO, collectChickenMapperBO, eggDetailMapperBO);
    }

}
