package ten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.AdventSolver;
import utils.Constants;
import utils.DailyFilePath;

public class SyntaxScoring extends AdventSolver<List<String>,Long> {

    protected SyntaxScoring() {
        super(DailyFilePath.TEN, inputs -> inputs);
    }

    @Override
    public Long findPartOneResult() {
        return getSyntaxErrorScore(false);
    }

    @Override
    public Long findPartTwoResult() {
        return getSyntaxErrorScore(true);
    }

    private long getSyntaxErrorScore(boolean autoCorrect){
        long score = 0;
        List<Long> correcScoreList = new ArrayList<>();
        for (int i = 0; i < this.processedInput.size(); i++) {
            List<String> chunks = new ArrayList<>();
            long correctScore = 0;
            String [] chunkChars = this.processedInput.get(i).split("");
            boolean corrupted = false;
            for (int j = 0; j < chunkChars.length && !corrupted; j++) {
                if(chunkChars[j].equals("(") || chunkChars[j].equals("[") || chunkChars[j].equals("{") || chunkChars[j].equals("<")){
                    chunks.add(chunkChars[j]);
                }else if((chunkChars[j].equals(")") && chunks.get(chunks.size()-1).equals("(")) 
                        || (chunkChars[j].equals("]") && chunks.get(chunks.size()-1).equals("["))
                        || (chunkChars[j].equals("}") && chunks.get(chunks.size()-1).equals("{"))
                        || (chunkChars[j].equals(">") && chunks.get(chunks.size()-1).equals("<"))){
                    chunks.remove(chunks.size()-1);
                }
                else{
                    score += Constants.ILLEGAL_CHARACTER_SCORE_MAP.get(chunkChars[j]);
                    corrupted=true;
                }
                if(autoCorrect && j ==chunkChars.length-1 && !corrupted){
                    int currentScore = 0;
                    for (int k = chunks.size()-1; k >= 0; k--) {
                        if(chunks.get(k).equals("(")){
                            currentScore = Constants.CORRECT_SCORE_MAP.get(")");
                        }else if(chunks.get(k).equals("[")){
                            currentScore = Constants.CORRECT_SCORE_MAP.get("]");
                        }else if(chunks.get(k).equals("{")){
                            currentScore = Constants.CORRECT_SCORE_MAP.get("}");
                        }else if(chunks.get(k).equals("<")){
                            currentScore = Constants.CORRECT_SCORE_MAP.get(">");
                        }
                        correctScore = (correctScore*5) + currentScore;
                    }
                    correcScoreList.add(correctScore);
                }
            }
        }
        if(autoCorrect){
            Collections.sort(correcScoreList);
            score = correcScoreList.get((correcScoreList.size()/2));
        }
        return score;
    }
    
}
