package datatests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import data.Restaurant;

public class RestaurantTest {
	

	@Test
	public void testConstructor() {
		
		String name = "A Good Restaurant";
		String websiteUrl = "https://www.mcdonalds.com/us/en-us.html";
		int price = 1;
		String address = "Everywhere";
		String phoneNumber = "(123)456-7890";
		double rating = 2.25;
		int drivingTime = 5;
		
		Restaurant restaurant = new Restaurant(name, websiteUrl, price, address, phoneNumber, rating, drivingTime);
		
		assertEquals(name, restaurant.getName());
		assertEquals(websiteUrl, restaurant.getWebsiteUrl());
		assertEquals(price, restaurant.getPrice());
		assertEquals(address, restaurant.getAddress());
		assertEquals(phoneNumber, restaurant.getPhoneNumber());
		assertEquals(rating, restaurant.getRating(), 0);
		assertEquals(drivingTime, restaurant.getDrivingTime());
		
	}
	
	@Test
	public void testCompare() {
		
		String name = "A Good Restaurant";
		String websiteUrl = "https://www.mcdonalds.com/us/en-us.html";
		int price = 1;
		String address = "Everywhere";
		String phoneNumber = "(123)456-7890";
		double rating = 2.25;
		int drivingTime = 5;
		
		Restaurant restaurant1 = new Restaurant(name, websiteUrl, price, address, phoneNumber, rating, drivingTime);
		Restaurant restaurant2 = new Restaurant(name, websiteUrl, price, address, phoneNumber, rating, drivingTime - 5);
		Restaurant restaurant3 = new Restaurant(name, websiteUrl, price, address, phoneNumber, rating, drivingTime + 5);
		Restaurant restaurant4 = new Restaurant(name, websiteUrl, price, address, phoneNumber, rating, drivingTime);
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants.add(restaurant1);
		restaurants.add(restaurant2);
		restaurants.add(restaurant3);
		restaurants.add(restaurant4);
		
		assertEquals(restaurant1, restaurants.get(0));
		assertEquals(restaurant2, restaurants.get(1));
		assertEquals(restaurant3, restaurants.get(2));
		assertEquals(restaurant4, restaurants.get(3));
		
		Collections.sort(restaurants);
		
		assertEquals(restaurant2, restaurants.get(0));
		assertEquals(restaurant1, restaurants.get(1));
		assertEquals(restaurant4, restaurants.get(2));
		assertEquals(restaurant3, restaurants.get(3));
		
		
	}

}
