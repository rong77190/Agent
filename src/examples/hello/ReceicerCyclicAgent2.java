package examples.hello;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Administrator on 2017/5/4/004.
 */
public class ReceicerCyclicAgent2 extends Agent {
    protected void setup(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null){
                    System.out.println("I receiver this Message:"+msg.getContent());
                }else {
                    System.out.println("I have nott received ant message");
                    block();
                }
            }
        });
    }

}
