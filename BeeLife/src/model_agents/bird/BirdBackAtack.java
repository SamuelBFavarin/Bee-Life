package model_agents.bird;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import static model_agents.controller.AbstractAgent.behaviour.BACK_ATACK;
import static model_agents.controller.AbstractAgent.behaviour.PEACE;

/**
 *
 * @author Vinícius Machado
 */
public class BirdBackAtack extends TickerBehaviour {
    
    Bird bird;

    public BirdBackAtack(Agent a, long period) {
        super(a, period);
        this.bird = (Bird) a;
    }

    @Override
    protected void onTick() {
         if (bird.getAbstractState().equals(BACK_ATACK)){
            // alter X
            if (bird.getPos_x() > bird.getEnvironment().getWidth() - 75 || bird.getPos_x() < 20){
                bird.setDirection_x(bird.getDirection_x() * - 1);
                bird.setPos_x(bird.getPos_x() + (bird.getSpeed() +  (int) (Math.random()*10)) * bird.getDirection_x());
            }        

            
            bird.setPos_x(bird.getPos_x() + (bird.getSpeed() +  (int) (Math.random()*10)) * bird.getDirection_x());

            
            // alter Y
            bird.setPos_y(bird.getPos_y() - bird.getSpeed() * 2);
            
            if (bird.getPos_y() < 30){
                bird.setAbstractState(PEACE);
            }
        }                
    }

    
}
