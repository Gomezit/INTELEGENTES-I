/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point2;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class SearchService extends Agent {

    Scanner keyboard;

    public SearchService() {
        keyboard = new Scanner(System.in);
    }

    @Override
    protected void setup() {

        System.out.println(" ¡Hello!, I am the agent :" + this.getLocalName() + "searching registered services ");
        doWait(3000);
        LinkedList<String> nameAgents = this.searchServices();

        System.out.println("Start range");
        int _start = Integer.parseInt(keyboard.nextLine());
        System.out.println("Final range");
        int _final = Integer.parseInt(keyboard.nextLine());

        int result = _final / nameAgents.size();
        int acuStart = _start;
        int acuFinal = result;
        String messagesReceived = "";

        for (int i = 0; i < nameAgents.size(); i++) {

            sendMessages(nameAgents.get(i), acuStart, acuFinal);
            doWait(4000);
            messagesReceived += receive().getContent();
            acuStart = acuFinal + 1;
            acuFinal += result;
        }

        System.out.println("Messages received : " + messagesReceived);

    }

    public void sendMessages(String nameAgent, int _start, int _final) {

        AID id = new AID(nameAgent, AID.ISLOCALNAME);
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

        msg.addReceiver(id);

        msg.setContent(_start + "," + _final);
        this.send(msg);

    }

    protected LinkedList<String> searchServices() {

        DFAgentDescription dfd = new DFAgentDescription();
        LinkedList<String> namesAgents = new LinkedList<>();

        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            System.out.println("total wanted " + result.length);

            for (int i = 0; i < result.length; i++) {

                String out = result[i].getName() + " provides";
                Iterator iter = result[i].getAllServices();
                while (iter.hasNext()) {
                    ServiceDescription sd = (ServiceDescription) iter.next();
                    if (sd.getName().equalsIgnoreCase("Cousins")) {
                        out += " " + sd.getName();
                        namesAgents.add(result[i].getName().getLocalName());

                    }
                }
                // System.out.println(this.getLocalName()+": "+out);
            }
        } catch (Exception fe) {
            System.err.println(getLocalName() + " search with DF unsucceeded - "
                    + fe.getMessage());
            doDelete();
        }

        return namesAgents;

    }

}
