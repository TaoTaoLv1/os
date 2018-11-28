package com.ywt.os.storagemanagement.web;

import com.ywt.os.message.ResponseMessage;
import com.ywt.os.storagemanagement.entity.Process;
import com.ywt.os.storagemanagement.param.RAMAndProcess;
import com.ywt.os.storagemanagement.service.FFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: YwT
 * @create: 2018-11-28 18:28
 **/
@RestController
public class FFController {

    @Autowired
    private FFService ffService;

    @PostMapping("/ff")
    public ResponseMessage ff(@RequestBody RAMAndProcess ramAndProcess){
        return ResponseMessage.newOkInstance(ffService.allocatePartition(ramAndProcess));
    }

}
