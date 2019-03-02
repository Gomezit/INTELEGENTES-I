/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point1;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.introspection.AddedBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author andres
 *
 * -gui ag:point1.AgentPoint1
 *
 */
public class FilterMessages extends CyclicBehaviour {

    private MessageTemplate template;
    //private  Agent agent;

    public FilterMessages(MessageTemplate mt) {

        super();
        this.template = mt;
        //this.agent = a;

    }

    @Override
    public void action() {

        ACLMessage msg = myAgent.blockingReceive(template);

        System.out.println("\nReceived a REQUEST message from" + msg.getSender().getName());

        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.CONFIRM);
        myAgent.send(reply);

        System.out.println("\nSending an INFORM message");

    }

}
