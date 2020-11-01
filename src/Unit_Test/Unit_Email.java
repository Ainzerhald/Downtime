package Unit_Test;
import static org.junit.Assert.*;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import Code.Main;

public class Unit_Email {
	Main asd = new Main();	

@Test

	public void Email() throws MessagingException {
		String url = "https://vk.com";
		String email = "89225940713@mail.ru";
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		assertEquals(send(email, url), asd.send(email, url));
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	}

	public int send(String email, String service) throws MessagingException {
		int status = 0;
		try {
			String FromEmail = "89225940713@mail.ru";
			String yourpass = "Ainzerhald";
			String subject = "Неработающий сервис";
			String test = "Сервис " + service + " не работает";
			String ending = "89225940713@mail.ru";
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			if (ending.contains("@gmail.com")) {
				properties.put("mail.smtp.host", "smtp.gmail.com");
			} else if (ending.contains("@mail.ru")) {
				properties.put("mail.smtp.host", "smtp.mail.ru");
			} else if (ending.contains("@yandex.ru")) {
				properties.put("mail.smtp.host", "smtp.yandex.ru");
				properties.put("mail.smtp.port", "465");
			}
			properties.put("mail.smtp.port", "587");
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(FromEmail, yourpass);
				}
			});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FromEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));																		// письма
			message.setSubject(subject);
			message.setText(test);
			Transport.send(message);
			status = 1;
		} catch (Exception e) {
			status = 0;
		}
		return status;
	}
	
}
