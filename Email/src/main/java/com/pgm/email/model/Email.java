package com.pgm.email.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email")
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "emailid")   
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_generator")
    @SequenceGenerator(name = "email_generator", sequenceName = "email_seq", allocationSize = 1)
    int emailId;
    @Column(name = "emailfrom")  
    String emailFrom;
    @OneToMany(mappedBy="codemail" )
    List<EmailTo> emailsTo;
    @OneToMany(mappedBy="codemail" )
    List<EmailCC> emailsCC;
    @Column(name = "emailbody")  
    String emailBody;
    int state;
    @Column(name = "emaildate")
    Date emaildate;


    
}
