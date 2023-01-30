package com.pgm.email.controller;

import com.pgm.email.controller.dto.EmailDTO;
import com.pgm.email.service.EmailService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emails")
public class EmailController{
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping(value="/",produces=MediaType.APPLICATION_JSON_VALUE)
    public List<EmailDTO> getEmails(){
        return emailService.getEmails();
    }
    
    @PostMapping(value="add",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public List<EmailDTO> addEmail(@Valid @RequestBody EmailDTO email){
        emailService.saveEmail(email);
        return emailService.getEmails();
    }
    
    @DeleteMapping(value="delete/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public List<EmailDTO> deleteEmail(@PathVariable ("id") int id){
       return emailService.delete(id);
    }
    
    @PutMapping(value="changeState/{id}/{state}",produces=MediaType.APPLICATION_JSON_VALUE)
    public EmailDTO changeStateEmail(@PathVariable ("id") int id,@PathVariable ("state") int state){
       return emailService.updateState(id,state);
    }
    
}
