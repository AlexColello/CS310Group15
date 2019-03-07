import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import apitests.GoogleDirectionsTest;
import apitests.GoogleImageSearchTest;
import apitests.ScrapperTest;
import apitests.YelpTest;
import datatests.RecipeTest;
import datatests.RestaurantTest;

@RunWith(Suite.class)
@SuiteClasses({ GoogleDirectionsTest.class, GoogleImageSearchTest.class, ScrapperTest.class, YelpTest.class, RecipeTest.class, RestaurantTest.class })
public class AllTests {
	
	@BeforeClass
	public static void test() {
		System.out.println("Starting tests");
	}

}
