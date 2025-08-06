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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        return getFinancialReportByDates(List.of(date), batchId);
    }

    public FinancialDetailsDTO getWeeklyFinancialReport(LocalDate anyDayOfWeek, Long batchId) throws Exception {
        LocalDate startOfWeek = anyDayOfWeek.with(DayOfWeek.MONDAY);
        List<LocalDate> weekDates = IntStream.range(0, 7)
                .mapToObj(startOfWeek::plusDays)
                .toList();
        return getFinancialReportByDates(weekDates, batchId);
    }

    public FinancialDetailsDTO getMonthlyFinancialReport(YearMonth month, Long batchId) throws Exception {
        List<LocalDate> monthDates = IntStream.rangeClosed(1, month.lengthOfMonth())
                .mapToObj(month::atDay)
                .toList();
        return getFinancialReportByDates(monthDates, batchId);
    }

    private FinancialDetailsDTO getFinancialReportByDates(List<LocalDate> dates, Long batchId) throws Exception {

        List<AviaryDTO> aviaryDTOs = listAviariesByBatchUseCase.invoke(batchId);

        if (aviaryDTOs.isEmpty()) {
            throw new RuntimeException("Não existem aviários cadastrados para esse lote");
        }

        List<FinancialDetailsVO> financialDetails = new ArrayList<>();
        BigDecimal farmHatchable = BigDecimal.ZERO;
        BigDecimal farmMarket = BigDecimal.ZERO;
        BigDecimal farmTotal = BigDecimal.ZERO;

        for (AviaryDTO aviary : aviaryDTOs) {
            List<CollectEggBO> allCollects = new ArrayList<>();

            for (LocalDate date : dates) {
                List<CollectEggBO> eggsCollects = collectEggMapperBO.toBOList(
                        listEggCollectsByDateAndAviaryUseCase.invoke(aviary.id(), date)
                );
                allCollects.addAll(eggsCollects);
            }

            EggValueBO eggValue = eggValueMapperBO.toBO(getLastInsertedEggValueUseCase.invoke(batchId));
            FinancialDetailsVO financial = FinancialDetailsFactory.createFinancialDetails(aviary.name(), eggValue, allCollects);
            farmHatchable = farmHatchable.add(financial.getHatchableTotal());
            farmMarket = farmMarket.add(financial.getMarketTotal());
            farmTotal = farmTotal.add(financial.getTotal());
            financialDetails.add(financial);
        }

        return new FinancialDetailsDTO(financialDetails, farmHatchable, farmMarket, farmTotal);
    }



}
