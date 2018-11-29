package com.ywt.os.storagemanagement.web;

import com.ywt.os.message.ResponseMessage;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import com.ywt.os.storagemanagement.service.BFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-29 14:21
 **/
@RestController
public class BFController {

    @Autowired
    private BFService bfService;

    @PostMapping("/bf")
    public ResponseMessage bf(@RequestBody RAMAndProcess ramAndProcess){
        return ResponseMessage.newOkInstance(bfService.allocatePartition(ramAndProcess));
    }
}
