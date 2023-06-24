package com.sobieraj.olivia.SongManager.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.olivia.SongManager.Entity.Account;
import com.sobieraj.olivia.SongManager.Entity.ConfirmationToken;

@Repository
public interface TokenRepo extends JpaRepository<ConfirmationToken, Long>{

	public ConfirmationToken findByConfirmationToken(String confirmationToken);
	public ConfirmationToken findByAccount(Account account);
	
}
