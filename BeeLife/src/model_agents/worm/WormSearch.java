package model_agents.worm;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.environment.AbstractAgent.behaviour.INFECT;
import static model_agents.environment.AbstractAgent.behaviour.SEARCH;
import model_agents.flower.Flower;

/**
 *
 * @author Vinícius Machado
 */
public class WormSearch extends TickerBehaviour{
    
    Worm worm;
    
    public WormSearch(Agent a, long period) {
        super(a, period);
        this.worm = (Worm) a;
    }

    @Override
    protected void onTick() {
        if (worm.getAbstractState().equals(SEARCH)){
            ACLMessage msg = worm.receive();
            if (msg != null){
                if (worm.getPos_x() > worm.getEnvironment().getWidth() || worm.getPos_x() < 0){
                    worm.setDirection_x(worm.getDirection_x() * - 1);
                }        
                worm.setPos_x(worm.getPos_x() + (worm.getSpeed() + (int) (Math.random()*10)) * worm.getDirection_x());
                if(msg.getContent().equalsIgnoreCase("INFECT")){
                    if ( Math.random() * 100 > worm.percent_no_infect ){
                        worm.setAbstractState(INFECT);
                    }
                }  
            }
        }
    }
}
