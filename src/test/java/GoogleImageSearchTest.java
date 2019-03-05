import static org.junit.Assert.assertEquals;

import org.junit.Test;

import api.GoogleImageSearch;

public class GoogleImageSearchTest {

	@Test
	public void testBasicFunctionality() {
		String[] arr = GoogleImageSearch.GetImagesFromGoogle("mexican");
		assertEquals(10, arr.length);
	}

	@Test
	public void testMultipleWordInput() {
		String[] arr = GoogleImageSearch.GetImagesFromGoogle("mexican food");
		assertEquals(10, arr.length);

	}

	@Test
	public void testBadInput() {
		String[] arr = GoogleImageSearch.GetImagesFromGoogle("mexican");
		assertEquals(0, arr.length);

	}

}
