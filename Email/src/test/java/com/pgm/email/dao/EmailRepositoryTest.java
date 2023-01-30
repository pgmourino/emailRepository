package com.pgm.email.dao;

import com.pgm.email.EmailApplication;
import com.pgm.email.model.Email;
import com.pgm.email.model.EmailTo;
import com.pgm.email.model.State;
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
public class EmailRepositoryTest {

    @Autowired
    EmailRepository dao;

    private final int totalSize = 2; //Init size of emails preloaded in database 
    private final String emailFrom ="a@a.com";

    @Test
    public void testFindById() {
        Email email = getEmail();
        email = dao.save(email);
        Email result = dao.findById(email.getEmailId()).get();
        assertEquals(email.getEmailId(), result.getEmailId());
    }
    
    @Test
    public void testFindByFrom() {
        Email email = getEmail();
        dao.save(email);
        List<Email> result = dao.findByEmailFrom(emailFrom);
        assertEquals(email.getEmailFrom(), result.stream().findFirst().get().getEmailFrom());
    }

    @Test
    public void testFindAll() {
        Email email = getEmail();
        dao.save(email);
        List<Email> result = new ArrayList<>();
        dao.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), totalSize + 1);
    }

    @Test
    public void testSave() {
        Email email = getEmail();
        dao.save(email);
        Email found = dao.findById(email.getEmailId()).get();
        assertEquals(email.getEmailId(), found.getEmailId());
    }

    @Test
    public void testDeleteById() {
        Email email = getEmail();
        dao.save(email);
        dao.deleteById(email.getEmailId());
        List<Email> result = new ArrayList<>();
        dao.findAll().forEach(e -> result.add(e));
        assertEquals(result.size(), totalSize);
    }

    private Email getEmail() {

        Email e = new Email();
        e.setEmailFrom(emailFrom);
        e.setEmailsTo(getEmailTo());
        e.setEmailBody(" Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text");
        e.setState(State.DRAFT.getValue());
        return e;
    }

    private List<EmailTo> getEmailTo() {

        EmailTo eTo = new EmailTo();
        eTo.setTxtEmail("b@b.com");

        List<EmailTo> emailsTo = new ArrayList<>();
        emailsTo.add(eTo);
        return emailsTo;
    }
    
}
