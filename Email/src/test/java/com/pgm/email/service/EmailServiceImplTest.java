package com.pgm.email.service;

import com.pgm.email.EmailApplication;
import com.pgm.email.controller.dto.EmailDTO;
import com.pgm.email.controller.dto.mapper.EmailMapper;
import com.pgm.email.dao.EmailCCRepository;
import com.pgm.email.dao.EmailRepository;
import com.pgm.email.dao.EmailToRepository;
import com.pgm.email.model.Email;
import com.pgm.email.model.EmailTo;
import com.pgm.email.model.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailApplication.class)
public class EmailServiceImplTest {

    private EmailService emailService;

    @MockBean
    private EmailRepository dao;
    
    
    @MockBean
    private EmailToRepository daoTo;
    
    @MockBean
    private EmailCCRepository daoCC;
    
    @Autowired
    private EmailMapper emailMapper;
    
    @BeforeEach
    public void setUp() {
        
        Email email = createEmail();

        emailService = new EmailServiceImpl(dao, daoTo, daoCC, emailMapper);
        Mockito.when(dao.findAll()).thenReturn(getListEmails());
        Mockito.when(dao.save(Mockito.any(Email.class))).thenReturn(email);
        Mockito.when(dao.findById(1)).thenReturn(Optional.of(createEmail()));
        Mockito.when(dao.findByEmailFrom("a@a.com")).thenReturn(getListEmails());
    }
    
    @Test
    public void testGetEmails() {
        
        List<EmailDTO> listEmails = emailService.getEmails();
        
        assertNotNull(emailService.getEmails());
        assertThat(listEmails).hasSize(1);
    }
    
    @Test
    public void testGetEmailsFrom() {
        String from = "a@a.com";
        List<Email> expResult = getListEmails();
        List<Email> result = emailService.getEmailsFrom(from);
        assertNotNull(emailService.getEmails());
        assertEquals(expResult.size(), result.size());
    }
   
   
    @Test
    public void testSaveEmail() {
        EmailDTO dto = emailMapper.mapEmailIntoEmailDTO(createEmail());  
        Email expResult = createEmail();
        Email result = emailService.saveEmail(dto);
        assertEquals(expResult.getEmailFrom(), result.getEmailFrom());
        assertEquals(expResult.getEmailId(), result.getEmailId());
    }

    @Test
    public void testSearchEmail() {
        int id = 1;
        Email expResult = createEmail();
        Email result = emailService.searchEmail(id);
        assertEquals(expResult.getEmailId(), result.getEmailId());
    }
    
    @Test
    public void testUpdateState() {
        int id = 1;
        int uState = State.REMOVED.getValue();
        EmailDTO expResult = emailMapper.mapEmailIntoEmailDTO(createEmail()); 
        EmailDTO result = emailService.updateState(id, uState);
        assertEquals(expResult.getState(), result.getState());
    }
    
    private List<Email> getListEmails(){
        List<Email> listEmails = new ArrayList<>();
        listEmails.add(createEmail());
        return listEmails;
    }
    
    private Email createEmail() {

        Email e = new Email();
        e.setEmailId(1);
        e.setEmailFrom("a@a.com");
        e.setEmailsTo(createEmailTo());
        e.setEmailBody(" Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text");
        e.setState(State.DRAFT.getValue());
        return e;
    }

    private List<EmailTo> createEmailTo() {

        EmailTo eTo = new EmailTo();
        eTo.setId(1);
        eTo.setCodemail(1);
        eTo.setTxtEmail("b@b.com");

        List<EmailTo> emailsTo = new ArrayList<>();
        emailsTo.add(eTo);
        return emailsTo;
    }
    
}
