package Unit_Test;
import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import org.junit.Test;
import Code.Main;

public class Unit_Acces {
	Main asd = new Main();	

@Test

	public void Access() throws MessagingException {
		String url = "https://vk.com";
		int timeout = 10000;
		
		System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
		assertEquals(access(url, timeout), asd.access(url, timeout));
		System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
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
	
}
