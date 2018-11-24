package com.ywt.os.process.web;

import com.ywt.os.process.entity.Model;
import com.ywt.os.process.service.AFPFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-24 22:46
 **/
@RestController
public class AFPFController {
    @Autowired
    private AFPFService afpfService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/afpf")
    @SendTo("/topic/afpf")
    public int afpfSchedule(Model... processList){
        return afpfService.execute(processList);
    }

    public void sendAFPF(Model model){
        messagingTemplate.convertAndSend("/topic/afpf", model);
    }
}
