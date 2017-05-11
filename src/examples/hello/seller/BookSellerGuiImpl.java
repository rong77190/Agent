package examples.hello.seller;

import jade.gui.TimeChooser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Date;

/**
   This is the GUI of the agent that tries to sell books on behalf of its user
 */
public class BookSellerGuiImpl extends JFrame implements BookSellerGui {
	private BookSellerAgent myAgent;
	
	private JTextField titleTF, desiredPriceTF, minPriceTF, deadlineTF;
	private JButton setDeadlineB;
	private JButton setCCB, sellB, resetB, exitB;
	private JTextArea logTA;
	
	private Date deadline;
	
  public void setAgent(BookSellerAgent a) {
		myAgent = a;
		setTitle(myAgent.getName());
  }

	public BookSellerGuiImpl() {
		super();
		
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );

		

		JPanel rootPanel = new JPanel();
		rootPanel.setLayout(new GridBagLayout());
    rootPanel.setMinimumSize(new Dimension(330, 125));
    rootPanel.setPreferredSize(new Dimension(330, 125));
		
    ///////////
    // Line 0
    ///////////
		JLabel l = new JLabel("Book to sell:");
    l.setHorizontalAlignment(SwingConstants.LEFT);
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(l, gridBagConstraints);

    titleTF = new JTextField(64);
    titleTF.setMinimumSize(new Dimension(222, 20));
    titleTF.setPreferredSize(new Dimension(222, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 3;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(titleTF, gridBagConstraints);

    ///////////
    // Line 1
    ///////////
		l = new JLabel("Best price:");
    l.setHorizontalAlignment(SwingConstants.LEFT);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(l, gridBagConstraints);

    desiredPriceTF = new JTextField(64);
    desiredPriceTF.setMinimumSize(new Dimension(70, 20));
    desiredPriceTF.setPreferredSize(new Dimension(70, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(desiredPriceTF, gridBagConstraints);

		l = new JLabel("Min price:");
    l.setHorizontalAlignment(SwingConstants.LEFT);
    l.setMinimumSize(new Dimension(70, 20));
    l.setPreferredSize(new Dimension(70, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(l, gridBagConstraints);

    minPriceTF = new JTextField(64);
    minPriceTF.setMinimumSize(new Dimension(70, 20));
    minPriceTF.setPreferredSize(new Dimension(70, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(minPriceTF, gridBagConstraints);

    ///////////
    // Line 2
    ///////////
		l = new JLabel("Deadline:");
    l.setHorizontalAlignment(SwingConstants.LEFT);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(l, gridBagConstraints);

    deadlineTF = new JTextField(64);
    deadlineTF.setMinimumSize(new Dimension(146, 20));
    deadlineTF.setPreferredSize(new Dimension(146, 20));
    deadlineTF.setEnabled(false);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(deadlineTF, gridBagConstraints);

    setDeadlineB = new JButton("Set");
    setDeadlineB.setMinimumSize(new Dimension(70, 20));
    setDeadlineB.setPreferredSize(new Dimension(70, 20));
		setDeadlineB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		Date d = deadline;
	  		if (d == null) {
	  			d = new Date();
	  		}
	  		TimeChooser tc = new TimeChooser(d);
	  		if (tc.showEditTimeDlg(BookSellerGuiImpl.this) == TimeChooser.OK) {
	  			deadline = tc.getDate();
	  			deadlineTF.setText(deadline.toString());
	  		}
	  	}
		} );
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(setDeadlineB, gridBagConstraints);    
		
    rootPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
    
    getContentPane().add(rootPanel, BorderLayout.NORTH);
    
    
    logTA = new JTextArea();
    logTA.setEnabled(false);
    JScrollPane jsp = new JScrollPane(logTA);
    jsp.setMinimumSize(new Dimension(300, 180));
    jsp.setPreferredSize(new Dimension(300, 180));
    JPanel p = new JPanel();
    p.setBorder(new BevelBorder(BevelBorder.LOWERED));
    p.add(jsp);
    getContentPane().add(p, BorderLayout.CENTER);
  
    
    p = new JPanel();
    sellB = new JButton("Sell");
		sellB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		String title = titleTF.getText();
	  		int desiredPrice = -1;
	  		int minPrice = -1;	  		
	  		if (title != null && title.length() > 0) {
	  			if (deadline != null && deadline.getTime() > System.currentTimeMillis()) {
			  		try {
				  		desiredPrice = Integer.parseInt(desiredPriceTF.getText());
				  		try {
					  		minPrice = Integer.parseInt(minPriceTF.getText());
					  		if (minPrice <= desiredPrice) {
					  			// myAgent.addToCatalogue(title, desiredPrice, minPrice, deadline.getTime());
					  			myAgent.putForSale(title, desiredPrice, minPrice, deadline);
                  notifyUser("PUT FOR SALE: "+title+" between "+desiredPrice+" and "+minPrice+" by "+deadline); 
					  		}
					  		else {
					  			// minPrice > desiredPrice
					  			JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Min price must be cheaper than best price", "WARNING", JOptionPane.WARNING_MESSAGE);
					  		}					  			
				  		}
				  		catch (Exception ex1) {
				  			// Invalid max cost
				  			JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Invalid min price", "WARNING", JOptionPane.WARNING_MESSAGE);
				  		}
			  		}
			  		catch (Exception ex2) {
			  			// Invalid desired cost
			  			JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Invalid best price", "WARNING", JOptionPane.WARNING_MESSAGE);
			  		}
	  			}
	  			else {
	  				// No deadline specified
		  			JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "Invalid deadline", "WARNING", JOptionPane.WARNING_MESSAGE);
	  			}
	  		}
  			else {
  				// No book title specified
	  			JOptionPane.showMessageDialog(BookSellerGuiImpl.this, "No book title specified", "WARNING", JOptionPane.WARNING_MESSAGE);
  			}
	  	}
		} );
    resetB = new JButton("Reset");
		resetB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		titleTF.setText("");
	  		desiredPriceTF.setText("");
	  		minPriceTF.setText("");
	  		deadlineTF.setText("");
	  		deadline = null;
	  	}
		} );
    exitB = new JButton("Exit");
		exitB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		myAgent.doDelete();
	  	}
		} );
		
    sellB.setPreferredSize(resetB.getPreferredSize());
    exitB.setPreferredSize(resetB.getPreferredSize());
    
    p.add(sellB);
    p.add(resetB);
    p.add(exitB);
    
    p.setBorder(new BevelBorder(BevelBorder.LOWERED));
    getContentPane().add(p, BorderLayout.SOUTH);
    
    pack();
    
    setResizable(false);
	}
	
	public void notifyUser(String message) {
		logTA.append(message+"\n");
	}
}	