package com.example.demo.service;

import com.example.demo.config.TargetDataSource;
import com.example.demo.entity.Msg;
import com.example.demo.entity.Order;

public interface DynamicService {
	
	@TargetDataSource("msgDataSource")
	void addMsg(Msg msg);
	
	@TargetDataSource("orderDataSource")
	void addOrder(Order order);
}
