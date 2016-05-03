package nz.rafikn.datatables.dto;

import java.util.ArrayList;
import java.util.List;

public class DataTableRequest {

    private int draw;
    private int start;
    private int length;

    private DataTableSearch search;
    private List<DataTableOrder> order = new ArrayList<DataTableOrder>();
    private List<DataTableColumn> columns = new ArrayList<DataTableColumn>();
    
    public int getDraw() {
        return draw;
    }
    
    public void setDraw(int draw) {
        this.draw = draw;
    }
    
    public int getStart() {
        return start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public DataTableSearch getSearch() {
        return search;
    }
    
    public void setSearch(DataTableSearch search) {
        this.search = search;
    }
    
    public List<DataTableOrder> getOrder() {
        return order;
    }
    
    public void setOrder(List<DataTableOrder> order) {
        this.order = order;
    }
    
    public List<DataTableColumn> getColumns() {
        return columns;
    }
    
    public void setColumns(List<DataTableColumn> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "DataTableRequest [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search + ", order=" + order + ", columns=" + columns + "]";
    }
    
    
    
}
