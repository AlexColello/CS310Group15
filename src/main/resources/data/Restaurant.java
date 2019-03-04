package data;

public class Restaurant implements Comparable<Restaurant>{
	// String variables = "NULL", numeric variables = -1 if data is not available from Yelp
	private String name;
	private String websiteUrl;
	private String price;
	private String address;
	private String phoneNumber;
	private double rating;
	private int drivingTime;
	
	public Restaurant(String name, String websiteUrl, String price, String address, String phoneNumber, double rating,
			int drivingTime) {
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


	public double getRating() {
		return rating;
	}


	public int getDrivingTime() {
		return drivingTime;
	}

	public int compareTo(Restaurant o) {
		//converting strings to ints
		return this.getDrivingTime() - o.getDrivingTime();
	}
}
