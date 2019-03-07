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
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);

		when(request.getParameter("user")).thenReturn("abhinav");
		when(request.getParameter("password")).thenReturn("passw0rd");
		when(request.getParameter("rememberMe")).thenReturn("Y");
		when(request.getSession()).thenReturn(session);
		when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		new ResultsPageServlet().service(request, response);

		// Verify the session attribute value
		verify(session).setAttribute("user", "abhinav");

		verify(rd).forward(request, response);

		String result = sw.getBuffer().toString().trim();

		System.out.println("Result: " + result);

		assertEquals("Login successfull...", result);
	}
}
