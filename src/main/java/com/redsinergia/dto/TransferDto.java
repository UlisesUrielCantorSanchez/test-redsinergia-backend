package com.redsinergia.dto;

import lombok.Data;

@Data
public class TransferDto {
	
	private Integer idAccountOrigin ;
	private Integer idAccountDestination;
	private Float amount;

}
