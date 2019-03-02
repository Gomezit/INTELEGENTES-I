/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package point2;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author andres
 */
public class BehaviourGetCousins extends CyclicBehaviour {

    @Override
    public void action() {

        ACLMessage m1 = myAgent.blockingReceive();
        String[] rangeNumbers = m1.getContent().split(",");
        int _start = Integer.parseInt(rangeNumbers[0]);
        int _final = Integer.parseInt(rangeNumbers[1]);
        String output = "";

        for (int i = _start; i < _final; i++) {
            output += isPrime(i) ? i + "," : "";
        }

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(m1.getSender());
        msg.setContent(output);
        myAgent.send(msg);

    }

    // Function to check if a number is prime or not 
    static boolean isPrime(int n) {
        // Corner cases 
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        // This is checked so that we can skip 
        // middle five numbers in below loop 
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

}
