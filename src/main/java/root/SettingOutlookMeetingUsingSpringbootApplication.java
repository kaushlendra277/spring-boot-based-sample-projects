package root;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SettingOutlookMeetingUsingSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingOutlookMeetingUsingSpringbootApplication.class, args);
		// sendMailWithoutAuthentication();
	}

	private static void sendMailWithoutAuthentication() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "<local-email-server>");
			props.put("mail.smtp.port", "25");
			props.put("mail.debug", "true");
			props.put("useAuth", "false");
			props.put("useEhlo", "true");
			//props.put("mail.properties.mail.smtp.starttls.enable", "true");
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("kaushlendra277@gmail.com"));
			message.setRecipients(RecipientType.TO, new InternetAddress[] { 
					new InternetAddress("kaushlendra277@gmail.com"),
					new InternetAddress("Kaushlendra.Chauhan@Xoriant.Com")});
			message.setSubject("Notification");
			message.setText("Successful!", "UTF-8"); // as "text/plain"
			message.setSentDate(new Date());
			Transport.send(message);	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
