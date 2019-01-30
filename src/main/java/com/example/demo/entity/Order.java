package com.example.demo.entity;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
	private String id;
	private String order_no;
	private String order_name;
	private BigDecimal order_money;
	private Date create_time;
}
