package com.project.ooaid.fromMisha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;

public class DataCulture {

    private JFrame frame;
    private JFrame start;
    private JFrame oldframe;
    private JPanel consolePanel;
    private JPanel textPanel;
    private JButton backBtn, okBtn;
    private JScrollPane listScroller;
    private JTextField seedsField = new JTextField("Enter count seeds",25);
    private JTextField pochvaField = new JTextField("Enter pochva",25);
    private JTextField temperatureField = new JTextField("Enter temperature",25);
    private JTextField fertilizerField = new JTextField("Enter fertilizer",25);
    private Figure figure;

    private  DrawArea drawArea;

    public DataCulture(Figure figure, JFrame frame, JFrame startFrame, DrawArea drawArea){
        this.figure = figure;
        this.oldframe = frame;
        this.start = startFrame;
        this.drawArea = drawArea;
    }

    ActionListener actionListener = new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            int status = 0;

            if (e.getSource() == okBtn) {
                status = 1;
            } else if (e.getSource() == backBtn) {
                frame.removeAll();
                frame.setVisible(false);
                oldframe.setVisible(true);
            }


            if(status == 1) {
                String text = seedsField.getText();
                try {
                    int seeds = Integer.parseInt(text);
                    figure.setFertilize(fertilizerField.getText());
                    figure.setSeeds(seeds);
                    figure.createCells();

                    int temperature = Integer.parseInt(temperatureField.getText());
                    figure.setTemperature(temperature);
                    drawArea.paintCells(figure);
                    seedsField.setText(null);

                    frame.removeAll();
                    frame.setVisible(false);
                    start.setVisible(true);

                    try(FileWriter writer = new FileWriter(new File("src/main/resources/bd/bd.txt")
                            .getAbsolutePath(), true))
                    {

                        text = Integer.toString(figure.getStartX()) +
                                " " + Integer.toString(figure.getStartY()) +
                                " " + Integer.toString(figure.getWidth()) +
                                " " + Integer.toString(figure.getHeight()) +
                                " " + Integer.toString(seeds) +
                                " " + figure.getCulture() +
                                " " + figure.getTemperature() +
                                " " + figure.getFertilize() +
                                "\n";

                        writer.write(text);
                        writer.flush();
                    }
                    catch(Exception ex){

                        System.out.println(ex.getMessage());
                    }

                } catch (NumberFormatException exception) {
                    seedsField.setText("It's not number");
                    temperatureField.setText("It's not number");
                }
            }

        }
    };

    public void show() {

        frame = new JFrame("Date");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JPanel controls = new JPanel();

        okBtn = new JButton("ok");
        okBtn.addActionListener(actionListener);
        backBtn = new JButton("back");
        backBtn.addActionListener(actionListener);

        textPanel = new JPanel(new VerticalLayout());
        listScroller = new JScrollPane(textPanel);
        listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setBorder(BorderFactory.createEmptyBorder());

        Panel ptrPanel;
        ptrPanel = new Panel();
        ptrPanel.add(seedsField);

        textPanel.add(ptrPanel);

        ptrPanel = new Panel();
        ptrPanel.add(pochvaField);

        textPanel.add(ptrPanel);

        ptrPanel = new Panel();
        ptrPanel.add(temperatureField);

        textPanel.add(ptrPanel);

        ptrPanel = new Panel();
        ptrPanel.add(fertilizerField);

        textPanel.add(ptrPanel);

        consolePanel = new JPanel();
        consolePanel.add(okBtn);
        consolePanel.add(backBtn);

        listScroller.revalidate();

        content.add(listScroller, BorderLayout.CENTER);
        content.add(consolePanel, BorderLayout.SOUTH);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(oldframe.getLocation());
        frame.setVisible(true);

    }
}
