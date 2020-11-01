package Code;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Main extends JFrame{
	private JTextField url;
	private JTextField sec;
	private JTextField adress;
	int check_repeat = 0;
	int check_email = 0;
	public Main() {
		setLocation(new Point(550, 250));
		getContentPane().setLocation(new Point(650, 350));
		setMinimumSize(new Dimension(330, 230));
		setResizable(false);
		getContentPane().setLayout(null);
		
		url = new JTextField();
		url.setName("");
		url.setBounds(100, 11, 187, 20);
		getContentPane().add(url);
		url.setColumns(10);
		
		JLabel label_1 = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 URL");
		label_1.setBounds(10, 14, 100, 14);
		getContentPane().add(label_1);
		
		JButton check = new JButton("\u041F\u0440\u043E\u0432\u0435\u0440\u0438\u0442\u044C");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = 0;
				try {
					status = access(url.getText(), 10000);
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
				if(status == 1) {
					JOptionPane.showMessageDialog(null, "Работает");
				}
				else if(status == 0) {
					if (check_email == 0) {
						JOptionPane.showMessageDialog(null, "Не работает");
					}
					if (check_repeat == 1) {
						new java.util.Timer().schedule(new TimerTask() {
							public void run() {
								try {
									access(url.getText(), 10000);
								} catch (MessagingException e) {
									e.printStackTrace();
								}
							}
						}, Integer.parseInt(sec.getText()) * 1000);
					}
					if (check_email == 1) {
						try {
							send(adress.getText(), url.getText());
						} catch (MessagingException e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		});
		JCheckBox repeat = new JCheckBox("\u041F\u043E\u0432\u0442\u043E\u0440\u043D\u0430\u044F \u043F\u0440\u043E\u0432\u0435\u0440\u043A\u0430");
		check.setBounds(167, 42, 100, 23);
		getContentPane().add(check);
		repeat.setBounds(10, 76, 184, 23);
		getContentPane().add(repeat);
		
		sec = new JTextField();
		sec.setVisible(false);
		sec.setBounds(151, 133, 43, 20);
		getContentPane().add(sec);
		sec.setColumns(10);
		
		JLabel label_2 = new JLabel("\u041F\u043E\u0432\u0442\u043E\u0440 \u0447\u0435\u0440\u0435\u0437 \u043A\u0430\u0436\u0434\u044B\u0435");
		label_2.setVisible(false);
		label_2.setBounds(10, 136, 139, 14);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u0441\u0435\u043A");
		label_3.setVisible(false);
		label_3.setBounds(204, 136, 46, 14);
		getContentPane().add(label_3);
		
		JLabel label_email = new JLabel("Email:");
		label_email.setVisible(false);
		label_email.setBounds(10, 167, 46, 14);
		getContentPane().add(label_email);
		
		JCheckBox email = new JCheckBox("\u0423\u0432\u0435\u0434\u043E\u043C\u043B\u0435\u043D\u0438\u0435 \u043F\u043E \u043F\u043E\u0447\u0442\u0435");
		email.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(email.isSelected()) {
					label_email.setVisible(true);
					adress.setVisible(true);
					check_email = 1;
				}
				else {
					label_email.setVisible(false);
					adress.setVisible(false);
					check_email = 0;
				}
			}
		});
		email.setBounds(10, 102, 163, 23);
		getContentPane().add(email);
		
		adress = new JTextField();
		adress.setVisible(false);
		adress.setBounds(73, 164, 214, 20);
		getContentPane().add(adress);
		adress.setColumns(10);
		
		repeat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(repeat.isSelected()) {
					label_2.setVisible(true);
					sec.setVisible(true);
					label_3.setVisible(true);
					check_repeat = 1;
				}
				else {
					label_2.setVisible(false);
					sec.setVisible(false);
					label_3.setVisible(false);
					check_repeat = 0;
				}
			}
		});
	}
	
	public int access(String url, int timeout) throws MessagingException {
		int status = 0;
	    try {
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				connection.setConnectTimeout(timeout);
				connection.setReadTimeout(timeout);
				connection.setRequestMethod("HEAD");
				connection.getResponseCode();
				status = 1;
		} catch (Exception e) {
			status = 0;
		}
		return status;
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

	public static void main(String[] args) {
		Main asd = new Main();
		asd.setVisible(true);
	}
}
