package com.ki.tools;

import javax.swing.*;
import java.awt.*;

public class GUI {

    static final int PADDING = 50;

    public static void displayImage(Image img)
    {
        displayImage(img, "");
    }

    public static void displayImage(Image img, String title){
        //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img.getWidth(null)+PADDING, img.getHeight(null)+PADDING);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(title);
    }
}
