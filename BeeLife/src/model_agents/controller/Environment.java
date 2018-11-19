package model_agents.controller;

import jade.JadeConfig;
import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model_agents.bee.Bee;
import model_agents.bird.Bird;
import static model_agents.controller.AbstractAgent.behaviour.*;
import model_agents.flower.Flower;
import model_agents.hive.Hive;
import model_agents.worm.Worm;

/**
 * @author Vinicius
 */
public class Environment extends Agent{
    
    private final List<Bee> bees = new ArrayList<>();
    private final List<Flower> flowers = new ArrayList<>();
    private final List<Bird> birds = new ArrayList<>();
    private final List<Worm> worms = new ArrayList<>();
    private final Hive hive;
    private final AgentContainer ac;
    private final int width;
    private final int height;
    
    public Environment(int height, int width) {
        JadeConfig jade_config = new JadeConfig("127.0.0.1", "1199");
        this.ac = jade_config.getAgent_controler();
        this.hive = new Hive();
        this.height = height;
        this.width = width;
    }
    
    public void configAgents(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm){
        
        for (int i = 0; i < qtd_bees; i++){           
            try {
                int x = (0 + (int) (Math.random() * 100));
                int y = (300 + i + (int)(Math.random() * 100));
                Bee new_bee = new Bee(x, y);
                AgentController agentBird = this.ac.acceptNewAgent("Bee" + String.valueOf(this.bees.size()+1), new_bee);
                agentBird.start();   
                this.bees.add(new_bee);
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(null, "Erro em iniciar as Bees: " + ex.getMessage());
            }  
        }
        
        for (int i = 0; i < qtd_flowers; i++){
            int x = (i * (this.width / qtd_flowers));
            int y = 600;
            if (i % 2 == 0 ){
                Flower new_flower = new Flower(x, y, "F");
                this.flowers.add(new_flower);
            }else{
                Flower new_flower = new Flower(x, y, "M");
                this.flowers.add(new_flower);
            }
        }
        
        for (int i = 0; i < qtd_birds; i++){              
            try {
                int x = (0 + (int)(Math.random() * 1000));
                int y = (i + (int)(Math.random() * 100));
                Bird new_bird = new Bird(x , y); 
                AgentController agentBird = this.ac.acceptNewAgent("Bird" + String.valueOf(this.birds.size()+1), new_bird);
                agentBird.start();   
                this.birds.add(new_bird);
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(null, "Erro em iniciar as Birds: " + ex.getMessage());
            }      
        }
        
        for (int i = 0; i < qtd_worm; i++){ 
            try {
                int x = ((int) (Math.random() * 1000));
                int y = 640;
                Worm new_worm = new Worm(x , y);
                AgentController agentWorm = this.ac.acceptNewAgent("Worm" + String.valueOf(this.worms.size()+1), new_worm);
                agentWorm.start();   
                this.worms.add(new_worm);
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(null, "Erro em iniciar os Worms: " + ex.getMessage());
            }           
        }    
        
        try {
            AgentController rma = this.ac.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
        } catch(StaleProxyException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar/startar o agente do Jade Tools: " + e.getMessage());
        }
    }   
    
