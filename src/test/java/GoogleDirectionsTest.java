import static org.junit.Assert.*;

import org.junit.Test;

import api.GoogleDirections;

public class GoogleDirectionsTest {

	@Test
	public void test() {
		
		double timeDuration = GoogleDirections.getDrivingTime(34.0206, -118.2854, 34.0252, -118.2788);
		
		System.out.println(timeDuration);
	}

}
