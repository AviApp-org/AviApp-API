package br.com.aviapp.api.application.mappers;

import br.com.aviapp.api.application.dto.FinancialDetailsDTO;
import br.com.aviapp.api.domain.entities.FinancialDetailsVO;

import java.util.List;
import java.util.stream.Collectors;

public class FinancialDetailMapperBO {

    public FinancialDetailsVO toVO(FinancialDetailsDTO financialDetailsDTO) {
        return new FinancialDetailsVO(
                financialDetailsDTO.hatchableTotal(),
                financialDetailsDTO.marketTotal(),
                financialDetailsDTO.total()
        );
    }

    public FinancialDetailsDTO toDTO(FinancialDetailsVO financialDetailsVO) {
        return new FinancialDetailsDTO(
                financialDetailsVO.getHatchableTotal(),
                financialDetailsVO.getMarketTotal(),
                financialDetailsVO.getTotal()
        );
    }

    public List<FinancialDetailsDTO> toDTOList(List<FinancialDetailsVO> vos) {
        return vos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
