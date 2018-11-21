package model_agents.worm;

import jade.core.Agent;
import static model_agents.environment.AbstractAgent.behaviour.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import model_agents.environment.AbstractAgent;
import model_agents.environment.AbstractAgent.behaviour;
import static model_agents.environment.AbstractAgent.typeAgent.*;
import model_agents.environment.Environment;
import model_agents.flower.Flower;

/**
 *
 * @author Samuel
 */
public class Worm extends Agent implements AbstractAgent{
    
    private Flower current_flower = null;
    public int percent_no_infect = 95;
    private File image;
    private int height;
    private int width;
    private int pos_x;
    private int pos_y;
    private AbstractAgent.behaviour state;
    private AbstractAgent.typeAgent tpAgent;
    private int direction_x;
    private int direction_y;
    private int speed;
    private Environment environment;
    private String nickName;
    private int time_waiting;
    private Clip worm_sound;
    
    public Worm( int pos_x, int pos_y) {
        
        this.height = 30;
        this.width = 30;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.speed = 2;
        this.direction_y = -1;
        this.state = SEARCH;
        this.tpAgent = WORM;
        this.image = new File("src/img/bug.gif");
        this.time_waiting = 0;
        if (Math.random()*100 > 50){
            this.direction_x = 1;
        } else {
            this.direction_x = -1;
        }
    }
    
    @Override
    protected void setup(){
        addBehaviour(new WormSearch(this, 100));
        addBehaviour(new WormInfect(this, 100));
        addBehaviour(new WormGoAtack(this, 100));    
    }

    public int getPercent_no_infect() {
        return percent_no_infect;
    }

    public void setPercent_no_infect(int percent_no_infect) {
        this.percent_no_infect = percent_no_infect;
    }

    @Override
    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getPos_x() {
        return pos_x;
    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    @Override
    public int getPos_y() {
        return pos_y;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    @Override
    public behaviour getAbstractState() {
        return state;
    }

    public void setState(AbstractAgent.behaviour state) {
        this.state = state;
    }

    @Override
    public AbstractAgent.typeAgent getTpAgent() {
        return tpAgent;
    }

    @Override
    public void setTpAgent(AbstractAgent.typeAgent tpAgent) {
        this.tpAgent = tpAgent;
    }

    public int getDirection_x() {
        return direction_x;
    }

    public void setDirection_x(int direction_x) {
        this.direction_x = direction_x;
    }

    public int getDirection_y() {
        return direction_y;
    }

    public void setDirection_y(int direction_y) {
        this.direction_y = direction_y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public void setAbstractState(behaviour state) {
        this.state = state;
    }

    public Flower getCurrent_flower() {
        return current_flower;
    }

    public void setCurrent_flower(Flower current_flower) {
        this.current_flower = current_flower;
    }

    public int getTime_waiting() {
        return time_waiting;
    }

    public void setTime_waiting(int time_waiting) {
        this.time_waiting = time_waiting;
    }
    
        public void playSound(){
        if (this.worm_sound != null){
            if (!this.worm_sound.isRunning()){
                try {
                    this.worm_sound = AudioSystem.getClip();
                    this.worm_sound.open(AudioSystem.getAudioInputStream(new File("src/sound/worm.wav")));
                    this.worm_sound.start(); 
                }catch(LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro em Worm.playSound(): " + ex.getMessage());
                }                
            }
        }else{
            try {
                this.worm_sound = AudioSystem.getClip();
                this.worm_sound.open(AudioSystem.getAudioInputStream(new File("src/sound/worm.wav")));
                this.worm_sound.start(); 
            }catch(LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro em Worm.playSound(): " + ex.getMessage());
            }
        }
    }
        
    public void stopSound(){
         if (worm_sound != null){
            if (worm_sound.isRunning()){
                worm_sound.stop();
            }
        }         
    }
   
}
