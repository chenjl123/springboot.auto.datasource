package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Msg {
	private String id;
	private String title;
	private String content;
	private String msg_type;
}
