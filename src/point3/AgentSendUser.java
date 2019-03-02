/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point3;

import jade.core.Agent;

/**
 *
 * @author andres
 *
 * -gui ag1:point3_3.AgentSendUser;ag2:point3_3.AgentReceiverUser
 */
public class AgentSendUser extends Agent {

    @Override
    protected void setup() {
        BehaviourSendUser c = new BehaviourSendUser();
        addBehaviour(c);
    }

}
