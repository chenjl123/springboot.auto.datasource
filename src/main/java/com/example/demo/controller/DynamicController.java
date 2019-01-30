package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Msg;
import com.example.demo.entity.Order;
import com.example.demo.service.DynamicService;

/**
 * 静态多数据源操作实例
 * @author Administrator
 *
 */
@RestController
public class DynamicController {
	@Autowired
	private DynamicService service;
	
	@RequestMapping("/dynamic/add")
    public String add() {
		Msg msg = new Msg();
		msg.setId("11");
		msg.setTitle("测试");
		msg.setMsg_type("1");
		msg.setContent("测试测啛啛喳喳");
		service.addMsg(msg);
		
		Order order = new Order();
		order.setCreate_time(new Date());
		order.setId("22");
		order.setOrder_money(new BigDecimal(12.25));
		order.setOrder_name("购买VIP");
		order.setOrder_no("1234567890");
		service.addOrder(order);
		return "success";
    }
}
