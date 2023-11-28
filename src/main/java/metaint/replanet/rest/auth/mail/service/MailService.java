package metaint.replanet.rest.auth.mail.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import metaint.replanet.rest.auth.mail.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MailService {
    @Autowired
    MailHandler mailHandler;
    private static final String FROM_ADDRESS = "projectdasi2023@gmail.com";

    public void mailSend(MailDto mailDto) throws MessagingException {
        mailHandler.setFrom(FROM_ADDRESS);
        mailHandler.setTo(mailDto.getEmail());
        mailHandler.setSubject(mailDto.getTitle());
        mailHandler.setText(mailDto.getMessage(), true);
        mailHandler.send();
    }
}