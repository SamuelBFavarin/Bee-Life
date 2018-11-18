package model_agents.bee;

import static model_agents.controller.AbstractAgent.behaviour.SEARCH;
import java.io.File;
import java.util.List;
import model_agents.flower.Flower;
import model_agents.controller.AbstractAgent;
import static model_agents.controller.AbstractAgent.typeAgent.BEE;

/**
 *
 * @author Samuel
 */
public class Bee extends AbstractAgent{
       
    private String sex_last_flower = null;
   
    public Bee(int pos_x, int pos_y) {
        
        super.setHeight(30);
        super.setWidth(30);
        super.setPos_x(pos_x);
        super.setPos_y(pos_y);
        super.setSpeed(2);
        super.setDirection_y(-1);
        super.setAbstractState(SEARCH);
        super.setTpAgent(BEE);
        super.setImage(new File("src/img/bee.png"));
        
        if (Math.random()*100 > 50){
            super.setDirection_x(1);
        } else {
            super.setDirection_x(-1);
        }
    }
    
    @Override
    protected void setup(){
        
    }
    
    public boolean haveFlower(int posx, int posy, List<Flower> flowers){
        return false;
    };
    
    public boolean havePolen(Flower flower){
        return false;
    };
    
    public boolean isMasc(Flower flower){
        return false;
    };
    
    public boolean nearMascFlower(List<Flower> flowers){
      return false;  
    };

    public String getSex_last_flower() {
        return sex_last_flower;
    }

    public void setSex_last_flower(String sex_last_flower) {
        this.sex_last_flower = sex_last_flower;
    }

    @Override
    public void addBehaviour(behaviour tp) {
       
    }    
}
