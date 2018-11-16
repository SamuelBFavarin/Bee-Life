package model_agents.bird;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author Vinicius
 */
public class BirdAtack extends Behaviour {

    private Bird bird;
    
    public BirdAtack(Bird bird) {
        this.bird = bird;    
        this.bird.setState("atack");
    }
    
    @Override
    public void action() {
        System.out.println("Bird: " + this.bird.getAID().getName() + " | Atacando!");       
    }

    @Override
    public boolean done() {
       return this.bird.getStateBird().equals("pleno");
    }
    
}
