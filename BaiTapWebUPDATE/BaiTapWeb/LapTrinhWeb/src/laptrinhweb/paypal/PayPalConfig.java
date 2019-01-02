package laptrinhweb.paypal;

public class PayPalConfig {
	private String authToken;
	private String portUrl;
	private String business;
	private String returnUrl;
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getPortUrl() {
		return portUrl;
	}
	public void setPortUrl(String portUrl) {
		this.portUrl = portUrl;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	
}
