/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point3;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class BehaviourSendUser extends OneShotBehaviour {

    Scanner teclado;

    public BehaviourSendUser() {
        teclado = new Scanner(System.in);
    }

    @Override
    public void action() {
        String login;
        String pass;
        System.out.println("Put the user name");
        login = teclado.nextLine();
        System.out.println("Put the password");
        pass = teclado.nextLine();
        ValidateData u = new ValidateData(login, pass);
        System.out.println("Name agent that receive the user");
        String agRecibe = teclado.nextLine();

        //Send message ACL
        AID id = new AID(agRecibe, AID.ISLOCALNAME);
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(id); //Indicate who receiver
        try {
            msg.setContentObject(u); //Send Object 
        } catch (Exception ce) {
            System.out.println(ce);
        }

        myAgent.send(msg);
        myAgent.doDelete();

    }

}
