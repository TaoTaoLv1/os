package com.ywt.os.exception;

import com.ywt.os.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: YwT
 * @description: 全局异常处理
 * @create: 2018-11-24 19:52
 **/
@RestControllerAdvice
@SendTo("/topic/error")
public class RestHandlerExceptionController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageExceptionHandler
    public ResponseMessage IllegalArgument(IllegalArgumentException e){
        return ResponseMessage.newErrorInstance(e.getMessage());
    }

    @MessageExceptionHandler
    public ResponseMessage NullPointer(NullPointerException e){
        return ResponseMessage.newErrorInstance(e.getMessage());
    }

    @MessageExceptionHandler
    public ResponseMessage exception(UnknownException e){
        return ResponseMessage.newErrorInstance(e.getMessage());
    }

}
