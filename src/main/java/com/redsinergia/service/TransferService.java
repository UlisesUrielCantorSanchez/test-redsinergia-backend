package com.redsinergia.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.redsinergia.dto.GraficTransferDto;
import com.redsinergia.dto.MyAccountDto;
import com.redsinergia.dto.MyTransfersDto;
import com.redsinergia.dto.TransferDto;
import com.redsinergia.model.Account;
import com.redsinergia.model.Transfer;
import com.redsinergia.repository.AccountRepository;
import com.redsinergia.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {
	
	private final TransferRepository transferRepository;
	private final AccountRepository accountRepository;
	
	@Transactional
	public Transfer saveTransfer(TransferDto transferDto) {
	    Transfer transfer = toTransfer(transferDto);

	    Account origin = getAccountOrThrow(transferDto.getIdAccountOrigin(), "Cuenta de origen no encontrada");
	    Account destination = getAccountOrThrow(transferDto.getIdAccountDestination(), "Cuenta de destino no encontrada");

	    validateSufficientBalance(origin.getBalance(), transfer.getAmount());
	    
	    updateBalances(origin, destination, transfer.getAmount());
	    
	    saveAccounts(origin, destination);
	    
	    return transferRepository.save(transfer);
	}
	
	private Transfer toTransfer(TransferDto dto) {
	    Transfer transfer = new Transfer();
	    transfer.setIdAccountOrigin(dto.getIdAccountOrigin());
	    transfer.setIdAccountDestination(dto.getIdAccountDestination());
	    transfer.setAmount(dto.getAmount());
	    transfer.setDate(LocalDateTime.now());
	    return transfer;
	}

	private Account getAccountOrThrow(Integer id, String message) {
	    return accountRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException(message));
	}

	private void validateSufficientBalance(Float currentBalance, Float amount) {
	    if (currentBalance < amount) {
	        throw new RuntimeException("Saldo insuficiente en cuenta de origen");
	    }
	}

	private void updateBalances(Account origin, Account destination, Float amount) {
	    origin.setBalance(origin.getBalance() - amount);
	    destination.setBalance(destination.getBalance() + amount);
	}

	private void saveAccounts(Account origin, Account destination) {
	    accountRepository.save(origin);
	    accountRepository.save(destination);
	}
	
	public List<MyTransfersDto> getMyTransfers(Integer idUser) {
		return transferRepository.getMyTransfers(idUser);
	}
	
	public List<GraficTransferDto> getGraficTransfers(Integer idUser) {
		return transferRepository.getGraficTransfers(idUser);
	}

}
