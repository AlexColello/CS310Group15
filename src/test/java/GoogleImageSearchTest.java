import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import api.GoogleImageSearch;

public class GoogleImageSearchTest {

	@Test
	public void testBasicFunctionality() {
		Vector<String> arr = GoogleImageSearch.GetImagesFromGoogle("mexican");
		assertEquals(10, arr.size());
	}

	@Test
	public void testMultipleWordInput() {
		Vector<String> arr = GoogleImageSearch.GetImagesFromGoogle("mexican food");
		assertEquals(10, arr.size());

	}

	@Test
	public void testBadInput() {
		Vector<String> arr = GoogleImageSearch.GetImagesFromGoogle("qwertyuiopoiuyrtyuiopoiuyghjuytfvb");
		assertEquals(0, arr.size());

	}

}
