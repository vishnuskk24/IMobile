package com.infy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infy.entity.Accounts;
//import com.infy.service.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, Long> {
	@Query("Select a from Accounts a where a.user.mobileNumber=?1")
	List<Accounts> findByMobileNo(Long mobileNo);

	@Query("Select a from Accounts a where a.bankName=?1 and a.accountType=?2")
	List<Accounts> findByBankNameAndAccountType(String bankName, String accountType);

}
