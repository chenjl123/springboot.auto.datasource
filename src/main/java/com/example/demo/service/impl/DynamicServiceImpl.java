package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.DynamicMapper;
import com.example.demo.entity.Msg;
import com.example.demo.entity.Order;
import com.example.demo.entity.TMsg;
import com.example.demo.service.DynamicService;

@Service
public class DynamicServiceImpl implements DynamicService{
	@Autowired
	private DynamicMapper mapper;
	
	@Override
	//@DS("msgDataSource")
	public void addMsg(Msg msg) {
		// TODO Auto-generated method stub
		mapper.addMsg(msg);
	}

	@Override
	//@DS("orderDataSource")
	@Transactional
	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		mapper.addOrder(order);
		TMsg msg = new TMsg();
		msg.setId("1");
		msg.setName("test");
		mapper.addtMsg(msg);
	}
}
