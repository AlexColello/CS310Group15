import static org.junit.Assert.*;

import org.junit.Test;

import api.GoogleDirections;

public class GoogleDirectionsTest {

	@Test
	public void test() {
		
		int timeDuration = GoogleDirections.getDrivingTime(34.0206, -118.2854, 34.0252, -118.2788);
		System.out.println(timeDuration);
		assertTrue(timeDuration > 120);
	}
	
	@Test
	public void badUrltest() {
		
		int timeDuration = GoogleDirections.getDrivingTime(34000.0206, -118.2854, 34.0252, -118.2788);
		System.out.println(timeDuration);
		assertTrue(timeDuration < 0);
	}

}
