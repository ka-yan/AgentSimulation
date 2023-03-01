package Extensions;

/*
Kalyan Khatiwada
February 26, 2023
Professor Bender
CS231 A
Project 03

This class is used to create the landscape for the agent based simulation.
*/

import java.awt.*;
import java.util.ArrayList;

public class Landscape{
    int width;
    int height;
    LinkedList<Agent> agents;
    static boolean socialDead;
    static boolean antisocialDead;
    static boolean simulationended;

    public Landscape(int w, int h) {
        this.width = w;
        this.height = h;
        this.agents = new LinkedList<Agent>();
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public void addAgent(Agent a){
        agents.add(a);
    }

    public String toString(){
        return "Landscape with" + agents.size() + "agents";
    }

    public LinkedList<Agent> getNeighbors(double x0, double y0, double radius){
        LinkedList<Agent> neighbors = new LinkedList<Agent>();
        for (Agent a: agents){
            double dist = Math.sqrt(Math.pow((a.getX() -x0), 2)+ Math.pow((a.getY() - y0),2));
            if (dist <= radius) {
                neighbors.add(a);
            }
        }
        return neighbors;
    }

    public void draw(Graphics g){
        for (Agent a : agents){
            a.draw(g);   
        }
    }

    public void updateAgents(){
        for (Agent agent : agents){
            agent.updateState(this);
            
        }
    } 
    public static boolean hasSimulationEnded(Landscape scape) {
        simulationended= false;
        while (simulationended == false){
        ArrayList<Integer> socialDead = new ArrayList<Integer>();
        ArrayList<Integer> antisocialDead = new ArrayList<Integer>();
        for (Agent agent : scape.agents) {
            System.out.println(socialDead.size() + antisocialDead.size());
            if (agent instanceof SocialAgent) {

                if (((SocialAgent) agent).isMove()) {
                    socialDead.add(1);
                    System.out.println(socialDead);
                    
                } else{
                    socialDead.removeAll(socialDead);
                }
            } else {
                if (((AntiSocialAgent) agent).isMove()) {
                    antisocialDead.add(1);
                    System.out.println(antisocialDead);
                } else{
                    antisocialDead.removeAll(antisocialDead);
                }
            }
        
    }
    if (socialDead.size() + antisocialDead.size() ==0){
        simulationended = true;
        System.out.println(socialDead.size() + antisocialDead.size());
        System.out.println("^^^^^^^^ SIMULATION MUST END HERE");
        return simulationended;
    } else{
        simulationended = false;
        System.out.println(socialDead.size() + antisocialDead.size());
        System.out.println("^^^^^^^^ SIMULATION GOES ON!!!!");
        return simulationended;
    }
    }
    return simulationended;
}
    
}




    

