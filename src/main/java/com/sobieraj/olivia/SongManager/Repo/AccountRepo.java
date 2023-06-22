package com.sobieraj.olivia.SongManager.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.olivia.SongManager.Entity.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

	public Account findAccountByUsername(String user);
	
}
