package com.redsinergia.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyTransferDto {
	
	private Integer count;
	private String origin;
	private String destination;
	private Float amount;
	private LocalDateTime date;

}
