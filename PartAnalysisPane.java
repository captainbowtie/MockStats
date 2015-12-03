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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PartAnalysisPane extends JPanel{
	private JPanel[] rayPanel = {new JPanel(),new JPanel()}; //this panel name brought to you by Nick Lucafo
	private JComboBox[] partSelector = new JComboBox[2];
	private JCheckBox[] prePostCheck = {new JCheckBox("Pre-Stack",true),new JCheckBox("Post-Stack",true)};
	private String[] plaintiffDefenseText = {"All","Plaintiff","Defense"};
	private String[] partCategories = {"Open","Atty Direct","Atty Cross","Close","Wit Direct","Wit Cross"};
	private String[] witnessList;
	private JLabel[][] allTheLabels;
	private double[][] allTheScores;
	private ArrayList<Tournament> preStackTournament=new ArrayList<Tournament>();
	private ArrayList<Tournament> postStackTournament=new ArrayList<Tournament>();
	private Season theSeason;
	public PartAnalysisPane(Season aSeason){
		theSeason=aSeason;
		for(int i=0;i<theSeason.getNumberOfTournaments();i++){
			if(theSeason.getTournament(i).isPostStack()){
				postStackTournament.add(theSeason.getTournament(i));
			}else{
				preStackTournament.add(theSeason.getTournament(i));
			}
		}
		witnessList=new String[theSeason.getWitnesses().size()+1];
		witnessList[0]="All";
		for(int i=1;i<witnessList.length;i++){
			witnessList[i]=theSeason.getWitnesses().get(i-1);
		}
		partSelector[0]=new JComboBox(partCategories);
		partSelector[1]=new JComboBox(plaintiffDefenseText);
		partSelector[0].setSelectedIndex(0);
		partSelector[1].setSelectedIndex(0);
		partSelector[0].addItemListener(new ComboListener());
		partSelector[1].addItemListener(new ComboListener());
		prePostCheck[0].addActionListener(new CheckBoxListener());
		prePostCheck[1].addActionListener(new CheckBoxListener());
		open();
	}
	private void open(){
		removeAll();
		if(!partSelector[1].getItemAt(1).equals("Plaintiff")){
			partSelector[1]=new JComboBox(plaintiffDefenseText);
			partSelector[1].setSelectedIndex(0);
			partSelector[1].addItemListener(new ComboListener());
		}
		allTheLabels=new JLabel[theSeason.getRoster().size()+1][4];
		allTheScores=new double[theSeason.getRoster().size()][4];
		resetScores();
		for(int i=0;i<theSeason.getRoster().size();i++){
			//ballot average, team average, raw score, denominator
			double scores[] = {0.0,0.0,0.0,0.0};
			//all or plaintiff
			if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==1){
				//use pre-stack numbers
				if(prePostCheck[0].isSelected()){
					for(int j=0;j<preStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(preStackTournament.get(j).getRound(k).getAll()[7]==i){
								scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=preStackTournament.get(j).getRound(k).getAll()[29]+preStackTournament.get(j).getRound(k).getAll()[43];
								scores[3]+=2.0;
							}
						}
					}
				}
				//use post-stack numbers
				if(prePostCheck[1].isSelected()){
					for(int j=0;j<postStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(postStackTournament.get(j).getRound(k).getAll()[7]==i){
								scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=postStackTournament.get(j).getRound(k).getAll()[29]+postStackTournament.get(j).getRound(k).getAll()[43];
								scores[3]+=2.0;
							}
						}
					}
				}
			}
			//all or defense
			if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==2){
				//use pre-stack numbers
				if(prePostCheck[0].isSelected()){
					for(int j=0;j<preStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(preStackTournament.get(j).getRound(k).getAll()[18]==i){
								scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=preStackTournament.get(j).getRound(k).getAll()[57]+preStackTournament.get(j).getRound(k).getAll()[71];
								scores[3]+=2.0;
							}
						}
					}
				}
				//use post-stack numbers
				if(prePostCheck[1].isSelected()){
					for(int j=0;j<postStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(postStackTournament.get(j).getRound(k).getAll()[18]==i){
								scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=postStackTournament.get(j).getRound(k).getAll()[57]+postStackTournament.get(j).getRound(k).getAll()[71];
								scores[3]+=2.0;
							}
						}
					}
				}
			}
			//transfer calculated scores to permanent array
			if(scores[3]>0.0){
				allTheScores[i][1]=(scores[2]-scores[0])/scores[3];
				allTheScores[i][2]=(scores[2]-scores[1])/scores[3];
				allTheScores[i][3]=scores[2]/scores[3];
			}
		}	
		ballotSortAndFill();
	}
	private void close(){
		removeAll();
		if(!partSelector[1].getItemAt(1).equals("Plaintiff")){
			partSelector[1]=new JComboBox(plaintiffDefenseText);
			partSelector[1].setSelectedIndex(0);
			partSelector[1].addItemListener(new ComboListener());
		}
		allTheLabels=new JLabel[theSeason.getRoster().size()+1][4];
		allTheScores=new double[theSeason.getRoster().size()][4];
		resetScores();
		for(int i=0;i<theSeason.getRoster().size();i++){
			//ballot average, team average, raw score, denominator
			double scores[] = {0.0,0.0,0.0,0.0};
			//all or plaintiff
			if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==1){
				//use pre-stack numbers
				if(prePostCheck[0].isSelected()){
					for(int j=0;j<preStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(preStackTournament.get(j).getRound(k).getAll()[17]==i){
								scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=preStackTournament.get(j).getRound(k).getAll()[42]+preStackTournament.get(j).getRound(k).getAll()[56];
								scores[3]+=2.0;
							}
						}
					}
				}
				//use post-stack numbers
				if(prePostCheck[1].isSelected()){
					for(int j=0;j<postStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(postStackTournament.get(j).getRound(k).getAll()[17]==i){
								scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=postStackTournament.get(j).getRound(k).getAll()[42]+postStackTournament.get(j).getRound(k).getAll()[56];
								scores[3]+=2.0;
							}
						}
					}
				}
			}
			//all or defense
			if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==2){
				//use pre-stack numbers
				if(prePostCheck[0].isSelected()){
					for(int j=0;j<preStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(preStackTournament.get(j).getRound(k).getAll()[28]==i){
								scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=preStackTournament.get(j).getRound(k).getAll()[70]+preStackTournament.get(j).getRound(k).getAll()[84];
								scores[3]+=2.0;
							}
						}
					}
				}
				//use post-stack numbers
				if(prePostCheck[1].isSelected()){
					for(int j=0;j<postStackTournament.size();j++){
						for(int k=0;k<4;k++){
							if(postStackTournament.get(j).getRound(k).getAll()[28]==i){
								scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
								scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
								scores[2]+=postStackTournament.get(j).getRound(k).getAll()[70]+postStackTournament.get(j).getRound(k).getAll()[84];
								scores[3]+=2.0;
							}
						}
					}
				}
			}
			//transfer calculated scores to permanent array
			if(scores[3]>0.0){
				allTheScores[i][1]=(scores[2]-scores[0])/scores[3];
				allTheScores[i][2]=(scores[2]-scores[1])/scores[3];
				allTheScores[i][3]=scores[2]/scores[3];
			}
		}
		ballotSortAndFill();
	}
	private void direct(){
		this.add(new JLabel("hola"));
		if(partSelector[1].getItemAt(1).equals("Plaintiff")){
			partSelector[1]=new JComboBox(witnessList);
			partSelector[1].setSelectedIndex(0);
			partSelector[1].addItemListener(new ComboListener());
		}
		allTheLabels=new JLabel[theSeason.getRoster().size()+1][4];
		allTheScores=new double[theSeason.getRoster().size()][4];
		resetScores();

		for(int i=0;i<theSeason.getRoster().size();i++){
			double[] scores={0.0,0.0,0.0,0.0};
			for(int a=0;a<theSeason.getWitnesses().size();a++){
				if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==(a+1)){
					//pre-stack
					if(prePostCheck[0].isSelected()){
						for(int j=0;j<preStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(preStackTournament.get(j).getRound(k).getAll()[1]==a && preStackTournament.get(j).getRound(k).getAll()[8]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[30]+preStackTournament.get(j).getRound(k).getAll()[44];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[2]==a && preStackTournament.get(j).getRound(k).getAll()[10]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[33]+preStackTournament.get(j).getRound(k).getAll()[47];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[3]==a && preStackTournament.get(j).getRound(k).getAll()[12]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[36]+preStackTournament.get(j).getRound(k).getAll()[50];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[4]==a && preStackTournament.get(j).getRound(k).getAll()[22]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[61]+preStackTournament.get(j).getRound(k).getAll()[75];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[5]==a && preStackTournament.get(j).getRound(k).getAll()[24]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[64]+preStackTournament.get(j).getRound(k).getAll()[78];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[6]==a && preStackTournament.get(j).getRound(k).getAll()[26]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[67]+preStackTournament.get(j).getRound(k).getAll()[81];
									scores[3]+=2.0;
								}
							}
						}
					}
					//post-stack
					if(prePostCheck[1].isSelected()){
						for(int j=0;j<postStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(postStackTournament.get(j).getRound(k).getAll()[1]==a && postStackTournament.get(j).getRound(k).getAll()[8]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[30]+postStackTournament.get(j).getRound(k).getAll()[44];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[2]==a && postStackTournament.get(j).getRound(k).getAll()[10]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[33]+postStackTournament.get(j).getRound(k).getAll()[47];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[3]==a && postStackTournament.get(j).getRound(k).getAll()[12]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[36]+postStackTournament.get(j).getRound(k).getAll()[50];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[4]==a && postStackTournament.get(j).getRound(k).getAll()[22]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[61]+postStackTournament.get(j).getRound(k).getAll()[75];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[5]==a && postStackTournament.get(j).getRound(k).getAll()[24]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[64]+postStackTournament.get(j).getRound(k).getAll()[78];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[6]==a && postStackTournament.get(j).getRound(k).getAll()[26]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[67]+postStackTournament.get(j).getRound(k).getAll()[81];
									scores[3]+=2.0;
								}
							}
						}
					}
				}
			}
			//transfer calculated scores to permanent array
			if(scores[3]>0.0){
				allTheScores[i][1]=(scores[2]-scores[0])/scores[3];
				allTheScores[i][2]=(scores[2]-scores[1])/scores[3];
				allTheScores[i][3]=scores[2]/scores[3];
			}
		}
		ballotSortAndFill();
	}
	private void cross(){
		removeAll();
		if(partSelector[1].getItemAt(1).equals("Plaintiff")){
			partSelector[1]=new JComboBox(witnessList);
			partSelector[1].setSelectedIndex(0);
			partSelector[1].addItemListener(new ComboListener());
		}
		allTheLabels=new JLabel[theSeason.getRoster().size()+1][4];
		allTheScores=new double[theSeason.getRoster().size()][4];
		resetScores();
		for(int i=0;i<theSeason.getRoster().size();i++){
			double[] scores={0.0,0.0,0.0,0.0};
			for(int a=0;a<theSeason.getWitnesses().size();a++){
				if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==(a+1)){
					//pre-stack
					if(prePostCheck[0].isSelected()){
						for(int j=0;j<preStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(preStackTournament.get(j).getRound(k).getAll()[1]==a && preStackTournament.get(j).getRound(k).getAll()[19]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[58]+preStackTournament.get(j).getRound(k).getAll()[72];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[2]==a && preStackTournament.get(j).getRound(k).getAll()[20]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[59]+preStackTournament.get(j).getRound(k).getAll()[73];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[3]==a && preStackTournament.get(j).getRound(k).getAll()[21]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[60]+preStackTournament.get(j).getRound(k).getAll()[74];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[4]==a && preStackTournament.get(j).getRound(k).getAll()[14]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[39]+preStackTournament.get(j).getRound(k).getAll()[53];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[5]==a && preStackTournament.get(j).getRound(k).getAll()[15]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[40]+preStackTournament.get(j).getRound(k).getAll()[54];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[6]==a && preStackTournament.get(j).getRound(k).getAll()[16]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[41]+preStackTournament.get(j).getRound(k).getAll()[55];
									scores[3]+=2.0;
								}
							}
						}
					}
					//post-stack
					if(prePostCheck[1].isSelected()){
						for(int j=0;j<postStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(postStackTournament.get(j).getRound(k).getAll()[1]==a && postStackTournament.get(j).getRound(k).getAll()[19]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[58]+postStackTournament.get(j).getRound(k).getAll()[72];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[2]==a && postStackTournament.get(j).getRound(k).getAll()[20]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[59]+postStackTournament.get(j).getRound(k).getAll()[73];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[3]==a && postStackTournament.get(j).getRound(k).getAll()[21]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[60]+postStackTournament.get(j).getRound(k).getAll()[74];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[4]==a && postStackTournament.get(j).getRound(k).getAll()[14]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[39]+postStackTournament.get(j).getRound(k).getAll()[53];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[5]==a && postStackTournament.get(j).getRound(k).getAll()[15]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[40]+postStackTournament.get(j).getRound(k).getAll()[54];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[6]==a && postStackTournament.get(j).getRound(k).getAll()[16]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[41]+postStackTournament.get(j).getRound(k).getAll()[55];
									scores[3]+=2.0;
								}
							}
						}
					}
				}
			}
			//transfer calculated scores to permanent array
			if(scores[3]>0.0){
				allTheScores[i][1]=(scores[2]-scores[0])/scores[3];
				allTheScores[i][2]=(scores[2]-scores[1])/scores[3];
				allTheScores[i][3]=scores[2]/scores[3];
			}
		}
		ballotSortAndFill();
	}
	private void witnessDirect(){
		removeAll();
		if(partSelector[1].getItemAt(1).equals("Plaintiff")){
			partSelector[1]=new JComboBox(witnessList);
			partSelector[1].setSelectedIndex(0);
			partSelector[1].addItemListener(new ComboListener());
		}
		allTheLabels=new JLabel[theSeason.getRoster().size()+1][4];
		allTheScores=new double[theSeason.getRoster().size()][4];
		resetScores();
		for(int i=0;i<theSeason.getRoster().size();i++){
			double[] scores={0.0,0.0,0.0,0.0};
			for(int a=0;a<theSeason.getWitnesses().size();a++){
				if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==(a+1)){
					//pre-stack
					if(prePostCheck[0].isSelected()){
						for(int j=0;j<preStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(preStackTournament.get(j).getRound(k).getAll()[1]==a && preStackTournament.get(j).getRound(k).getAll()[9]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[31]+preStackTournament.get(j).getRound(k).getAll()[45];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[2]==a && preStackTournament.get(j).getRound(k).getAll()[11]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[34]+preStackTournament.get(j).getRound(k).getAll()[48];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[3]==a && preStackTournament.get(j).getRound(k).getAll()[13]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[37]+preStackTournament.get(j).getRound(k).getAll()[51];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[4]==a && preStackTournament.get(j).getRound(k).getAll()[23]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[62]+preStackTournament.get(j).getRound(k).getAll()[76];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[5]==a && preStackTournament.get(j).getRound(k).getAll()[25]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[65]+preStackTournament.get(j).getRound(k).getAll()[79];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[6]==a && preStackTournament.get(j).getRound(k).getAll()[27]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[68]+preStackTournament.get(j).getRound(k).getAll()[82];
									scores[3]+=2.0;
								}
							}
						}
					}
					//post-stack
					if(prePostCheck[1].isSelected()){
						for(int j=0;j<postStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(postStackTournament.get(j).getRound(k).getAll()[1]==a && postStackTournament.get(j).getRound(k).getAll()[9]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[31]+postStackTournament.get(j).getRound(k).getAll()[45];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[2]==a && postStackTournament.get(j).getRound(k).getAll()[11]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[34]+postStackTournament.get(j).getRound(k).getAll()[48];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[3]==a && postStackTournament.get(j).getRound(k).getAll()[13]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[37]+postStackTournament.get(j).getRound(k).getAll()[51];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[4]==a && postStackTournament.get(j).getRound(k).getAll()[23]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[62]+postStackTournament.get(j).getRound(k).getAll()[76];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[5]==a && postStackTournament.get(j).getRound(k).getAll()[25]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[65]+postStackTournament.get(j).getRound(k).getAll()[79];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[6]==a && postStackTournament.get(j).getRound(k).getAll()[27]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[68]+postStackTournament.get(j).getRound(k).getAll()[82];
									scores[3]+=2.0;
								}
							}
						}
					}
				}
			}
			//transfer calculated scores to permanent array
			if(scores[3]>0.0){
				allTheScores[i][1]=(scores[2]-scores[0])/scores[3];
				allTheScores[i][2]=(scores[2]-scores[1])/scores[3];
				allTheScores[i][3]=scores[2]/scores[3];
			}
		}
		ballotSortAndFill();
	}
	private void witnessCross(){
		removeAll();
		if(partSelector[1].getItemAt(1).equals("Plaintiff")){
			partSelector[1]=new JComboBox(witnessList);
			partSelector[1].setSelectedIndex(0);
			partSelector[1].addItemListener(new ComboListener());
		}
		allTheLabels=new JLabel[theSeason.getRoster().size()+1][4];
		allTheScores=new double[theSeason.getRoster().size()][4];
		resetScores();
		for(int i=0;i<theSeason.getRoster().size();i++){
			double[] scores={0.0,0.0,0.0,0.0};
			for(int a=0;a<theSeason.getWitnesses().size();a++){
				if(partSelector[1].getSelectedIndex()==0 || partSelector[1].getSelectedIndex()==(a+1)){
					//pre-stack
					if(prePostCheck[0].isSelected()){
						for(int j=0;j<preStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(preStackTournament.get(j).getRound(k).getAll()[1]==a && preStackTournament.get(j).getRound(k).getAll()[9]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[32]+preStackTournament.get(j).getRound(k).getAll()[46];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[2]==a && preStackTournament.get(j).getRound(k).getAll()[11]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[35]+preStackTournament.get(j).getRound(k).getAll()[49];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[3]==a && preStackTournament.get(j).getRound(k).getAll()[13]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[38]+preStackTournament.get(j).getRound(k).getAll()[52];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[4]==a && preStackTournament.get(j).getRound(k).getAll()[23]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[63]+preStackTournament.get(j).getRound(k).getAll()[77];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[5]==a && preStackTournament.get(j).getRound(k).getAll()[25]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[66]+preStackTournament.get(j).getRound(k).getAll()[80];
									scores[3]+=2.0;
								}else if(preStackTournament.get(j).getRound(k).getAll()[6]==a && preStackTournament.get(j).getRound(k).getAll()[27]==i){
									scores[0]+=preStackTournament.get(j).getRound(k).getBallot1Average()+preStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=preStackTournament.get(j).getRound(k).getBallot1TeamAverage()+preStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=preStackTournament.get(j).getRound(k).getAll()[69]+preStackTournament.get(j).getRound(k).getAll()[83];
									scores[3]+=2.0;
								}
							}
						}
					}
					//post-stack
					if(prePostCheck[1].isSelected()){
						for(int j=0;j<postStackTournament.size();j++){
							for(int k=0;k<4;k++){
								if(postStackTournament.get(j).getRound(k).getAll()[1]==a && postStackTournament.get(j).getRound(k).getAll()[9]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[32]+postStackTournament.get(j).getRound(k).getAll()[46];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[2]==a && postStackTournament.get(j).getRound(k).getAll()[11]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[35]+postStackTournament.get(j).getRound(k).getAll()[49];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[3]==a && postStackTournament.get(j).getRound(k).getAll()[13]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[38]+postStackTournament.get(j).getRound(k).getAll()[52];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[4]==a && postStackTournament.get(j).getRound(k).getAll()[23]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[63]+postStackTournament.get(j).getRound(k).getAll()[77];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[5]==a && postStackTournament.get(j).getRound(k).getAll()[25]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[66]+postStackTournament.get(j).getRound(k).getAll()[80];
									scores[3]+=2.0;
								}else if(postStackTournament.get(j).getRound(k).getAll()[6]==a && postStackTournament.get(j).getRound(k).getAll()[27]==i){
									scores[0]+=postStackTournament.get(j).getRound(k).getBallot1Average()+postStackTournament.get(j).getRound(k).getBallot2Average();
									scores[1]+=postStackTournament.get(j).getRound(k).getBallot1TeamAverage()+postStackTournament.get(j).getRound(k).getBallot2TeamAverage();
									scores[2]+=postStackTournament.get(j).getRound(k).getAll()[69]+postStackTournament.get(j).getRound(k).getAll()[83];
									scores[3]+=2.0;
								}
							}
						}
					}
				}
			}
			//transfer calculated scores to permanent array
			if(scores[3]>0.0){
				allTheScores[i][1]=(scores[2]-scores[0])/scores[3];
				allTheScores[i][2]=(scores[2]-scores[1])/scores[3];
				allTheScores[i][3]=scores[2]/scores[3];
			}
		}
		ballotSortAndFill();
	}
	private void resetScores(){
		for(int i=0;i<allTheScores.length;i++){
			for(int j=0;j<allTheLabels[0].length;j++){
				allTheLabels[i+1][j]=new JLabel("NA");
				allTheScores[i][j]=-10.0;
			}
			allTheScores[i][0]=0.0+i;
		}
	}
	private void ballotSortAndFill(){
		ArrayList<double[]> unsortedScores = new ArrayList<double[]>(Arrays.asList(allTheScores));
		ArrayList<double[]> sortedScores = new ArrayList<double[]>();
		//sorts scores into an ArrayList from most positive to ballot to least positive to ballot
		while(unsortedScores.size()!=0){
			double max=-10.0; //Would be pretty hard to be more than 10 points below average
			int maxIndex=-1;
			for(int i=0;i<unsortedScores.size();i++){
				if(unsortedScores.get(i)[1]>=max){
					max=unsortedScores.get(i)[1];
					maxIndex=i;
				}
			}
			sortedScores.add(unsortedScores.get(maxIndex));
			unsortedScores.remove(maxIndex);
		}
		//Takes the indexes and scores and converts them to labels; colors accordingly
		allTheLabels[0][0]=new JLabel("Name");
		allTheLabels[0][1]=new JLabel("+/- Ballot");
		allTheLabels[0][2]=new JLabel("+/- Team");
		allTheLabels[0][3]=new JLabel("Raw");
		for(int i=0;i<sortedScores.size();i++){
			if(sortedScores.get(i)[1]!=-10.0){
				int subIndex;
				allTheLabels[i+1][0]=new JLabel(theSeason.getRoster().get((int)sortedScores.get(i)[0]));
				subIndex=4;
				if(sortedScores.get(i)[1]<0){
					subIndex=5;
				}
				if(Double.toString(sortedScores.get(i)[1]).length()<subIndex){
					subIndex=Double.toString(sortedScores.get(i)[1]).length();
				}
				allTheLabels[i+1][1]=new JLabel(Double.toString(sortedScores.get(i)[1]).substring(0,subIndex));
				allTheLabels[i+1][1].setOpaque(true);
				if(sortedScores.get(i)[1]<0){
					allTheLabels[i+1][1].setBackground(Color.pink);
				}else{
					allTheLabels[i+1][1].setBackground(Color.green);
				}
				subIndex=4;
				if(sortedScores.get(i)[2]<0){
					subIndex=5;
				}
				if(Double.toString(sortedScores.get(i)[2]).length()<subIndex){
					subIndex=Double.toString(sortedScores.get(i)[2]).length();
				}
				allTheLabels[i+1][2]=new JLabel(Double.toString(sortedScores.get(i)[2]).substring(0,subIndex));
				allTheLabels[i+1][2].setOpaque(true);
				if(sortedScores.get(i)[2]<0){
					allTheLabels[i+1][2].setBackground(Color.pink);
				}else{
					allTheLabels[i+1][2].setBackground(Color.green);
				}
				subIndex=4;
				if(sortedScores.get(i)[3]<0){
					subIndex=5;
				}
				if(Double.toString(sortedScores.get(i)[3]).length()<subIndex){
					subIndex=Double.toString(sortedScores.get(i)[3]).length();
				}
				allTheLabels[i+1][3]=new JLabel(Double.toString(sortedScores.get(i)[3]).substring(0,subIndex));
			}else{
				allTheLabels[i+1][0]=new JLabel(theSeason.getRoster().get((int)sortedScores.get(i)[0]));
			}
		}
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new BorderLayout());
		rayPanel[0].setLayout(new GridLayout(2,2));
		rayPanel[1].setLayout(gbl);
		rayPanel[0].add(partSelector[0]);
		rayPanel[0].add(prePostCheck[0]);
		rayPanel[0].add(partSelector[1]);
		rayPanel[0].add(prePostCheck[1]);
		gbc.fill=GridBagConstraints.BOTH;
		for(int i=0;i<4;i++){
			gbc.gridx=i;
			for(int j=0;j<theSeason.getRoster().size()+1;j++){
				gbc.gridy=j;
				allTheLabels[j][i].setBorder(BorderFactory.createLineBorder(Color.black));
				gbl.setConstraints(allTheLabels[j][i],gbc);
			}
		}
		for(int i=0;i<4;i++){
			for(int j=0;j<theSeason.getRoster().size()+1;j++){
				rayPanel[1].add(allTheLabels[j][i]);
			}
		}
		add(rayPanel[0],BorderLayout.NORTH);
		add(rayPanel[1],BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}
	private void respondToStateChange(){
		rayPanel[0].removeAll();
		rayPanel[1].removeAll();
		this.remove(rayPanel[0]);
		this.remove(rayPanel[1]);
		switch(partSelector[0].getSelectedIndex()){
		case 0:
			open();
			break;
		case 1:
			direct();
			break;
		case 2:
			cross();
			break;
		case 3:
			close();
			break;
		case 4:
			witnessDirect();
			break;
		case 5:
			witnessCross();
			break;
		}
	}
	private class ComboListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED){
				respondToStateChange();
			}
		}
	}
	private class CheckBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==prePostCheck[0]||e.getSource()==prePostCheck[1]){
				respondToStateChange();
			}
		}
	}
}
