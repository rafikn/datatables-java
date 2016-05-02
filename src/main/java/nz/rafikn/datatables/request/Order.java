package nz.rafikn.datatables.request;

import javax.servlet.http.HttpServletRequest;

import nz.rafikn.datatables.dto.DataTableOrder;

public class Order {

    private int column;
    private Direction direction;

    /**
     * Column to which ordering should be applied. This is an index reference to
     * the columns array of information that is also submitted to the server.
     * 
     * @return
     */
    public int getColumn() {
        return column;
    }

    /**
     * Ordering direction for this column. It will be asc or desc to indicate
     * ascending ordering or descending ordering, respectively.
     * 
     * @return
     */
    public Direction getDirection() {
        return direction;
    }

    
    protected void setColumn(int column) {
        this.column = column;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    
    public static enum Direction {
        ASC("asc"), DESC("desc");

        private String value;

        private Direction(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static Direction parseString(String value) {
            switch (value) {
            case "asc":
                return ASC;
            case "desc":
                return DESC;
            default:
                return null;
            }

        }
    }
    
    
    public static Order build(int index, HttpServletRequest request) {
        Order order = new Order();
        order.setColumn(Integer.parseInt(request.getParameter(new StringBuilder().append("order[").append(index).append("][column]").toString())));
        order.setDirection(Direction.parseString(request.getParameter(new StringBuilder().append("order[").append(index).append("][dir]").toString())));
        return order;
    }
    
    public DataTableOrder toDto() {
        DataTableOrder dto = new DataTableOrder();
        dto.setColumn(this.getColumn());
        dto.setDirection(this.getDirection().getValue());
        return dto;
    }


    @Override
    public String toString() {
        return "Order [column=" + column + ", direction=" + direction + "]";
    }
    
    

}
