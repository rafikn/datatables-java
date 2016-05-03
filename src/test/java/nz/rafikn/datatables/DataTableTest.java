package nz.rafikn.datatables;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nz.rafikn.datatables.request.Order;
import nz.rafikn.datatables.request.Request;

import java.util.HashMap;
import java.util.Map;


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
		
		Request requestData = Request.build(validRequest());
		
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
		Request requestData = Request.build(invalidRequest());
		assertNull(requestData);
	}
	
	private Map<String, String[]> validRequest() {		
		Map<String, String[]> parametersMap = new HashMap<String, String[]>();
		
		parametersMap.put("draw", new String[] {"0"});
		parametersMap.put("start", new String[] {"10"});
		parametersMap.put("length", new String[] {"20"});
		parametersMap.put("search[value]", new String[] {"search query"});
		parametersMap.put("search[regex]", new String[] {"false"});
		parametersMap.put("order[0][column]", new String[] {"0"});
		parametersMap.put("order[0][dir]", new String[] {"asc"});
		parametersMap.put("columns[0][data]", new String[] {"data1"});
		parametersMap.put("columns[0][name]", new String[] {"name1"});
		parametersMap.put("columns[0][searchable]", new String[] {"false"});
		parametersMap.put("columns[0][orderable]", new String[] {"true"});
		parametersMap.put("columns[0][search][value]", new String[] {""});
		parametersMap.put("columns[0][search][regex]", new String[] {"false"});
		parametersMap.put("columns[1][data]", new String[] {"data2"});
		parametersMap.put("columns[1][name]", new String[] {"name2"});
		parametersMap.put("columns[1][searchable]", new String[] {"false"});
		parametersMap.put("columns[1][orderable]", new String[] {"true"});
		parametersMap.put("columns[1][search][value]", new String[] {""});
		parametersMap.put("columns[1][search][regex]", new String[] {"false"});
		
		return parametersMap;
	}
	
	private Map<String, String[]>  invalidRequest() {
		Map<String, String[]> parametersMap = new HashMap<String, String[]>();
		
		parametersMap.put("draw", new String[] {"novalid"});
		return parametersMap;
	}
	
	
	public void testResponse() {
		//TODO
		assertTrue(true);
	}
}
