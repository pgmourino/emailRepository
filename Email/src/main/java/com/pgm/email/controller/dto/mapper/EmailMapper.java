package com.pgm.email.controller.dto.mapper;

import com.pgm.email.controller.dto.EmailDTO;
import com.pgm.email.model.Email;
import java.util.List;


public interface EmailMapper {
    
    Email mapEmailDTOIntoEmail(EmailDTO dto);
    
    EmailDTO mapEmailIntoEmailDTO(Email email);
    
    List<EmailDTO> mapListEmailIntoListEmailDTO(List<Email> listEmail);
    
}
