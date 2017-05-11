package examples.hello;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created by Administrator on 2017/5/4/004.
 *
 * Bob1  alice1propose
 * Bob2       alice2
 */
public class TemplateAgent2 extends Agent {


    MessageTemplate mt1 = null;
    protected void setup(){
        String alice = null;
        Object[] args = getArguments();
        if (args != null) {
            alice =(String)args[0];
        }
         mt1 = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.PROPOSE),
                MessageTemplate.MatchSender(new AID(alice, AID.ISLOCALNAME)));
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("Ping");
        for(int i = 0; i <= 2;i++){
            msg.addReceiver(new AID("Alice"+i,AID.ISLOCALNAME));
        }
        send(msg);
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive(mt1);
                if (msg != null){
                    String sender = msg.getSender().getLocalName();
                    char num1 = sender.charAt(sender.length()-1);

                    String receiver = getAID().getLocalName();
                    char num2 = receiver.charAt(receiver.length()-1);
                    if (num1 == num2){
                        System.out.println("gets "+msg.getPerformative()+" from "
                                +msg.getSender().getLocalName()+"= "+msg.getContent());
                    }
                    else{
                        System.out.println(" Over ");
                    }
                }else {
                    System.out.println("gets null ");
                    block();
                }
            }
        });
    }
}
