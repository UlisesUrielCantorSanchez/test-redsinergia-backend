package com.redsinergia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.redsinergia.dto.GraficTransferDto;
import com.redsinergia.dto.MyAccountDto;
import com.redsinergia.dto.MyTransfersDto;
import com.redsinergia.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Integer>{
	
	@Query(value = """
			SELECT 
			ROW_NUMBER() OVER (ORDER BY t.id) AS count,
			a.number_account AS origin,
			a2.number_account AS destination,
			t.date AS date,
			t.amount AS amount
			FROM public.transfer t
			INNER JOIN public.account a ON t.id_account_origin = a.id
			INNER JOIN public.account a2 ON t.id_account_destination = a2.id
			INNER JOIN public.users u ON a.id_user = u.id
			WHERE u.id = :idUser
			ORDER BY t.id DESC
			""",nativeQuery = true)
	public List<MyTransfersDto> getMyTransfers(Integer idUser);
	
	@Query(value = """
		SELECT 
        a2.number_account AS destination,
        CAST(SUM(t.amount) AS FLOAT) AS amount
	    FROM public.transfer t
	    INNER JOIN public.account a ON t.id_account_origin = a.id
		INNER JOIN public.account a2 ON t.id_account_destination = a2.id
	    INNER JOIN public.users u ON a.id_user = u.id
	    WHERE a.id = :idAcount
	    GROUP BY a2.number_account
			""",nativeQuery = true)
	public List<GraficTransferDto> getGraficTransfers(Integer idAcount);
	
	@Query(value = """
		SELECT 
		a.id AS idAccount,
		a.number_account AS numberAccount
		FROM public.account a
		WHERE a.id_user= :id_user
		AND a.erased = FALSE
		ORDER BY a.id DESC
		""",nativeQuery = true)
	public List<MyAccountDto> getAccountByIdUser(Integer id_user);
	
	@Query(value = """
			SELECT 
			a.balance
			FROM public.account a
			WHERE a.id = :idAcount
			""",nativeQuery = true)
	public Float getBalance(Integer idAcount);
	
	@Query(value = """
			SELECT 
			ROW_NUMBER() OVER (ORDER BY t.id) AS count,
			a.number_account AS origin,
			a2.number_account AS destination,
			t.date AS date,
			t.amount AS amount
			FROM public.transfer t
			INNER JOIN public.account a ON t.id_account_origin = a.id
			INNER JOIN public.account a2 ON t.id_account_destination = a2.id
			INNER JOIN public.users u ON a.id_user = u.id
			WHERE u.id = :idUser
			ORDER BY t.amount DESC
			LIMIT 3
			""",nativeQuery = true)
	public List<MyTransfersDto> getMyTransfersTop(Integer idUser);

}
