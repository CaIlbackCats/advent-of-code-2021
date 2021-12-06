package four;

import java.util.List;
import java.util.Map;

public class Bingo {
    private List<Integer> drawnNumbers;

    private Map<Integer,List<NumberLocation>> bingoMap;

    public void setDrawnNumbers(List<Integer> drawnNumbers){
        this.drawnNumbers = drawnNumbers;
    }

    public void setBingoMap(Map<Integer,List<NumberLocation>> bingoMap){
        this.bingoMap = bingoMap;
    }

    public List<Integer> getDrawnNumbers(){
        return this.drawnNumbers;
    }

    public Map<Integer,List<NumberLocation>> getBingoMap(){
        return this.bingoMap;
    }
}
