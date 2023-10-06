package com.example.order.persistence;

import com.example.order.persistence.DO.OrderDO;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

    int save(@Param("order") OrderDO order) throws Exception;
    void update(@Param("order") OrderDO order) throws Exception;

}
