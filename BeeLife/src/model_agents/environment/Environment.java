package model_agents.environment;

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
import static model_agents.environment.AbstractAgent.behaviour.*;
import model_agents.environment.AbstractAgent.typeAgent;
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
                    }
                    if(msg.getOntology().equalsIgnoreCase("BEE_ONTOLOGY")){
                        if(msg.getContent().equalsIgnoreCase("BORN_NEW_FLOWER")){
                            bornNewFlower();
                        }
                    }
                    if(msg.getOntology().equalsIgnoreCase("WORM_ONTOLOGY")){
                        if(msg.getLanguage().equalsIgnoreCase("KILL_BEE")){
                            String posicoes_bee[] = msg.getContent().split(",");
                            int x = Integer.valueOf(posicoes_bee[0]);
                            int y = Integer.valueOf(posicoes_bee[1]);
                            killBee(x, y);                           
                        }
                    }
                }else{
                    block();
                }
            }
        }); 
    }
    
    public void startEnvironment(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm){
        
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
    
    public void updateAgents(){
        moveBee();
        moveBird();
        moveWorm();
    }
    
    public void moveWorm(){
        for (Worm worm : this.worms) {
            if(worm.getAbstractState().equals(SEARCH)){
                ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
                msg.setOntology("WORM_ONTOLOGY");
                msg.setLanguage("WORM_LANGUAGE");
                Flower flower = haveFlower(worm.getPos_x(), worm.getPos_y());
                if (flower != null ){
                    worm.setCurrent_flower(flower);
                    msg.setContent("INFECT");
                }else{
                    msg.setContent("SEARCH");
                }
                msg.addReceiver(new AID(worm.getLocalName(), AID.ISLOCALNAME));
                send(msg);
            }
            if(worm.getAbstractState().equals(INFECT)){
                ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
                msg.setOntology("WORM_ONTOLOGY");
                msg.setLanguage("WORM_LANGUAGE");
                if (haveBeeInX(worm.getPos_x())){
                    msg.setContent("GO_ATACK");
                }else{
                    msg.setContent("INFECT");
                }
                msg.addReceiver(new AID(worm.getLocalName(), AID.ISLOCALNAME));
                send(msg);           
            }
        }
    }
    
    public void moveBird(){
        for (Bird bird : this.birds){
            if (bird.getAbstractState().equals(PEACE)){
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
    }
    
    public void moveBee(){  
        /*
         PRECISA SER UM FOR DE I, SE FOR UM FOREACH,
         VAI DAR PROBLEMA PQ A GENTE REMOVE ABELHAS (KILLBEE) ENQUANTO
         TEM UM ITERATOR PERCORRENDO O ARRAYLIST
        */
        for (int i = 0; i < this.bees.size(); i++){
            if (this.bees.get(i).getAbstractState().equals(SEARCH)){                 
                ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
                msg.setOntology("BEE_ONTOLOGY");
                msg.setLanguage("BEE_LANGUAGE");
                Flower flower = haveFlower(this.bees.get(i).getPos_x(), this.bees.get(i).getPos_y());
                if (flower != null ){
                    this.bees.get(i).setCurrent_flower(flower);
                    msg.setContent("POLLINATING");
                }else{
                    msg.setContent("SEARCH");
                }
                msg.addReceiver(new AID(this.bees.get(i).getLocalName(), AID.ISLOCALNAME));
                send(msg);
            }
        
            if (this.bees.get(i).getAbstractState().equals(TO_HIVE)){
                ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
                msg.setOntology("BEE_ONTOLOGY");
                msg.setLanguage("BEE_LANGUAGE");   
                if(isHive(this.bees.get(i).getPos_x(), this.bees.get(i).getPos_y())){
                    msg.setContent("IN_HIVE");
                }else{
                    msg.setContent("TO_HIVE");
                }
                msg.addReceiver(new AID(this.bees.get(i).getLocalName(), AID.ISLOCALNAME));
                send(msg);
            }
        
            if (this.bees.get(i).getAbstractState().equals(IN_HIVE)){
                if (this.getHive().hive_nectar >= 4){
                    bornNewBee();
                    this.getHive().hive_nectar = 0;
                }
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
        try {
            int x = (0 + (int) (Math.random() * 100));
            int y = (300 + (int)(Math.random() * 100));
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
                JOptionPane.showMessageDialog(null, "Erro em bornNewBee: " + ex.getMessage());
        }  
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
                        this.bees.get(i).doDelete();
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
        
    public void stopEnvironment(){
 
        for (int i = 0; i < this.bees.size(); i++){
            this.bees.get(i).doDelete();
        }
        
        for (int i = 0; i < this.worms.size(); i++){
            this.worms.get(i).doDelete();
        }
        
        for (int i = 0; i < this.birds.size(); i++){
            this.birds.get(i).doDelete();
        }
               
        this.bees.removeAll(bees);
        this.birds.removeAll(birds);
        this.flowers.removeAll(flowers);
        this.worms.removeAll(worms);
        
        nextBee = 0;
        nextBird = 0;
        nextFlower = 0;
        nextWorm = 0;
    }
 }
