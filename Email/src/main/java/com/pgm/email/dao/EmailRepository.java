package com.pgm.email.dao;

import com.pgm.email.model.Email;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailRepository extends JpaRepository<Email, Integer>{
    
    List<Email> findByEmailFrom(String emailFrom);
}
