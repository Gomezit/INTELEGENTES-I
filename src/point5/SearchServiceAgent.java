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
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author andres
 */
public class SearchServiceAgent extends Agent {

    public SearchServiceAgent() {

    }

    @Override
    protected void setup() {

        System.out.println(" ¡Hello!, I am the agent: " + this.getLocalName() + "\nsearching registered services ");
        doWait(3000);
        LinkedList<String> nameAgents = this.searchServices();

    }

    protected LinkedList<String> searchServices() {

        DFAgentDescription dfd = new DFAgentDescription();
        LinkedList<String> namesAgents = new LinkedList<>();
        String searchService = "CreateAgent";

        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            System.out.println("total wanted " + result.length);

            for (int i = 0; i < result.length; i++) {

                String out = result[i].getName() + " provides";
                Iterator iter = result[i].getAllServices();
                while (iter.hasNext()) {
                    ServiceDescription sd = (ServiceDescription) iter.next();

                    out += " " + sd.getName();
                    namesAgents.add(result[i].getName().getLocalName());

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
