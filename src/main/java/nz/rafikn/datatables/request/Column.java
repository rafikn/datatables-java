package nz.rafikn.datatables.request;

import java.util.Map;

import nz.rafikn.datatables.dto.DataTableColumn;

public class Column {

    private String data;
    private String name;
    private boolean searchable;
    private boolean orderable;
    private Search search;

    private Column() {}
    
    /**
     * Column's data source, as defined by columns.data.
     * 
     * @return
     */
    public String getData() {
        return data;
    }

    protected void setData(String data) {
        this.data = data;
    }

    /**
     * Column's name, as defined by columns.name.
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Flag to indicate if this column is searchable (true) or not (false). This
     * is controlled by columns.searchable.
     * 
     * @param name
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Flag to indicate if this column is orderable (true) or not (false). This
     * is controlled by columns.orderable.
     * 
     * @return
     */
    public boolean isSearchable() {
        return searchable;
    }

    protected void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    /**
     * Search value to apply to this specific column.
     * 
     * @return
     */
    public boolean isOrderable() {
        return orderable;
    }

    protected void setOrderable(boolean orderable) {
        this.orderable = orderable;
    }

    /**
     * Flag to indicate if the search term for this column should be treated as
     * regular expression (true) or not (false). As with global search, normally
     * server-side processing scripts will not perform regular expression
     * searching for performance reasons on large data sets, but it is
     * technically possible and at the discretion of your script.
     * 
     * @return
     */
    public Search getSearch() {
        return search;
    }

    protected void setSearch(Search search) {
        this.search = search;
    }


    public static Column build(int index, Map<String, String[]> parametersMap) {
        Column column = new Column();
        column.setData(parametersMap.get(new StringBuilder().append("columns[").append(index).append("][data]").toString())[0]);
        column.setName(parametersMap.get(new StringBuilder().append("columns[").append(index).append("][name]").toString())[0]);
        column.setSearchable(Boolean.parseBoolean(parametersMap.get(new StringBuilder().append("columns[").append(index).append("][searchable]").toString())[0]));
        column.setOrderable(Boolean.parseBoolean(parametersMap.get(new StringBuilder().append("columns[").append(index).append("][orderable]").toString())[0]));

        column.setSearch(Search.buildForColumn(index, parametersMap));

        return column;
    }
    
    public DataTableColumn toDto() {
        DataTableColumn dto = new DataTableColumn();
        
        dto.setData(getData());
        dto.setName(getName());
        dto.setSearch(getSearch().toDto());
        dto.setOrderable(isOrderable());
        dto.setSearchable(isSearchable());
        return dto;
    }

    @Override
    public String toString() {
        return "Column [data=" + data + ", name=" + name + ", searchable=" + searchable + ", orderable=" + orderable + ", search=" + search + "]";
    }

}
