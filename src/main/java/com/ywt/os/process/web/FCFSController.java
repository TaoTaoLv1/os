package com.ywt.os.process.web;

import com.ywt.os.message.ResponseMessage;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.param.ResponseData;
import com.ywt.os.process.service.FCFSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-24 20:35
 **/
@RestController
public class FCFSController {

    @Autowired
    private FCFSService fcfsService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/fcfs")
    @SendTo("/topic/fcfs")
    public ResponseMessage FCFSSchedule(Model... processList) throws InterruptedException {
        return ResponseMessage.newOkInstance(fcfsService.execute(processList));
    }

    public void sendFCFS(Model model){
        messagingTemplate.convertAndSend("/topic/fcfs", ResponseMessage.newOkInstance(model));
    }

}
