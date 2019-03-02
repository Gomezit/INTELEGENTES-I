/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point4_6;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author soporte
 */
public class ListenMessagesWindow extends CyclicBehaviour {

    TellAgent agent;

    public ListenMessagesWindow(TellAgent a) {
        this.agent = a;
    }

    @Override
    public void action() {

        ACLMessage m1 = agent.receive();

        if (m1 != null && m1.getPerformative() == ACLMessage.REQUEST) {
            agent.owner.addMessage(m1.getSender().getLocalName() + "--->" + m1.getContent());

        }

    }

}
