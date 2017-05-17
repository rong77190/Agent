package examples.hello;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

/**
 * Created by Administrator on 2017/5/17/017.
 * Parallel Agent
 *
 * output:
 * first child
 */
public class ParallelAgent2 extends Agent {
    protected void setup(){
        ParallelBehaviour pb = new ParallelBehaviour(this,ParallelBehaviour.WHEN_ANY);
        pb.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("first child");
            }
        });
        pb.addSubBehaviour(new WakerBehaviour(this,6000) {
            @Override
            protected void onWake() {
                super.onWake();
                System.out.println("second child");
            }
        });
        pb.addSubBehaviour(new Behaviour() {
            int step = 0;
            @Override
            public void action() {
                System.out.println("third child");
                step++;
            }

            @Override
            public boolean done() {
                return step == 2;
            }
        });
        addBehaviour(pb);
    }
}
