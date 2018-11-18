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
        System.out.println("Bird: " + this.bird.getAID().getName() + " | Atacando!");       
    }

    @Override
    public boolean done() {
       return (this.bird.getAbstractState() == PEACE);
    }
    
}
