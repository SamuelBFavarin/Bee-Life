package gui;

import model_agents.controller.AbstractAgent;
import static model_agents.controller.AbstractAgent.behaviour.*;
import static model_agents.controller.AbstractAgent.typeAgent.*;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import model_agents.bee.Bee;
import model_agents.bird.Bird;
import model_agents.flower.Flower;
import model_agents.worm.Worm;

/**
 *
 * @author Samuel & Vinícius
 */
public class ScenarioPanel extends JPanel implements ActionListener{
    
    final Image background;
    
    // atributos da colmeia
    final Image hive;
    final int hive_x = 20;
    final int hive_y = 300;
    final int hive_width = 80;
    final int hive_height = 100;
    private int hive_nectar = 0;

    private final Timer timer;
    private final List<AbstractAgent> agents;
    private final AgentContainer ac;
    
    int nextBee = 0;
    int nextBird = 0;
    int nextWorm = 0;
    int nextFlower = 0;

    public ScenarioPanel(AgentContainer ac) throws IOException {
        this.ac = ac;         
        this.agents = new ArrayList<>();
        this.background = ImageIO.read(new File("src/img/jungle.jpg"));
        this.hive = ImageIO.read(new File("src/img/hive.png"));                
        this.timer = new Timer(100, this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        g2d.drawImage(hive, hive_x, hive_y, hive_width, hive_height, this);
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
        this.initAgents(qtd_bees, qtd_flowers, qtd_birds, qtd_worm);
        this.timer.start();
    }
    
    public void stopSimulation(){
        this.timer.stop();
        this.agents.removeAll(agents);
        repaint();
    }
    
    public void update(){
        moveAgents();
        bornNewBee();
        repaint();
    }
                  
    public void initAgents(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm){
        
        for (int i = 0; i < qtd_bees; i++){           
            try {
                int x = (0 + (int) (Math.random() * 100));
                int y = (300 + i + (int)(Math.random() * 100));
                Bee new_bee = new Bee(x, y);
                AgentController agentBird = this.ac.acceptNewAgent("Bee" + String.valueOf(nextBee), new_bee);
                agentBird.start();   
                this.agents.add(new_bee);
                nextBee++;
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(this, "Erro em initBees: " + ex.getMessage());
            }  
        }
        
        for (int i = 0; i < qtd_flowers; i++){
            int x = (i * (getWidth() / qtd_flowers));
            int y = 600;
            if (i % 2 == 0 ){
                Flower new_flower = new Flower(x, y, "F");
                this.agents.add(new_flower);
            }else{
                Flower new_flower = new Flower(x, y, "M");
                this.agents.add(new_flower);
            }
        }
        
        for (int i = 0; i < qtd_birds; i++){              
            try {
                int x = (0 + (int)(Math.random() * 1000));
                int y = (i + (int)(Math.random() * 100));
                Bird new_bird = new Bird(x , y); 
                AgentController agentBird = this.ac.acceptNewAgent("Bird" + String.valueOf(nextBird), new_bird);
                agentBird.start();   
                this.agents.add(new_bird);
                nextBird++;
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(this, "Erro em initBirds: " + ex.getMessage());
            }      
        }
        
        for (int i = 0; i < qtd_worm; i++){ 
            try {
                int x = ((int) (Math.random() * 1000));
                int y = 640;
                Worm new_worm = new Worm(x , y);
                AgentController agentWorm = this.ac.acceptNewAgent("Worm" + String.valueOf(nextWorm), new_worm);
                agentWorm.start();   
                this.agents.add(new_worm);
                nextWorm++;
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(this, "Erro em initWorm: " + ex.getMessage());
            }           
        }
                
    }
        
    public void drawAgents(Graphics2D g2d) throws IOException {
        for (int i = 0; i<this.agents.size(); i++){
            AbstractAgent agent = this.agents.get(i);
            g2d.drawImage(ImageIO.read(agent.getImage()), agent.getPos_x(), agent.getPos_y(), agent.getWidth(), agent.getHeight(), this);
        }   
    }
    
     /******* DAQUI PRA BAIXO PRECISAMOS MUDAR PROS AGENTS ENTAO?  ***********/
    
    public void moveAgents(){
     
        for (int i = 0; i < this.agents.size(); i++){
            switch(this.agents.get(i).getTpAgent()){
                case BEE:
                    Bee bee = (Bee) this.agents.get(i);
                    if (bee.getAbstractState() == PEACE){
                        if (bee.getPos_x() > this.getWidth() || bee.getPos_x() < 0){
                            bee.setDirection_x(bee.getDirection_x() * -1);
                        }  
                        bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirection_x());
                        if (bee.getPos_y() > this.getHeight() - 200 || bee.getPos_y() < 400 ){
                            bee.setDirection_y(bee.getDirection_y() * -1);
                        }
                        bee.setPos_y(bee.getPos_y() - bee.getSpeed() * bee.getDirection_y());
                    }           
                    if (bee.getAbstractState() == SEARCH){
                        if (bee.getPos_x() > this.getWidth() || bee.getPos_x() < 0){
                            bee.setDirection_x(bee.getDirection_x() * -1);
                        }
                        bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirection_x()); 
                        if (bee.getPos_y() > this.getHeight() - 50 || bee.getPos_y() < 300 ){
                            bee.setDirection_y(bee.getDirection_y() * -1);
                        }
                        bee.setPos_y(bee.getPos_y() - bee.getSpeed() * bee.getDirection_y());
                    }     
                    Flower flower = haveFlower(bee.getPos_x(), bee.getPos_y());
                    if (flower != null ){
                        bee.setAbstractState(POLLINATING);
                    }
                    if (bee.getAbstractState() == POLLINATING){   
                        if (bee.getSex_last_flower() != null){
                            if (!bee.getSex_last_flower().equals(flower.getSex())){
                                System.out.println("Reproduziu Flor");
                                bornNewFlower();
                            }
                        }
                        bee.setSex_last_flower(flower.getSex());
                        System.out.println("Nectar colhido");
                        bee.setAbstractState(TO_HIVE);
                    }
                    // voltando pra colmeia movimentação
                    if (bee.getAbstractState() == TO_HIVE){
                        if (hive_x <= bee.getPos_x()){
                            bee.setPos_x(bee.getPos_x() - bee.getSpeed() *2);
                        }
                        if (hive_x >= bee.getPos_x()){
                            bee.setPos_x(bee.getPos_x() + bee.getSpeed() *2);
                        }
                        if (hive_y <= bee.getPos_y()){
                            bee.setPos_y(bee.getPos_y() - bee.getSpeed() *2);
                        }
                        if (hive_x >= bee.getPos_y()){
                            bee.setPos_y(bee.getPos_y() + bee.getSpeed() *2);
                        }
                        if (isHive(bee.getPos_x(), bee.getPos_y())){
                            bee.setAbstractState(IN_HIVE);
                        }
                    } 
                    // chegou na base e adiciona um nectar, já muda para procurando novamente
                    if (bee.getAbstractState() == IN_HIVE){
                        System.out.println("Nectar entregue a colmeia");
                        this.hive_nectar+=2;
                        bee.setPos_x(bee.getPos_x() + bee.getSpeed());
                        bee.setPos_y(bee.getPos_y() + bee.getSpeed());
                        bee.setDirection_y(1);
                        bee.setAbstractState(SEARCH);
                    }
                    break;
                case BIRD:
                    Bird bird = (Bird) this.agents.get(i);         
                    // passaro pleno
                    if (bird.getAbstractState() == PEACE){              
                        // aletera o estado do pássaro para ataque
                        if (haveBeeInX(bird.getPos_x())&& Math.random()*100 > 90){
                            bird.addBehaviour(Bird.behaviour.ATACK);
                        }               
                        if (bird.getPos_x() > this.getWidth() || bird.getPos_x() < 0){
                            bird.setDirection_x(bird.getDirection_x() * - 1);
                        }        
                        bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());
                    }            
                    // passaro em ataque
                    if (bird.getAbstractState() == ATACK){
                        if (bird.getPos_x() > this.getWidth() || bird.getPos_x() < 0){
                            bird.setDirection_x(bird.getDirection_x() * - 1);
                        }        
                        bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirection_x());
                        if (bird.getPos_y() > this.getHeight() - 100 || bird.getPos_y() < 10){
                            bird.setDirection_y(bird.getDirection_y()*-1);                   
                            // altera o estado do pássaro para pleno
                            if (bird.getPos_y() < 10){
                                bird.addBehaviour(Bird.behaviour.PEACE);
                            }
                        }
                        bird.setPos_y(bird.getPos_y() - bird.getSpeed()*5 * bird.getDirection_y());          
                        killBee(bird.getPos_x(), bird.getPos_y());               
                    }          
                    bird.setPos_y(bird.getPos_y());
                    break;
                case FLOWER:
                    break;
                case WORM:
                    Worm worm = (Worm) this.agents.get(i); 
                    if (worm.getAbstractState()== SEARCH){ 
                        if (worm.getPos_x() > this.getWidth() || worm.getPos_x() < 0){
                            worm.setDirection_x(worm.getDirection_x() * - 1);
                        }        
                        worm.setPos_x(worm.getPos_x() + (worm.getSpeed() + (int) (Math.random()*10)) * worm.getDirection_x());
                
                        Flower flower_worm = haveFlower(worm.getPos_x(), worm.getPos_y());
                        if (flower_worm != null && (Math.random() * 100) > worm.percent_no_infect){
                            worm.setAbstractState(INFECT);
                        }
                    }
                    if (worm.getAbstractState() == INFECT){
                        if (haveBeeInX(worm.getPos_x())){
                            worm.setAbstractState(ATACK);
                        }
                    }
                    if (worm.getAbstractState() == ATACK){   
                        if (worm.getPos_y() > this.getHeight() - 100 || worm.getPos_y() < this.getHeight() - 170){
                            worm.setDirection_y(worm.getDirection_y() * -1);
                            killBee(worm.getPos_x(), worm.getPos_y());
                            if (worm.getPos_y() > this.getHeight() - 100){
                                worm.setAbstractState(INFECT);
                            }
                        }
                        worm.setPos_y(worm.getPos_y() - worm.getSpeed()*5 * worm.getDirection_y());
                    }
                    break;
            }       
        }   
    }
            
    public void bornNewBee () {
        
        if (hive_nectar > 0){
            initAgents(hive_nectar, 0, 0, 0);
            System.out.println(hive_nectar + " nova(s) abelha(s) criada(s)");
        } 
        hive_nectar = 0;
    }
    
    public void bornNewFlower () {
        int val = (int) (Math.random() * (this.getWidth()- 1)) + 1;
        if (val > (this.getWidth())/ 2){
            this.agents.add(new Flower(val ,600, "F"));
        }else{
            this.agents.add(new Flower(val ,600, "M"));
        }
    }
    
    public Flower haveFlower (int posx, int posy) {
        
        for (int i = 0; i < this.agents.size(); i++){
            if (this.agents.get(i).getTpAgent() == FLOWER){
                Flower flower = (Flower) this.agents.get(i);
                if (posx >= flower.getPos_x() && posx <= (flower.getPos_x() + flower.getWidth())){
                    if (posy >= flower.getPos_y() && posy <= (flower.getPos_y() + flower.getHeight())){
                        return flower;
                    }
                }    
            }                     
        }
        return null;
    };
    
    public boolean haveBeeInX (int posx){
      
        for (int i = 0; i < this.agents.size(); i++){
            if(this.agents.get(i).getTpAgent() == BEE){
                if (this.agents.get(i).getPos_x() >= (posx - 20) && 
                        this.agents.get(i).getPos_x() <= (posx + 20)  ){
                    return true;   
                }        
            }
        }
        return false;  
    };
    
    public boolean killBee (int posx, int posy) {
        
        for (int i = 0; i < this.agents.size(); i++){
            if(this.agents.get(i).getTpAgent() == BEE){
                if (!isHive(this.agents.get(i).getPos_x(), this.agents.get(i).getPos_y())){       
                    if (this.agents.get(i).getPos_x() >= (posx - 40) && this.agents.get(i).getPos_x() <= (posx + 40)){
                        if (this.agents.get(i).getPos_y() >= (posy - 40) && this.agents.get(i).getPos_y() <= (posy + 40)){
                            System.out.println("abelha morta");
                            this.agents.get(i).setAbstractState(DIE);
                            this.agents.remove(i);
                            return true;
                        }
                    }
                }
            }     
        }
        return false;
    };
    
    public boolean isHive (int posx, int posy) {
        if (posx >= hive_x - 10 && posx < (hive_x + hive_width)){
            if (posy >= hive_y - 10 && posy < (hive_y + hive_height)){
                System.out.println("Ta na colmeia");
                return true;
            }
        }
        return false;
    };
                  
}
