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
 */
public class AgentReceiverUser extends Agent {

    @Override
    protected void setup() {
        BehaviourReceiveUser c = new BehaviourReceiveUser();
        addBehaviour(c);
    }

}
