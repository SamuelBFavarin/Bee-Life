package model_agents.environment;

import java.io.File;

/**
 *
 * @author vinicius
 */
public interface AbstractAgent{
   
    public enum typeAgent{
        BEE, BIRD, FLOWER, WORM, HIVE, ENVIRONMENT
    }
    public enum behaviour{
        PEACE, GO_ATACK, BACK_ATACK, SEARCH, POLLINATING, TO_HIVE, IN_HIVE, INFECT, DIE
    }
    
    public typeAgent getTpAgent();
    public void setTpAgent(AbstractAgent.typeAgent tpAgent);
    
    public void setAbstractState(behaviour state);
    public behaviour getAbstractState();
    
    public File getImage();
    public int getPos_x();
    public int getPos_y();
    public int getWidth();
    public int getHeight();
}
