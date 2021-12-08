package six;

public class BreedingCycle {

    private int spawn;

    private boolean breedable;


    public BreedingCycle(){
        this.spawn=8;
        this.breedable = false;
    }

    public BreedingCycle(int spawn){
        this.spawn=spawn;
        this.breedable = true;
    }

    public void breed(){
        if(this.breedable){
            int newSpawn = this.spawn--;
            if(newSpawn==0){
                this.spawn= 6;
            }
        }else{
            this.breedable = true;
        }

    }

    public int getSpawn(){
        return this.spawn;
    }




    
}
