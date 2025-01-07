package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infy.entity.DigitalBanking;

public interface DigitalBankAccountRepository extends JpaRepository<DigitalBanking, String> {

	@Query("Select d from DigitalBanking d where d.user.mobileNumber =?1 and d.account.accountNumber=?2")
	List<DigitalBanking> findByMobileAndAccountNumber(Long mobileNo, Long accountNo);

//	DigitalBanking findByMobileNoAndAccountNo(Long mobileNo, Long accountNo);

}
