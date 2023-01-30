package com.pgm.email.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDTO implements Serializable {

    Integer emailId;
    @NotNull
    @Email
    @NotEmpty
    String emailFrom;
    @NotNull
    @NotEmpty
    List<String> emailTo;
    @NotNull
    @NotEmpty
    List<String> emailCC;
    @NotNull
    String emailBody;
    @NotNull
    Integer state;

}
