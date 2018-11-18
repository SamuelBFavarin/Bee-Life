package model_agents.bird;

import model_agents.controller.AbstractAgent;
import model_agents.controller.AbstractAgent.behaviour;
import static model_agents.controller.AbstractAgent.behaviour.PEACE;
import static model_agents.controller.AbstractAgent.typeAgent.BIRD;
import java.io.File;

/**
 *
 * @author Samuel
 */

public class Bird extends AbstractAgent{
    
    public Bird() {
        super.setHeight(80);
        super.setWidth(80);
        super.setPos_x(0);
        super.setPos_y(0);
        super.setSpeed(10);
        super.setDirection_y(-1);
        super.setAbstractState(PEACE);
        super.setTpAgent(BIRD);
        super.setImage(new File("src/img/bird.gif"));
    }
    
    public Bird(int pos_x, int pos_y) {
        super.setHeight(80);
        super.setWidth(80);
        super.setPos_x(pos_x);
        super.setPos_y(pos_y);
        super.setSpeed(10);
        super.setDirection_y(-1);
        super.setAbstractState(PEACE);
        super.setTpAgent(BIRD);
        super.setImage(new File("src/img/bird.gif"));
        
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
        switch(tp){
            case ATACK: 
                setAbstractState(tp);
                addBehaviour(new BirdAtack(this));
                break;
            case PEACE: 
                setAbstractState(tp);
                addBehaviour(new BirdPeace(this));
                break;
        }
    }
    
}
