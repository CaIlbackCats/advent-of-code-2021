package four;

public class NumberLocation {
    
    private Integer tableNumber;

    private Integer number;

    private Integer x;

    private Integer y;

    public NumberLocation(Integer tableNumber,Integer number, Integer x, Integer y){
        this.tableNumber = tableNumber;
        this.number = number;
        this.x = x;
        this.y = y;
    }

    public Integer getNumber(){
        return this.number;
    }

    public Integer getTableNumber(){
        return this.tableNumber;
    }
    public Integer getX(){
        return this.x;
    }

    public Integer getY(){
        return this.y;
    }
}
