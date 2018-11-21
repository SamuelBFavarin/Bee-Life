package model_agents.bee;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.environment.AbstractAgent.behaviour.POLLINATING;
import static model_agents.environment.AbstractAgent.behaviour.TO_HIVE;

/**
 *
 * @author Vinicius
 */
public class BeePollinate extends TickerBehaviour {
    
    Bee bee;

    public BeePollinate(Agent a, long period) {
        super(a, period);
        this.bee = (Bee) a;
    }

    @Override
    protected void onTick() {
        if(bee.getAbstractState().equals(POLLINATING)){
            if (bee.getLast_flower() != null && bee.getCurrent_flower() != null){
                if (!bee.getLast_flower().getSex().equals(bee.getCurrent_flower().getSex())){
                     sendBordNewFlower();
                }
            }
            bee.setLast_flower(bee.getCurrent_flower());
            bee.setAbstractState(TO_HIVE);
        }
    }
    
    private boolean sendBordNewFlower(){
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.setLanguage("BEE_LANGUAGE");
        msg.setOntology("BEE_ONTOLOGY");
        msg.setContent("BORN_NEW_FLOWER");
        msg.addReceiver(new AID(bee.getEnvironment().getLocalName(), AID.ISLOCALNAME));
        bee.send(msg);
        return true;
    }
    
}
