package Temp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationWindow extends JFrame{
		private JLabel notificationText=new JLabel();
		private JButton notificationButton=new JButton("Back");
		private JPanel notificationPanel=new JPanel();
		NotificationWindow(String s){
			notificationButton.addActionListener(new nButtonListener());
			notificationText.setText(s);
			notificationPanel.add(notificationText);
			notificationPanel.add(notificationButton);
			add(notificationPanel);
			pack();
			setVisible(true);
		}
		private class nButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==notificationButton){
					setVisible(false);
				}
				
			}
			
		}
	}