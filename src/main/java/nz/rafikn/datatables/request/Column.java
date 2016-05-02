package nz.rafikn.datatables.request;

import javax.servlet.http.HttpServletRequest;

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


    public static Column build(int index, HttpServletRequest request) {
        Column column = new Column();
        column.setData(request.getParameter(new StringBuilder().append("columns[").append(index).append("][data]").toString()));
        column.setName(request.getParameter(new StringBuilder().append("columns[").append(index).append("][name]").toString()));
        column.setSearchable(Boolean.parseBoolean(request.getParameter(new StringBuilder().append("columns[").append(index).append("][searchable]").toString())));
        column.setOrderable(Boolean.parseBoolean(request.getParameter(new StringBuilder().append("columns[").append(index).append("][orderable]").toString())));

        column.setSearch(Search.buildForColumn(index, request));

        return column;
    }

    @Override
    public String toString() {
        return "Column [data=" + data + ", name=" + name + ", searchable=" + searchable + ", orderable=" + orderable + ", search=" + search + "]";
    }

}
