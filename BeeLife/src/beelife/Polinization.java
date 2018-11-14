package beelife;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import jade.core.Runtime;

/**
 * @author Vinícius Machado
 * @since 31/10/2018
 */
public class Polinization {
    
    public void polinize() {
                
        //Configuraçoes manuais de criaçao, como porta, ip, etc
        Runtime rt = Runtime.instance();     
        rt.setCloseVM(true);
        Profile p = new ProfileImpl();   
        p.setParameter(Profile.MAIN_HOST, "127.0.0.1");       
        p.setParameter(Profile.MAIN_PORT, "1199");      
        AgentContainer ac = rt.createMainContainer(p);
        
        //Criaçao do agente da Abelha
        try {
            AgentController agentAbelha = ac.createNewAgent ("Abelha", "ambiente.Abelha", null);
            agentAbelha.start();
        } catch (StaleProxyException ex) {
            System.out.println("Erro ao criar/startar o agente Abelha: " + ex.getMessage());
        }
        
        //Criaçao do agente da Predador
        try {
            AgentController agentPredador = ac.createNewAgent ("Predador", "ambiente.Predador", null);
            agentPredador.start();
        } catch (StaleProxyException ex) {
            System.out.println("Erro ao criar/startar o agente Predador: " + ex.getMessage());
        }
        
        //Criaçao do jade tools para visualizar os agentes        
        try {
            AgentController rma = ac.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
        } catch(StaleProxyException e) {
            System.out.println("Erro ao criar/startar o agente do Jade Tools: " + e.getMessage());
        }
        
        
    }
    
}
