
package com.pgm.email.dao;

import com.pgm.email.EmailApplication;
import com.pgm.email.model.EmailTo;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = EmailApplication.class)
public class EmailToRepositoryTest {
    
    @Autowired
    EmailToRepository dao;
    
    private final int totalSize = 4; //Init size of emailsTo preloaded in database 
    
    @Test
    public void testFindById() {
        EmailTo email = getEmailTo();
        email = dao.save(email);
        EmailTo result = dao.findById(email.getId()).get();
        assertEquals(email.getId(), result.getId());
    }

    @Test
    public void testSave() {
        EmailTo email = getEmailTo();
        dao.save(email);
        EmailTo found = dao.findById(email.getId()).get();
        assertEquals(email.getId(), found.getId());
    }

    @Test
    public void testDeleteById() {
        EmailTo email = getEmailTo();
        dao.save(email);
        dao.deleteById(email.getId());
        List<EmailTo> result = new ArrayList<>();
        dao.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), totalSize);
    }

    private EmailTo getEmailTo() {

        EmailTo emailTo = new EmailTo();
        emailTo.setCodemail(1435);
        emailTo.setTxtEmail("b@b.com");
        return emailTo;
    }
    
}
