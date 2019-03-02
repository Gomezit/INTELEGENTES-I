/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point2;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author andres
 *
 * -gui
 * rag1:point2.RegisterService;rag2:point2.RegisterService;rag3:point2.RegisterService;sag1:point2.SearchService
 */
public class RegisterService extends Agent {

    public String service;

    protected void setup() {
        //Object [] arg2 = getArguments();

        //service = (String) arg2[0];
        System.out.println("The name of this agent is :" + this.getLocalName() + " : I give the service ");
        registrerService();
        BehaviourGetCousins b = new BehaviourGetCousins();
        this.addBehaviour(b);

    }

    private void registrerService() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(this.getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("Cousins");
        sd.setName("Cousins");

        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException ex) {
            System.err.println("The agent :" + getLocalName() + "can't register the service : " + ex.getMessage());
            doDelete();
        }

    }

}
