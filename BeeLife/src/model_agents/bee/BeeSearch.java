package model_agents.bee;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.environment.AbstractAgent.behaviour.POLLINATING;
import static model_agents.environment.AbstractAgent.behaviour.SEARCH;

/**
 *
 * @author Vinicius
 */
public class BeeSearch extends TickerBehaviour {
    
    Bee bee;

    public BeeSearch(Agent a, long period) {
        super(a, period);
        this.bee = (Bee) a;
    }

    @Override
    protected void onTick() {
        if (bee.getAbstractState().equals(SEARCH)){
            ACLMessage msg = bee.receive();
            if (msg != null){
                if (bee.getPos_x() > bee.getEnvironment().getWidth() || bee.getPos_x() < 0){
                    bee.setDirection_x(bee.getDirection_x() * -1);
                }
                bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirection_x()); 
                if (bee.getPos_y() > bee.getEnvironment().getHeight() - 50 || bee.getPos_y() < 300 ){
                    bee.setDirection_y(bee.getDirection_y() * -1);
                }
                bee.setPos_y(bee.getPos_y() - bee.getSpeed() * bee.getDirection_y());    
                if(msg.getContent().equalsIgnoreCase("POLLINATING")){
                    bee.setAbstractState(POLLINATING);
                }
            }
        }
    }
    
}
