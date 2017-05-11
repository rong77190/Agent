package examples.hello;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class TickerAgent extends Agent {
    public void setup(){
        addBehaviour(new TickerBehaviour(this,5000) {
            protected int n = 0;
            @Override
            protected void onTick() {
                System.out.println("I am doing Tick Behaviour.Times:"+ ++n);
                if(n == 6){
                    stop();
                }
            }
        });
    }
}
