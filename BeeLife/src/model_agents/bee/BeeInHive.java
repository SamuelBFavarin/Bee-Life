package model_agents.bee;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import static model_agents.controller.AbstractAgent.behaviour.IN_HIVE;
import static model_agents.controller.AbstractAgent.behaviour.SEARCH;

/**
 *
 * @author Vinicius
 */
public class BeeInHive extends TickerBehaviour {

    private Bee bee;
    
    public BeeInHive(Agent a, long period) {
        super(a, period);
        this.bee = (Bee) a;
    }

    @Override
    protected void onTick() {
        if (bee.getAbstractState().equals(IN_HIVE)){
            bee.getEnvironment().getHive().hive_nectar += 2;
            bee.setPos_x(bee.getPos_x() + bee.getSpeed());
            bee.setPos_y(bee.getPos_y() + bee.getSpeed());
            bee.setDirection_y(1);
            bee.setAbstractState(SEARCH);
        }
    }
    
}
