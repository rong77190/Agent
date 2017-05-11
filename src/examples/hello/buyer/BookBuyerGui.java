/**
 * Section 4.1.5
 *
 * definition of the BookBuyerGui interface 
 **/

package examples.hello.buyer;

public interface BookBuyerGui {
  void setAgent(BookBuyerAgent a);
  void show();
  void hide();
  void notifyUser(String message);
  void dispose();
}