package br.com.aviapp.api.application.usecases.financial;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.FinancialDetailsDTO;
import br.com.aviapp.api.application.mappers.*;
import br.com.aviapp.api.application.usecases.aviary.ListAviariesByBatchUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.eggValue.GetLastInsertedEggValueUseCase;
import br.com.aviapp.api.domain.entities.*;
import br.com.aviapp.api.domain.factories.FinancialDetailsFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateFinancialReportUseCase {

    private final ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase;
    private final ListAviariesByBatchUseCase listAviariesByBatchUseCase;
    private final CollectEggMapperBO collectEggMapperBO;
    private final GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase;
    private final EggValueMapperBO eggValueMapperBO;


    public GenerateFinancialReportUseCase( ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase,
                                          ListAviariesByBatchUseCase listAviariesByBatchUseCase, CollectEggMapperBO collectEggMapperBO, GetLastInsertedEggValueUseCase getLastInsertedEggValueUseCase, EggValueMapperBO eggValueMapperBO) {
        this.listEggCollectsByDateAndAviaryUseCase = listEggCollectsByDateAndAviaryUseCase;
        this.listAviariesByBatchUseCase = listAviariesByBatchUseCase;
        this.collectEggMapperBO = collectEggMapperBO;

        this.getLastInsertedEggValueUseCase = getLastInsertedEggValueUseCase;
        this.eggValueMapperBO = eggValueMapperBO;
    }


    public FinancialDetailsDTO getDailyFinancialReport(LocalDate date, Long batchId) throws Exception {

        List<AviaryDTO> aviaries = listAviariesByBatchUseCase.invoke(batchId);

        if (aviaries.isEmpty()) {
            throw new Exception("Nenhum aviário encontrado para o lote");
        }

        List<FinancialDetailsVO> financialDetails = new ArrayList<>();
        BigDecimal farmHatchable = BigDecimal.ZERO;
        BigDecimal farmMarket = BigDecimal.ZERO;
        BigDecimal farmTotal = BigDecimal.ZERO;

        for (AviaryDTO aviary : aviaries) {
            List<CollectEggBO> eggsCollects = collectEggMapperBO.toBOList(listEggCollectsByDateAndAviaryUseCase.invoke(aviary.id(), date));
            EggValueBO eggValue = eggValueMapperBO.toBO(getLastInsertedEggValueUseCase.invoke(batchId));
            FinancialDetailsVO financial = FinancialDetailsFactory.createFinancialDetails(aviary.name(), eggValue, eggsCollects);
            farmHatchable = farmHatchable.add(financial.getHatchableTotal());
            farmMarket = farmMarket.add(financial.getMarketTotal());
            farmTotal = farmTotal.add(financial.getTotal());
            financialDetails.add(financial);
        }


        return new FinancialDetailsDTO(financialDetails, farmHatchable, farmMarket, farmTotal);

    }

    ;


}
