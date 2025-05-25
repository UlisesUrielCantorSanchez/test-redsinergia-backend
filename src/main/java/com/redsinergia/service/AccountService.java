package com.redsinergia.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.redsinergia.dto.AccountDto;
import com.redsinergia.dto.MyAccountDto;
import com.redsinergia.model.Account;
import com.redsinergia.model.Contact;
import com.redsinergia.repository.AccountRepository;
import com.redsinergia.repository.ContactRepository;
import com.redsinergia.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	private final AccountRepository accountRepository;
	private final ContactRepository contactRepository;
	private final TransferRepository transferRepository;
	
	@Transactional
	public Account saveAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setIdUser(accountDto.getIdUser());
		account.setNumberAccount(accountDto.getNumberAccount());
		account.setBalance(accountDto.getBalance());
		account.setErased(false);
		validateNumerAccount(accountDto.getNumberAccount());
		return accountRepository.save(account);
	}
	
	private void validateNumerAccount(String NumerAccount) {
	    if (accountRepository.existsByNumberAccount(NumerAccount)) {
	        throw new RuntimeException("El numero de cuenta ya existe.");
	    }
	}
	
	@Transactional
	public void saveAccountContact(Integer idUser, String numberAccount) {
	    Account account = new Account();
	    account.setIdUser(null);
	    account.setNumberAccount(numberAccount);
	    account.setBalance(0f);
	    account.setErased(false);
	    Account savedAccount = accountRepository.save(account);

	    Contact contact = new Contact();
	    contact.setIdUser(idUser);
	    contact.setIdAccountDestination(savedAccount.getId());
	    contactRepository.save(contact);
	}
	
	public List<MyAccountDto> getMyAccount(Integer idUser) {
		return transferRepository.getAccountByIdUser(idUser);
	}
	
	public Float getBalance(Integer idAcount) {
		return transferRepository.getBalance(idAcount);
	}

}
