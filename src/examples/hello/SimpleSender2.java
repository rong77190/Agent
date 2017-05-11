package examples.hello;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Administrator on 2017/5/4/004. Alice
 */
public class SimpleSender2 extends Agent {
    protected void setup(){
        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println(getLocalName()+":about to inform Bob Hello");
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.setSender(getAID());
                msg.addReceiver(new AID("Bob",AID.ISLOCALNAME));
                msg.setContent("Yes,I am here");
                send(msg);
                System.out.println(getAID().getLocalName()+":send msg to ");
                myAgent.doDelete();
            }
        });
    }


    public void takeDown(){
        System.out.println(getAID().getLocalName()+":teminated");
    }
}
