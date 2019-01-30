package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Msg;
import com.example.demo.entity.Order;
import com.example.demo.entity.TMsg;

/**
 * 订单
 * @author chenjl
 *
 */

@Mapper
public interface DynamicMapper {
	void addMsg(Msg msg);
	
	void addOrder(Order order);
	void addtMsg(TMsg tmsg);
}
