package model_agents.bird;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import static model_agents.controller.AbstractAgent.behaviour.ATACK;
import static model_agents.controller.AbstractAgent.behaviour.PEACE;

/**
 *
 * @author Vinícius Machado
 */
public class BirdAtack extends Behaviour {
    
    private Bird bird;

    public BirdAtack(Agent a) {
        super(a);
        this.bird = (Bird) a;
    }

    @Override
    public void action() {
        
        bird.setPos_y(bird.getPos_y() - bird.getSpeed()*5 * bird.getDirection_y());  
        
        if (bird.getPos_x() > bird.getEnvironment().getWidth() || bird.getPos_x() < 0){
            bird.setDirection_x(bird.getDirection_x() * - 1);
        }        
        
        bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());
               
        if (bird.getPos_x() > bird.getEnvironment().getWidth()|| bird.getPos_x() < 0){
             bird.setDirection_x(bird.getDirection_x() * - 1);
        }        
        
        bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirection_x());
        
        if (bird.getPos_y() > bird.getEnvironment().getHeight() - 100 || bird.getPos_y() < 10){
            bird.setDirection_y(bird.getDirection_y()*-1);                   
            // altera o estado do pássaro para pleno
            if (bird.getPos_y() < 10){
                bird.removeBehaviour(this);
                bird.setAbstractState(PEACE);
                bird.addBehaviour(new BirdPeace(bird));
                
            }
        }
        
        bird.setPos_y(bird.getPos_y() - bird.getSpeed()*5 * bird.getDirection_y());          
        //killBee(bird.getPos_x(), bird.getPos_y()); 
    }

    @Override
    public boolean done() {
        return (bird.getAbstractState().equals(PEACE));
    }

    
}
