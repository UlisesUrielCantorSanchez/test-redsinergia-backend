package com.redsinergia.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface MyTransfersDto {
	
	public Integer getCount();
	public String getOrigin();
	public String getDestination();
	public Float getAmount();
	public LocalDateTime getDate();
	
	default String getDate_format() {
	    return getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

}
