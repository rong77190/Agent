package examples.hello;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Administrator on 2017/5/4/004.
 *
 *
 * Alice1 Alice2
 */
public class Responder2 extends Agent {
    protected void setup(){
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null){
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("Yes,I am here.");
                    send(reply);

                    reply.setPerformative(ACLMessage.PROPOSE);
                    String msgcontent = "Tell me your opinion about "+reply.getSender().getLocalName();
                    reply.setContent(msgcontent);
                    send(reply);
                } else {
                    block();
                }
            }
        });
    }
}
