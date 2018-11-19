package model_agents.bird;

import static model_agents.controller.AbstractAgent.behaviour.ATACK;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author Vinicius
 */
public class BirdPeace extends Behaviour {
    
    private final Bird bird;

    public BirdPeace(Bird bird) {
        this.bird = bird;
    }    

    @Override
    public void action() {                      
        //bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());
        //System.out.println("Bird: " + this.bird.getAID().getName()+ " | Voando plenamente!");
    }

    @Override
    public boolean done() {
        return (this.bird.getAbstractState() == ATACK);
    }
    
}
