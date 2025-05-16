package br.com.aviapp.api.application.usecases.report;

import br.com.aviapp.api.application.dto.AviaryDTO;
import br.com.aviapp.api.application.dto.AviaryReportDTO;
import br.com.aviapp.api.application.mappers.AviaryMapperBO;
import br.com.aviapp.api.application.mappers.AviaryReportMapperBO;
import br.com.aviapp.api.application.mappers.CollectChickenMapperBO;
import br.com.aviapp.api.application.mappers.CollectEggMapperBO;
import br.com.aviapp.api.application.usecases.aviary.FindAviaryByIdUseCase;
import br.com.aviapp.api.application.usecases.collectChicken.ListChickenCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.application.usecases.collectEgg.ListEggCollectsByDateAndAviaryUseCase;
import br.com.aviapp.api.domain.entities.AviaryBO;
import br.com.aviapp.api.domain.entities.AviaryReportBO;
import br.com.aviapp.api.domain.entities.CollectChickenBO;
import br.com.aviapp.api.domain.entities.CollectEggBO;
import br.com.aviapp.api.domain.factories.AviaryReportFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class GenerateAviaryReportUseCase {

    private final ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase;
    private final ListChickenCollectsByDateAndAviaryUseCase listCollectChickensByDateAndAviaryUseCase;
    private final FindAviaryByIdUseCase findAviaryByIdUseCase;
    private final CollectEggMapperBO collectEggMapperBO;
    private final CollectChickenMapperBO collectChickenMapperBO;
    private final AviaryMapperBO aviaryMapperBO;
    private final AviaryReportMapperBO aviaryReportMapperBO;

    public GenerateAviaryReportUseCase(ListEggCollectsByDateAndAviaryUseCase listEggCollectsByDateAndAviaryUseCase, ListChickenCollectsByDateAndAviaryUseCase listCollectChickensByDateAndAviaryUseCase,
                                       FindAviaryByIdUseCase findAviaryByIdUseCase, CollectEggMapperBO collectEggMapperBO, CollectChickenMapperBO collectChickenMapperBO, AviaryMapperBO aviaryMapperBO, AviaryReportMapperBO aviaryReportMapperBO) {
        this.listEggCollectsByDateAndAviaryUseCase = listEggCollectsByDateAndAviaryUseCase;
        this.listCollectChickensByDateAndAviaryUseCase = listCollectChickensByDateAndAviaryUseCase;
        this.findAviaryByIdUseCase = findAviaryByIdUseCase;
        this.collectEggMapperBO = collectEggMapperBO;
        this.collectChickenMapperBO = collectChickenMapperBO;
        this.aviaryMapperBO = aviaryMapperBO;
        this.aviaryReportMapperBO = aviaryReportMapperBO;
    }

    public AviaryReportDTO invoke(Long aviaryId, LocalDateTime date) {

        List<CollectEggBO> collectEgg = collectEggMapperBO.toBOList(listEggCollectsByDateAndAviaryUseCase.invoke(aviaryId, date));
        List<CollectChickenBO> collectChicken = collectChickenMapperBO.toBOList(listCollectChickensByDateAndAviaryUseCase.invoke(aviaryId, date));
        Optional<AviaryDTO> aviaryDTO = findAviaryByIdUseCase.invoke(aviaryId);
        AviaryBO aviary = aviaryMapperBO.toBO(aviaryDTO.get());

        return aviaryReportMapperBO.toDTO(AviaryReportFactory.createFromAviary(aviary, collectEgg, collectChicken)) ;
    }
}
