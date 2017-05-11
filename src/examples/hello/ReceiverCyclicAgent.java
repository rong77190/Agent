package examples.hello;


import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class ReceiverCyclicAgent extends Agent {

    public void setup(){
        addBehaviour(new CyclicBehaviour(){
            public void action(){
                ACLMessage message = receive();
                if (message != null){
                    System.out.println("I receive this message:"+message.getContent());
                }
            }
        });
    }
}
