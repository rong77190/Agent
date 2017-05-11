package examples.hello;

import jade.core.Agent;
import jade.core.behaviours.*;
public class WorkingAgent extends Agent {
	public void setup() {
		addBehaviour(new WorkingBehaviour2());
		addBehaviour(new WorkingBehaviour1());
		addBehaviour(new WorkingBehaviour2());
		System.out.println(getLocalName() + ": starting to work!");
	}
	public void takeDown() {
	System.out.println(getLocalName() + ": done working!");
	}
	class WorkingBehaviour1 extends Behaviour {
		private int step = 0;
		public void action() {
			System.out.println(myAgent.getLocalName() + ": doing behaviour1." + step++);
		}
		public boolean done() {
			return step == 3;
		}
		public int onEnd() {
			myAgent.doDelete();
			System.out.println(myAgent.getLocalName() + ": Finished!");
			return super.onEnd();
		}
	}
	class WorkingBehaviour2 extends OneShotBehaviour {
		public void action() {
			System.out.println(myAgent.getLocalName() + ": doing behaviour2.");
		}
}
}
