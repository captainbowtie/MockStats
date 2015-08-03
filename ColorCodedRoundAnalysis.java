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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorCodedRoundAnalysis extends JPanel {
	public ColorCodedRoundAnalysis(int rndNumber, ArrayList<String> rosterList,ArrayList<String> witnessList,int[] data){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gbl);
		gbc.fill=GridBagConstraints.BOTH;
		JLabel roundNumber = new JLabel("Round "+Integer.toString(rndNumber));
		JLabel side = null;
		if(data[0]==0){
			side=new JLabel("Plaintiff");
		}else{
			side =new JLabel("Defense");
		}
		//calculate the team and ballot average for each ballot
		double b1Numerator=0.0;
		double b2Numerator=0.0;
		double b1TeamNumerator=0.0;
		double b2TeamNumerator=0.0;
		for(int i=0;i<14;i++){
			b1Numerator+=data[i+29];
			b1Numerator+=data[i+57];
			b2Numerator+=data[i+43];
			b2Numerator+=data[i+71];
			if(data[0]==0){
				b1TeamNumerator+=data[i+29];
				b2TeamNumerator+=data[i+43];
			}else{
				b1TeamNumerator+=data[i+57];
				b2TeamNumerator+=data[i+71];
			}
		}
		double b1Average=(b1Numerator)/28.0;
		double b2Average=(b2Numerator)/28.0;
		double b1TeamAverage=(b1TeamNumerator)/14.0;
		double b2TeamAverage=(b2TeamNumerator)/14.0;
		JLabel[] partLabels = new JLabel[6];
		partLabels[0]=new JLabel("Direct (A)");
		partLabels[1]=new JLabel("Cross (A)");
		partLabels[2]=new JLabel("Direct (W)");
		partLabels[3]=new JLabel("Cross (W)");
		partLabels[4]=new JLabel("Opening");
		partLabels[5]=new JLabel("Closing");
		JLabel[] ballotLabels = new JLabel[12];
		for(int i=0;i<6;i++){
			ballotLabels[i*2]=new JLabel("Ballot 1");
			ballotLabels[i*2+1]=new JLabel("Ballot 2");
		}
		/*Here, perhaps a note of clarification would be helpful:
		 * The roster names go primarily by section, then by top to bottom
		 * Witness names go by witness call order
		 * Therefore, if Drake is plaintiff, then roster would be D1, D2, D3, X1, X2, X3, W1, W2, W3, O, C
		 * If Drake is defense, then roster would be X1, X2, X3, D1, D2, D3, W1, W2, W3, O, C
		 */
		//first the witnessses
		//there are 12, because the names appear twice
		JLabel[] witness = new JLabel[12];
		for(int i=0;i<6;i++){
			witness[i]=new JLabel(witnessList.get(data[1+i]));
			witness[i+6]=new JLabel(witnessList.get(data[1+i]));
		}
		//then the roster list
		JLabel[] roster = new JLabel[11];
		//switch that pulls from the proper data[] cells depending on which side would actually have data
		if(data[0]==0){
			roster[0]=new JLabel(rosterList.get(data[8]));
			roster[1]=new JLabel(rosterList.get(data[10]));
			roster[2]=new JLabel(rosterList.get(data[12]));
			roster[3]=new JLabel(rosterList.get(data[14]));
			roster[4]=new JLabel(rosterList.get(data[15]));
			roster[5]=new JLabel(rosterList.get(data[16]));
			roster[6]=new JLabel(rosterList.get(data[9]));
			roster[7]=new JLabel(rosterList.get(data[11]));
			roster[8]=new JLabel(rosterList.get(data[13]));
			roster[9]=new JLabel(rosterList.get(data[7]));
			roster[10]=new JLabel(rosterList.get(data[17]));
		}else{
			roster[0]=new JLabel(rosterList.get(data[19]));
			roster[1]=new JLabel(rosterList.get(data[20]));
			roster[2]=new JLabel(rosterList.get(data[21]));
			roster[3]=new JLabel(rosterList.get(data[22]));
			roster[4]=new JLabel(rosterList.get(data[24]));
			roster[5]=new JLabel(rosterList.get(data[26]));
			roster[6]=new JLabel(rosterList.get(data[23]));
			roster[7]=new JLabel(rosterList.get(data[25]));
			roster[8]=new JLabel(rosterList.get(data[27]));
			roster[9]=new JLabel(rosterList.get(data[18]));
			roster[10]=new JLabel(rosterList.get(data[28]));
		}
		//and now we fill the scores JLabels, horay!!!
		JLabel[] scores = new JLabel[56];
		//ballot 1 atty direct
		scores[0]=new JLabel(Integer.toString(data[30]));
		scores[1]=new JLabel(Integer.toString(data[33]));
		scores[2]=new JLabel(Integer.toString(data[36]));
		scores[3]=new JLabel(Integer.toString(data[61]));
		scores[4]=new JLabel(Integer.toString(data[64]));
		scores[5]=new JLabel(Integer.toString(data[67]));
		for(int i=0;i<6;i++){
			if(Integer.parseInt(scores[i].getText())<b1Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 2 atty direct
		scores[6]=new JLabel(Integer.toString(data[44]));
		scores[7]=new JLabel(Integer.toString(data[47]));
		scores[8]=new JLabel(Integer.toString(data[50]));
		scores[9]=new JLabel(Integer.toString(data[75]));
		scores[10]=new JLabel(Integer.toString(data[78]));
		scores[11]=new JLabel(Integer.toString(data[81]));
		for(int i=6;i<12;i++){
			if(Integer.parseInt(scores[i].getText())<b2Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 1 atty cross
		scores[12]=new JLabel(Integer.toString(data[58]));
		scores[13]=new JLabel(Integer.toString(data[59]));
		scores[14]=new JLabel(Integer.toString(data[60]));
		scores[15]=new JLabel(Integer.toString(data[39]));
		scores[16]=new JLabel(Integer.toString(data[40]));
		scores[17]=new JLabel(Integer.toString(data[41]));
		for(int i=12;i<18;i++){
			if(Integer.parseInt(scores[i].getText())<b1Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 2 atty cross
		scores[18]=new JLabel(Integer.toString(data[72]));
		scores[19]=new JLabel(Integer.toString(data[73]));
		scores[20]=new JLabel(Integer.toString(data[74]));
		scores[21]=new JLabel(Integer.toString(data[53]));
		scores[22]=new JLabel(Integer.toString(data[54]));
		scores[23]=new JLabel(Integer.toString(data[55]));
		for(int i=18;i<24;i++){
			if(Integer.parseInt(scores[i].getText())<b2Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 1 wit direct
		scores[24]=new JLabel(Integer.toString(data[31]));
		scores[25]=new JLabel(Integer.toString(data[34]));
		scores[26]=new JLabel(Integer.toString(data[37]));
		scores[27]=new JLabel(Integer.toString(data[62]));
		scores[28]=new JLabel(Integer.toString(data[65]));
		scores[29]=new JLabel(Integer.toString(data[68]));
		for(int i=24;i<30;i++){
			if(Integer.parseInt(scores[i].getText())<b1Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 2 wit direct
		scores[30]=new JLabel(Integer.toString(data[45]));
		scores[31]=new JLabel(Integer.toString(data[48]));
		scores[32]=new JLabel(Integer.toString(data[51]));
		scores[33]=new JLabel(Integer.toString(data[76]));
		scores[34]=new JLabel(Integer.toString(data[79]));
		scores[35]=new JLabel(Integer.toString(data[82]));
		for(int i=30;i<36;i++){
			if(Integer.parseInt(scores[i].getText())<b2Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 1 wit cross
		scores[36]=new JLabel(Integer.toString(data[32]));
		scores[37]=new JLabel(Integer.toString(data[35]));
		scores[38]=new JLabel(Integer.toString(data[38]));
		scores[39]=new JLabel(Integer.toString(data[63]));
		scores[40]=new JLabel(Integer.toString(data[66]));
		scores[41]=new JLabel(Integer.toString(data[69]));
		for(int i=36;i<42;i++){
			if(Integer.parseInt(scores[i].getText())<b1Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 2 wit cross
		scores[42]=new JLabel(Integer.toString(data[46]));
		scores[43]=new JLabel(Integer.toString(data[49]));
		scores[44]=new JLabel(Integer.toString(data[52]));
		scores[45]=new JLabel(Integer.toString(data[77]));
		scores[46]=new JLabel(Integer.toString(data[80]));
		scores[47]=new JLabel(Integer.toString(data[83]));
		for(int i=42;i<48;i++){
			if(Integer.parseInt(scores[i].getText())<b2Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 1 opening
		scores[48]=new JLabel(Integer.toString(data[29]));
		scores[49]=new JLabel(Integer.toString(data[57]));
		for(int i=48;i<50;i++){
			if(Integer.parseInt(scores[i].getText())<b1Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 2 opening
		scores[50]=new JLabel(Integer.toString(data[43]));
		scores[51]=new JLabel(Integer.toString(data[71]));
		for(int i=50;i<52;i++){
			if(Integer.parseInt(scores[i].getText())<b2Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 1 closing
		scores[52]=new JLabel(Integer.toString(data[42]));
		scores[53]=new JLabel(Integer.toString(data[70]));
		for(int i=52;i<54;i++){
			if(Integer.parseInt(scores[i].getText())<b1Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//ballot 2 closing
		scores[54]=new JLabel(Integer.toString(data[56]));
		scores[55]=new JLabel(Integer.toString(data[84]));
		for(int i=54;i<56;i++){
			if(Integer.parseInt(scores[i].getText())<b2Average){
				scores[i].setBackground(Color.PINK);
			}else{
				scores[i].setBackground(Color.GREEN);
			}
		}
		//make the background color visible
		for(int i=0;i<56;i++){
			scores[i].setOpaque(true);
		}
		//put averages in labels
		int subIndex=4;
		if(Double.toString(b1Average).length()<4){
			subIndex=Double.toString(b1Average).length();
		}
		JLabel ballot1Average = new JLabel("Ballot 1 Average: "+Double.toString(b1Average).substring(0,subIndex));
		subIndex=4;
		if(Double.toString(b2Average).length()<4){
			subIndex=Double.toString(b2Average).length();
		}
		JLabel ballot2Average = new JLabel("Ballot 2 Average: "+Double.toString(b2Average).substring(0,subIndex));
		subIndex=4;
		if(Double.toString(b1TeamAverage).length()<4){
			subIndex=Double.toString(b1TeamAverage).length();
		}
		JLabel ballot1TeamAverage = new JLabel("Team Average: "+Double.toString(b1TeamAverage).substring(0,subIndex));
		subIndex=4;
		if(Double.toString(b2TeamAverage).length()<4){
			subIndex=Double.toString(b2TeamAverage).length();
		}
		JLabel ballot2TeamAverage = new JLabel("Team Average: "+Double.toString(b2TeamAverage).substring(0,subIndex));
		//Round and side label
		gbc.gridy=0;
		gbc.gridx=1;
		gbl.setConstraints(roundNumber,gbc);
		gbc.gridx=4;
		gbl.setConstraints(side,gbc);
		//Direct and Cross attorney Labels
		gbc.gridy=1;
		gbc.gridwidth=2;
		gbc.gridx=1;
		gbl.setConstraints(partLabels[0],gbc);
		gbc.gridx=4;
		gbl.setConstraints(partLabels[1],gbc);
		//Ballot labels for direct and cross
		gbc.gridwidth=1;
		gbc.gridy=2;
		gbc.gridx=1;
		gbl.setConstraints(ballotLabels[0],gbc);
		gbc.gridx=2;
		gbl.setConstraints(ballotLabels[1],gbc);
		gbc.gridx=4;
		gbl.setConstraints(ballotLabels[2],gbc);
		gbc.gridx=5;
		gbl.setConstraints(ballotLabels[3],gbc);
		//direct and cross attorney roster labels
		if(data[0]==0){
			gbc.gridx=0;
			for(int i=0;i<3;i++){
				gbc.gridy=3+i;
				gbl.setConstraints(roster[i],gbc);
			}
			gbc.gridx=6;
			for(int i=3;i<6;i++){
				gbc.gridy=3+i;
				gbl.setConstraints(roster[i],gbc);
			}
		}else{
			gbc.gridx=6;
			for(int i=0;i<3;i++){
				gbc.gridy=3+i;
				gbl.setConstraints(roster[i],gbc);
			}
			gbc.gridx=0;
			for(int i=3;i<6;i++){
				gbc.gridy=3+i;
				gbl.setConstraints(roster[i],gbc);
			}
		}
		//direct and cross score labels (also, witnesses in the middle
		gbc.gridx=1;
		for(int i=0;i<6;i++){
			gbc.gridy=3+i;
			gbl.setConstraints(scores[i],gbc);
		}
		gbc.gridx=2;
		for(int i=0;i<6;i++){
			gbc.gridy=3+i;
			gbl.setConstraints(scores[6+i],gbc);
		}
		//see what I said about witnesses :)
		gbc.gridx=3;
		for(int i=0;i<6;i++){
			gbc.gridy=3+i;
			gbl.setConstraints(witness[i],gbc);
		}
		gbc.gridx=4;
		for(int i=0;i<6;i++){
			gbc.gridy=3+i;
			gbl.setConstraints(scores[12+i],gbc);
		}
		gbc.gridx=5;
		for(int i=0;i<6;i++){
			gbc.gridy=3+i;
			gbl.setConstraints(scores[18+i],gbc);
		}
		//witness direct and cross labels
		gbc.gridy=9;
		gbc.gridx=1;
		gbl.setConstraints(partLabels[2], gbc);
		gbc.gridx=4;
		gbl.setConstraints(partLabels[3],gbc);
		//witness ballot labels
		gbc.gridy=10;
		gbc.gridx=1;
		gbl.setConstraints(ballotLabels[4],gbc);
		gbc.gridx=2;
		gbl.setConstraints(ballotLabels[5],gbc);
		gbc.gridx=4;
		gbl.setConstraints(ballotLabels[6],gbc);
		gbc.gridx=5;
		gbl.setConstraints(ballotLabels[7],gbc);
		//witness roster labels
		gbc.gridx=0;
		for(int i=0;i<3;i++){
			if(data[0]==0){
				gbc.gridy=11+i;
				gbl.setConstraints(roster[6+i], gbc);
			}else{
				gbc.gridy=14+i;
				gbl.setConstraints(roster[6+i], gbc);
			}
		}
		//witness scores (also witness portrayed in center, again)
		gbc.gridx=1;
		for(int i=0;i<6;i++){
			gbc.gridy=11+i;
			gbl.setConstraints(scores[24+i], gbc);
		}
		gbc.gridx=2;
		for(int i=0;i<6;i++){
			gbc.gridy=11+i;
			gbl.setConstraints(scores[30+i], gbc);
		}
		gbc.gridx=3;
		for(int i=0;i<6;i++){
			gbc.gridy=11+i;
			gbl.setConstraints(witness[6+i], gbc);
		}
		gbc.gridx=4;
		for(int i=0;i<6;i++){
			gbc.gridy=11+i;
			gbl.setConstraints(scores[36+i], gbc);
		}
		gbc.gridx=5;
		for(int i=0;i<6;i++){
			gbc.gridy=11+i;
			gbl.setConstraints(scores[42+i], gbc);
		}
		//opening and closing title labels
		gbc.gridy=17;
		gbc.gridx=1;
		gbl.setConstraints(partLabels[4], gbc);
		gbc.gridx=4;
		gbl.setConstraints(partLabels[5], gbc);
		//opening and closing ballot labels
		gbc.gridy=18;
		gbc.gridx=1;
		gbl.setConstraints(ballotLabels[8], gbc);
		gbc.gridx=2;
		gbl.setConstraints(ballotLabels[9], gbc);
		gbc.gridx=4;
		gbl.setConstraints(ballotLabels[10], gbc);
		gbc.gridx=5;
		gbl.setConstraints(ballotLabels[11], gbc);
		//opening and closing roster labels
		gbc.gridx=0;
		if(data[0]==0){
			gbc.gridy=19;
			gbl.setConstraints(roster[9],gbc);
			gbc.gridx=6;
			gbl.setConstraints(roster[10],gbc);
		}else{
			gbc.gridy=20;
			gbl.setConstraints(roster[9],gbc);
			gbc.gridx=6;
			gbl.setConstraints(roster[10],gbc);
		}
		//opening and closing scores
		gbc.gridx=1;
		for(int i=0;i<2;i++){
			gbc.gridy=19+i;
			gbl.setConstraints(scores[48+i],gbc);
		}
		gbc.gridx=2;
		for(int i=0;i<2;i++){
			gbc.gridy=19+i;
			gbl.setConstraints(scores[50+i],gbc);
		}
		gbc.gridx=4;
		for(int i=0;i<2;i++){
			gbc.gridy=19+i;
			gbl.setConstraints(scores[52+i],gbc);
		}
		gbc.gridx=5;
		for(int i=0;i<2;i++){
			gbc.gridy=19+i;
			gbl.setConstraints(scores[54+i],gbc);
		}
		//ballot averages
		gbc.gridy=21;
		gbc.gridx=0;
		gbc.gridwidth=3;
		gbl.setConstraints(ballot1Average,gbc);
		gbc.gridy=22;
		gbl.setConstraints(ballot1TeamAverage,gbc);
		gbc.gridy=21;
		gbc.gridx=4;
		gbl.setConstraints(ballot2Average, gbc);
		gbc.gridy=22;
		gbl.setConstraints(ballot2TeamAverage, gbc);
		//add ALL THE THINGS!!!
		add(roundNumber);
		add(side);
		for(int i=0;i<partLabels.length;i++){
			add(partLabels[i]);
		}
		for(int i=0;i<ballotLabels.length;i++){
			add(ballotLabels[i]);
		}
		for(int i=0;i<roster.length;i++){
			roster[i].setBorder(BorderFactory.createLineBorder(Color.black));
			add(roster[i]);
		}
		for(int i=0;i<scores.length;i++){
			scores[i].setBorder(BorderFactory.createLineBorder(Color.black));
			add(scores[i]);
		}
		for(int i=0;i<witness.length;i++){
			witness[i].setBorder(BorderFactory.createLineBorder(Color.black));
			add(witness[i]);
		}
		add(ballot1Average);
		add(ballot2Average);
		add(ballot1TeamAverage);
		add(ballot2TeamAverage);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}	
}
