package model_agents.bird;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.controller.AbstractAgent.behaviour.BACK_ATACK;
import static model_agents.controller.AbstractAgent.behaviour.PEACE;

/**
 *
 * @author Vinícius Machado
 */
public class BirdBackAtack extends TickerBehaviour {
    
    private Bird bird;

    public BirdBackAtack(Agent a, long period) {
        super(a, period);
        this.bird = (Bird) a;
    }

    @Override
    protected void onTick() {
         if (bird.getAbstractState().equals(BACK_ATACK)){
            // alter X
            if (bird.getPos_x() > bird.getEnvironment().getWidth() - 30 || bird.getPos_x() < 30){
                bird.setDirection_x(bird.getDirection_x() * - 1);
            }        

            bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());

            if (bird.getPos_x() > bird.getEnvironment().getWidth()|| bird.getPos_x() < 0){
                 bird.setDirection_x(bird.getDirection_x() * - 1);
            }   
            
            bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirection_x());
            
            // alter Y
            bird.setPos_y(bird.getPos_y() - bird.getSpeed() * 2);
            
            if (bird.getPos_y() < 30){
                bird.setAbstractState(PEACE);
            }
        }                
        //killBee(bird.getPos_x(), bird.getPos_y()); 
    }

    
}
