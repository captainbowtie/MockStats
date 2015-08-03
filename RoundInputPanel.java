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
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RoundInputPanel extends JPanel  {
	private JLabel roundNumber;
	private JComboBox sideSelector = new JComboBox(new String[]{"Plaintiff","Defense"});
	private JLabel[] ballotLabels={new JLabel("Ballot 1"),new JLabel("Ballot 2")};;
	private JComboBox[] witnesses=new JComboBox[6];
	private JComboBox[] names=new JComboBox[22];
	private JTextField[] scores = new JTextField[56];
	public RoundInputPanel(int rndNumber, ArrayList<String> rosterList,ArrayList<String> witnessList,int[] data){
		//Do initial setup of GridBagLayout
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		gbc.fill=GridBagConstraints.BOTH;
		//Set side selector, Fill the witness and roster ComboBoxes, Fill the score fields
		sideSelector.setSelectedIndex(data[0]);
		String[] roster = new String[rosterList.size()+1];
		String[] witness=new String[witnessList.size()+1];
		roster[0]="Student";
		witness[0]="Witness";
		for(int i=0;i<rosterList.size();i++){
			roster[i+1]=rosterList.get(i);
		}
		for(int i=0;i<witnessList.size();i++){
			witness[i+1]=witnessList.get(i);
		}
		for(int i=0;i<6;i++){
			witnesses[i]=new JComboBox(witness);
			witnesses[i].setSelectedIndex(data[1+i]+1);
		}
		for(int i=0;i<22;i++){
			names[i]=new JComboBox(roster);
			names[i].setSelectedIndex(data[7+i]+1);
		}
		for(int i=0;i<56;i++){
			scores[i]=new JTextField(Integer.toString(data[29+i]),2);
		}
		//Set the text of the round number
		roundNumber = new JLabel("Round "+rndNumber);
		//Set the positioning constraints of all the components
		//Header
		gbc.gridwidth=2;
		gbc.gridx=0;
		gbl.setConstraints(roundNumber, gbc);
		gbc.gridx=2;
		gbl.setConstraints(sideSelector, gbc);
		gbl.setConstraints(ballotLabels[0],gbc);
		//
		gbc.gridwidth=1;
		//opening
		gbc.gridy=2;
		gbc.gridx=0;
		gbl.setConstraints(names[0],gbc);//p open name
		gbc.gridx=1;
		gbl.setConstraints(scores[0],gbc);//p open score
		gbc.gridx=2;
		gbl.setConstraints(scores[28],gbc);//d open score
		gbc.gridx=3;
		gbl.setConstraints(names[11],gbc);//d open name
		//first witness
		gbc.gridy=3;
		gbc.gridx=0;
		gbl.setConstraints(names[1], gbc);//p d1 atty name
		gbc.gridx=1;
		gbl.setConstraints(scores[1], gbc);//p d1 atty score
		gbc.gridx=2;
		gbl.setConstraints(scores[29],gbc);//d x1 atty score
		gbc.gridx=3;
		gbl.setConstraints(names[12],gbc);//d x1 atty name
		gbc.gridy=4;
		gbc.gridx=0;
		gbl.setConstraints(witnesses[0],gbc);//1st witness
		gbc.gridx=1;
		gbl.setConstraints(scores[2],gbc);//p d1 wit score
		gbc.gridy=5;
		gbc.gridx=0;
		gbl.setConstraints(names[2],gbc);//p d1 wit name
		gbc.gridx=1;
		gbl.setConstraints(scores[3],gbc);//p1 d1x wit score
		//second witness
		gbc.gridy=6;
		gbc.gridx=0;
		gbl.setConstraints(names[3], gbc);//p d1 atty name
		gbc.gridx=1;
		gbl.setConstraints(scores[4], gbc);//p d1 atty score
		gbc.gridx=2;
		gbl.setConstraints(scores[30],gbc);//d x1 atty score
		gbc.gridx=3;
		gbl.setConstraints(names[13],gbc);//d x1 atty name
		gbc.gridy=7;
		gbc.gridx=0;
		gbl.setConstraints(witnesses[1],gbc);//1st witness
		gbc.gridx=1;
		gbl.setConstraints(scores[5],gbc);//p d1 wit score
		gbc.gridy=8;
		gbc.gridx=0;
		gbl.setConstraints(names[4],gbc);//p d1 wit name
		gbc.gridx=1;
		gbl.setConstraints(scores[6],gbc);//p1 d1x wit score		
		//third witness
		gbc.gridy=9;
		gbc.gridx=0;
		gbl.setConstraints(names[5], gbc);//p d1 atty name
		gbc.gridx=1;
		gbl.setConstraints(scores[7], gbc);//p d1 atty score
		gbc.gridx=2;
		gbl.setConstraints(scores[31],gbc);//d x1 atty score
		gbc.gridx=3;
		gbl.setConstraints(names[14],gbc);//d x1 atty name
		gbc.gridy=10;
		gbc.gridx=0;
		gbl.setConstraints(witnesses[2],gbc);//1st witness
		gbc.gridx=1;
		gbl.setConstraints(scores[8],gbc);//p d1 wit score
		gbc.gridy=11;
		gbc.gridx=0;
		gbl.setConstraints(names[6],gbc);//p d1 wit name
		gbc.gridx=1;
		gbl.setConstraints(scores[9],gbc);//p1 d1x wit score
		//fourth witness
		gbc.gridy=12;
		gbc.gridx=0;
		gbl.setConstraints(names[7], gbc);
		gbc.gridx=1;
		gbl.setConstraints(scores[10],gbc);
		gbc.gridx=2;
		gbl.setConstraints(scores[32], gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[15],gbc);
		gbc.gridy=13;
		gbc.gridx=2;
		gbl.setConstraints(scores[33], gbc);
		gbc.gridx=3;
		gbl.setConstraints(witnesses[3],gbc);
		gbc.gridy=14;
		gbc.gridx=2;
		gbl.setConstraints(scores[34],gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[16],gbc);
		//fifth witness
		gbc.gridy=15;
		gbc.gridx=0;
		gbl.setConstraints(names[8], gbc);
		gbc.gridx=1;
		gbl.setConstraints(scores[11],gbc);
		gbc.gridx=2;
		gbl.setConstraints(scores[35], gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[17],gbc);
		gbc.gridy=16;
		gbc.gridx=2;
		gbl.setConstraints(scores[36], gbc);
		gbc.gridx=3;
		gbl.setConstraints(witnesses[4],gbc);
		gbc.gridy=17;
		gbc.gridx=2;
		gbl.setConstraints(scores[37],gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[18],gbc);
		//sixth witness
		gbc.gridy=18;
		gbc.gridx=0;
		gbl.setConstraints(names[9], gbc);
		gbc.gridx=1;
		gbl.setConstraints(scores[12],gbc);
		gbc.gridx=2;
		gbl.setConstraints(scores[38], gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[19],gbc);
		gbc.gridy=19;
		gbc.gridx=2;
		gbl.setConstraints(scores[39], gbc);
		gbc.gridx=3;
		gbl.setConstraints(witnesses[5],gbc);
		gbc.gridy=20;
		gbc.gridx=2;
		gbl.setConstraints(scores[40],gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[20],gbc);
		//closing
		gbc.gridy=21;
		gbc.gridx=0;
		gbl.setConstraints(names[10],gbc);
		gbc.gridx=1;
		gbl.setConstraints(scores[13],gbc);
		gbc.gridx=2;
		gbl.setConstraints(scores[41],gbc);
		gbc.gridx=3;
		gbl.setConstraints(names[21],gbc);
		//ballot Label 2
		gbc.gridy=22;
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbl.setConstraints(ballotLabels[1],gbc);
		gbc.gridwidth=1;
		gbc.gridx=1;
		for(int i=0;i<11;i++){
			gbc.gridy=23+i;
			gbl.setConstraints(scores[14+i],gbc);
		}
		gbc.gridy=36;
		gbl.setConstraints(scores[25],gbc);
		gbc.gridy=39;
		gbl.setConstraints(scores[26], gbc);
		gbc.gridy=42;
		gbl.setConstraints(scores[27], gbc);
		gbc.gridx=2;
		gbc.gridy=23;
		gbl.setConstraints(scores[42],gbc);
		gbc.gridy=24;
		gbl.setConstraints(scores[43],gbc);
		gbc.gridy=27;
		gbl.setConstraints(scores[44],gbc);
		gbc.gridy=30;
		gbl.setConstraints(scores[45],gbc);
		for(int i=0;i<10;i++){
			gbc.gridy=33+i;
			gbl.setConstraints(scores[46+i],gbc);
		}
		//add components to Panel
		add(roundNumber);
		add(sideSelector);
		add(ballotLabels[0]);
		add(ballotLabels[1]);
		for(int i=0;i<22;i++){
			add(names[i]);
		}
		for(int i=0;i<56;i++){
			add(scores[i]);
		}
		for(int i=0;i<6;i++){
			add(witnesses[i]);
		}
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public int correctValues(){
		int retVal=0;
		if(sideSelector.getSelectedIndex()==0){
			for(int i=0;i<11;i++){
				if(names[i].getSelectedIndex()==0 || names[i+11].getSelectedIndex()!=0){
					retVal=1;
				}
			}
		}else if(sideSelector.getSelectedIndex()==1){
			for(int i=11;i<22;i++){
				if(names[i].getSelectedIndex()==0 || names[i-11].getSelectedIndex()!=0){
					retVal=1;
				}
			}
		}
		for(int i=0;i<6;i++){
			if(witnesses[i].getSelectedIndex()==0){
				retVal=2;
			}
		}
		try{
			for(int i=0;i<56;i++){
				if(Integer.parseInt(scores[i].getText())>-1 && Integer.parseInt(scores[i].getText())<11){
					Integer.parseInt(scores[i].getText());
				}else{
					retVal=3;
				}
			}
		}catch(Exception NumberFormatException){
			retVal=3;
		}
		return retVal;
	}
	public int[] getRoundInformation(){
		int[] retArray = new int[85];
		retArray[0]=sideSelector.getSelectedIndex();
		for(int i=0;i<6;i++){
			retArray[i+1]=witnesses[i].getSelectedIndex()-1;
		}
		for(int i=0;i<22;i++){
			retArray[i+7]=names[i].getSelectedIndex()-1;
		}
		for(int i=0;i<56;i++){
			retArray[29+i]=Integer.parseInt(scores[i].getText());
		}
		return retArray;
	}
}
