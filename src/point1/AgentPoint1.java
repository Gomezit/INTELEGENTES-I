/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point1;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author soporte
 */
public class AgentPoint1 extends Agent {

    @Override
    protected void setup() {
        System.out.println("I am the agent: " + this.getLocalName());

        MessageTemplate myTemplate = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
        FilterMessages behaviour = new FilterMessages(myTemplate);
        this.addBehaviour(behaviour);

    }

}
