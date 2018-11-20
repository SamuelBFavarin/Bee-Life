package gui;

import static gui.ConfiguratePanel.Status.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Samuel
 */
public class ConfiguratePanel extends JPanel {
    
    public enum Status{
        START, STOP
    }
    
    public JButton btn_start = new JButton("Start");
    public JButton btn_stop = new JButton("Stop");
    
    public JFormattedTextField input_bees;
    public JFormattedTextField input_bird;
    public JFormattedTextField input_flower;
    public JFormattedTextField input_worm;
        
    public int flag_start = 0;
    public int flag_stop = 1;
    
    public ConfiguratePanel() {
        this.setBackground(new Color(63, 50, 45));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            
        // imlpementa função start
        btn_start.addActionListener((ActionEvent e) -> {
            this.flag_start = 1;
            this.flag_stop = 0;
        });
        
        // imlpementa função pause
        btn_stop.addActionListener((ActionEvent e) -> {
            this.flag_start = 0;
            this.flag_stop = 1;
        });
               
        
        // insere os componentes no panel
        JLabel lb_title = new JLabel("Bee Life");
        lb_title.setFont(new Font("Arial", Font.PLAIN, 32));
        lb_title.setForeground(Color.WHITE);
        this.add(lb_title);
        
        // insere label para espaço
        
        this.add(new JLabel(" "));
        
        btn_start.setBackground(new Color(255, 214, 50));
        btn_start.setMaximumSize(new Dimension(160, 30));
        
        btn_stop.setBackground(new Color(255, 212, 43));
        btn_stop.setMaximumSize(new Dimension(160, 30));
        // insere botões
        this.add(btn_start);
        this.add(btn_stop);
        
        
        // insere label para espaço
        JLabel lb_space = new JLabel(" ");
        lb_space.setFont(new Font("Arial", Font.PLAIN, 18));
        lb_space.setForeground(Color.WHITE);
        this.add(lb_space);
        
        // insere label de abelhas
        JLabel lb_bees = new JLabel("Quantidade de abelhas");
        lb_bees.setFont(new Font("Arial", Font.PLAIN, 14));
        lb_bees.setForeground(Color.WHITE);
        this.add(lb_bees);
        
        // insere input de abelhas
        input_bees = new JFormattedTextField(10);
        input_bees.setValue(60);
        input_bees.setMaximumSize(new Dimension(500, 30));
        this.add(input_bees);
        
        
        // insere label de passaros
        JLabel lb_bird = new JLabel("Quantidade de pássaros");
        lb_bird.setFont(new Font("Arial", Font.PLAIN, 14));
        lb_bird.setForeground(Color.WHITE);
        this.add(lb_bird);
        
        // insere input de passaros
        input_bird = new JFormattedTextField(10);
        input_bird.setValue(3);
        input_bird.setMaximumSize(new Dimension(500, 30));
        this.add(input_bird);
        
        // insere label de flores
        JLabel lb_flower = new JLabel("Quantidade de flores");
        lb_flower.setFont(new Font("Arial", Font.PLAIN, 14));
        lb_flower.setForeground(Color.WHITE);
        this.add(lb_flower);
        
        // insere input de flores
        input_flower = new JFormattedTextField(10);
        input_flower.setValue(20);
        input_flower.setMaximumSize(new Dimension(500, 30));
        this.add(input_flower);
        
        // insere label de pragas
        JLabel lb_worm = new JLabel("Quantidade de percevejos");
        lb_worm.setFont(new Font("Arial", Font.PLAIN, 14));
        lb_worm.setForeground(Color.WHITE);
        this.add(lb_worm);
        
        // insere input de pragas
        input_worm = new JFormattedTextField(5);
        input_worm.setValue(5);
        input_worm.setMaximumSize(new Dimension(500, 30));
        this.add(input_worm);
        
         
        this.setVisible(true);
    }
    
    public Status getStatus(){
      if (this.flag_start == 1) return START;
      else if (this.flag_stop == 1) return STOP;
      return null;
    };
    
    
    public int getInputBees(){
        return (int) this.input_bees.getValue();
    };
    
    public int getInputBirds(){
        return (int) this.input_bird.getValue();
    };
    
    public int getInputFlower(){
        return (int) this.input_flower.getValue();
    };
    
    public int getInputWorm(){
        return (int) this.input_worm.getValue();
    };
    
}
