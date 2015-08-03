package Temp;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MemberStatisticsPanel extends JPanel{
	public MemberStatisticsPanel(Season theSeason, boolean preStack, boolean postStack){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(gbl);
		constraints.fill=GridBagConstraints.BOTH;
		JLabel[] scoreCategory ={new JLabel("Relative to Ballot"),new JLabel("Relative to Team"),new JLabel("Raw Score")};
		for(int i=0;i<scoreCategory.length;i++){
			scoreCategory[i].setAlignmentX(CENTER_ALIGNMENT);
		}
		int[] roster = new int[theSeason.getRoster().size()];
		ArrayList<Tournament> preStackTournaments=new ArrayList<Tournament>();
		ArrayList<Tournament> postStackTournaments=new ArrayList<Tournament>();
		for(int i=0;i<theSeason.getNumberOfTournaments();i++){
			if(theSeason.getTournament(i).isPostStack()){
				postStackTournaments.add(theSeason.getTournament(i));
			}else{
				preStackTournaments.add(theSeason.getTournament(i));
			}
		}
		JLabel[][] allTheLabels = new JLabel[roster.length][27];
		for(int i=0;i<roster.length;i++){
			for(int j=0;j<27;j++){
				allTheLabels[i][j]=new JLabel("NA");
			}
		}
		JButton[] rosterNames = new JButton[roster.length];
		for(int i=0;i<rosterNames.length;i++){
			rosterNames[i]=new JButton(theSeason.getRoster().get(i));
		}
		JLabel[] headerLabel = new JLabel[28];
		headerLabel[0]=new JLabel("Name");
		for(int i=0;i<3;i++){
			headerLabel[i*9+1]=new JLabel("Mean");
			headerLabel[i*9+2]=new JLabel("Atty Mean");
			headerLabel[i*9+3]=new JLabel("Wit Mean");
			headerLabel[i*9+4]=new JLabel("Open");
			headerLabel[i*9+5]=new JLabel("Direct");
			headerLabel[i*9+6]=new JLabel("Cross");
			headerLabel[i*9+7]=new JLabel("Close");
			headerLabel[i*9+8]=new JLabel("W Direct");
			headerLabel[i*9+9]=new JLabel("W Cross");
		}
		for(int i=0;i<roster.length;i++){
			int subIndex=4;
			//{ballot average sum, team average sum, raw score sum, denominator}
			double[] open = {0.0,0.0,0.0,0.0};
			double[] direct = {0.0,0.0,0.0,0.0};
			double[] cross = {0.0,0.0,0.0,0.0};
			double[] close = {0.0,0.0,0.0,0.0};
			double[] wdirect = {0.0,0.0,0.0,0.0};
			double[] wcross = {0.0,0.0,0.0,0.0};
			double[] mean = {0.0,0.0,0.0,0.0};
			double[] attyMean = {0.0,0.0,0.0,0.0};
			double[] witMean = {0.0,0.0,0.0,0.0};
			if(preStack){
				for(int j=0;j<preStackTournaments.size();j++){
					for(int k=0;k<4;k++){
						//open
						if(preStackTournaments.get(j).getRound(k).getAll()[7]==i){
							open[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							open[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							open[2]+=preStackTournaments.get(j).getRound(k).getAll()[29]+preStackTournaments.get(j).getRound(k).getAll()[43];
							open[3]+=2.0;
						}
						else if(preStackTournaments.get(j).getRound(k).getAll()[18]==i){
							open[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							open[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							open[2]+=preStackTournaments.get(j).getRound(k).getAll()[57]+preStackTournaments.get(j).getRound(k).getAll()[71];
							open[3]+=2.0;
						}
						//close
						if(preStackTournaments.get(j).getRound(k).getAll()[17]==i){
							close[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							close[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							close[2]+=preStackTournaments.get(j).getRound(k).getAll()[42]+preStackTournaments.get(j).getRound(k).getAll()[56];
							close[3]+=2.0;
						}
						else if(preStackTournaments.get(j).getRound(k).getAll()[28]==i){
							close[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							close[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							close[2]+=preStackTournaments.get(j).getRound(k).getAll()[70]+preStackTournaments.get(j).getRound(k).getAll()[84];
							close[3]+=2.0;
						}
						//direct
						if(preStackTournaments.get(j).getRound(k).getAll()[8]==i){
							direct[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=preStackTournaments.get(j).getRound(k).getAll()[30]+preStackTournaments.get(j).getRound(k).getAll()[44];
							direct[3]+=2.0;
						}
						else if(preStackTournaments.get(j).getRound(k).getAll()[10]==i){
							direct[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=preStackTournaments.get(j).getRound(k).getAll()[33]+preStackTournaments.get(j).getRound(k).getAll()[47];
							direct[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[12]==i){
							direct[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=preStackTournaments.get(j).getRound(k).getAll()[36]+preStackTournaments.get(j).getRound(k).getAll()[50];
							direct[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[22]==i){
							direct[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=preStackTournaments.get(j).getRound(k).getAll()[61]+preStackTournaments.get(j).getRound(k).getAll()[75];
							direct[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[24]==i){
							direct[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=preStackTournaments.get(j).getRound(k).getAll()[64]+preStackTournaments.get(j).getRound(k).getAll()[78];
							direct[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[26]==i){
							direct[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=preStackTournaments.get(j).getRound(k).getAll()[67]+preStackTournaments.get(j).getRound(k).getAll()[81];
							direct[3]+=2.0;
						}
						//cross
						if(preStackTournaments.get(j).getRound(k).getAll()[14]==i){
							cross[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=preStackTournaments.get(j).getRound(k).getAll()[39]+preStackTournaments.get(j).getRound(k).getAll()[53];
							cross[3]+=2.0;
						}
						else if(preStackTournaments.get(j).getRound(k).getAll()[15]==i){
							cross[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=preStackTournaments.get(j).getRound(k).getAll()[40]+preStackTournaments.get(j).getRound(k).getAll()[54];
							cross[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[16]==i){
							cross[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=preStackTournaments.get(j).getRound(k).getAll()[41]+preStackTournaments.get(j).getRound(k).getAll()[55];
							cross[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[19]==i){
							cross[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=preStackTournaments.get(j).getRound(k).getAll()[58]+preStackTournaments.get(j).getRound(k).getAll()[72];
							cross[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[20]==i){
							cross[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=preStackTournaments.get(j).getRound(k).getAll()[59]+preStackTournaments.get(j).getRound(k).getAll()[73];
							cross[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[21]==i){
							cross[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=preStackTournaments.get(j).getRound(k).getAll()[60]+preStackTournaments.get(j).getRound(k).getAll()[74];
							cross[3]+=2.0;
						}
						//wdirect & wcross
						if(preStackTournaments.get(j).getRound(k).getAll()[9]==i){
							wdirect[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=preStackTournaments.get(j).getRound(k).getAll()[31]+preStackTournaments.get(j).getRound(k).getAll()[45];
							wcross[2]+=preStackTournaments.get(j).getRound(k).getAll()[32]+preStackTournaments.get(j).getRound(k).getAll()[46];
							wdirect[3]+=2.0;
						}
						else if(preStackTournaments.get(j).getRound(k).getAll()[11]==i){
							wdirect[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=preStackTournaments.get(j).getRound(k).getAll()[34]+preStackTournaments.get(j).getRound(k).getAll()[48];
							wcross[2]+=preStackTournaments.get(j).getRound(k).getAll()[35]+preStackTournaments.get(j).getRound(k).getAll()[49];				
							wdirect[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[13]==i){
							wdirect[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=preStackTournaments.get(j).getRound(k).getAll()[37]+preStackTournaments.get(j).getRound(k).getAll()[51];
							wcross[2]+=preStackTournaments.get(j).getRound(k).getAll()[38]+preStackTournaments.get(j).getRound(k).getAll()[52];		
							wdirect[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[23]==i){
							wdirect[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=preStackTournaments.get(j).getRound(k).getAll()[62]+preStackTournaments.get(j).getRound(k).getAll()[76];
							wcross[2]+=preStackTournaments.get(j).getRound(k).getAll()[63]+preStackTournaments.get(j).getRound(k).getAll()[77];
							wdirect[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[25]==i){
							wdirect[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=preStackTournaments.get(j).getRound(k).getAll()[65]+preStackTournaments.get(j).getRound(k).getAll()[79];
							wcross[2]+=preStackTournaments.get(j).getRound(k).getAll()[66]+preStackTournaments.get(j).getRound(k).getAll()[80];	
							wdirect[3]+=2.0;
						}else if(preStackTournaments.get(j).getRound(k).getAll()[27]==i){
							wdirect[0]+=preStackTournaments.get(j).getRound(k).getBallot1Average()+preStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=preStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+preStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=preStackTournaments.get(j).getRound(k).getAll()[68]+preStackTournaments.get(j).getRound(k).getAll()[82];
							wcross[2]+=preStackTournaments.get(j).getRound(k).getAll()[69]+preStackTournaments.get(j).getRound(k).getAll()[83];
							wdirect[3]+=2.0;
						}
					}	
				}
			}
			if(postStack){
				for(int j=0;j<postStackTournaments.size();j++){
					for(int k=0;k<4;k++){
						//open
						if(postStackTournaments.get(j).getRound(k).getAll()[7]==i){
							open[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							open[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							open[2]+=postStackTournaments.get(j).getRound(k).getAll()[29]+postStackTournaments.get(j).getRound(k).getAll()[43];
							open[3]+=2.0;
						}
						else if(postStackTournaments.get(j).getRound(k).getAll()[18]==i){
							open[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							open[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							open[2]+=postStackTournaments.get(j).getRound(k).getAll()[57]+postStackTournaments.get(j).getRound(k).getAll()[71];
							open[3]+=2.0;
						}
						//close
						if(postStackTournaments.get(j).getRound(k).getAll()[17]==i){
							close[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							close[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							close[2]+=postStackTournaments.get(j).getRound(k).getAll()[42]+postStackTournaments.get(j).getRound(k).getAll()[56];
							close[3]+=2.0;
						}
						else if(postStackTournaments.get(j).getRound(k).getAll()[28]==i){
							close[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							close[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							close[2]+=postStackTournaments.get(j).getRound(k).getAll()[70]+postStackTournaments.get(j).getRound(k).getAll()[84];
							close[3]+=2.0;
						}
						//direct
						if(postStackTournaments.get(j).getRound(k).getAll()[8]==i){
							direct[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=postStackTournaments.get(j).getRound(k).getAll()[30]+postStackTournaments.get(j).getRound(k).getAll()[44];
							direct[3]+=2.0;
						}
						else if(postStackTournaments.get(j).getRound(k).getAll()[10]==i){
							direct[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=postStackTournaments.get(j).getRound(k).getAll()[33]+postStackTournaments.get(j).getRound(k).getAll()[47];
							direct[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[12]==i){
							direct[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=postStackTournaments.get(j).getRound(k).getAll()[36]+postStackTournaments.get(j).getRound(k).getAll()[50];
							direct[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[22]==i){
							direct[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=postStackTournaments.get(j).getRound(k).getAll()[61]+postStackTournaments.get(j).getRound(k).getAll()[75];
							direct[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[24]==i){
							direct[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=postStackTournaments.get(j).getRound(k).getAll()[64]+postStackTournaments.get(j).getRound(k).getAll()[78];
							direct[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[26]==i){
							direct[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							direct[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							direct[2]+=postStackTournaments.get(j).getRound(k).getAll()[67]+postStackTournaments.get(j).getRound(k).getAll()[81];
							direct[3]+=2.0;
						}
						//cross
						if(postStackTournaments.get(j).getRound(k).getAll()[14]==i){
							cross[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=postStackTournaments.get(j).getRound(k).getAll()[39]+postStackTournaments.get(j).getRound(k).getAll()[53];
							cross[3]+=2.0;
						}
						else if(postStackTournaments.get(j).getRound(k).getAll()[15]==i){
							cross[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=postStackTournaments.get(j).getRound(k).getAll()[40]+postStackTournaments.get(j).getRound(k).getAll()[54];
							cross[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[16]==i){
							cross[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=postStackTournaments.get(j).getRound(k).getAll()[41]+postStackTournaments.get(j).getRound(k).getAll()[55];
							cross[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[19]==i){
							cross[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=postStackTournaments.get(j).getRound(k).getAll()[58]+postStackTournaments.get(j).getRound(k).getAll()[72];
							cross[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[20]==i){
							cross[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=postStackTournaments.get(j).getRound(k).getAll()[59]+postStackTournaments.get(j).getRound(k).getAll()[73];
							cross[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[21]==i){
							cross[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							cross[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							cross[2]+=postStackTournaments.get(j).getRound(k).getAll()[60]+postStackTournaments.get(j).getRound(k).getAll()[74];
							cross[3]+=2.0;
						}
						//wdirect & wcross
						if(postStackTournaments.get(j).getRound(k).getAll()[9]==i){
							wdirect[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=postStackTournaments.get(j).getRound(k).getAll()[31]+postStackTournaments.get(j).getRound(k).getAll()[45];
							wcross[2]+=postStackTournaments.get(j).getRound(k).getAll()[32]+postStackTournaments.get(j).getRound(k).getAll()[46];
							wdirect[3]+=2.0;
						}
						else if(postStackTournaments.get(j).getRound(k).getAll()[11]==i){
							wdirect[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=postStackTournaments.get(j).getRound(k).getAll()[34]+postStackTournaments.get(j).getRound(k).getAll()[48];
							wcross[2]+=postStackTournaments.get(j).getRound(k).getAll()[35]+postStackTournaments.get(j).getRound(k).getAll()[49];				
							wdirect[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[13]==i){
							wdirect[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=postStackTournaments.get(j).getRound(k).getAll()[37]+postStackTournaments.get(j).getRound(k).getAll()[51];
							wcross[2]+=postStackTournaments.get(j).getRound(k).getAll()[38]+postStackTournaments.get(j).getRound(k).getAll()[52];		
							wdirect[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[23]==i){
							wdirect[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=postStackTournaments.get(j).getRound(k).getAll()[62]+postStackTournaments.get(j).getRound(k).getAll()[76];
							wcross[2]+=postStackTournaments.get(j).getRound(k).getAll()[63]+postStackTournaments.get(j).getRound(k).getAll()[77];
							wdirect[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[25]==i){
							wdirect[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=postStackTournaments.get(j).getRound(k).getAll()[65]+postStackTournaments.get(j).getRound(k).getAll()[79];
							wcross[2]+=postStackTournaments.get(j).getRound(k).getAll()[66]+postStackTournaments.get(j).getRound(k).getAll()[80];	
							wdirect[3]+=2.0;
						}else if(postStackTournaments.get(j).getRound(k).getAll()[27]==i){
							wdirect[0]+=postStackTournaments.get(j).getRound(k).getBallot1Average()+postStackTournaments.get(j).getRound(k).getBallot2Average();
							wdirect[1]+=postStackTournaments.get(j).getRound(k).getBallot1TeamAverage()+postStackTournaments.get(j).getRound(k).getBallot2TeamAverage();
							wdirect[2]+=postStackTournaments.get(j).getRound(k).getAll()[68]+postStackTournaments.get(j).getRound(k).getAll()[82];
							wcross[2]+=postStackTournaments.get(j).getRound(k).getAll()[69]+postStackTournaments.get(j).getRound(k).getAll()[83];
							wdirect[3]+=2.0;
						}
					}	
				}
			}
			wcross[0]=wdirect[0];
			wcross[1]=wdirect[1];
			wcross[3]=wdirect[3];
			for(int j=0;j<4;j++){
				mean[j]=open[j]+direct[j]+cross[j]+close[j]+wdirect[j]+wcross[j];
				attyMean[j]=open[j]+direct[j]+cross[j]+close[j];
				witMean[j]=wdirect[j]+wcross[j];
			}
			
			//open
			if(open[3]>0.0){
				String ballotText=Double.toString((open[2]-open[0])/open[3]);
				String teamText=Double.toString((open[2]-open[1])/open[3]);
				String rawText=Double.toString(open[2]/open[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][3].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][12].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][21].setText(rawText.substring(0,subIndex));
			}
			//direct
			if(direct[3]>0.0){
				String ballotText=Double.toString((direct[2]-direct[0])/direct[3]);
				String teamText=Double.toString((direct[2]-direct[1])/direct[3]);
				String rawText=Double.toString(direct[2]/direct[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][4].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][13].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][22].setText(rawText.substring(0,subIndex));
			}
			//cross
			if(cross[3]>0.0){
				String ballotText=Double.toString((cross[2]-cross[0])/cross[3]);
				String teamText=Double.toString((cross[2]-cross[1])/cross[3]);
				String rawText=Double.toString(cross[2]/cross[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][5].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][14].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][23].setText(rawText.substring(0,subIndex));
			}
			//close
			if(close[3]>0.0){
				String ballotText=Double.toString((close[2]-close[0])/close[3]);
				String teamText=Double.toString((close[2]-close[1])/close[3]);
				String rawText=Double.toString(close[2]/close[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][6].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][15].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][24].setText(rawText.substring(0,subIndex));
			}
			//wdirect
			if(wdirect[3]>0.0){
				String ballotText=Double.toString((wdirect[2]-wdirect[0])/wdirect[3]);
				String teamText=Double.toString((wdirect[2]-wdirect[1])/wdirect[3]);
				String rawText=Double.toString(wdirect[2]/wdirect[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][7].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][16].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][25].setText(rawText.substring(0,subIndex));
			}
			//wcross
			if(wcross[3]>0.0){
				String ballotText=Double.toString((wcross[2]-wcross[0])/wcross[3]);
				String teamText=Double.toString((wcross[2]-wcross[1])/wcross[3]);
				String rawText=Double.toString(wcross[2]/wcross[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][8].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][17].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][26].setText(rawText.substring(0,subIndex));
			}
			//atty Average
			if(attyMean[3]>0.0){
				String ballotText=Double.toString((attyMean[2]-attyMean[0])/attyMean[3]);
				String teamText=Double.toString((attyMean[2]-attyMean[1])/attyMean[3]);
				String rawText=Double.toString(attyMean[2]/attyMean[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][1].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][10].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][19].setText(rawText.substring(0,subIndex));
			}
			//wit Average
			if(witMean[3]>0.0){
				String ballotText=Double.toString((witMean[2]-witMean[0])/witMean[3]);
				String teamText=Double.toString((witMean[2]-witMean[1])/witMean[3]);
				String rawText=Double.toString(witMean[2]/witMean[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][2].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][11].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][20].setText(rawText.substring(0,subIndex));
			}
			//average
			if(mean[3]>0.0){
				String ballotText=Double.toString((mean[2]-mean[0])/mean[3]);
				String teamText=Double.toString((mean[2]-mean[1])/mean[3]);
				String rawText=Double.toString(mean[2]/mean[3]);
				subIndex=4;
				if(ballotText.startsWith("-")){
					subIndex=5;
				}if(ballotText.length()<subIndex){
					subIndex=ballotText.length();
				}
				allTheLabels[i][0].setText(ballotText.substring(0,subIndex));
				subIndex=4;
				if(teamText.startsWith("-")){
					subIndex=5;
				}if(teamText.length()<subIndex){
					subIndex=teamText.length();
				}
				allTheLabels[i][9].setText(teamText.substring(0,subIndex));
				subIndex=4;
				if(rawText.startsWith("-")){
					subIndex=5;
				}if(rawText.length()<subIndex){
					subIndex=rawText.length();
				}
				allTheLabels[i][18].setText(rawText.substring(0,subIndex));
			}
		}
		//category labels
		constraints.gridy=0;
		constraints.gridx=1;
		constraints.gridwidth=9;
		gbl.setConstraints(scoreCategory[0],constraints);
		constraints.gridx=10;
		gbl.setConstraints(scoreCategory[1],constraints);
		constraints.gridx=19;
		gbl.setConstraints(scoreCategory[2],constraints);
		constraints.gridwidth=1;
		for(int i=0;i<scoreCategory.length;i++){
			add(scoreCategory[i]);
		}
		//header labels
		constraints.gridy=1;
		for(int i=0;i<28;i++){
			constraints.gridx=i;
			headerLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			gbl.setConstraints(headerLabel[i], constraints);
			add(headerLabel[i]);
		}
		constraints.gridx=0;
		for(int i=0;i<rosterNames.length;i++){
			constraints.gridy=i+2;
			gbl.setConstraints(rosterNames[i], constraints);
			add(rosterNames[i]);
		}
		for(int i=0;i<allTheLabels.length;i++){
			for(int j=0;j<allTheLabels[0].length;j++){
				constraints.gridx=j+1;
				constraints.gridy=i+2;
				if(j<18){
					if(allTheLabels[i][j].getText().startsWith("-")){
						allTheLabels[i][j].setOpaque(true);
						allTheLabels[i][j].setBackground(Color.pink);
					}else if(!allTheLabels[i][j].getText().startsWith("N")){
						allTheLabels[i][j].setOpaque(true);
						allTheLabels[i][j].setBackground(Color.green);
					}
				}else if(!allTheLabels[i][j].getText().startsWith("N")){
					allTheLabels[i][j].setOpaque(true);
					allTheLabels[i][j].setBackground(Color.yellow);
				}
				allTheLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				gbl.setConstraints(allTheLabels[i][j], constraints);
				add(allTheLabels[i][j]);
			}
		}
	}
}
