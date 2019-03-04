package data;

public class Restaurant implements Comparable<Restaurant>{
	
	private String name;
	private String websiteUrl;
	private String price;
	private String address;
	private String phoneNumber;
	private String rating;
	private String drivingTime;
	
	public Restaurant(String name, String websiteUrl, String price, String address, String phoneNumber, String rating,
			String drivingTime) {
		this.name = name;
		this.websiteUrl = websiteUrl;
		this.price = price;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.rating = rating;
		this.drivingTime = drivingTime;
	}

	
	public String getName() {
		return name;
	}


	public String getWebsiteUrl() {
		return websiteUrl;
	}


	public String getPrice() {
		return price;
	}


	public String getAddress() {
		return address;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public String getRating() {
		return rating;
	}


	public String getDrivingTime() {
		return drivingTime;
	}
	
	
	public int compareTo(Restaurant o) {
		//converting strings to ints
		return Integer.parseInt( this.getDrivingTime() ) - Integer.parseInt( o.getDrivingTime() );
	}

}
