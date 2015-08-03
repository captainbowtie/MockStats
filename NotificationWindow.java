/*This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    */
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