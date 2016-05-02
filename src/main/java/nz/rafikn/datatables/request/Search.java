package nz.rafikn.datatables.request;

import javax.servlet.http.HttpServletRequest;

import nz.rafikn.datatables.dto.DataTableSearch;

public class Search {
    
    private String value;
    private boolean regex;
    
    private Search() {}
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public boolean isRegex() {
        return regex;
    }
    
    public void setRegex(boolean regex) {
        this.regex = regex;
    }
    
    public static Search build(HttpServletRequest request) {
        Search search = new Search();
        search.setValue(request.getParameter("search[value]"));
        search.setRegex(Boolean.parseBoolean(request.getParameter("search[regex]")));
        return search;
    }
    
    public static Search buildForColumn(int column, HttpServletRequest request) {
        Search search = new Search();
        search.setValue(request.getParameter(new StringBuilder().append("columns[").append(column).append("][search][value]").toString()));
        search.setRegex(Boolean.parseBoolean(new StringBuilder().append("columns[").append(column).append("][search][regex]").toString()));
        return search;
    }
    
    public DataTableSearch toDto() {
        DataTableSearch dto = new DataTableSearch();
        dto.setValue(this.getValue());
        dto.setRegex(this.isRegex());
        return dto;
    }

    @Override
    public String toString() {
        return "Search [value=" + value + ", regex=" + regex + "]";
    }
    
    
    
}
