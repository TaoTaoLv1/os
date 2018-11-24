package com.ywt.os.process.web;

import com.ywt.os.process.entity.AFPFModel;
import com.ywt.os.process.entity.Model;
import com.ywt.os.process.service.AFPFService;
import com.ywt.os.process.service.FCFSService;
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
    //public int afpfSchedule(Model... processList){
    public int afpfSchedule(String a){

        AFPFModel[] processList = new AFPFModel[6];

        processList[0] = new AFPFModel("A", 4, 0, 1);
        processList[1] = new AFPFModel("B", 4, 1, 2);
        processList[2] = new AFPFModel("C", 4, 3, 3);
        processList[3] = new AFPFModel("D", 1, 5, 4);
        processList[4] = new AFPFModel("E", 2, 14, 3);
        processList[5] = new AFPFModel("F", 3, 15, 4);

        return afpfService.execute(processList);
    }

    public void sendAFPF(Model model){
        messagingTemplate.convertAndSend("/topic/afpf", model);
    }
}
