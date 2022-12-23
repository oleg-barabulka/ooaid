package com.project.ooaid.fromMisha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DrawArea extends JComponent {

    private JFrame frame;
    private Image image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;
    private ArrayList<Figure> allFigure = new ArrayList();
    private DrawArea drawArea;
    private int flag = 1;

    public DrawArea(JFrame frame) {
        this.frame = frame;
        setDoubleBuffered(false);
        drawArea = this;
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                checkBd();
                oldX = e.getX();
                oldY = e.getY();

                int numField = getNumField(oldX, oldY);
                if(numField != -1){
                    Figure figure = allFigure.get(numField);
                    if(figure.isCells(oldX, oldY) == false) {
                        frame.setVisible(false);
                        ChoseCulture choseCulture = new ChoseCulture(figure, frame, drawArea);
                        choseCulture.show();
                    }
                    else {
                        figure = figure.getCells(oldX, oldY);
                        InfoCells infoCells = new InfoCells(figure);
                        infoCells.show();
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                currentX = e.getX();
                currentY = e.getY();

                int[] arrayX  = new int[4];
                int[] arrayY = new int[4];

                arrayX[0] = oldX;
                arrayY[0] = oldY;
                arrayX[1] = currentX;
                arrayY[1] = oldY;
                arrayX[2] = currentX;
                arrayY[2] = currentY;
                arrayX[3] = oldX;
                arrayY[3] = currentY;

                Figure figure = new Figure(arrayX, arrayY);

                int numField = -1;
                int check = 0;
                for (int i = figure.getStartX() - 20; i < figure.getStartX() + figure.getWidth() + 20 && check == 0; i += 1) {
                  for(int j = figure.getStartY() - 20; j < figure.getStartY() + figure.getHeight() + 20 && check == 0; j += 1) {
                       numField = getNumField(i, j);
                       if(numField != -1){
                           check = 1;
                       }
                  }
                }

                if(numField == -1 && figure.isFigure()) {
                    allFigure.add(new Figure(arrayX, arrayY));

                    Polygon poly = new Polygon(arrayX, arrayY, 4);
                    g2.drawPolygon(poly);
                    repaint();


                }
            }
        });

    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear(1);
        }

        g.drawImage(image, 0, 0, null);

    }

    public void paintCells(Figure figure){
        for(int i = 0; i < figure.getAllCells().size(); i += 1){
            Figure cells = figure.getAllCells().get(i);
            Polygon poly = new Polygon(cells.getArrayX(), cells.getArrayY(), 4);
            g2.drawPolygon(poly);

            g2.setPaint(Color.black);
            g2.fillRect(cells.getStartX(), cells.getStartY(), cells.getWidth(), cells.getHeight());
            repaint();
        }
    }

    private int getNumField (int x, int y){
        int num = -1;
        for(int i = 0; i < allFigure.size(); i += 1){
            if(allFigure.get(i).isPoint(x, y) == true){
                num = i;
            }
        }

        return num;
    }

    public void checkBd(){
        if(flag == 1) {
            try {

                File file = new File(new File("src/main/resources/bd/bd.txt")
                        .getAbsolutePath());

                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] str = line.split(" ");
                    int[] arrayX = new int[4];
                    int[] arrayY = new int[4];

                    int oldX = Integer.parseInt(str[0]);
                    int oldY = Integer.parseInt(str[1]);
                    int currentX = oldX + Integer.parseInt(str[2]);
                    int currentY = oldY + Integer.parseInt(str[3]);
                    int seeds = Integer.parseInt(str[4]);
                    String culture = str[5];
                    int temperature = Integer.parseInt(str[6]);
                    String fertilaze = str[7];

                    arrayX[0] = oldX;
                    arrayY[0] = oldY;
                    arrayX[1] = currentX;
                    arrayY[1] = oldY;
                    arrayX[2] = currentX;
                    arrayY[2] = currentY;
                    arrayX[3] = oldX;
                    arrayY[3] = currentY;

                    Figure figure = new Figure(arrayX, arrayY);
                    figure.setCulture(culture);
                    figure.setFertilize(fertilaze);
                    figure.setTemperature(temperature);
                    figure.setSeeds(seeds);

                    allFigure.add(figure);
                    figure.createCells();
                    paintCells(figure);

                    Polygon poly = new Polygon(arrayX, arrayY, 4);
                    g2.drawPolygon(poly);
                    repaint();

                }

                scanner.close();
                flag = 2;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear(int flag) {
        g2.setPaint(Color.white);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(Color.black);
        repaint();

        allFigure.clear();

        if(flag != 1) {
            try (FileWriter writer = new FileWriter(new File("src/main/resources/bd/bd.txt")
                    .getAbsolutePath(), false)) {
                writer.close();
            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }
        }
    }


    public void back() {

        if(allFigure.size() != 0) {
            Figure figure = allFigure.get(allFigure.size() - 1);
            g2.setPaint(Color.white);
            g2.fillRect(figure.getStartX(), figure.getStartY(), figure.getWidth() + 1, figure.getHeight() + 1);
            g2.setPaint(Color.black);
            repaint();

            allFigure.remove(allFigure.size() - 1);

            String text = "";
            String line = "Null";
            try {

                File file = new File(new File("src/main/resources/bd/bd.txt")
                        .getAbsolutePath());

                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    if(line.equals("Null") == false) {
                        text = line.toString();
                    }

                    if(line.equals("Null"))
                        line = scanner.nextLine() + "\n";
                    else {
                        line += scanner.nextLine() + "\n";
                    }
                }

                scanner.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try(FileWriter writer = new FileWriter(new File("src/main/resources/bd/bd.txt")
                    .getAbsolutePath(), false))
            {
                writer.write(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
