import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import api.AccessYelpAPI;
import data.Restaurant;

public class YelpTest {

	@Test
	public void test() {
		int count = 20;
		Vector<Restaurant> arr = AccessYelpAPI.YelpRestaurantSearch("mexican", count);
		assertEquals(count, arr.size());
		
		for (int i = 0; i < count; i++) {
			System.out.println(i);
			System.out.println("name: " + arr.get(i).getName());
			System.out.println("driveTime: " + arr.get(i).getDrivingTime());
		}
	}

	@Test
	public void testMultipleInput() {
		int count = 20;
		Vector<Restaurant> arr = AccessYelpAPI.YelpRestaurantSearch("mexican chicken", count);
		assertEquals(count, arr.size());
		
		for (int i = 0; i < count; i++) {
			System.out.println(i);
			System.out.println("name: " + arr.get(i).getName());
			System.out.println("driveTime: " + arr.get(i).getDrivingTime());
		}
	}
	
	@Test
	public void testBadInput() {
		int count = 20;
		Vector<Restaurant> arr = AccessYelpAPI.YelpRestaurantSearch("qwertyuioiuytrewwdc", count);
		assertEquals(0, arr.size());
		
	}

}
