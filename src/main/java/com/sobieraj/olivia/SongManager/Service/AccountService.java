package com.sobieraj.olivia.SongManager.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.sobieraj.olivia.SongManager.Entity.Account;
import com.sobieraj.olivia.SongManager.Entity.ConfirmationToken;
import com.sobieraj.olivia.SongManager.Repo.AccountRepo;
import com.sobieraj.olivia.SongManager.Repo.TokenRepo;

@Service
public class AccountService {
	
	@Autowired
	AccountRepo aRepo;
	
	@Autowired
	TokenRepo tRepo;
	
	@Autowired
	EmailService eService;
	
	public boolean accountExists(Account account) {
		
		if(account != null) {
			
			Account accountExist = aRepo.findAccountByUsername(account.getUsername());
			if(accountExist != null) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public boolean accountPassCheck(Account account) {
		
		Account accountExist = aRepo.findAccountByUsername(account.getUsername());
		if(accountExist != null) {
			
			if(accountExist.getPassword().equals(account.getPassword())) {
				return true;
			}
			
		}
		return false;
	}
	
	public boolean save(Account account) {
		Account accountExist = aRepo.findAccountByUsername(account.getUsername());
		if(accountExist != null) {
			return false;
		    
		}
		aRepo.save(account);


        return true;
    }

    public boolean confirmEmail(String confirmationToken) {
        ConfirmationToken token = tRepo.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            Account account = aRepo.findAccountByUsername(token.getAccount().getUsername());
            account.setEnabled(true);
            aRepo.save(account);
            return true;
        }
        return false;
    }
}
