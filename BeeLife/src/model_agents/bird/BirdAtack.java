package model_agents.bird;

import static model_agents.controller.AbstractAgent.behaviour.PEACE;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author Vinicius
 */
public class BirdAtack extends Behaviour {

    private final Bird bird;
    
    public BirdAtack(Bird bird) {
        this.bird = bird;    
    }
    
    @Override
    public void action() {
        //System.out.println("Bird: " + this.bird.getAID().getName() + " | Atacando!");  
        //bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirection_x());
        //bird.setPos_y(bird.getPos_y() - bird.getSpeed()*5 * bird.getDirection_y());  
        //bird.setPos_y(bird.getPos_y());
    }

    @Override
    public boolean done() {
       return (this.bird.getAbstractState() == PEACE);
    }
    
}
