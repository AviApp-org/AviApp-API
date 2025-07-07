package br.com.aviapp.api.config;

import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.application.mappers.EggValueMapperBO;
import br.com.aviapp.api.application.mappers.FinancialDetailMapperBO;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.eggValue.GetLastInsertedEggValueUseCase;
import br.com.aviapp.api.application.usecases.financial.GenerateFinancialReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinancialDetailConfig {

    @Bean
    public GenerateFinancialReportUseCase generateFinancialReportUseCase(FinancialDetailMapperBO mapperBO, ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase, ListAviariesByBatchUseCase listAviariesByBatchUseCase, CollectEggMapperBO eggMapperBO, GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase, EggValueMapperBO eggValueMapperBO) {
        return new GenerateFinancialReportUseCase(mapperBO, listEggCollectsByDateAndAviaryUseCase, listAviariesByBatchUseCase, eggMapperBO, getLastInsertedEggValueUseCase, eggValueMapperBO);
    }

    @Bean
    public FinancialDetailMapperBO financialDetailMapperBO() {
        return new FinancialDetailMapperBO();
    }
}
