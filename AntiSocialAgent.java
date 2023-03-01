package Extensions;

/*
Kalyan Khatiwada
February 26, 2023
Professor Bender
CS231 A
Project 03

This class is  for anti social agents who move randomly in the simulation until there is no other agent within the given radius.
*/

import java.awt.*;

public class AntiSocialAgent extends Agent{
    private boolean moved = false;
    private int radius;
    private LinkedList<String> attributes;

    public AntiSocialAgent(double x0, double y0, int radius, LinkedList<String> attributes){
        super( x0, y0);
        this.radius = radius;
        this.moved = false;
        this.attributes = attributes;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }

    public LinkedList<String> getAttributes(){
        return attributes;
    }

    public boolean isMove(){
        return moved;
    }

    public void draw(Graphics g) {
        if (!moved) g.setColor(new Color(255, 0, 0));
        else g.setColor(new Color(255, 125, 125));
    
        g.fillOval((int) getX(), (int) getY(), 5, 5);

    }
    
    
    
    public void updateState(Landscape scape){
        LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), radius);
        int numNeighbors = neighbors.size();
    
        if (numNeighbors > 1) {
            boolean stopMoving = false;
            for (Agent neighbor : neighbors) {
                if (neighbor instanceof SocialAgent) { // check if neighbor is a SocialAgent
                    LinkedList<String> neighborAttributes = (LinkedList<String>) ((SocialAgent) neighbor).getAttributes();
                    int numCommonAttributes = 0;
                    for (String attribute : attributes) {
                        if (neighborAttributes.contains(attribute)) {
                            numCommonAttributes++;
                        }
                    }
                    if (numCommonAttributes >= 2) {
                        stopMoving = true;
                        break; // stop checking the remaining neighbors
                    }
                }
            }
    
            if (!stopMoving) {
                double newX = getX() + (Math.random() * 20 - 10);
                double newY = getY() + (Math.random() * 20 - 10);
    
                // Make sure new position is within landscape boundary
                newX = Math.max(0, newX);
                newX = Math.min(scape.getWidth(), newX);
    
                newY = Math.max(0, newY);
                newY = Math.min(scape.getHeight(), newY);
    
                setX(newX);
                setY(newY);
    
                moved = true;
            } else {
                moved = false;
            }
        } else {
            moved = false;
        }
    }

    
    
}
