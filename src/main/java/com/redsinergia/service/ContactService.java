package com.redsinergia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.redsinergia.dto.MyContactDto;
import com.redsinergia.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {
	
	private final ContactRepository contactRepository;
	
	public List<MyContactDto> getContacts(Integer idUser){
		return contactRepository.getContacts(idUser);
	}

}
