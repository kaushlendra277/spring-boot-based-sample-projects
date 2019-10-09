package root.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// https://www.mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/
@RestController
@RequestMapping("gmail")
public class GmailController {
	
	// application.properties
	/*
			spring.mail.host = smtp.gmail.com
			spring.mail.port = 587
			# for this sender turn Allow less secure apps: ON from below below url after logging in to gmail
			# https://myaccount.google.com/lesssecureapps
			spring.mail.username = kaushlendra277@gmail.com
			spring.mail.password = Kaush!2707
			spring.mail.protocol = smtp
			spring.mail.tls = true
			spring.mail.properties.mail.smtp.auth = true
			spring.mail.properties.mail.smtp.starttls.enable = true
			spring.mail.properties.mail.smtp.ssl.trust = smtp.gmail.com
			isEnabled.mail.config=false
		*/	
	// for this sender turn Allow less secure apps: ON from below below url after logging in to gmail
	// https://myaccount.google.com/lesssecureapps
	@Autowired
	private JavaMailSender javaMailSender;

	@GetMapping
	public ResponseEntity<Void> send() {
		try {
			//sendEmail();
			write( "subject",
					"20190911",
					"20190911T070000",
					"20190911T0741000",
					"content");
			System.out.println("ics file created");
			sendEmailWithAttachment();
			System.out.println("Email Sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	void sendEmail() {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("kaushlendra277@gmail.com");

		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);

	}
	
	void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("kaushlendra.chauhan@xoriant.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("meeting-invite.ics", new File("./meeting.ics"));

        javaMailSender.send(msg);

    }
	
	public void write( 
			String subject,
			String date,
			String startTime,
			String endTime,
			String content){
		String version =    "VERSION:2.0\n";
		//String prodid =     "PRODID:-//Taleo Corp//EvaluationManagement//EN\n";
		String calBegin =   "BEGIN:VCALENDAR\n";
		String eventBegin = "BEGIN:VEVENT\n";
		String eventEnd =   "END:VEVENT\n";
		String calEnd =     "END:VCALENDAR";
        try {

            File file = new File("./meeting.ics");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(calBegin);
            //bw.write(prodid);
            bw.write(version);
            bw.write("CALSCALE:GREGORIAN\n");
            bw.write(eventBegin);
            //bw.write("DTSTART:20190911T070000Z\n");
            bw.write(String.format("DTSTART:%s\n", startTime));
            //bw.write("DTEND:20190911T073000Z\n");
            bw.write(String.format("DTEND:%s\n",endTime));
            //bw.write("DTSTAMP:20190910T202755Z\n");
            bw.write(String.format("DTSTAMP:%s\n", date));
            //bw.write("ORGANIZER:appointment@donotreply.com\n");
            bw.write("ORGANIZER:Kaushlendrachauhan@xdgoriant.com\n");
            //bw.write("UID:10700\n");
            //bw.write("CREATED:20190910T202755Z\n");
            bw.write("CREATED:20190910T12000SZ\n");
            bw.write("X-ALT-DESC;FMTTYPE=text/html\n");
            bw.write(String.format("SUMMARY:%s\n", subject) );
            //bw.write("LOCATION: F2F, Baner\n");
            //bw.write("SEQUENCE:0\n");
            //bw.write("STATUS:CANCELLED\n");
            bw.write("TRANSP:OPAQUE\n");
            
            bw.write(eventEnd);
            bw.write(calEnd);

            bw.close();

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
