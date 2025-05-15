package br.com.aviapp.api.config;

import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.application.mappers.DailyReportMapperBO;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListChickenCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.report.GenerateAviaryReportUseCase;
import br.com.aviapp.api.application.usecases.report.GenerateDailyReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DailyReportConfig {

    @Bean
    public GenerateDailyReportUseCase generateDailyReportUseCase(GenerateAviaryReportUseCase generateAviaryReportUseCase, ListAviariesByBatchUseCase listAviariesByBatchUseCase, AviaryMapperBO aviaryMapperBO) {
        return new GenerateDailyReportUseCase(generateAviaryReportUseCase, listAviariesByBatchUseCase, aviaryMapperBO);
    }

    @Bean
    public GenerateAviaryReportUseCase generateAviaryReportUseCase(ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase, ListChickenCollectsByDateAndAviaryUseCase listCollectChickensByDateAndAviaryUseCase,
                                                                   FindAviaryByIdUseCase findAviaryByIdUseCase, CollectEggMapperBO collectEggMapperBO, CollectChickenMapperBO collectChickenMapperBO, AviaryMapperBO aviaryMapperBO) {
        return new GenerateAviaryReportUseCase(listEggCollectsByDateAndAviaryUseCase, listCollectChickensByDateAndAviaryUseCase, findAviaryByIdUseCase, collectEggMapperBO, collectChickenMapperBO, aviaryMapperBO);
    }

    @Bean
    public DailyReportMapperBO dailyReportMapperBO(){
        return new DailyReportMapperBO();
    }

}
