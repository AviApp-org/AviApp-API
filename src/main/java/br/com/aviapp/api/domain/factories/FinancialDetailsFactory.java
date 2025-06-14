package br.com.aviapp.api.domain.factories;

import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.entities.EggDetailBO;
import br.com.aviapp.api.domain.entities.EggValueBO;
import br.com.aviapp.api.domain.entities.FinancialDetailsVO;

import java.math.BigDecimal;
import java.util.List;

public class FinancialDetailsFactory extends CollectCalculator {

    public static FinancialDetailsVO createFinancialDetails(EggValueBO eggValueBO, List<CollectEggBO> eggCollects) {

        List<EggDetailBO> eggDetails = calculateEggsByType(eggCollects);

        int hatchableEggs = calculateHatchableEggs(eggDetails);
        int marketEggs = calculateMarketEggs(eggDetails);

        BigDecimal marketEggsValue = BigDecimal.valueOf(marketEggs).multiply(eggValueBO.getValue());
        BigDecimal hatchableEggsValue = BigDecimal.valueOf(hatchableEggs).multiply(eggValueBO.getValue());
        BigDecimal totalValue = marketEggsValue.add(hatchableEggsValue);

        return new FinancialDetailsVO(
                hatchableEggsValue,
                marketEggsValue,
                totalValue
        );
    }
}
