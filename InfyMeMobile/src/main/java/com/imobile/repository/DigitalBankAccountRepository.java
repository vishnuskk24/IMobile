package com.imobile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.imobile.entity.DigitalBanking;

public interface DigitalBankAccountRepository extends JpaRepository<DigitalBanking, Integer> {

	@Query("Select d from DigitalBanking d where d.user.mobileNumber =?1 and d.account.accountNumber=?2")
	List<DigitalBanking> findByMobileAndAccountNumber(Long mobileNo, Long accountNo);

//	DigitalBanking findByMobileNoAndAccountNo(Long mobileNo, Long accountNo);

}
