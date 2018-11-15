package behaviour;

import jade.core.behaviours.Behaviour;
import model_agents.Bird;

/**
 *
 * @author Samuel
 */
public class BirdAtack extends Behaviour {

    private Bird bird;
    
    public BirdAtack(Bird bird) {
        this.bird = bird;
    }
    
    @Override
    public void action() {
        System.out.println("atacando loucamente");       
    }

    @Override
    public boolean done() {
        return this.bird.getStateBird().equals("pleno");
    }
    
}
