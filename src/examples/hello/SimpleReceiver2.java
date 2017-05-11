package examples.hello;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Administrator on 2017/5/4/004.  BOb
 */
public class SimpleReceiver2 extends Agent {
    class SImpleReveiveBehaviour extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if (msg != null){
                System.out.println(getAID().getLocalName() + ": received the following message:");
                System.out.println(msg.toString());
                myAgent.doDelete();
            }else {
                System.out.println(getAID().getLocalName() + ": No message received");
            }
        }
    }

    protected void setup(){
        addBehaviour(new SImpleReveiveBehaviour());
    }
    public void takeDown(){
        System.out.println(getAID().getLocalName() + ":terminated");
    }
}
