package behaviour;

import jade.core.behaviours.Behaviour;
import model_agents.Bird;

/**
 *
 * @author Samuel
 */
public class BirdPeace extends Behaviour {
    
    private Bird bird;

    public BirdPeace(Bird bird) {
        this.bird = bird;
    }    

    @Override
    public void action() {
        System.out.println("voando plenamente");
    }

    @Override
    public boolean done() {
        return this.bird.getStateBird().equals("atack");
    }
    
}
