package gui;

import model_agents.environment.AbstractAgent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import model_agents.environment.Environment;

/**
 *
 * @author Samuel & Vinícius
 */
public class ScenarioPanel extends JPanel implements ActionListener{
    
    final Image background;
    
    private final Timer timer;
    private Environment environment;
    
    public ScenarioPanel() throws IOException {        
        this.background = ImageIO.read(new File("src/img/jungle.jpg"));            
        this.timer = new Timer(100, this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        try {
            this.drawAgents(g2d);
        } catch (IOException ex) {
            Logger.getLogger(ScenarioPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
    }
    
    public void startSimulation(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm){
        this.environment = new Environment(getHeight(), getWidth());
        this.environment.startEnvironment(qtd_bees, qtd_flowers, qtd_birds, qtd_worm);
        this.timer.start();
    }
    
    public void stopSimulation(){
        this.environment.stopEnvironment();
        this.timer.stop();
        repaint();
    }
    
    public void update(){
        this.environment.updateAgents();
        repaint();
    }
                          
    public void drawAgents(Graphics2D g2d) throws IOException {
        if (this.environment != null){
            List<AbstractAgent> agents = this.environment.getAllAgents();
            for (int i = 0; i < agents.size(); i++){
                AbstractAgent agent = agents.get(i);
                g2d.drawImage(ImageIO.read(agent.getImage()), agent.getPos_x(), agent.getPos_y(), agent.getWidth(), agent.getHeight(), this);
            }
        }
    }             
                 
}
