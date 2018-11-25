package com.ywt.os.process.web;

import com.ywt.os.message.ResponseMessage;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.service.RFPFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-24 22:24
 **/
@RestController
public class RFPFController {

    @Autowired
    private RFPFService rfpfService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/rfpf")
    @SendTo("/topic/rfpf")
    public ResponseMessage rfpfSchedule(Model... processList){
        return ResponseMessage.newOkInstance(rfpfService.execute(processList));
    }

    public void sendRFPF(Model model){
        messagingTemplate.convertAndSend("/topic/rfpf", ResponseMessage.newOkInstance(model));
    }
}
