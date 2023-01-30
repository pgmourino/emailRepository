
package com.pgm.email.dao;

import com.pgm.email.model.EmailCC;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmailCCRepository extends JpaRepository<EmailCC, Integer>{
    
}
