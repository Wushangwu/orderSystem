package com.example.order.web.Exception;

public class RedisLockedException extends RuntimeException {

    public RedisLockedException(String msg){
        super(msg);
    }

}
