/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point3;

import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author andres
 */
public class BehaviourReceiveUser extends OneShotBehaviour {

    @Override
    public void action() {
        ACLMessage resp = myAgent.blockingReceive();
        if (resp != null) {
            if (resp.getPerformative() == ACLMessage.REQUEST) {
                try {
                    Object obj
                            = resp.getContentObject();
                    if (obj instanceof ValidateData) {
                        ValidateData u = (ValidateData) obj;
                        //System.out.println( pb.toString());
                        System.out.println("Login " + u.getUser());
                        System.out.println("pass " + u.getPassword());
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        myAgent.doDelete();
    }

}
