package com.project.ooaid.fromMisha;

import java.util.ArrayList;

public class Figure {

    public int[] getArrayX() {
        return arrayX;
    }

    public int[] getArrayY() {
        return arrayY;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Figure> getAllCells() {
        return allCells;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public String getCulture() {
        return culture;
    }

    public int getSeeds() {
        return seeds;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    private int[] arrayX = new int[4];
    private int[] arrayY = new int[4];

    private int startX;
    private int startY;
    private int width;
    private int height;

    private ArrayList<Figure> allCells = new ArrayList<>();

    private String culture;
    private int seeds;

    public String getFertilize() {
        return fertilize;
    }

    public void setFertilize(String fertilize) {
        this.fertilize = fertilize;
    }

    private int temperature;
    private String fertilize;

    public Figure(int[] arrayX, int[] arrayY){
        for(int i = 0; i < 4; i += 1){
            this.arrayX[i] = arrayX[i];
            this.arrayY[i] = arrayY[i];
        }

        if(arrayX[0] >= arrayX[2]){
            startX = arrayX[2];
            width = arrayX[0] - arrayX[2] + 1;
        }
        else {
            startX = arrayX[0];
            width = arrayX[2] - arrayX[0] + 1;
        }

        if(arrayY[0] >= arrayY[2]){
            startY = arrayY[2];
            height = arrayY[0] - arrayY[2];
        }
        else {
            startY = arrayY[0];
            height = arrayY[2] - arrayY[0];
        }
    }

    public boolean isPoint(int x, int y){
        if((x >= startX && x <= startX + width) && (y >= startY && y <= startY + height)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isFigure(){
        if(width >= 20 && height >= 20) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isCells(int x, int y) {
        int check = 0;
        for (int i = 0; i < allCells.size() && check == 0; i += 1) {
            Figure figure = allCells.get(i);
            if((x >= figure.getStartX() && x <= getStartX() + getWidth()) && (y >= figure.getStartY() && y <= figure.getStartY() + figure.getHeight())) {
                check = 1;
            }
        }
        if(check == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public Figure getCells(int x, int y) {
        int check = 0;
        for (int i = 0; i < allCells.size() && check == 0; i += 1) {
            Figure figure = allCells.get(i);
            if((x >= figure.getStartX() && x <= getStartX() + getWidth()) && (y >= figure.getStartY() && y <= figure.getStartY() + figure.getHeight())) {
                check = 1;
                return figure;
            }
        }

        return null;
    }

    public void createCells(){

        if(culture.equals("Potato")){
            int cellX = width / 40 + 1;
            int cellY = height / 40 + 1;

            if((cellX - 1) * 40 + 20>= width){
                cellX -= 1;
            }

            if((cellY - 1) * 40 + 20 >= height){
                cellY -= 1;
            }

            int step = seeds / (cellX * cellY);
            for(int i = 0; i < cellY; i += 1){
                for (int j = 0; j < cellX; j += 1){
                    int[] arrayX = new int[4];
                    int[] arrayY = new int[4];

                    arrayX[0] = startX + j * 40;
                    arrayY[0] = startY + i * 40;
                    arrayX[1] = startX + 20 + j * 40;
                    arrayY[1] = startY + i * 40;
                    arrayX[2] = startX + 20 + j * 40;
                    arrayY[2] = startY + 20 + i * 40;
                    arrayX[3] = startX + j * 40;
                    arrayY[3] = startY + 20 + i * 40;

                    Figure figure = new Figure(arrayX, arrayY);
                    figure.setCulture(culture);

                    figure.setFertilize(fertilize);
                    if(seeds >= cellX * cellY) {
                        figure.setSeeds(step);
                        seeds -= step;
                    }
                    else {
                        figure.setSeeds(seeds);
                        seeds -= seeds;
                    }

                    allCells.add(figure);

                }

            }

        }
        else if(culture.equals("Peas")){
            int cellY = height / 20;
            if(cellY * 20 >= height){
                cellY -= 1;
            }

            int step = seeds / cellY;
            for(int i = 0; i < cellY; i += 1){
                    int[] arrayX = new int[4];
                    int[] arrayY = new int[4];

                    arrayX[0] = startX;
                    arrayY[0] = startY + i * 20;
                    arrayX[1] = startX + width - 1;
                    arrayY[1] = startY + i * 20;
                    arrayX[2] = startX + width - 1;
                    arrayY[2] = startY + 10 + i * 20;
                    arrayX[3] = startX;
                    arrayY[3] = startY + 10 + i * 20;

                    Figure figure = new Figure(arrayX, arrayY);
                    figure.setCulture(culture);
                    figure.setFertilize(fertilize);
                    if(seeds >= cellY) {
                        figure.setSeeds(step);
                        seeds -= step;
                    }
                    else {
                        figure.setSeeds(seeds);
                        seeds -= seeds;
                    }

                allCells.add(figure);
            }
        }
    }


}
