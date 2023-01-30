package com.pgm.email.service;

import com.pgm.email.controller.dto.EmailDTO;
import com.pgm.email.exception.EmailNotFoundException;
import com.pgm.email.exception.StateNotFoundException;
import com.pgm.email.model.Email;
import java.util.List;


public interface EmailService {
    
    List<EmailDTO> getEmails();
    Email saveEmail(EmailDTO email);
    Email searchEmail(int id);
    EmailDTO updateState(int id, int state)throws EmailNotFoundException, StateNotFoundException;
    List<EmailDTO> delete(int id) throws EmailNotFoundException;
    List<Email> getEmailsFrom(String from)throws EmailNotFoundException;
    void updateListEmailStateToSpam(String from);
}
