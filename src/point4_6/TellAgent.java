/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point4_6;

import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author soporte
 */
public class TellAgent extends GuiAgent {

    ChatWindow owner;

    @Override
    protected void onGuiEvent(GuiEvent ge) {

        if (ge.getType() == 0) {

            AID id = new AID((String) ge.getParameter(0), AID.ISLOCALNAME);
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

            msg.addReceiver(id);

            msg.setContent((String) ge.getParameter(1));
            this.send(msg);

        }

        if (ge.getType() == 1) {

            LinkedList<String> nameAgents = searchServices();
            owner.addUserConnected(usersConnected(nameAgents));
        }

    }

    public String usersConnected(LinkedList<String> nameAgents) {

        String usersConnected = "";

        for (int i = 0; i < nameAgents.size(); i++) {

            usersConnected += "\n" + nameAgents.get(i);

        }

        return usersConnected;
    }

    protected LinkedList<String> searchServices() {
        
        doWait(1000);

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
                    if (sd.getName().equalsIgnoreCase("Chat")) {
                        out += " " + sd.getName();
                        namesAgents.add(result[i].getName().getLocalName());

                    }
                }
            }
        } catch (Exception fe) {
            System.err.println(getLocalName() + " search with DF unsucceeded - "
                    + fe.getMessage());
            doDelete();
        }

        return namesAgents;

    }

    @Override
    protected void setup() {

        owner = new ChatWindow(this, this.getLocalName());
        owner.setVisible(true);
        registrerService();
        ListenMessagesWindow es = new ListenMessagesWindow(this);
        this.addBehaviour(es);

    }

    private void registrerService() {

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(this.getAID());

        ServiceDescription sd = new ServiceDescription();
        sd.setType("Chat");
        sd.setName("Chat");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException ex) {
            System.err.println("The agent :" + getLocalName() + "can't register the service : " + ex.getMessage());
            doDelete();
        }
    }

}
