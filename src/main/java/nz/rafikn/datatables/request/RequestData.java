package nz.rafikn.datatables.request;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RequestData {

    private int draw;
    private int start;
    private int length;

    private Search search;
    private List<Order> order = new ArrayList<Order>();
    private List<Column> columns = new ArrayList<Column>();

    private RequestData() {
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


    public static RequestData build(HttpServletRequest request) {

        RequestData requestData = new RequestData();
        try {
            requestData.setDraw(Integer.parseInt((request.getParameter("draw"))));
            requestData.setStart(Integer.parseInt((request.getParameter("start"))));
            requestData.setLength(Integer.parseInt((request.getParameter("length"))));

            requestData.setSearch(Search.build(request));

            int iOrder = 0;
            while (request.getParameter("order[" + iOrder + "][column]") != null) {
                requestData.getOrder().add(Order.build(iOrder++, request));
            }
            
            int iColumn = 0;
            while (request.getParameter("columns[" + iColumn + "][data]") != null) {
                requestData.getColumns().add(Column.build(iColumn++, request));
            }

        } catch (Exception e) {
            System.out.println("Cannot build a DataTable Request instance\n" + e.getStackTrace());
            return null;
        }

        return requestData;

    }

    @Override
    public String toString() {
        return "Request [\n" + "draw=" + draw + ",\n" + "start=" + start + ",\n" + "length=" + length + ",\n" + "search=" + search + ",\n" + "order=" + order + ",\n" + "columns=" + columns + "\n]";
    }

}
