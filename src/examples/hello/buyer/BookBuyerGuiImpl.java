package examples.hello.buyer;

import jade.gui.TimeChooser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Date;

/**
   J2SE (Swing-based) implementation of the GUI of the agent that 
   tries to buy books on behalf of its user
 */
public class BookBuyerGuiImpl extends JFrame implements BookBuyerGui {
	private BookBuyerAgent myAgent;
	
	private JTextField titleTF, desiredCostTF, maxCostTF, deadlineTF;
	private JButton setDeadlineB;
	private JButton setCCB, buyB, resetB, exitB;
	private JTextArea logTA;
	
	private Date deadline;
	
	public BookBuyerGuiImpl() {
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
		JLabel l = new JLabel("Book to buy:");
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
		/*l = new JLabel("Best cost:");
    l.setHorizontalAlignment(SwingConstants.LEFT);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(5, 3, 0, 3);
    rootPanel.add(l, gridBagConstraints);

    desiredCostTF = new JTextField(64);
    desiredCostTF.setMinimumSize(new Dimension(70, 20));
    desiredCostTF.setPreferredSize(new Dimension(70, 20));
    desiredCostTF.setEditable(false); //FIXME just for this example
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(desiredCostTF, gridBagConstraints);*/

		l = new JLabel("Max cost:");
    l.setHorizontalAlignment(SwingConstants.LEFT);
    l.setMinimumSize(new Dimension(70, 20));
    l.setPreferredSize(new Dimension(70, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(l, gridBagConstraints);

    maxCostTF = new JTextField(64);
    maxCostTF.setMinimumSize(new Dimension(70, 20));
    maxCostTF.setPreferredSize(new Dimension(70, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(maxCostTF, gridBagConstraints);

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
	  		if (tc.showEditTimeDlg(BookBuyerGuiImpl.this) == TimeChooser.OK) {
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
    
    /*setCCB = new JButton("Set CreditCard");
		setCCB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		String cc = JOptionPane.showInputDialog(BookBuyerGuiImpl.this, "Insert the Credit Card number");
	  		if (cc != null && cc.length() > 0) {
	  			myAgent.setCreditCard(cc);
	  		}
	  		else {
	  			JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid Credit Card number", "WARNING", JOptionPane.WARNING_MESSAGE);
	  		}
	  	}
		} );
		//setCCB.setMinimumSize(new Dimension(70, 20));
    //setCCB.setPreferredSize(new Dimension(70, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
    gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new Insets(5, 3, 0, 3);
    rootPanel.add(setCCB, gridBagConstraints);*/
		
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
    buyB = new JButton("Buy");
		buyB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		String title = titleTF.getText();
	  		int desiredCost = -1;
	  		int maxCost = -1;	  		
	  		if (title != null && title.length() > 0) {
	  			if (deadline != null && deadline.getTime() > System.currentTimeMillis()) {
			  		try {
				  		//desiredCost = Integer.parseInt(desiredCostTF.getText());
				  		try {
					  		maxCost = Integer.parseInt(maxCostTF.getText());
					  		// if (maxCost >= desiredCost) {
					  			// myAgent.purchase(title, desiredCost, maxCost, deadline.getTime());
					  			myAgent.purchase(title, maxCost, deadline);
                  notifyUser("PUT FOR BUY: "+title+" at max "+maxCost+" by "+deadline); 
					  		//}
					  		//else {
					  			// Max cost < desiredCost
					  			//JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Max cost must be greater than best cost", "WARNING", JOptionPane.WARNING_MESSAGE);
					  		//}					  			
				  		}
				  		catch (Exception ex1) {
				  			// Invalid max cost
				  			JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid max cost", "WARNING", JOptionPane.WARNING_MESSAGE);
				  		}
			  		}
			  		catch (Exception ex2) {
			  			// Invalid desired cost
			  			JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid best cost", "WARNING", JOptionPane.WARNING_MESSAGE);
			  		}
	  			}
	  			else {
	  				// No deadline specified
		  			JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "Invalid deadline", "WARNING", JOptionPane.WARNING_MESSAGE);
	  			}
	  		}
  			else {
  				// No book title specified
	  			JOptionPane.showMessageDialog(BookBuyerGuiImpl.this, "No book title specified", "WARNING", JOptionPane.WARNING_MESSAGE);
  			}
	  	}
		} );
    resetB = new JButton("Reset");
		resetB.addActionListener(new ActionListener(){
	  	public void actionPerformed(ActionEvent e) {
	  		titleTF.setText("");
	  		desiredCostTF.setText("");
	  		maxCostTF.setText("");
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
		
    buyB.setPreferredSize(resetB.getPreferredSize());
    exitB.setPreferredSize(resetB.getPreferredSize());
    
    p.add(buyB);
    p.add(resetB);
    p.add(exitB);
    
    p.setBorder(new BevelBorder(BevelBorder.LOWERED));
    getContentPane().add(p, BorderLayout.SOUTH);
    
    pack();
    
    setResizable(false);
	}

	public void setAgent(BookBuyerAgent a) {
		myAgent = a;
		setTitle(myAgent.getName());
	}
		
	public void notifyUser(String message) {
		logTA.append(message+"\n");
	}
}	