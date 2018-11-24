package com.ywt.os.process.web;

import com.ywt.os.process.entity.Model;
import com.ywt.os.process.entity.SPFModel;
import com.ywt.os.process.service.SPFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-24 22:00
 **/
@RestController
public class SPFController {

    @Autowired
    private SPFService spfService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/spf")
    @SendTo("/topic/spf")
    public int spfSchedule(Model... processList){
        return spfService.execute(processList);
    }

    public void sendSPF(Model model){
        messagingTemplate.convertAndSend("/topic/spf", model);
    }
}
