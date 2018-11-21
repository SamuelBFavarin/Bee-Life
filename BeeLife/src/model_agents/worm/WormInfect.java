package model_agents.worm;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.environment.AbstractAgent.behaviour.GO_ATACK;
import static model_agents.environment.AbstractAgent.behaviour.INFECT;
import static model_agents.environment.AbstractAgent.behaviour.SEARCH;
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
                
                //Tempo de espera na flor "infectando"
                if(worm.getTime_waiting() >= 50){
                    worm.setTime_waiting(0);
                    worm.setAbstractState(SEARCH);
                }
                                
                if(msg.getContent().equalsIgnoreCase("GO_ATACK")){
                    worm.setAbstractState(GO_ATACK);
                }
                worm.setTime_waiting(worm.getTime_waiting() + 1);
            }
        }
    }
    
}
