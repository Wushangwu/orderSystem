package com.example.order.persistence.mapper;

import com.example.order.persistence.DO.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    int save(@Param("order") OrderDO order) throws Exception;
    void update(@Param("orderDo") OrderDO order) throws Exception;
    List<OrderDO> find();
    String getStatus(String id);

}