    public void moveAgents(){
        bornNewBee();
        List<AbstractAgent> agents = this.getAllAgents();
        for (int i = 0; i < agents.size(); i++){
            switch(agents.get(i).getTpAgent()){
                case BEE:
                    Bee bee = this.bees.get(i);
                    if (bee.getAbstractState() == PEACE){
                        if (bee.getPos_x() > this.width || bee.getPos_x() < 0){
                            bee.setDirection_x(bee.getDirection_x() * -1);
                        }  
                        bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirection_x());
                        if (bee.getPos_y() > this.height - 200 || bee.getPos_y() < 400 ){
                            bee.setDirection_y(bee.getDirection_y() * -1);
                        }
                        bee.setPos_y(bee.getPos_y() - bee.getSpeed() * bee.getDirection_y());
                    }           
                    if (bee.getAbstractState() == SEARCH){
                        if (bee.getPos_x() > this.width || bee.getPos_x() < 0){
                            bee.setDirection_x(bee.getDirection_x() * -1);
                        }
                        bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirection_x()); 
                        if (bee.getPos_y() > this.height - 50 || bee.getPos_y() < 300 ){
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
                                this.bornNewFlower();
                            }
                        }
                        bee.setSex_last_flower(flower.getSex());
                        bee.setAbstractState(TO_HIVE);
                    }
                    // voltando pra colmeia movimentação
                    if (bee.getAbstractState() == TO_HIVE){
                        if (hive.getPos_x() <= bee.getPos_x()){
                            bee.setPos_x(bee.getPos_x() - bee.getSpeed() *2);
                        }
                        if (hive.getPos_x() >= bee.getPos_x()){
                            bee.setPos_x(bee.getPos_x() + bee.getSpeed() *2);
                        }
                        if (hive.getPos_y() <= bee.getPos_y()){
                            bee.setPos_y(bee.getPos_y() - bee.getSpeed() *2);
                        }
                        if (hive.getPos_x() >= bee.getPos_y()){
                            bee.setPos_y(bee.getPos_y() + bee.getSpeed() *2);
                        }
                        if (isHive(bee.getPos_x(), bee.getPos_y())){
                            bee.setAbstractState(IN_HIVE);
                        }
                    } 
                    // chegou na base e adiciona um nectar, já muda para procurando novamente
                    if (bee.getAbstractState() == IN_HIVE){
                        this.hive.hive_nectar+=2;
                        bee.setPos_x(bee.getPos_x() + bee.getSpeed());
                        bee.setPos_y(bee.getPos_y() + bee.getSpeed());
                        bee.setDirection_y(1);
                        bee.setAbstractState(SEARCH);
                    }
                    break;
                case BIRD:
                    Bird bird = (Bird) agents.get(i);         
                    // passaro pleno
                    if (bird.getAbstractState() == PEACE){              
                        // aletera o estado do pássaro para ataque
                        if (haveBeeInX(bird.getPos_x())&& Math.random()*100 > 90){
                            bird.addBehaviour(Bird.behaviour.ATACK);
                        }               
                        if (bird.getPos_x() > this.width || bird.getPos_x() < 0){
                            bird.setDirection_x(bird.getDirection_x() * - 1);
                            
                        }        
                        bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirection_x());
                    }            
                    // passaro em ataque
                    if (bird.getAbstractState() == ATACK){
                        if (bird.getPos_x() > this.width || bird.getPos_x() < 0){
                            bird.setDirection_x(bird.getDirection_x() * - 1);
                        }        
                        bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirection_x());
                        if (bird.getPos_y() > this.height - 100 || bird.getPos_y() < 10){
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
                    Worm worm = (Worm) agents.get(i); 
                    if (worm.getAbstractState() == SEARCH){ 
                        if (worm.getPos_x() > this.width || worm.getPos_x() < 0){
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
                        if (worm.getPos_y() > this.height - 100 || worm.getPos_y() < this.height - 170){
                            worm.setDirection_y(worm.getDirection_y() * -1);
                            killBee(worm.getPos_x(), worm.getPos_y());
                            if (worm.getPos_y() > this.height - 100){
                                worm.setAbstractState(INFECT);
                            }
                        }
                        worm.setPos_y(worm.getPos_y() - worm.getSpeed()*5 * worm.getDirection_y());
                    }
                    break;
            }       
        }   
        
    }
    
    public List getAllAgents(){
        List<AbstractAgent> agents = new ArrayList<>();
        agents.addAll(bees);
        agents.addAll(birds);
        agents.addAll(worms);
        agents.addAll(flowers);
        agents.add(hive);
        return agents;
    }
    
    public void bornNewFlower () {
        int val = (int) (Math.random() * (this.width - 1)) + 1;
        if (val > (this.width / 2)){
            this.flowers.add(new Flower(val ,600, "F"));
        }else{
            this.flowers.add(new Flower(val ,600, "M"));
        }
    }
    
    public void bornNewBee () {
        if (this.hive.hive_nectar > 0){ 
            configAgents(hive.hive_nectar, 0, 0, 0);
        }
        hive.hive_nectar = 0;
    }
    
    public Flower haveFlower (int pos_x, int pos_y) {
        
        for (int i = 0; i < this.flowers.size(); i++){
            if (pos_x >= this.flowers.get(i).getPos_x() && pos_x <= (this.flowers.get(i).getPos_x() + this.flowers.get(i).getWidth())){
                if (pos_y >= this.flowers.get(i).getPos_y() && pos_y <= (this.flowers.get(i).getPos_y() + this.flowers.get(i).getHeight())){
                    return this.flowers.get(i);
                }
            }                        
        }
        return null;
    };
    
    public boolean haveBeeInX (int posx){
      
        for (int i = 0; i < this.bees.size(); i++){
            if (this.bees.get(i).getPos_x() >= (posx - 20) && 
                this.bees.get(i).getPos_x() <= (posx + 20)  ){
                    return true;   
            }        
        }
        return false;  
    };
    
    public boolean killBee (int posx, int posy) {
        
        for (int i = 0; i < this.bees.size(); i++){
            if (!isHive(this.bees.get(i).getPos_x(), this.bees.get(i).getPos_y())){       
                if (this.bees.get(i).getPos_x() >= (posx - 40) && this.bees.get(i).getPos_x() <= (posx + 40)){
                    if (this.bees.get(i).getPos_y() >= (posy - 40) && this.bees.get(i).getPos_y() <= (posy + 40)){
                        this.bees.get(i).setAbstractState(DIE);
                        this.bees.remove(i);
                        return true;
                    }
                }
            }   
        }
        return false;
    };
    
    public boolean isHive (int posx, int posy) {
        if (posx >= this.hive.getPos_x() - 10 && posx < (this.hive.getPos_x() + this.hive.getWidth())){
            if (posy >= this.hive.getPos_y() - 10 && posy < (hive.getPos_y() + this.hive.getHeight())){
                return true;
            }
        }
        return false;
    };
    
    public Hive getHive() {
        return hive;
    }
    
 }
