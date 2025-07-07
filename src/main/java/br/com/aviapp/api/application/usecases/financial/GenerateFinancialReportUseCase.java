package br.com.aviapp.api.application.usecases.financial;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.FinancialDetailsDTO;
import br.com.aviapp.api.application.mappers.*;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.eggValue.GetLastInsertedEggValueUseCase;
import br.com.aviapp.api.domain.entities.*;
import br.com.aviapp.api.domain.factories.FinancialDetailsFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenerateFinancialReportUseCase {

    private final FinancialDetailMapperBO financialDetailMapperBO;
    private final ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final CollectEggMapperBO collectEggMapperBO;
    private final GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase;
    private final EggValueMapperBO eggValueMapperBO;


    public GenerateFinancialReportUseCase(FinancialDetailMapperBO financialDetailMapperBO, ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase,
                                          ListAviariesByBatchUseCase listAviariesByBatchUseCase, CollectEggMapperBO collectEggMapperBO, GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase, EggValueMapperBO eggValueMapperBO) {
        this.financialDetailMapperBO = financialDetailMapperBO;
        this.listEggCollectsByDateAndAviaryUseCase = listEggCollectsByDateAndAviaryUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.collectEggMapperBO = collectEggMapperBO;

        this.getLastInsertedEggValueUseCase = getLastInsertedEggValueUseCase;
        this.eggValueMapperBO = eggValueMapperBO;
    }


    public List<FinancialDetailsDTO> getDailyFinancialReport(LocalDate date, Long batchId) throws Exception {

        Optional<List<AviaryDTO>> aviaries = listAviariesByBatchUseCase.invoke(batchId);

        if (aviaries.isEmpty()){
            throw new Exception("Nenhum aviário encontrado para o lote");
        }

        List<FinancialDetailsVO> financialDetails = new ArrayList<>();

        for (AviaryDTO aviary : aviaries.get()) {
            List<CollectEggBO> eggsCollects = collectEggMapperBO.toBOList(listEggCollectsByDateAndAviaryUseCase.invoke(aviary.id(), date));
            EggValueBO eggValue = eggValueMapperBO.toBO(getLastInsertedEggValueUseCase.invoke());

            financialDetails.add(FinancialDetailsFactory.createFinancialDetails(eggValue, eggsCollects));
        }

        return financialDetailMapperBO.toDTOList(financialDetails);

    }

    ;


}
