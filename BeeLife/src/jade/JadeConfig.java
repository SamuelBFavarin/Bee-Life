package jade;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;

/**
 * @author Vinícius Machado
 * @since 07/11/2018
 */

public class JadeConfig {
    
    private final AgentContainer agent_controler;

    public JadeConfig(String host, String port) {
 
        //Configuraçoes manuais de criaçao, como porta, ip, etc
        jade.core.Runtime rt = jade.core.Runtime.instance();     
        rt.setCloseVM(true);
        Profile p = new ProfileImpl();   
        p.setParameter(Profile.MAIN_HOST, host);       
        p.setParameter(Profile.MAIN_PORT, port);      
        this.agent_controler = rt.createMainContainer(p);
    
    }

    public AgentContainer getAgent_controler() {
        return agent_controler;
    } 
    
    
}
