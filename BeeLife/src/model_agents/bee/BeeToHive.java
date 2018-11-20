package model_agents.bee;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.controller.AbstractAgent.behaviour.IN_HIVE;
import static model_agents.controller.AbstractAgent.behaviour.TO_HIVE;

/**
 *
 * @author Vinicius
 */
public class BeeToHive extends TickerBehaviour {

    private Bee bee;
    
    public BeeToHive(Agent a, long period) {
        super(a, period);
        this.bee = (Bee) a;
    }

    @Override
    protected void onTick() {
        // voltando pra colmeia movimentação
        if (bee.getAbstractState() == TO_HIVE){
            ACLMessage msg = bee.receive();
            if (msg != null){
                if (bee.getEnvironment().getHive().getPos_x() <= bee.getPos_x()){
                    bee.setPos_x(bee.getPos_x() - bee.getSpeed() *2);
                }
                if (bee.getEnvironment().getHive().getPos_x() >= bee.getPos_x()){
                    bee.setPos_x(bee.getPos_x() + bee.getSpeed() *2);
                }
                if (bee.getEnvironment().getHive().getPos_y() <= bee.getPos_y()){
                    bee.setPos_y(bee.getPos_y() - bee.getSpeed() *2);
                }
                if (bee.getEnvironment().getHive().getPos_x() >= bee.getPos_y()){
                    bee.setPos_y(bee.getPos_y() + bee.getSpeed() *2);
                }
                if (msg.getContent().equalsIgnoreCase("IN_HIVE")){
                    bee.setAbstractState(IN_HIVE);
                }
            }
        }    
    }
    
}