package com.sobieraj.olivia.SongManager.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sobieraj.olivia.SongManager.Entity.Photo;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Long> {

}
