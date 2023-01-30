
package com.pgm.email.dao;

import com.pgm.email.EmailApplication;
import com.pgm.email.model.EmailCC;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = EmailApplication.class)
public class EmailCCRepositoryTest {
    
    @Autowired
    EmailCCRepository dao;
    
    private final int totalSize = 3; //Init size of emailsTo preloaded in database 
    
    @Test
    public void testFindById() {
        EmailCC email = getEmailCC();
        email = dao.save(email);
        EmailCC result = dao.findById(email.getId()).get();
        assertEquals(email.getId(), result.getId());
    }

    @Test
    public void testSave() {
        EmailCC email = getEmailCC();
        dao.save(email);
        EmailCC found = dao.findById(email.getId()).get();
        assertEquals(email.getId(), found.getId());
    }

    @Test
    public void testDeleteById() {
        EmailCC email = getEmailCC();
        dao.save(email);
        dao.deleteById(email.getId());
        List<EmailCC> result = new ArrayList<>();
        dao.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), totalSize);
    }

    private EmailCC getEmailCC() {

        EmailCC emailCC = new EmailCC();
        emailCC.setCodemail(1435);
        emailCC.setTxtEmail("b@b.com");
        return emailCC;
    }
    
    
}
