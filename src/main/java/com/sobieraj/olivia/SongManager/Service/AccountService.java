package com.sobieraj.olivia.SongManager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sobieraj.olivia.SongManager.Entity.Account;
import com.sobieraj.olivia.SongManager.Repo.AccountRepo;

@Service
public class AccountService {
	
	@Autowired
	AccountRepo aRepo;
	
	public boolean accountExists(Account account) {
		
		if(account != null) {
			
			Account accountExist = aRepo.findAccountByUsername(account.getUsername());
			if(accountExist != null) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public void save(Account account) {
		aRepo.save(account);
	}

}
