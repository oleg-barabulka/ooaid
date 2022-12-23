package com.project.ooaid.fromMisha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoCells {
    /*
    берет инфу о поле и отрисовывает
     */
    private JFrame frame;
    private JPanel culturePanel;
    private JButton backBtn;
    private JScrollPane listScroller;
    private Figure figure;

    public InfoCells(Figure figure){
        this.figure = figure;
    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            int status = 0;
            String culture = "Null";

            if(e.getSource() == backBtn){
                frame.removeAll();
                frame.setVisible(false);
            }

        }
    };

    public void show() {
        frame = new JFrame("Info");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel controls = new JPanel();

        backBtn = new JButton("Back");
        backBtn.addActionListener(actionListener);

        culturePanel = new JPanel(new VerticalLayout());
        listScroller = new JScrollPane(culturePanel);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setBorder(BorderFactory.createEmptyBorder());

        Panel ptrPanel;
        JLabel ptrLabel;
        ptrLabel = new JLabel("Seeds: " + figure.getSeeds());
        ptrPanel = new Panel();
        ptrPanel.add(ptrLabel);

        culturePanel.add(ptrPanel);

        ptrLabel = new JLabel("Culture: " + figure.getCulture());
        ptrPanel = new Panel();
        ptrPanel.add(ptrLabel);

        culturePanel.add(ptrPanel);

        if(figure.getFertilize().equals("Navoz")) {
            ptrLabel = new JLabel("Fertilize: " + figure.getFertilize());
            ptrPanel = new Panel();
            ptrPanel.add(ptrLabel);

            culturePanel.add(ptrPanel);
        }


        float k = 1;
        if(figure.getFertilize().equals("Navoz")){
            k += 0.2;
        }
        if(figure.getTemperature() < 17){
            k -= 0.5;
        }
        if(figure.getTemperature() >= 17 && figure.getTemperature() <= 24){
            k += 0.1;
        }
        if(figure.getTemperature() > 24){
            k -= 0.3;
        }

        ptrLabel = new JLabel("Expected: " + (int)((float)figure.getSeeds() * k * 10.0));
        ptrPanel = new Panel();
        ptrPanel.add(ptrLabel);

        culturePanel.add(ptrPanel);

        listScroller.revalidate();

        content.add(listScroller, BorderLayout.CENTER);
        content.add(backBtn,BorderLayout.SOUTH);

        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(figure.getStartX(), figure.getStartY());
        frame.setVisible(true);

    }
}
