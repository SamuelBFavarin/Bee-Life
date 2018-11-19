package model_agents.bird;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.controller.AbstractAgent.behaviour.GO_ATACK;
import static model_agents.controller.AbstractAgent.behaviour.PEACE;

/**
 *
 * @author Vinícius Machado
 */
public class BirdPeace extends Behaviour{
    
    private Bird bird;

    public BirdPeace(Agent a) {
        super(a);
        this.bird = (Bird) a;
    }

    @Override
    public void action() {
        
        if (bird.getAbstractState().equals(PEACE)){
            ACLMessage msg = myAgent.receive();
            if (msg != null){

                if (bird.getPos_x() > bird.getEnvironment().getWidth() || bird.getPos_x() < 0){
                    bird.setDirection_x(bird.getDirection_x() * - 1);
                }        
                bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());
                bird.setPos_y(bird.getPos_y()); 

                if (msg.getContent().equalsIgnoreCase("GO_ATACK")){
                    if (Math.random()*100 > 90){
                        bird.setAbstractState(GO_ATACK);
                    }
                }            
            }   
        }
    }

    @Override
    public boolean done() {
        return false; //(bird.getAbstractState().equals(ATACK));
    }

}
