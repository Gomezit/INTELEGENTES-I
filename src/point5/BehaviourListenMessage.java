/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point5;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author andres
 */
public class BehaviourListenMessage extends CyclicBehaviour {
    
    MessageTemplate template;
    
    

    public BehaviourListenMessage(MessageTemplate template) {
        
        this.template = template;
    }

    @Override
    public void action() {
        
        ACLMessage message = myAgent.blockingReceive(template);
        System.out.println("Received a REQUEST message from " + message.getSender().getName() );
        String nameAgentToCreate = "searchServiceAgent01";
        
        PlatformController container = myAgent.getContainerController();
        
        try {
            container.createNewAgent(nameAgentToCreate, "point5.SearchServiceAgent", null).start();
            System.out.println("  Agent created");
        } catch (ControllerException e) {
            
            Logger.getLogger(BehaviourListenMessage.class.getName()).log(Level.SEVERE,null,e);
        }
        
    }
    
}
