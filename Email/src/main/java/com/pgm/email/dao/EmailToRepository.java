
package com.pgm.email.dao;

import com.pgm.email.model.EmailTo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailToRepository extends JpaRepository<EmailTo, Integer>{
    
}
