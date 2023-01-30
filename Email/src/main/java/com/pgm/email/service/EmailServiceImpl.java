package com.pgm.email.service;

import com.pgm.email.controller.dto.EmailDTO;
import com.pgm.email.controller.dto.mapper.EmailMapper;
import com.pgm.email.dao.EmailCCRepository;
import com.pgm.email.dao.EmailRepository;
import com.pgm.email.dao.EmailToRepository;
import com.pgm.email.exception.EmailNotFoundException;
import com.pgm.email.exception.StateNotFoundException;
import com.pgm.email.model.Email;
import com.pgm.email.model.EmailCC;
import com.pgm.email.model.EmailTo;
import com.pgm.email.model.State;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository dao;
    @Autowired
    private EmailToRepository daoTo;
    @Autowired
    private EmailCCRepository daoCC;
    @Autowired
    private EmailMapper emailMapper;

    @Override
    public List<EmailDTO> getEmails() {
        return emailMapper.mapListEmailIntoListEmailDTO(dao.findAll());
    }

    @Override
    public Email saveEmail(EmailDTO dto) {
        Email email = emailMapper.mapEmailDTOIntoEmail(dto);
        email.setEmaildate(new Date());

        email = dao.save(email);

        if (email != null) {

            email.setEmailsTo(new ArrayList<>());
            email.setEmailsCC(new ArrayList<>());

            if (dto.getEmailCC() != null) {
                for (String x : dto.getEmailTo()) {
                    EmailTo emailTo = new EmailTo();
                    emailTo.setCodemail(email.getEmailId());
                    emailTo.setTxtEmail(x);
                    email.getEmailsTo().add(daoTo.save(emailTo));
                }
            }

            if (dto.getEmailCC() != null) {
                for (String x : dto.getEmailCC()) {
                    EmailCC emailCC = new EmailCC();
                    emailCC.setCodemail(email.getEmailId());
                    emailCC.setTxtEmail(x);
                    email.getEmailsCC().add(daoCC.save(emailCC));
                }
            }
        }

        return email;
    }

    @Override
    public Email searchEmail(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public EmailDTO updateState(int id, int uState) throws EmailNotFoundException, StateNotFoundException {
        Email email = searchEmail(id);

        if (email == null) {
            throw new EmailNotFoundException(id);
        }

        try {
            State.findByCode(uState);
        } catch (IllegalArgumentException ex) {
            throw new StateNotFoundException(uState);
        }

        email.setState(uState);
        return emailMapper.mapEmailIntoEmailDTO(dao.save(email));
    }

    @Override
    public List<EmailDTO> delete(int id) throws EmailNotFoundException {
        Email email = searchEmail(id);

        if (email != null) {
            dao.deleteById(id);
            return getEmails();
        } else {
            throw new EmailNotFoundException(id);
        }
    }

    @Override
    public List<Email> getEmailsFrom(String from) throws EmailNotFoundException {
        return dao.findByEmailFrom(from);
    }

    @Override
    public void updateListEmailStateToSpam(String from) {

        List<Email> emails = getEmailsFrom(from);
        emails.forEach(e -> e.setState(State.SPAM.getValue()));
        emails.forEach(e -> e.setEmaildate(new Date()));
        dao.saveAll(emails);
    }

}
