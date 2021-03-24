package org.roi_gui.Background;

import javax.swing.*;
import java.awt.*;

public class panel extends JPanel {
    ImageIcon icon;


    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }

    protected void paintComponent(Graphics g){
        super.paint(g);
        if(this.icon != null){
            g.drawImage(icon.getImage(),0,0,this);
        }

    }
}
