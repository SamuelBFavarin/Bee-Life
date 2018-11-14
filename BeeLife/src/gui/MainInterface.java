
package gui;
import jade.JadeConfig;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Samuel
 */
public class MainInterface {
    
    String status = "";

    public MainInterface() throws HeadlessException, IOException {
        
        //configuracoes do ambiente jade
        JadeConfig jade_config = new JadeConfig("127.0.0.1", "1199");
        //Criaçao do jade tools para visualizar os agentes        
        try {
            AgentController rma = jade_config.getAgent_controler().createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
        } catch(StaleProxyException e) {
            System.out.println("Erro ao criar/startar o agente do Jade Tools: " + e.getMessage());
        }
        
        // configuração gráfica
        JFrame  frame  = new JFrame("Bee Life");
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ConfiguratePanel configuratePanel = new ConfiguratePanel();
        ScenarioPanel scenarioPanel = new ScenarioPanel(jade_config.getAgent_controler());
        frame.add(configuratePanel, BorderLayout.WEST);
        frame.add(scenarioPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        // main loop 
        while (true){
            boolean alter_status = this.altetStatus(configuratePanel.getStatus());
            
            if (alter_status){
                if (this.status == "start"){
                    scenarioPanel.startSimulation(
                        configuratePanel.getInputBees(),
                        configuratePanel.getInputFlower(),
                        configuratePanel.getInputBirds(),
                        configuratePanel.getInputWorm()
                    );
                }
                if (this.status == "stop"){
                    scenarioPanel.stopSimulation();
                }
            }
           
        }
    }
    
    private boolean altetStatus (String status_now){
        System.out.print("");
        if (status_now != this.status){
            this.status = status_now;
            return true;
        }
        this.status = status_now;
        return false;
    }
    
}
