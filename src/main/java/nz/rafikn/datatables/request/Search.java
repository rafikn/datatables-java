package nz.rafikn.datatables.request;

import java.util.Map;

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
    
    public static Search build(Map<String, String[]> parametersMap) {
        Search search = new Search();
        search.setValue(parametersMap.get("search[value]")[0]);
        search.setRegex(Boolean.parseBoolean(parametersMap.get("search[regex]")[0]));
        return search;
    }
    
    public static Search buildForColumn(int column, Map<String, String[]> parametersMap) {
        Search search = new Search();
        search.setValue(parametersMap.get(new StringBuilder().append("columns[").append(column).append("][search][value]").toString())[0]);
        search.setRegex(Boolean.parseBoolean(parametersMap.get(new StringBuilder().append("columns[").append(column).append("][search][regex]").toString())[0]));
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
