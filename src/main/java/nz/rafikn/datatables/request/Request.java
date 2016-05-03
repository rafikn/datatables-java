package nz.rafikn.datatables.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nz.rafikn.datatables.dto.DataTableRequest;

public class Request {
	
    private static final Logger logger = LoggerFactory.getLogger(Request.class);


    private int draw;
    private int start;
    private int length;

    private Search search;
    private List<Order> order = new ArrayList<Order>();
    private List<Column> columns = new ArrayList<Column>();

    private Request() {
    }

    /**
     * Draw counter. This is used by DataTables to ensure that the Ajax returns
     * from server-side processing requests are drawn in sequence by DataTables
     * (Ajax requests are asynchronous and thus can return out of sequence).
     * This is used as part of the draw return parameter (see below).
     * 
     * @return
     */
    public int getDraw() {
        return draw;
    }

    /**
     * Paging first record indicator. This is the start point in the current
     * data set (0 index based - i.e. 0 is the first record).
     * 
     * @return
     */
    public int getStart() {
        return start;
    }

    /**
     * Number of records that the table can display in the current draw. It is
     * expected that the number of records returned will be equal to this
     * number, unless the server has fewer records to return. Note that this can
     * be -1 to indicate that all records should be returned (although that
     * negates any benefits of server-side processing!)
     * 
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     * Global search value. To be applied to all columns which have searchable
     * as true.
     * 
     * @return
     */
    public Search getSearch() {
        return this.search;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public List<Order> getOrder() {
        return this.order;
    }

    private void setDraw(int draw) {
        this.draw = draw;
    }

    private void setStart(int start) {
        this.start = start;
    }

    private void setLength(int length) {
        this.length = length;
    }

    private void setSearch(Search search) {
        this.search = search;
    }


    public static Request build(Map<String, String[]> parametersMap) {

    	Request requestData = new Request();
        try {
            requestData.setDraw(Integer.parseInt((parametersMap.get("draw")[0])));
            requestData.setStart(Integer.parseInt((parametersMap.get("start")[0])));
            requestData.setLength(Integer.parseInt((parametersMap.get("length")[0])));

            requestData.setSearch(Search.build(parametersMap));

            int iOrder = 0;
            while (parametersMap.get("order[" + iOrder + "][column]") != null) {
                requestData.getOrder().add(Order.build(iOrder++, parametersMap));
            }
            
            int iColumn = 0;
            while (parametersMap.get("columns[" + iColumn + "][data]") != null) {
                requestData.getColumns().add(Column.build(iColumn++, parametersMap));
            }

        } catch (Exception e) {
            logger.error("Cannot build a DataTable Request instance", e);
            return null;
        }

        return requestData;

    }

    public DataTableRequest toDto() {
        DataTableRequest dto = new DataTableRequest();
        
        dto.setDraw(getDraw());
        dto.setStart(getStart());
        dto.setLength(getLength());
        dto.setSearch(getSearch().toDto());
        
        for (Order order : getOrder()) {
            dto.getOrder().add(order.toDto());
        }
        
        for (Column column : getColumns()) {
            dto.getColumns().add(column.toDto());
        }
        
        return dto;
        
    }
    
    @Override
    public String toString() {
        return "Request [\n" + "draw=" + draw + ",\n" + "start=" + start + ",\n" + "length=" + length + ",\n" + "search=" + search + ",\n" + "order=" + order + ",\n" + "columns=" + columns + "\n]";
    }

}
