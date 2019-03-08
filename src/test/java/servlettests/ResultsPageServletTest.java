package servlettests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import servlets.ResultsPageServlet;

public class ResultsPageServletTest {

	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpSession session;

	@Mock
	RequestDispatcher rd;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		rd = mock(RequestDispatcher.class);
		
		when(request.getSession()).thenReturn(session);
		
	}

	@Test
	public void test() throws Exception {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);

		when(request.getSession()).thenReturn(session);
		when(request.getRequestDispatcher("/jsp/search.jsp")).thenReturn(rd);
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		new ResultsPageServlet().service(request, response);

		// Verify the session attribute value
		verify(session).setAttribute("user", "abhinav");

		verify(rd).forward(request, response);

		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);

	}
}
