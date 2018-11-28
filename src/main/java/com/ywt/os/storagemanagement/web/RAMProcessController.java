package com.ywt.os.storagemanagement.web;

import com.ywt.os.message.ResponseMessage;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import com.ywt.os.storagemanagement.service.RAMProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-28 20:07
 **/
@RestController
public class RAMProcessController {

    @Autowired
    private RAMProcessService RAMProcessService;

    @PostMapping("/init")
    public ResponseMessage init(){
        return ResponseMessage.newOkInstance(RAMProcessService.init());
    }

    @PostMapping("/clear")
    public ResponseMessage recover(@RequestBody RAMAndProcess ramAndProcess){
        return ResponseMessage.newOkInstance(RAMProcessService.recoverPartition(ramAndProcess));
    }

}
