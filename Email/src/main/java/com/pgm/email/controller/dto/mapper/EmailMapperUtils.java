package com.pgm.email.controller.dto.mapper;

import com.pgm.email.model.EmailCC;
import com.pgm.email.model.EmailTo;
import java.util.List;
import java.util.stream.Collectors;

public class EmailMapperUtils {

    public static List<String> listEmailToIntoListString(List<EmailTo> listEmail) {

        return listEmail == null ? null : listEmail.stream()
                .map(email -> email.getTxtEmail())
                .collect(Collectors.toList());
    }

    public static List<String> listEmailCCIntoListString(List<EmailCC> listEmail) {

        return listEmail == null ? null :listEmail.stream()
                .map(email -> email.getTxtEmail())
                .collect(Collectors.toList());
    }
}
