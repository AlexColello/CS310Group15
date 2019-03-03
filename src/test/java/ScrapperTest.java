import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class ScrapperTest {

	
	@Test
	public void searchTest() {
		Scrapper.search("chicken", 5);
	}
	
}
