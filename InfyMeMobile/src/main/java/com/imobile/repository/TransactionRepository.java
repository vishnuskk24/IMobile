package com.imobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imobile.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {

	@Query("select t from Transactions t where t.paidTo.mobileNumber=?1 or t.paidFrom.mobileNumber=?1" )
	List<Transactions> getAllTransaction(Long mobileNo);

}
