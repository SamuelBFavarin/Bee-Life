package model_agents.controller;

import jade.JadeConfig;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model_agents.bee.Bee;
import model_agents.bee.BeeInHive;
import model_agents.bee.BeePollinate;
import model_agents.bee.BeeSearch;
import model_agents.bee.BeeToHive;
import model_agents.bird.Bird;
import model_agents.bird.BirdBackAtack;
import model_agents.bird.BirdGoAtack;
import model_agents.bird.BirdPeace;
import static model_agents.controller.AbstractAgent.behaviour.*;
import model_agents.controller.AbstractAgent.typeAgent;
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
    private int envCreated;
    
    private int nextBee;
    private int nextFlower;
    private int nextBird;
    private int nextWorm;
    
    public Environment(int height, int width) {
        JadeConfig jade_config = new JadeConfig("127.0.0.1", "1199");
        this.ac = jade_config.getAgent_controler();
        this.hive = new Hive();
        this.height = height;
        this.width = width;
        this.envCreated = 0;
        this.nextBee = 0;
        this.nextFlower = 0;
        this.nextBird = 0;
        this.nextWorm = 0;
    }
    
    @Override
    protected void setup(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null){
                    if (msg.getOntology().equalsIgnoreCase("BIRD_ONTOLOGY")){
                        if(msg.getLanguage().equalsIgnoreCase("KILL_BEE")){
                            String posicoes_bee[] = msg.getContent().split(",");
                            int x = Integer.valueOf(posicoes_bee[0]);
                            int y = Integer.valueOf(posicoes_bee[1]);
                            killBee(x, y);
                        }
                    }else if(msg.getOntology().equalsIgnoreCase("BEE_ONTOLOGY")){
                        if(msg.getContent().equalsIgnoreCase("BORN_NEW_FLOWER")){
                            bornNewFlower();
                        }
                    }
                }else{
                    block();
                }
            }
        });
    
    }
    
    public void configAgents(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm){
        
        //Cria o ambiente apenas 1 vez, preciso dele para enviar/receber mensagens
        if (this.envCreated == 0){
            try {
                
                AgentController rma = this.ac.createNewAgent("rma", "jade.tools.rma.rma", null);
                rma.start();
                
                AgentController agentEnvironment = this.ac.acceptNewAgent("Environment", this);
                agentEnvironment.start();   
                this.envCreated = 1;
                } catch (StaleProxyException ex) {
                    JOptionPane.showMessageDialog(null, "Erro em iniciar o Environment: " + ex.getMessage());
            } 
        }
        
        for (int i = 0; i < qtd_bees; i++){           
            try {
                int x = (0 + (int) (Math.random() * 100));
                int y = (300 + i + (int)(Math.random() * 100));
                Bee new_bee = new Bee(x, y);
                new_bee.setEnvironment(this);
                String nickname = nextAgentName(typeAgent.BEE);
                new_bee.setNickName(nickname);
                new_bee.addBehaviour(new BeeSearch(new_bee, 100));
                new_bee.addBehaviour(new BeePollinate(new_bee, 100));
                new_bee.addBehaviour(new BeeToHive(new_bee, 100));
                new_bee.addBehaviour(new BeeInHive(new_bee, 100));
                AgentController agentBird = this.ac.acceptNewAgent(nickname, new_bee);
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
                new_flower.setEnvironment(this);
                String nickname = nextAgentName(typeAgent.FLOWER);
                new_flower.setNickName(nickname);
                this.flowers.add(new_flower);
            }else{
                Flower new_flower = new Flower(x, y, "M");
                new_flower.setEnvironment(this);
                String nickname = nextAgentName(typeAgent.FLOWER);
                new_flower.setNickName(nickname);
                this.flowers.add(new_flower);
            }
        }
        
        for (int i = 0; i < qtd_birds; i++){              
            try {
                int x = (0 + (int)(Math.random() * 1000));
                int y = (i + (int)(Math.random() * 100));
                Bird new_bird = new Bird(x , y); 
                new_bird.setEnvironment(this);
                String nickname = nextAgentName(typeAgent.BIRD);
                new_bird.setNickName(nickname);
                new_bird.addBehaviour(new BirdPeace(new_bird, 100));
                new_bird.addBehaviour(new BirdGoAtack(new_bird, 100));
                new_bird.addBehaviour(new BirdBackAtack(new_bird, 100));
                AgentController agentBird = this.ac.acceptNewAgent(nickname, new_bird);
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
                new_worm.setEnvironment(this);
                String nickname = nextAgentName(typeAgent.WORM);
                new_worm.setNickName(nickname);
                AgentController agentWorm = this.ac.acceptNewAgent(nickname, new_worm);
                agentWorm.start();            
                this.worms.add(new_worm);
            } catch (StaleProxyException ex) {
                JOptionPane.showMessageDialog(null, "Erro em iniciar os Worms: " + ex.getMessage());
            }           
        }    
    }   
    
    public void moveAgents(){
        bornNewBee();
        List<AbstractAgent> agents = this.getAllAgents();
        for (int i = 0; i < agents.size(); i++){
            switch(agents.get(i).getTpAgent()){
                case BEE:
                    moveBee((Bee) agents.get(i));
                    break;
                case BIRD:
                    moveBird((Bird) agents.get(i));
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
                            worm.setAbstractState(GO_ATACK);
                        }
                    }
                    if (worm.getAbstractState() == GO_ATACK){   
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
    
    public void moveBird(Bird bird){     
        if (bird.getAbstractState() == PEACE){
            ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
            msg.setOntology("BIRD_ONTOLOGY");
            msg.setLanguage("BIRD_LANGUAGE");
            if (haveBeeInX(bird.getPos_x())){
                msg.setContent("GO_ATACK");
            }else{
                msg.setContent("PEACE");
            }
            msg.addReceiver(new AID(bird.getLocalName(), AID.ISLOCALNAME));
            send(msg);
        }    
    }
    
    public void moveBee(Bee bee){
        
        if (bee.getAbstractState().equals(SEARCH)){                 
            ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
            msg.setOntology("BEE_ONTOLOGY");
            msg.setLanguage("BEE_LANGUAGE");
            Flower flower = haveFlower(bee.getPos_x(), bee.getPos_y());
            if (flower != null ){
                bee.setCurrent_flower(flower);
                msg.setContent("POLLINATING");
            }else{
                msg.setContent("SEARCH");
            }
            msg.addReceiver(new AID(bee.getLocalName(), AID.ISLOCALNAME));
            send(msg);
        }
        
        if (bee.getAbstractState() == TO_HIVE){
            ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
            msg.setOntology("BEE_ONTOLOGY");
            msg.setLanguage("BEE_LANGUAGE");   
            if(isHive(bee.getPos_x(), bee.getPos_y())){
                msg.setContent("IN_HIVE");
            }else{
                msg.setContent("TO_HIVE");
            }
            msg.addReceiver(new AID(bee.getLocalName(), AID.ISLOCALNAME));
            send(msg);
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
    
    public String nextAgentName(typeAgent agent){
        String name = "";
        switch(agent){
            case BEE:
                name = "Bee" + String.valueOf(nextBee); 
                nextBee++;
                return name;
            case BIRD:
                name = "Bird" + String.valueOf(nextBird);
                nextBird++;
                return name;
            case FLOWER:
                name = "Flower" + String.valueOf(nextFlower);
                nextFlower++;
                return name;
            case HIVE:
                break;
            case WORM:
                name = "Worm" + String.valueOf(nextWorm);
                nextWorm++;
                return name;
        }        
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
       
 }
