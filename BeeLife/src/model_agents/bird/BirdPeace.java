package model_agents.bird;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.environment.AbstractAgent.behaviour.GO_ATACK;
import static model_agents.environment.AbstractAgent.behaviour.PEACE;

/**
 *
 * @author Vinícius Machado
 */
public class BirdPeace extends TickerBehaviour{
    
    Bird bird;

    public BirdPeace(Agent a, long period) {
        super(a, period);
        this.bird = (Bird) a;
    }


    @Override
    protected void onTick() {
        if (bird.getAbstractState().equals(PEACE)){
            ACLMessage msg = bird.receive();
            if (msg != null){

                if (bird.getPos_x() > bird.getEnvironment().getWidth() - 75 || bird.getPos_x() < 20){
                    bird.setDirection_x(bird.getDirection_x() * - 1);
                    bird.setPos_x(bird.getPos_x() + (bird.getSpeed() +  (int) (Math.random()*10)) * bird.getDirection_x());
                }        
                bird.setPos_x(bird.getPos_x() + (bird.getSpeed() +  (int) (Math.random()*10)) * bird.getDirection_x());
                bird.setPos_y(bird.getPos_y()); 

                if (msg.getContent().equalsIgnoreCase("GO_ATACK")){
                    if (Math.random()*100 > 90){
                        bird.setAbstractState(GO_ATACK);
                    }
                }            
            }   
        }
    }
}
