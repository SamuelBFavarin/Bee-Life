package model_agents.bird;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import static model_agents.controller.AbstractAgent.behaviour.GO_ATACK;
import static model_agents.controller.AbstractAgent.behaviour.BACK_ATACK;

/**
 *
 * @author Vinícius Machado
 */
public class BirdGoAtack extends TickerBehaviour {
    
    private Bird bird;

    public BirdGoAtack(Agent a, long period) {
        super(a, period);
        this.bird = (Bird) a;
    }

    @Override
    protected void onTick() {
         if (bird.getAbstractState().equals(GO_ATACK)){
            
             // alter X
            if (bird.getPos_x() > bird.getEnvironment().getWidth() -50 || bird.getPos_x() < 50){
                bird.setDirection_x(bird.getDirection_x() * - 1);
            }        

            bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());

            if (bird.getPos_x() > bird.getEnvironment().getWidth()|| bird.getPos_x() < 0){
                 bird.setDirection_x(bird.getDirection_x() * - 1);
            }   
            
            bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirection_x());
            
            // alter Y
            bird.setPos_y(bird.getPos_y() + bird.getSpeed() * 2);
        
            if (bird.getPos_y() >= bird.getEnvironment().getHeight() - 200){
               
                bird.setAbstractState(BACK_ATACK);
            }            
             sendKillBee(bird.getPos_x(), bird.getPos_y());
        }                
    }
    
    public boolean sendKillBee(int x, int y){
        ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
        msg.setLanguage("KILL_BEE");
        String content = String.valueOf(x) + "," + String.valueOf(y);
        msg.setContent(content);
        msg.addReceiver(new AID(bird.getEnvironment().getLocalName(), AID.ISLOCALNAME));
        bird.send(msg);
        return true;
    }

    
}
