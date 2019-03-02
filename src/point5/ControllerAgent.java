/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point5;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author andres
 * 
 * -gui ag1:point5.ControllerAgent
 * 
 */
public class ControllerAgent extends Agent {

    @Override
    protected void setup() {
        
       this.registrerService();
       MessageTemplate myTemplate = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
       BehaviourListenMessage ls = new BehaviourListenMessage(myTemplate);
       this.addBehaviour(ls);
        
    }
    
    private void registrerService() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(this.getAID());

        ServiceDescription sd1 = new ServiceDescription();        
        sd1.setType("CreateAgent");
        sd1.setName("CreateAgent");
        
        ServiceDescription sd2 = new ServiceDescription();        
        sd2.setType("Cousins");
        sd2.setName("Cousins");

        dfd.addServices(sd1);
        dfd.addServices(sd2);
        
        doWait(3000);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException ex) {
            System.err.println("The agent :" + getLocalName() + "can't register the service : " + ex.getMessage());
            doDelete();
        }

    }

}
