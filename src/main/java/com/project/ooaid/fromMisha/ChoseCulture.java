package com.project.ooaid.fromMisha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoseCulture {
    private JFrame frame, oldframe;
    private JPanel culturePanel;
    private JButton peasBtn, potatoBtn, backBtn;
    private JScrollPane listScroller;
    private Figure figure;
    private DrawArea drawArea;

    public ChoseCulture(Figure figure, JFrame frame, DrawArea drawArea){
       this.figure = figure;
       oldframe = frame;
       this.drawArea = drawArea;
    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
           int status = 0;
           String culture = "Null";

           if(e.getSource() == backBtn){
               frame.removeAll();
               frame.setVisible(false);
               oldframe.setVisible(true);
           } else if (e.getSource() == peasBtn) {
                culture = "Peas";
                status = 1;
           } else if (e.getSource() == potatoBtn) {
                culture = "Potato";
                status = 1;
           }


            if(status == 1 && culture.equals("Null") == false) {
                figure.setCulture(culture);

                DataCulture dataCulture = new DataCulture(figure, frame, oldframe, drawArea);
                dataCulture.show();
               // frame.removeAll();
                frame.setVisible(false);
            }
        }
    };

    public void show() {
        frame = new JFrame("Chose culture");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel controls = new JPanel();

        peasBtn = new JButton("Peas");
        peasBtn.addActionListener(actionListener);
        potatoBtn = new JButton("Potato");
        potatoBtn.addActionListener(actionListener);

        backBtn = new JButton("Back");
        backBtn.addActionListener(actionListener);

        culturePanel = new JPanel(new VerticalLayout());
        listScroller = new JScrollPane(culturePanel);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setBorder(BorderFactory.createEmptyBorder());

        Panel ptrPanel;
        ptrPanel = new Panel();
        ptrPanel.add(peasBtn);

        culturePanel.add(ptrPanel);

        ptrPanel = new Panel();
        ptrPanel.add(potatoBtn);
        culturePanel.add(ptrPanel);

        listScroller.revalidate();

        content.add(listScroller, BorderLayout.CENTER);
        content.add(backBtn,BorderLayout.SOUTH);

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(oldframe.getLocation());
        frame.setVisible(true);

    }
}
