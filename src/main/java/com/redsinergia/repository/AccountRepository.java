package com.redsinergia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.redsinergia.dto.MyAccountDto;
import com.redsinergia.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	public Optional<Account> findById(Integer id);
	
	@Query(value = """
			SELECT 
			a.id AS idAccount,
			a.number_account AS numberAccount
			FROM public.account a
			WHERE a.id_user = :idUser
			AND a.erased = FALSE
			""",nativeQuery = true)
	public List<MyAccountDto> getAccountByIdUser(Integer idUser);
	
	public boolean existsByNumberAccount(String numberAccount);

}
