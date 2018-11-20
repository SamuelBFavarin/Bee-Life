package model_agents.worm;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.controller.AbstractAgent.behaviour.GO_ATACK;
import static model_agents.controller.AbstractAgent.behaviour.INFECT;
/**
 *
 * @author Vinícius Machado
 */
public class WormInfect extends TickerBehaviour{

    Worm worm;
    
    public WormInfect(Agent a, long period) {
        super(a, period);
        this.worm = (Worm) a;
    }

    @Override
    protected void onTick() {
        if (worm.getAbstractState().equals(INFECT)){
            ACLMessage msg = worm.receive();
            if (msg != null){    
                
                //Apenas limpa, nao tem motivo, só meio logico ele infectar e vazar
                if(worm.getCurrent_flower() != null){
                    worm.setCurrent_flower(null);
                }
                
                if(msg.getContent().equalsIgnoreCase("GO_ATACK")){
                    worm.setAbstractState(GO_ATACK);
                }
            }
        }
    }
    
}
