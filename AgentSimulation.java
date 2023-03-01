package Extensions;

/*
Kalyan Khatiwada
February 26, 2023
Professor Bender
CS231 A
Project 03

This class does the agent based simulation. This can be run by:
To compile: javac AgentSimulation.java
To run: java AgentSimulation
*/

import java.util.Random;

public class AgentSimulation {
    public static void main(String[] args) throws InterruptedException {
        int landscapeWidth = 500;
        int landscapeHeight = 500;
        int numAgents = 200;
        int radius = 25;
        LinkedList<String> listPossibleAttributes;
        
        listPossibleAttributes = new LinkedList<String>();
        listPossibleAttributes.add("philosophy");
        listPossibleAttributes.add("art");
        listPossibleAttributes.add("sports");
        listPossibleAttributes.add("anthropology");
        listPossibleAttributes.add("technology");
        listPossibleAttributes.add("sciences");
        listPossibleAttributes.add("environment");
        listPossibleAttributes.add("economics");
        for (String attribute: listPossibleAttributes){
            System.out.println(attribute);
        }
        
        Landscape scape = new Landscape(landscapeWidth, landscapeHeight);
        Random random = new Random();
        
        // add agents to the landscape
        for (int i = 0; i < numAgents; i++) {
            int index1 = (int)(Math.random() * listPossibleAttributes.size());
            int index2 = (int)(Math.random() * listPossibleAttributes.size());
            int index3 = (int)(Math.random() * listPossibleAttributes.size());
            LinkedList<String> attributes = new LinkedList<String>();
            attributes.add(listPossibleAttributes.get(index1));
            attributes.add(listPossibleAttributes.get(index2));
            attributes.add(listPossibleAttributes.get(index3));
            
            if (random.nextBoolean()) {
                scape.addAgent(new SocialAgent(random.nextDouble() * scape.getWidth(),
                                                random.nextDouble() * scape.getHeight(),
                                                radius, attributes ));
            } else {
                scape.addAgent(new AntiSocialAgent(random.nextDouble() * scape.getWidth(),
                                                    random.nextDouble() * scape.getHeight(),
                                                    radius, attributes));
            }
        }
        
        LandscapeDisplay display = new LandscapeDisplay(scape);
        
        long startTime = System.currentTimeMillis();
        boolean simulation = true;
        // while the simulation is running and true:
        while (simulation) {
            Thread.sleep(20);
            scape.updateAgents();
            display.repaint();
            if (Landscape.hasSimulationEnded(scape) == true){
                simulation = false;
            }
        }
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Total time: " + totalTime + "ms");
    }

}

