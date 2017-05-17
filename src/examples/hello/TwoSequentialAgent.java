package examples.hello;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;

/**
 * two sequential Agent examples
 * output:
 seq1:first child behaviour
 seq2:first child behaviour
 seq1:first child behaviour
 seq2:second child behaviour
 seq1:second child behaviour
 seq2:second child behaviour
 *
 */
public class TwoSequentialAgent extends Agent {
    protected void setup(){
        SequentialBehaviour suq1 = new SequentialBehaviour(this);
        suq1.addSubBehaviour(new Behaviour() {
            int step  = 0;
            @Override
            public void action() {
                System.out.println("seq1:first child behaviour");
                step++;
            }

            @Override
            public boolean done() {
                return step == 2;
            }
        });
        suq1.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("seq1:second child behaviour");
            }
        });

        SequentialBehaviour seq2 = new SequentialBehaviour(this);
        seq2.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("seq2:first child behaviour");
            }
        });
        seq2.addSubBehaviour(new Behaviour() {
            int step = 0;
            @Override
            public void action() {
                System.out.println("seq2:second child behaviour");
                step++;
            }

            @Override
            public boolean done() {
                return step == 2;
            }
        });

        addBehaviour(suq1);
        addBehaviour(seq2);
    }
}
