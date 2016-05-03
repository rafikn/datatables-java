package nz.rafikn.datatables.dto;


public class DataTableOrder {
    private int column;
    private String direction;
    
    public int getColumn() {
        return column;
    }
    
    public void setColumn(int column) {
        this.column = column;
    }
    
    public String getDirection() {
        return direction;
    }
    
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "DataTableOrder [column=" + column + ", direction=" + direction + "]";
    }
    
}
