/**
 * Section 4.1.5

 * skeleton of the Book-BuyerAgent class.
 **/
package examples.hello.buyer;

import jade.core.*;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;

import java.util.Vector;
import java.util.Date;



public class BookBuyerAgent extends Agent {
  // The list of known seller agents
  private Vector sellerAgents = new Vector();

  // The GUI to interact with the user
  private BookBuyerGui myGui;

  /**
   * Agent initializations
   **/
  protected void setup() {
    // Printout a welcome message
    System.out.println("Buyer-agent "+getAID().getName()+" is ready.");
    
    // Get names of seller agents as arguments
    Object[] args = getArguments();
    if (args != null && args.length > 0) {
      for (int i = 0; i < args.length; ++i) {
        AID seller = new AID((String) args[i], AID.ISLOCALNAME);
        sellerAgents.addElement(seller);
      }
    }

    // Show the GUI to interact with the user
    myGui = new BookBuyerGuiImpl();
    myGui.setAgent(this);
    myGui.show();

    /** This piece of code, to search services with the DF, is explained
     * in the book in section 4.4.3
    **/
    // Update the list of seller agents every minute
    /*addBehaviour(new TickerBehaviour(this, 6000).......*/
    addBehaviour(new TickerBehaviour(this,6000) {
      @Override
      protected void onTick() {
        //search sellers with DF
        DFAgentDescription df = new DFAgentDescription();
        ServiceDescription service = new ServiceDescription();
        service.setType("book-trade");
        df.addServices(service);
        try {
          DFAgentDescription[] result = DFService.search(getAgent(),
                  df);
          //update sellerAgents
          sellerAgents.clear();
          for (int i = 0; i < result.length; i++) {
            sellerAgents.add(result[i].getName());
          }
        } catch (FIPAException fe) {
          fe.printStackTrace();
        }
      }
    });
  }

  /**
   * Agent clean-up
   **/
  protected void takeDown() {
    // Dispose the GUI if it is there
    if (myGui != null) {
      myGui.dispose();
    }

    // Printout a dismissal message
    System.out.println("Buyer-agent "+getAID().getName()+"terminated.");
  }

  /**
   * This method is called by the GUI when the user inserts a new
   * book to buy
   * @param title The title of the book to buy
   * @param maxPrice The maximum acceptable price to buy the book
   * @param deadline The deadline by which to buy the book
  **/
  public void purchase(String title, int maxPrice, Date deadline) {
    // the following line is in the book at page 62
    addBehaviour(new PurchaseManager(this, title, maxPrice, deadline));
  }

	/**
   * This method is called by the GUI. At the moment it is not implemented.
   **/
  /*public void setCreditCard(String creditCarNumber) {
  }*/


  /**
   * Section 4.2.4
   **/
  private class PurchaseManager extends TickerBehaviour {
    private String title;
    private int maxPrice, startPrice;
    private long deadline, initTime, deltaT;

    private PurchaseManager(Agent a, String t, int mp, Date d) {
      super(a, 6000); // tick every minute
      title = t;
      maxPrice = mp;
      deadline = d.getTime();
      initTime = System.currentTimeMillis();
      deltaT = deadline - initTime;
    }

    public void onTick() {
      long currentTime = System.currentTimeMillis();
      if (currentTime > deadline) {
        // Deadline expired
        myGui.notifyUser("Cannot buy book "+title);
        stop();
      }
      else {
        // Compute the currently acceptable price and start a negotiation
        long elapsedTime = currentTime - initTime;
        int acceptablePrice = (int)Math.round(1.0 * maxPrice * (1.0 * elapsedTime / deltaT));
        // System.out.println("elapsedTime"+elapsedTime+"deltaT"+deltaT+"acceptablePrice"+acceptablePrice+"maxPrice="+maxPrice);
        myAgent.addBehaviour(new BookNegotiator(title, acceptablePrice, this));
      }
    }
  }



  /**
   * Section 4.3.5 of the book,
   * Inner class BookNegotiator.
   * This is the behaviour used by Book-buyer agents to actually
   * negotiate with seller agents the purchase of a book.
  **/
  private class BookNegotiator extends Behaviour {
    //......
    private
	  int step = 0;

    public BookNegotiator(String t, int p, PurchaseManager m) {
      //...........
      super(null);

    }

    public void action() {
    //.......
    }

    public boolean done() {
       return step == 4;
    }
  } // End of inner class BookNegotiator
}







