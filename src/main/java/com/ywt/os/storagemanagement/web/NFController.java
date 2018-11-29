package com.ywt.os.storagemanagement.web;

import com.ywt.os.message.ResponseMessage;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import com.ywt.os.storagemanagement.service.NFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: YwT
 * @create: 2018-11-29 13:31
 **/
@RestController
public class NFController {

    @Autowired
    private NFService nfService;

    @PostMapping("/nf")
    public ResponseMessage nf(@RequestBody RAMAndProcess ramAndProcess){
        return ResponseMessage.newOkInstance(nfService.allocatePartition(ramAndProcess));
    }
}
