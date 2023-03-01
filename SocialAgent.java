package Extensions;

/*
Kalyan Khatiwada
February 26, 2023
Professor Bender
CS231 A
Project 03

This class is for Social Agents who are simulated and move randomly until they are within the given radius of at least 4 other social agents.
*/
import java.awt.*;

public class SocialAgent extends Agent{
    private boolean moved = false;
    private int radius;
    private LinkedList<String> attributes;

    public SocialAgent(double x0, double y0, int radius, LinkedList<String> attributes){
        super( x0, y0);
        this.radius = radius;
        this.moved = true;
        this.attributes = attributes;
    }
    public void setRadius(int radius){
        this.radius = radius;
    }

    public int getRadius(){
        return radius;
    }

    public boolean isMove(){
        return moved;
    }

    public void draw(Graphics g) {
        if (!moved) g.setColor(new Color(0, 0, 255));
        else g.setColor(new Color(125, 125, 255));
    
        g.fillOval((int) getX(), (int) getY(), 5, 5);

    }
    

    public LinkedList<String> getAttributes(){
        return attributes;
    }

    
    public void updateState(Landscape scape){
        LinkedList<Agent> neighbors = scape.getNeighbors(getX(), getY(), radius);
        int numNeighbors = neighbors.size();
    
        if (numNeighbors > 1) {
            double newX = getX() + (Math.random() * 20 - 10);
            double newY = getY() + (Math.random() * 20 - 10);
    
            // Make sure new position is within landscape boundary
            newX = Math.max(0, newX);
            newX = Math.min(scape.getWidth(), newX);
    
            newY = Math.max(0, newY);
            newY = Math.min(scape.getHeight(), newY);
    
            for (Agent neighbor : neighbors) {
                if (neighbor instanceof AntiSocialAgent) {
                    AntiSocialAgent antisocial = (AntiSocialAgent) neighbor;
                    LinkedList<String> antisocialAttrs = antisocial.getAttributes();
    
                    int count = 0;
                    for (String attr : antisocialAttrs) {
                        if (attributes.contains(attr)) {
                            count++;
                        }
                    }
    
                    if (count >= 2) {
                        moved = false;
                        break;
                    }
                } 
                
            }
    
            if (moved) {
                setX(newX);
                setY(newY);
            }
        }
    }
    
}
