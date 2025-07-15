package br.com.aviapp.api.infra.services.webSocket;

import br.com.aviapp.api.application.dto.CollectEggDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CollectEggNotifier {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Async
    public void notifyNewCollect(CollectEggDataDTO dto) {
        messagingTemplate.convertAndSend("/topic/collects", dto);
    }
}
