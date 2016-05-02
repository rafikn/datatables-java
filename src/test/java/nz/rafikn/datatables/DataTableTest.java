package nz.rafikn.datatables;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nz.rafikn.datatables.request.Order;
import nz.rafikn.datatables.request.RequestData;

import static org.mockito.Mockito.*;


/**
 * Unit test for DataTable
 * 
 */
public class DataTableTest extends TestCase {

	
	public DataTableTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(DataTableTest.class);
	}
	
	public void testValidRequest() {
		
		RequestData requestData = RequestData.build(validRequest());
		
		assertNotNull(requestData);
		assertEquals(0, requestData.getDraw());
		assertEquals(10, requestData.getStart());
		assertEquals(20, requestData.getLength());
		
		assertNotNull(requestData.getSearch());
		assertEquals("search query", requestData.getSearch().getValue());
		assertFalse(requestData.getSearch().isRegex());
		
		assertNotNull(requestData.getOrder());
		assertEquals(1, requestData.getOrder().size());
		assertNotNull(requestData.getOrder().get(0));
		assertEquals(0, requestData.getOrder().get(0).getColumn());
		assertEquals(Order.Direction.ASC, requestData.getOrder().get(0).getDirection());
		
		assertNotNull(requestData.getColumns());
		assertEquals(2, requestData.getColumns().size());
	}
	
	public void testInvalidRequest() {
		RequestData requestData = RequestData.build(invalidRequest());
		assertNull(requestData);
	}
	
	private HttpServletRequest validRequest() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getParameter("draw")).thenReturn("0");
		when(req.getParameter("start")).thenReturn("10");
		when(req.getParameter("length")).thenReturn("20");
		when(req.getParameter("search[value]")).thenReturn("search query");
		when(req.getParameter("search[regex]")).thenReturn("false");
		when(req.getParameter("order[0][column]")).thenReturn("0");
		when(req.getParameter("order[0][dir]")).thenReturn("asc");
		when(req.getParameter("columns[0][data]")).thenReturn("data1");
		when(req.getParameter("columns[0][name]")).thenReturn("name1");
		when(req.getParameter("columns[0][searchable]")).thenReturn("false");
		when(req.getParameter("columns[0][orderable]")).thenReturn("true");
		when(req.getParameter("columns[0][search][value]")).thenReturn("");
		when(req.getParameter("columns[0][search][regex")).thenReturn("false");
		when(req.getParameter("columns[1][data]")).thenReturn("data2");
		when(req.getParameter("columns[1][name]")).thenReturn("name2");
		when(req.getParameter("columns[1][searchable]")).thenReturn("false");
		when(req.getParameter("columns[1][orderable]")).thenReturn("true");
		when(req.getParameter("columns[1][search][value]")).thenReturn("");
		when(req.getParameter("columns[1][search][regex")).thenReturn("false");
		
		return req;
	}
	
	private HttpServletRequest invalidRequest() {
		HttpServletRequest req = mock(HttpServletRequest.class);
		when(req.getParameter("draw")).thenReturn("invalid");
		
		return req;
	}
	
	
	public void testResponse() {
		//TODO
		assertTrue(true);
	}
}
