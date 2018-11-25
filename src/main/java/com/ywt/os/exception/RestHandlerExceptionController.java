package com.ywt.os.exception;

import com.ywt.os.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
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

    @MessageExceptionHandler
    public ResponseMessage interrupted(InterruptedException e){
        return ResponseMessage.newErrorInstance("休眠线程出错");
    }

    @MessageExceptionHandler
    public ResponseMessage methodArgumentNotValid(MethodArgumentNotValidException e){
        return ResponseMessage.newErrorInstance("消息处理程序方法的未处理异常（参数不正确）");
    }

}
