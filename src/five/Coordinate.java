package five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate {
    
    private Integer x;

    private Integer y;

    public Coordinate(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public static List<Coordinate> getInBetweenPoints(String [] rows){
        Coordinate from = new Coordinate(Integer.parseInt(rows[0]),Integer.parseInt(rows[1]));
        Coordinate to = new Coordinate(Integer.parseInt(rows[2]),Integer.parseInt(rows[3]));
        if(from.getX()==to.getX()){
            return getInbetweenByX(from, to);
        }
        if(from.getY()==to.getY()){
            return getInbetweenByY(from, to);
        }
        return Collections.emptyList();
    }

    public Integer getX(){
        return this.x;
    }
    public Integer getY(){
        return this.y;
    }

    @Override
    public boolean equals(Object o){
        if(o!=null && o instanceof Coordinate){
            Coordinate other = (Coordinate) o;
            if(this.x == other.getX() && this.y == other.getY()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        //TODO
        return 1;
    }
        
    private static List<Coordinate> getInbetweenByX(Coordinate from, Coordinate to){
        int startRange =0;
        int endRange = 0;
        if(from.getY()>to.getY()){
            endRange = from.getY();
            startRange = to.getY();
        }else{
            endRange = to.getY();
            startRange = from.getY();
        }
       return IntStream.range(startRange, endRange+1).mapToObj(yCoord -> new Coordinate(from.getX(), yCoord)).collect(Collectors.toList());
    }
    private static List<Coordinate> getInbetweenByY(Coordinate from,Coordinate to){
            int startRange =0;
            int endRange = 0;
            if(from.getX()>to.getX()){
                endRange = from.getX();
                startRange = to.getX();
            }else{
                endRange = to.getX();
                startRange = from.getX();
            }
           return IntStream.range(startRange, endRange+1).mapToObj(xCoord -> new Coordinate(xCoord, from.getY())).collect(Collectors.toList());
    }
}
