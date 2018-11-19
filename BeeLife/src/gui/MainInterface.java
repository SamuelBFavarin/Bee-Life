
package gui;
import gui.ConfiguratePanel.Status;
import static gui.ConfiguratePanel.Status.START;
import static gui.ConfiguratePanel.Status.STOP;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author Samuel
 */
public class MainInterface {
    
    Status status;

    public MainInterface() throws HeadlessException, IOException {
               
        // configuração gráfica
        JFrame  frame  = new JFrame("Bee Life");
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ConfiguratePanel configuratePanel = new ConfiguratePanel();
        ScenarioPanel scenarioPanel = new ScenarioPanel();
        frame.add(configuratePanel, BorderLayout.WEST);
        frame.add(scenarioPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        
        // main loop 
        while (true){
            boolean alter_status = this.altetStatus(configuratePanel.getStatus());
            
            if (alter_status){
                if (this.status == START){
                    scenarioPanel.startSimulation(
                        configuratePanel.getInputBees(),
                        configuratePanel.getInputFlower(),
                        configuratePanel.getInputBirds(),
                        configuratePanel.getInputWorm()
                    );
                }
                if (this.status == STOP){
                    scenarioPanel.stopSimulation();
                }
            }
           
        }
    }
    
    private boolean altetStatus (Status status_now){
        System.out.print("");
        if (status_now != this.status){
            this.status = status_now;
            return true;
        }
        this.status = status_now;
        return false;
    }
    
}
