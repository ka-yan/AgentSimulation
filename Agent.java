package Extensions;

/*
Kalyan Khatiwada
February 26, 2023
Professor Bender
CS231 A
Project 03

This abstract class is used as a blueprint for Social and Antisocial agents.
*/

import java.awt.*;

public abstract class Agent {
    private double x;
    private double y;

    public Agent(double x0, double y0){
        x = x0;
        y= y0;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX( double newX){
        x = newX;
    }

    public void setY(double newY){
        y= newY;
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }

    public void updateState(Landscape scape){
        return;
    }

    public void draw(Graphics g){
        return;
    }
}
