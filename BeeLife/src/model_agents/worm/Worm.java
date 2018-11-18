package model_agents.worm;

import static model_agents.controller.AbstractAgent.behaviour.SEARCH;
import java.io.File;
import model_agents.controller.AbstractAgent;
import static model_agents.controller.AbstractAgent.typeAgent.WORM;

/**
 *
 * @author Samuel
 */
public class Worm extends AbstractAgent{
    
    public int percent_no_infect = 95;
    
    public Worm( int pos_x, int pos_y) {
        super.setHeight(30);
        super.setWidth(30);
        super.setPos_x(pos_x);
        super.setPos_y(pos_y);
        super.setAbstractState(SEARCH);
        super.setSpeed(1);
        super.setDirection_y(1);
        super.setTpAgent(WORM);
        super.setImage(new File("src/img/worm.png"));      
        if (Math.random()*100 > 50){
            super.setDirection_x(1);
        } else {
            super.setDirection_x(-1);
        }
    }
    
    @Override
    protected void setup(){
        
    }

    @Override
    public void addBehaviour(behaviour tp) {
        
    }
   
}
