package com.pgm.email.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emailcc")
public class EmailCC implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")   
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emailcc_generator")
    @SequenceGenerator(name = "emailcc_generator", sequenceName = "emailcc_seq", allocationSize = 1)
    int id;
    int codemail;
    @Column(name="txtemail")
    String txtEmail;
   
}
