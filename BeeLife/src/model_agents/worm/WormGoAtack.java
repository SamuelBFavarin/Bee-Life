package model_agents.worm;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.environment.AbstractAgent.behaviour.GO_ATACK;
import static model_agents.environment.AbstractAgent.behaviour.INFECT;

/**
 *
 * @author Vinícius Machado
 */
public class WormGoAtack extends TickerBehaviour{

    Worm worm;
    
    public WormGoAtack(Agent a, long period) {
        super(a, period);
        this.worm = (Worm) a;
    }

    @Override
    protected void onTick() {
        if (worm.getAbstractState().equals(GO_ATACK)){
            if (worm.getPos_y() > worm.getEnvironment().getHeight() - 100 || 
                worm.getPos_y() < worm.getEnvironment().getHeight() - 170){
                
                worm.setDirection_y(worm.getDirection_y() * -1);
                sendKillBee(worm.getPos_x(), worm.getPos_y());
                if (worm.getPos_y() > worm.getEnvironment().getHeight() - 100){
                    worm.setAbstractState(INFECT);
                }
            }
        worm.setPos_y(worm.getPos_y() - worm.getSpeed()*5 * worm.getDirection_y());
        }
    }
    
    private boolean sendKillBee(int x, int y){
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.setOntology("WORM_ONTOLOGY");
        msg.setLanguage("KILL_BEE");       
        String content = String.valueOf(x) + "," + String.valueOf(y);
        msg.setContent(content);
        msg.addReceiver(new AID(worm.getEnvironment().getLocalName(), AID.ISLOCALNAME));
        worm.send(msg);
        return true;
    }
    
}
