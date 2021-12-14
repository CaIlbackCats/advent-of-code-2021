package five;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Coordinate {

    private static final int HASH_PRIME = 43;
    
    private int x;

    private int y;

    public Coordinate(Integer x, Integer y){
        this.x = x.intValue();
        this.y = y.intValue();
    }

    public static List<Coordinate> getInBetweenPoints(String [] rows, boolean checkDiagonal){
        Coordinate from = new Coordinate(Integer.parseInt(rows[0]),Integer.parseInt(rows[1]));
        Coordinate to = new Coordinate(Integer.parseInt(rows[2]),Integer.parseInt(rows[3]));
        int xDiff = Math.abs(from.getX()-to.getX());
        int yDiff = Math.abs(from.getY()-to.getY());
        if(from.getX()==to.getX() || from.getY()==to.getY()){
            return getInbetweenCoordinates(from, to,xDiff,yDiff);
        }
        if(checkDiagonal && xDiff==yDiff){
            return getInbetweenCoordinates(from, to, xDiff, yDiff);
        }
        return Collections.emptyList();
    }

    public List<Coordinate> getNeighbours(){
        List<Coordinate> coordinates= new ArrayList<>();
        Coordinate left = new Coordinate(this.x-1, this.y);
        Coordinate right = new Coordinate(this.x+1, this.y);
        Coordinate up = new Coordinate(this.x, this.y-1);
        Coordinate down = new Coordinate(this.x, this.y+1);
        coordinates.add(left);
        coordinates.add(right);
        coordinates.add(up);
        coordinates.add(down);

        return coordinates;
    }

    public Coordinate addCoordinate( Coordinate b){
        return new Coordinate(this.x+b.getX(), this.y+b.getY());       
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
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
        int hash = 7;
        hash = HASH_PRIME * hash  + this.x;
        hash = HASH_PRIME * hash + this.y;        
        return hash;
    }
        
    private static List<Coordinate> getInbetweenCoordinates(Coordinate from, Coordinate to, int xDiff,int yDiff){
        int xDirection = (xDiff==0)?0: (from.getX()-to.getX()>0)?-1:1;
        int yDirection =(yDiff==0)?0: (from.getY()-to.getY()>0)?-1:1;
        int step = (xDiff ==0)?yDiff:xDiff;
        List<Coordinate> coords = IntStream.range(0, step+1).mapToObj(growth -> from.addCoordinate(new Coordinate(growth*xDirection, growth*yDirection))).collect(Collectors.toList());
        return coords;
    }
}
