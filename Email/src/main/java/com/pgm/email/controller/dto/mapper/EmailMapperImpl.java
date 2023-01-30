package com.pgm.email.controller.dto.mapper;

import com.pgm.email.controller.dto.EmailDTO;
import com.pgm.email.model.Email;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EmailMapperImpl implements EmailMapper {

    @Override
    public Email mapEmailDTOIntoEmail(EmailDTO dto) {

        return Email.builder()
                .emailFrom(dto.getEmailFrom())
                .emailBody(dto.getEmailBody())
                .state(dto.getState())
                .build();
    }

    @Override
    public EmailDTO mapEmailIntoEmailDTO(Email email) {
        return EmailDTO.builder()
                .emailId(email.getEmailId())
                .emailFrom(email.getEmailFrom())
                .emailTo(EmailMapperUtils.listEmailToIntoListString(email.getEmailsTo()))
                .emailCC(EmailMapperUtils.listEmailCCIntoListString(email.getEmailsCC()))
                .emailBody(email.getEmailBody())
                .state(email.getState())
                .build();
    }

    @Override
    public List<EmailDTO> mapListEmailIntoListEmailDTO(List<Email> listEmails) {
        List<EmailDTO> dtos = new ArrayList<>();

        if (listEmails != null) {
            for (Email email : listEmails) {
                dtos.add(mapEmailIntoEmailDTO(email));
            }
        }

        return dtos;
    }

}
