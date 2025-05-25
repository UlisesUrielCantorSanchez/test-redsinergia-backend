package com.redsinergia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.redsinergia.dto.MyContactDto;
import com.redsinergia.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	@Query(value =  """
			SELECT
			a.id AS idAccount,
			a.number_account AS numberAccount
			FROM public.contacts c
			INNER JOIN public.account a ON c.id_account_destination = a.id
			WHERE c.id_user = :idUser
			ORDER BY c.id DESC
			""",nativeQuery = true)
	public List<MyContactDto> getContacts(Integer idUser);
}
