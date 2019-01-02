package laptrinhweb.service;

public interface GmailService {
	public boolean sendMail(String to, String subject, String text);
}
