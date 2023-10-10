package com.example.order.web.Response;

import com.example.order.entity.Order;
import com.example.order.exception.DisCorrectInputException;
import com.example.order.exception.OrderException;
import com.example.order.web.Exception.RedisLockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

@RestControllerAdvice()
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * 统一包装
     *
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        /**
         * String、ActionResult不需要再包一层（不想包一层ActionResult对象的可以在这里把这个对象过滤掉）
         */
        if (o instanceof String || o instanceof ResponseModel || o instanceof ErrorResponseModel || o instanceof Order || o instanceof List) {
            return o;
        }
        return ResponseModel.defaultOk(o);
    }


    /**
     * validate failed
     *
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = DisCorrectInputException.class)
    public Object exceptionHandler(DisCorrectInputException e) {
        log.error("validate failed", e.getMessage());
        return new ErrorResponseModel(e.getMessage());
    }


    /**
     * validate failed
     *
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NumberFormatException.class)
    public Object exceptionHandler(NumberFormatException e) {
        log.error(e.toString());
        return new ErrorResponseModel("input is not the integer");
    }

    /**
     * validate failed
     *
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = OrderException.class)
    public Object exceptionHandler(OrderException e) {
        log.error(e.toString());
        return new ErrorResponseModel("something wrong with orders");
    }

    /**
     * validate failed
     *
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object exceptionHandler(HttpMessageNotReadableException e) {
        log.error(e.toString());
        return new ErrorResponseModel("input is not correct");
    }

    /**
     * validate failed
     *
     * @param
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RedisLockedException.class)
    public Object exceptionHandler(RedisLockedException e) {
        log.error(e.toString());
        return new ErrorResponseModel("It is locked");
    }
}