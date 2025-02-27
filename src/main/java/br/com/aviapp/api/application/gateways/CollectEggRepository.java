package br.com.aviapp.api.application.gateways;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;

public interface CollectEggRepository {

    CollectEggDataDTO createCollectEgg(CollectEggDataDTO collectEggDataDTO);
    
}
