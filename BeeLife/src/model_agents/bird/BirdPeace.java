package model_agents.bird;

import jade.core.behaviours.Behaviour;

/**
 *
 * @author Vinicius
 */
public class BirdPeace extends Behaviour {
    
    private Bird bird;

    public BirdPeace(Bird bird) {
        this.bird = bird;
    }    

    @Override
    public void action() {
        System.out.println("Bird: " + this.bird.getAID().getName()+ " | Voando plenamente!");
    }

    @Override
    public boolean done() {
        return this.bird.getStateBird().equals("atack");
    }
    
}
