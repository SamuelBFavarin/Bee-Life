package gui;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.awt.Color;
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
import javax.swing.JPanel;
import javax.swing.Timer;
import model_agents.Bee;
import model_agents.Bird;
import model_agents.Flower;
import model_agents.Worm;

/**
 *
 * @author Samuel
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

    private Timer timer;
    private final List<Bee> bees;
    private final List<Flower> flowers;
    private final List<Bird> birds;
    private final List<Worm> worms;
    private AgentContainer ac;

    
    public ScenarioPanel(AgentContainer ac) throws IOException {
        this.ac = ac;
        this.bees = new ArrayList<Bee>();
        this.flowers = new ArrayList<Flower>();
        this.birds = new ArrayList<Bird>();
        this.worms = new ArrayList<Worm>();
                
        this.background = ImageIO.read(new File("src/img/jungle.jpg"));
        this.hive = ImageIO.read(new File("src/img/hive.png"));
        
      
        this.timer = new Timer(100, this);
    }
    
    // desenha cada agente
    public void drawBees(Graphics2D g2d) throws IOException {
        for (int i = 0; i<this.bees.size(); i++){
            Bee bee = this.bees.get(i);
            g2d.drawImage(ImageIO.read(bee.getImage()), bee.getPos_x(), bee.getPos_y(), bee.getWidth(), bee.getHeight(), this);
        }                 
    }
    
    public void drawFlowers(Graphics2D g2d) throws IOException {
        for (int i = 0; i<this.flowers.size(); i++){
            Flower flower = this.flowers.get(i);
            g2d.drawImage(ImageIO.read(flower.getImage()), flower.getPos_x(), flower.getPos_y(), flower.getWidth(), flower.getHeight(), this);
        }                 
    }
    
    public void drawBirds(Graphics2D g2d) throws IOException {
        for (int i = 0; i<this.birds.size(); i++){
            Bird bird = this.birds.get(i);
            g2d.drawImage(ImageIO.read(bird.getImage()), bird.getPos_x(), bird.getPos_y(), bird.getWidth(), bird.getHeight(), this);
        }                 
    }
    
    public void drawWorm(Graphics2D g2d) throws IOException {
        for (int i = 0; i<this.worms.size(); i++){
            Worm worm = this.worms.get(i);
            g2d.drawImage(ImageIO.read(worm.getImage()), worm.getPos_x(), worm.getPos_y(), worm.getWidth(), worm.getHeight(), this);
        }                 
    }
    
    // controla a movimentação dos agentes
    public void update(){
        moveBees();
        moveBirds();
        moveWorms();
        bornNewBee();
        repaint();
    }
    
    // funcões de movimentação e alteração de estados
    public void moveBees () {
        for (int i = 0; i<this.bees.size(); i++){
            Bee bee = this.bees.get(i);
            
            // abelhas quando plena (nunca utilizada)
            if (bee.getStateBee().equals("pleno")){
                if (bee.getPos_x() > this.getWidth() || bee.getPos_x() < 0){
                    bee.setDirectionX(bee.getDirectionX()* -1);
                }  
                bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirectionX());
                
                if (bee.getPos_y() > this.getHeight() - 200 || bee.getPos_y() < 400 ){
                    bee.setDirectionY(bee.getDirectionY()*-1);
                }
                bee.setPos_y(bee.getPos_y() - bee.getSpeed() * bee.getDirectionY());
                
            }
            
            // abelhas quando está procurando flores
            if (bee.getStateBee().equals("search")){
                if (bee.getPos_x() > this.getWidth() || bee.getPos_x() < 0){
                    bee.setDirectionX(bee.getDirectionX()* -1);
                }
                bee.setPos_x(bee.getPos_x() + (bee.getSpeed() + (int) (Math.random()*10)) * bee.getDirectionX());
                
                if (bee.getPos_y() > this.getHeight() - 50 || bee.getPos_y() < 300 ){
                    bee.setDirectionY(bee.getDirectionY()*-1);
                }
                bee.setPos_y(bee.getPos_y() - bee.getSpeed() * bee.getDirectionY());
            }
            
            // se encontrar flor, muda o estado para plinizando
            Flower flower = haveFlower(bee.getPos_x(), bee.getPos_y());
            if (flower != null ){
                bee.setStateBee("pollinating");
            }
            
            // muda de estado para voltando pra colmeia
            if (bee.getStateBee().equals("pollinating")){
                
                // faz a polinização da flor se possível
                if (bee.getSex_last_flower() != null){
                    if (!bee.getSex_last_flower().equals(flower.getSex())){
                        System.out.println("Reproduziu Flor");
                        bornNewFlower();
                    }
                }
                bee.setSex_last_flower(flower.getSex());
                
                System.out.println("Nectar colhido");
                bee.setStateBee("going_to_hive");
            }
            
            // voltando pra colmeia movimentação
            if (bee.getStateBee().equals("going_to_hive")){
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
                    bee.setStateBee("in_hive");
                }
            }
            
            // chegou na base e adiciona um nectar, já muda para procurando novamente
            if (bee.getStateBee().equals("in_hive")){
                System.out.println("Nectar entregue a colmeia");
                this.hive_nectar+=2;
                bee.setPos_x(bee.getPos_x() + bee.getSpeed());
                bee.setPos_y(bee.getPos_y() + bee.getSpeed());
                bee.setDirectionY(1);
                bee.setStateBee("search");
            }
        }
    }
    
    public void moveBirds () {
         // movimentação dos pássaros
        for (int i = 0; i<this.birds.size(); i++){
            Bird bird = this.birds.get(i);
            
            // passaro pleno
            if (bird.getStateBird().equals("pleno")){
                
                // aletera o estado do pássaro para ataque
                if (haveBeeInX(bird.getPos_x())&& Math.random()*100 > 90){
                    bird.setState("atack");
                    bird.addBehaviourBird(Bird.behaviour.ATACK);
                }
                
                if (bird.getPos_x() > this.getWidth() || bird.getPos_x() < 0){
                    bird.setDirectionX(bird.getDirectionX() * - 1);
                }        
                bird.setPos_x(bird.getPos_x() + (bird.getSpeed() + (int) (Math.random()*10)) * bird.getDirectionX());
            }
            
            // passaro em ataque
            if (bird.getStateBird().equals("atack")){
                if (bird.getPos_x() > this.getWidth() || bird.getPos_x() < 0){
                    bird.setDirectionX(bird.getDirectionX() * - 1);
                }        
                bird.setPos_x(bird.getPos_x() + bird.getSpeed() * bird.getDirectionX());
                if (bird.getPos_y() > this.getHeight() - 100 || bird.getPos_y() < 10){
                    bird.setDirectionY(bird.getDirectionY()*-1);
                    
                    // altera o estado do pássaro para pleno
                    if (bird.getPos_y() < 10){
                        bird.setState("pleno");
                        bird.addBehaviourBird(Bird.behaviour.PEACE);
                    }
                }
                bird.setPos_y(bird.getPos_y() - bird.getSpeed()*5 * bird.getDirectionY());
                
                killBee(bird.getPos_x(), bird.getPos_y());
                
            }
           
            bird.setPos_y(bird.getPos_y());
        }
    }
    
    public void moveWorms () {
         for (int i = 0; i<this.worms.size(); i++){
            Worm worm = this.worms.get(i);
            
            // passaro pleno
            if (worm.getStateWorm().equals("search")){
                
                if (worm.getPos_x() > this.getWidth() || worm.getPos_x() < 0){
                    worm.setDirectionX(worm.getDirectionX() * - 1);
                }        
                worm.setPos_x(worm.getPos_x() + (worm.getSpeed() + (int) (Math.random()*10)) * worm.getDirectionX());
                
                Flower flower = haveFlower(worm.getPos_x(), worm.getPos_y());
                if (flower != null && (Math.random() * 100) > worm.percent_no_infect){
                    worm.setStateWorm("infect");
                }
            }
            
            if (worm.getStateWorm().equals("infect")){
                
                if (haveBeeInX(worm.getPos_x())){
                    worm.setStateWorm("atack");
                }
            }
            
            if (worm.getStateWorm().equals("atack")){   
                if (worm.getPos_y() > this.getHeight() - 100 || worm.getPos_y() < this.getHeight() - 170){
                    worm.setDirectionY(worm.getDirectionY()*-1);
                    
                    killBee(worm.getPos_x(), worm.getPos_y());
                    // altera o estado do pássaro para pleno
                    if (worm.getPos_y() > this.getHeight() - 100){
                        worm.setStateWorm("infect");
                    }
                }
                worm.setPos_y(worm.getPos_y() - worm.getSpeed()*5 * worm.getDirectionY());
            }
            
            
        }
    }
    
    public void bornNewBee () {
        
        if (hive_nectar > 0){
           initBees(hive_nectar); 
           System.out.println(hive_nectar + " nova(s) abelha(s) criada(s)");
        } 
        hive_nectar = 0;
    }
    
    public void bornNewFlower () {
        int val = (int) (Math.random() * (this.getWidth()- 1)) + 1;
        if (val > (this.getWidth())/ 2){
            this.flowers.add(new Flower(val ,600, "F"));
        }else{
            this.flowers.add(new Flower(val ,600, "M"));
        }
    }
    
    
    // verifica que existe uma flor pela posição para as abelhas
    public Flower haveFlower (int posx, int posy) {
        
        for (int i = 0; i < flowers.size(); i++){
            Flower flower = this.flowers.get(i);

            if (posx >= flower.getPos_x() && posx <= (flower.getPos_x() + flower.getWidth())){
                if (posy >= flower.getPos_y() && posy <= (flower.getPos_y() + flower.getHeight())){
                    return flower;
                }
            }
                     
        }
        return null;
    };
    
    // observa se existe alguma abelha no eixo x , usado por worm e por bird
    public boolean haveBeeInX (int posx){
      
        for (int i =0; i < bees.size(); i++){
            Bee bee = bees.get(i);
            if (bee.getPos_x() >= (posx - 20) && bee.getPos_x() <= (posx + 20)  ){
                return true;   
            }           
        }
       
        return false;  
    };
    
    // tenta matar a abelha
    public boolean killBee (int posx, int posy) {
        
        for (int i =0; i < bees.size(); i++){
            Bee bee = bees.get(i);
            
            if (!isHive(bee.getPos_x(), bee.getPos_y())){       
                if (bee.getPos_x() >= (posx - 40) && bee.getPos_x() <= (posx + 40)){
                    if (bee.getPos_y() >= (posy - 40) && bee.getPos_y() <= (posy + 40)){
                        System.out.println("abelha morta");
                        bee.setStateBee("die");
                        bees.remove(i);
                        return true;
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
    
    // função de sobreescrita que printa os agentes
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();    
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
        g2d.drawImage(hive, hive_x, hive_y, hive_width, hive_height, this);
        try {
            this.drawBees(g2d);
            this.drawFlowers(g2d);
            this.drawBirds(g2d);
            this.drawWorm(g2d);
        } catch (IOException ex) {
            Logger.getLogger(ScenarioPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.update();
    }
    
    // inicia os agentes em suas posições
    public void initBees(int qtd){
        for (int i = 0; i < qtd; i++){
            Bee new_bee = new Bee(0+ (int) (Math.random()*100), 300 + i + (int)(Math.random()*100));
            this.bees.add(new_bee);
        }
    }
    
    public void initFlowers(int qtd){
        for (int i = 0; i < qtd; i++){
            if (i % 2 == 0 ){
                this.flowers.add(new Flower(i*(getWidth() / qtd),600, "F"));            
            }else{
                this.flowers.add(new Flower(i*(getWidth() / qtd),600, "M"));            
            }
        }
    }
    
    public void initBirds(int qtd){
        for (int i = 0; i < qtd; i++){
            
            String nome = "Bird" + String.valueOf(i);
                    
            try {
                Bird bird = new Bird(0+ (int) (Math.random()*1000) ,i + (int)(Math.random()*100)); 
                AgentController agentBird = this.ac.createNewAgent(nome, "model_agents.Bird", null);
                agentBird.start();
                this.birds.add(bird);
            } catch (StaleProxyException ex) {
                System.out.println("deu pau " + ex.getMessage());
            }      
        }
    }
    
    public void initWorm(int qtd){
        for (int i = 0; i < qtd; i++){
            this.worms.add(new Worm((int) (Math.random()*1000) , 640));
        }
    }
    
    // para a simulação
    public void stopSimulation(){
        this.timer.stop();
        
        for (int i=0; i < this.bees.size()-1; i++){
            this.bees.get(i).killAgent();
        }
        
        this.bees.removeAll(bees);
        this.flowers.removeAll(flowers);
        this.birds.removeAll(birds);
        this.worms.removeAll(worms);
        repaint();
    }
    
    // inicia a simulação
    public void startSimulation(int qtd_bees, int qtd_flowers, int qtd_birds, int qtd_worm){
        this.initBees(qtd_bees);
        this.initFlowers(qtd_flowers);
        this.initBirds(qtd_birds);
        this.initWorm(qtd_worm);
        this.timer.start();
    }
    
}
