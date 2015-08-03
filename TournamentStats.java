package Temp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TournamentStats extends JFrame{
	public TournamentStats(Tournament theTournament, ArrayList<String> roster){
		JPanel statsPanel = new JPanel();
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,3));
		JLabel[] topLabels = {new JLabel("Average",JLabel.CENTER),new JLabel("Plaintiff",JLabel.CENTER),new JLabel("Defense",JLabel.CENTER)};
		for(int i=0;i<topLabels.length;i++){
			topPanel.add(topLabels[i]);
		}
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		statsPanel.setLayout(gbl);
		constraints.fill=GridBagConstraints.BOTH;
		ArrayList<Round> plaintiffRounds=new ArrayList<Round>();
		ArrayList<Round> defenseRounds=new ArrayList<Round>();
		ArrayList<Integer> teamRoster=new ArrayList<Integer>();
		JLabel[][] allTheLabels;
		for(int i=0;i<4;i++){
			if(theTournament.getRound(i).getSide()){
				plaintiffRounds.add(theTournament.getRound(i));
				for(int j=0;j<11;j++){
					if(!teamRoster.contains(theTournament.getRound(i).getDrakeRoles()[j])){
						teamRoster.add(theTournament.getRound(i).getDrakeRoles()[j]);
					}
				}
			}else{
				defenseRounds.add(theTournament.getRound(i));
				for(int j=11;j<22;j++){
					if(!teamRoster.contains(theTournament.getRound(i).getDrakeRoles()[j])){
						teamRoster.add(theTournament.getRound(i).getDrakeRoles()[j]);
					}
				}
			}
		}
		allTheLabels=new JLabel[(teamRoster.size()+1)*3][22];
		allTheLabels[0][0]=new JLabel("+/- Ballot");
		allTheLabels[teamRoster.size()+1][0]=new JLabel("+/- Team");
		allTheLabels[2*teamRoster.size()+2][0]=new JLabel("Raw Score");
		for(int i=0;i<teamRoster.size();i++){
			allTheLabels[i+1][0]=new JLabel(roster.get(teamRoster.get(i)));
			allTheLabels[i+teamRoster.size()+2][0]=new JLabel(roster.get(teamRoster.get(i)));
			allTheLabels[i+2*teamRoster.size()+3][0]=new JLabel(roster.get(teamRoster.get(i)));
		}
		for(int i=1;i<16;i+=7){
			for(int j=0;j<teamRoster.size()*3;j+=(teamRoster.size()+1)){
				allTheLabels[j][i]=new JLabel("Average");
				allTheLabels[j][i+1]=new JLabel("Open");
				allTheLabels[j][i+2]=new JLabel("Direct");
				allTheLabels[j][i+3]=new JLabel("Cross");
				allTheLabels[j][i+4]=new JLabel("Close");
				allTheLabels[j][i+5]=new JLabel("Wit Direct");
				allTheLabels[j][i+6]=new JLabel("Wit Cross");
			}
		}
		//plaintiff opening
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(plaintiffRounds.get(0).getAll()[7]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[29];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[43];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}
			if(plaintiffRounds.get(1).getAll()[7]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[29];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[43];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][9]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][9]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][9]=new JLabel(averageRawText.substring(0,subIndex));
			}else{
				allTheLabels[i+1][9]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][9]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][9]=new JLabel("NA");
			}
		}
		//defense opening
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(defenseRounds.get(0).getAll()[18]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[57];
				rawScoreSum+=defenseRounds.get(0).getAll()[71];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}
			if(defenseRounds.get(1).getAll()[18]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[57];
				rawScoreSum+=defenseRounds.get(1).getAll()[71];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][16]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][16]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][16]=new JLabel(averageRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][16]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][16]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][16]=new JLabel("NA");
			}
		}
		//Plaintiff attorney directs
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(plaintiffRounds.get(0).getAll()[8]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[30];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[44];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(0).getAll()[10]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[33];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[47];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(0).getAll()[12]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[36];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[50];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}
			if(plaintiffRounds.get(1).getAll()[8]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[30];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[44];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(1).getAll()[10]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[33];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[47];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(1).getAll()[12]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[36];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[50];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][10]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][10]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][10]=new JLabel(averageRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][10]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][10]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][10]=new JLabel("NA");
			}
		}
		//Defense attorney directs
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(defenseRounds.get(0).getAll()[22]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[61];
				rawScoreSum+=defenseRounds.get(0).getAll()[75];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}else if(defenseRounds.get(0).getAll()[24]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[64];
				rawScoreSum+=defenseRounds.get(0).getAll()[78];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}else if(defenseRounds.get(0).getAll()[26]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[67];
				rawScoreSum+=defenseRounds.get(0).getAll()[81];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}
			if(defenseRounds.get(1).getAll()[22]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[61];
				rawScoreSum+=defenseRounds.get(1).getAll()[75];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}else if(defenseRounds.get(1).getAll()[24]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[64];
				rawScoreSum+=defenseRounds.get(1).getAll()[78];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}else if(defenseRounds.get(1).getAll()[26]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[67];
				rawScoreSum+=defenseRounds.get(1).getAll()[81];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][17]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][17]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][17]=new JLabel(averageRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][17]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][17]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][17]=new JLabel("NA");
			}
		}
		//plaintiff attorney cross
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(plaintiffRounds.get(0).getAll()[14]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[39];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[53];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(0).getAll()[15]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[40];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[54];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(0).getAll()[16]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[41];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[55];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}
			if(plaintiffRounds.get(1).getAll()[14]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[39];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[53];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(1).getAll()[15]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[40];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[54];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(1).getAll()[16]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[41];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[55];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][11]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][11]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][11]=new JLabel(averageRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][11]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][11]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][11]=new JLabel("NA");
			}
		}
		//defense attorney cross
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(defenseRounds.get(0).getAll()[19]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[58];
				rawScoreSum+=defenseRounds.get(0).getAll()[72];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}else if(defenseRounds.get(0).getAll()[20]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[59];
				rawScoreSum+=defenseRounds.get(0).getAll()[73];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}else if(defenseRounds.get(0).getAll()[21]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[60];
				rawScoreSum+=defenseRounds.get(0).getAll()[74];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}
			if(defenseRounds.get(1).getAll()[19]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[58];
				rawScoreSum+=defenseRounds.get(1).getAll()[72];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}else if(defenseRounds.get(1).getAll()[20]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[59];
				rawScoreSum+=defenseRounds.get(1).getAll()[73];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}else if(defenseRounds.get(1).getAll()[21]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[60];
				rawScoreSum+=defenseRounds.get(1).getAll()[74];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][18]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][18]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][18]=new JLabel(averageRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][18]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][18]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][18]=new JLabel("NA");
			}
		}
		//plaintiff closing
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(plaintiffRounds.get(0).getAll()[17]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[42];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[56];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}
			if(plaintiffRounds.get(1).getAll()[17]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[42];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[56];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][12]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][12]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][12]=new JLabel(averageRawText.substring(0,subIndex));
			}else{
				allTheLabels[i+1][12]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][12]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][12]=new JLabel("NA");
			}
		}
		//defense closing
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double denominator=0.0;
			if(defenseRounds.get(0).getAll()[28]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[70];
				rawScoreSum+=defenseRounds.get(0).getAll()[84];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}
			if(defenseRounds.get(1).getAll()[28]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[70];
				rawScoreSum+=defenseRounds.get(1).getAll()[84];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageBallotText = Double.toString(averageBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][19]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][19]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][19]=new JLabel(averageRawText.substring(0,subIndex));
			}else{
				allTheLabels[i+1][19]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][19]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][19]=new JLabel("NA");
			}
		}
		//plaintiff witness direct & cross
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double rawScoreXSum=0.0;
			double denominator=0.0;
			if(plaintiffRounds.get(0).getAll()[9]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[31];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[45];
				rawScoreXSum+=plaintiffRounds.get(0).getAll()[32];
				rawScoreXSum+=plaintiffRounds.get(0).getAll()[46];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(0).getAll()[11]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[34];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[48];
				rawScoreXSum+=plaintiffRounds.get(0).getAll()[35];
				rawScoreXSum+=plaintiffRounds.get(0).getAll()[49];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(0).getAll()[13]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(0).getAll()[37];
				rawScoreSum+=plaintiffRounds.get(0).getAll()[51];
				rawScoreXSum+=plaintiffRounds.get(0).getAll()[38];
				rawScoreXSum+=plaintiffRounds.get(0).getAll()[52];
				ballotAverageSum+=plaintiffRounds.get(0).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(0).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(0).getBallot2TeamAverage();
			}
			if(plaintiffRounds.get(1).getAll()[9]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[31];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[45];
				rawScoreXSum+=plaintiffRounds.get(1).getAll()[32];
				rawScoreXSum+=plaintiffRounds.get(1).getAll()[46];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(1).getAll()[11]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[34];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[48];
				rawScoreXSum+=plaintiffRounds.get(1).getAll()[35];
				rawScoreXSum+=plaintiffRounds.get(1).getAll()[49];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}else if(plaintiffRounds.get(1).getAll()[13]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=plaintiffRounds.get(1).getAll()[37];
				rawScoreSum+=plaintiffRounds.get(1).getAll()[51];
				rawScoreXSum+=plaintiffRounds.get(1).getAll()[38];
				rawScoreXSum+=plaintiffRounds.get(1).getAll()[52];
				ballotAverageSum+=plaintiffRounds.get(1).getBallot1Average();
				ballotAverageSum+=plaintiffRounds.get(1).getBallot2Average();
				teamAverageSum+=plaintiffRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=plaintiffRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageXRaw = rawScoreXSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageXTeam = averageXRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				double averageXBallot = averageXRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageXRawText = Double.toString(averageXRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageXTeamText = Double.toString(averageXTeam);
				String averageBallotText = Double.toString(averageBallot);
				String averageXBallotText = Double.toString(averageXBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][13]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][13]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][13]=new JLabel(averageRawText.substring(0,subIndex));
				subIndex=4;
				if(averageXBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageXBallotText.length()<subIndex){
					subIndex=averageXBallotText.length();
				}
				allTheLabels[i+1][14]=new JLabel(averageXBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageXTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageXTeamText.length()<subIndex){
					subIndex=averageXTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][14]=new JLabel(averageXTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageXRawText.length()<4){
					subIndex=averageXRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][14]=new JLabel(averageXRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][13]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][13]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][13]=new JLabel("NA");
				allTheLabels[i+1][14]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][14]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][14]=new JLabel("NA");
			}
		}
		//defense witness direct & cross
		for(int i=0;i<teamRoster.size();i++){
			double ballotAverageSum=0.0;
			double teamAverageSum=0.0;
			double rawScoreSum=0.0;
			double rawScoreXSum=0.0;
			double denominator=0.0;
			if(defenseRounds.get(0).getAll()[23]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[62];
				rawScoreSum+=defenseRounds.get(0).getAll()[76];
				rawScoreXSum+=defenseRounds.get(0).getAll()[63];
				rawScoreXSum+=defenseRounds.get(0).getAll()[77];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}else if(defenseRounds.get(0).getAll()[25]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[65];
				rawScoreSum+=defenseRounds.get(0).getAll()[79];
				rawScoreXSum+=defenseRounds.get(0).getAll()[66];
				rawScoreXSum+=defenseRounds.get(0).getAll()[80];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}else if(defenseRounds.get(0).getAll()[27]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(0).getAll()[68];
				rawScoreSum+=defenseRounds.get(0).getAll()[82];
				rawScoreXSum+=defenseRounds.get(0).getAll()[69];
				rawScoreXSum+=defenseRounds.get(0).getAll()[83];
				ballotAverageSum+=defenseRounds.get(0).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(0).getBallot2Average();
				teamAverageSum+=defenseRounds.get(0).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(0).getBallot2TeamAverage();
			}
			if(defenseRounds.get(1).getAll()[23]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[62];
				rawScoreSum+=defenseRounds.get(1).getAll()[76];
				rawScoreXSum+=defenseRounds.get(1).getAll()[63];
				rawScoreXSum+=defenseRounds.get(1).getAll()[77];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}else if(defenseRounds.get(1).getAll()[25]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[65];
				rawScoreSum+=defenseRounds.get(1).getAll()[79];
				rawScoreXSum+=defenseRounds.get(1).getAll()[66];
				rawScoreXSum+=defenseRounds.get(1).getAll()[80];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}else if(defenseRounds.get(1).getAll()[27]==teamRoster.get(i)){
				denominator+=2.0;
				rawScoreSum+=defenseRounds.get(1).getAll()[68];
				rawScoreSum+=defenseRounds.get(1).getAll()[82];
				rawScoreXSum+=defenseRounds.get(1).getAll()[69];
				rawScoreXSum+=defenseRounds.get(1).getAll()[83];
				ballotAverageSum+=defenseRounds.get(1).getBallot1Average();
				ballotAverageSum+=defenseRounds.get(1).getBallot2Average();
				teamAverageSum+=defenseRounds.get(1).getBallot1TeamAverage();
				teamAverageSum+=defenseRounds.get(1).getBallot2TeamAverage();
			}
			if(denominator!=0.0){
				double averageRaw = rawScoreSum/denominator;
				double averageXRaw = rawScoreXSum/denominator;
				double averageTeam = averageRaw-(teamAverageSum/denominator);
				double averageXTeam = averageXRaw-(teamAverageSum/denominator);
				double averageBallot = averageRaw-(ballotAverageSum/denominator);
				double averageXBallot = averageXRaw-(ballotAverageSum/denominator);
				String averageRawText = Double.toString(averageRaw);
				String averageXRawText = Double.toString(averageXRaw);
				String averageTeamText = Double.toString(averageTeam);
				String averageXTeamText = Double.toString(averageXTeam);
				String averageBallotText = Double.toString(averageBallot);
				String averageXBallotText = Double.toString(averageXBallot);
				int subIndex=4;
				if(averageBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageBallotText.length()<subIndex){
					subIndex=averageBallotText.length();
				}
				allTheLabels[i+1][20]=new JLabel(averageBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageTeamText.length()<subIndex){
					subIndex=averageTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][20]=new JLabel(averageTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageRawText.length()<4){
					subIndex=averageRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][20]=new JLabel(averageRawText.substring(0,subIndex));
				subIndex=4;
				if(averageXBallotText.startsWith("-")){
					subIndex=5;
				}
				if(averageXBallotText.length()<subIndex){
					subIndex=averageXBallotText.length();
				}
				allTheLabels[i+1][21]=new JLabel(averageXBallotText.substring(0,subIndex));
				subIndex=4;
				if(averageXTeamText.startsWith("-")){
					subIndex=5;
				}
				if(averageXTeamText.length()<subIndex){
					subIndex=averageXTeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][21]=new JLabel(averageXTeamText.substring(0,subIndex));
				subIndex=4;
				if(averageXRawText.length()<4){
					subIndex=averageXRawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][21]=new JLabel(averageXRawText.substring(0,subIndex));
			}
			else{
				allTheLabels[i+1][20]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][20]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][20]=new JLabel("NA");
				allTheLabels[i+1][21]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][21]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][21]=new JLabel("NA");
			}
		}
		//plaintiff & defense averages
		for(int i=0;i<teamRoster.size();i++){
			double plaintiffRaw=0.0;
			double plaintiffTeam=0.0;
			double plaintiffBallot=0.0;
			double plaintiffDenominator=0.0;
			double defenseRaw=0.0;
			double defenseTeam=0.0;
			double defenseBallot=0.0;
			double defenseDenominator=0.0;
			for(int j=0;j<6;j++){	
				if(!allTheLabels[i+1][j+9].getText().startsWith("N")){
					plaintiffBallot+=Double.parseDouble(allTheLabels[i+1][j+9].getText());
					plaintiffTeam+=Double.parseDouble(allTheLabels[i+2+teamRoster.size()][j+9].getText());
					plaintiffRaw+=Double.parseDouble(allTheLabels[i+3+2*teamRoster.size()][j+9].getText());
					plaintiffDenominator+=1.0;
				}
				if(!allTheLabels[i+1][j+16].getText().startsWith("N")){
					defenseBallot+=Double.parseDouble(allTheLabels[i+1][j+16].getText());
					defenseTeam+=Double.parseDouble(allTheLabels[i+2+teamRoster.size()][j+16].getText());
					defenseRaw+=Double.parseDouble(allTheLabels[i+3+2*teamRoster.size()][j+16].getText());
					defenseDenominator+=1.0;
				}
			}
			if(plaintiffDenominator==0.0){
				allTheLabels[i+1][8]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][8]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][8]=new JLabel("NA");
			}else{
				String TeamText=Double.toString(plaintiffTeam/plaintiffDenominator);
				String BallotText=Double.toString(plaintiffBallot/plaintiffDenominator);
				String RawText=Double.toString(plaintiffRaw/plaintiffDenominator);
				int subIndex=4;
				if(BallotText.startsWith("-")){
					subIndex=5;
				}
				if(BallotText.length()<subIndex){
					subIndex=BallotText.length();
				}
				allTheLabels[i+1][8]=new JLabel(BallotText.substring(0,subIndex));
				subIndex=4;
				if(TeamText.startsWith("-")){
					subIndex=5;
				}
				if(TeamText.length()<subIndex){
					subIndex=TeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][8]=new JLabel(TeamText.substring(0,subIndex));
				subIndex=4;
				if(RawText.length()<4){
					subIndex=RawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][8]=new JLabel(RawText.substring(0,subIndex));
			}
			if(defenseDenominator==0.0){
				allTheLabels[i+1][15]=new JLabel("NA");
				allTheLabels[i+2+teamRoster.size()][15]=new JLabel("NA");
				allTheLabels[i+3+2*teamRoster.size()][15]=new JLabel("NA");
			}else{
				String TeamText=Double.toString(defenseTeam/defenseDenominator);
				String BallotText=Double.toString(defenseBallot/defenseDenominator);
				String RawText=Double.toString(defenseRaw/defenseDenominator);
				int subIndex=4;
				if(BallotText.startsWith("-")){
					subIndex=5;
				}
				if(BallotText.length()<subIndex){
					subIndex=BallotText.length();
				}
				allTheLabels[i+1][15]=new JLabel(BallotText.substring(0,subIndex));
				subIndex=4;
				if(TeamText.startsWith("-")){
					subIndex=5;
				}
				if(TeamText.length()<subIndex){
					subIndex=TeamText.length();
				}
				allTheLabels[i+2+teamRoster.size()][15]=new JLabel(TeamText.substring(0,subIndex));
				subIndex=4;
				if(RawText.length()<4){
					subIndex=RawText.length();
				}
				allTheLabels[i+3+2*teamRoster.size()][15]=new JLabel(RawText.substring(0,subIndex));
			}
		}
		//overall averages
		for(int i=0;i<teamRoster.size();i++){
			double totalRaw = 0.0;
			double totalBallot = 0.0;
			double totalTeam = 0.0;
			double totalDenominator = 0.0;
			for(int j=0;j<6;j++){
				double raw = 0.0;
				double ballot = 0.0;
				double team =0.0;
				double denominator=0.0;
				if(!allTheLabels[i+1][j+9].getText().startsWith("N")){
					ballot+=Double.parseDouble(allTheLabels[i+1][j+9].getText());
					team+=Double.parseDouble(allTheLabels[i+2+teamRoster.size()][j+9].getText());
					raw+=Double.parseDouble(allTheLabels[i+3+2*teamRoster.size()][j+9].getText());
					denominator++;
					totalBallot+=Double.parseDouble(allTheLabels[i+1][j+9].getText());
					totalTeam+=Double.parseDouble(allTheLabels[i+2+teamRoster.size()][j+9].getText());
					totalRaw+=Double.parseDouble(allTheLabels[i+3+2*teamRoster.size()][j+9].getText());
					totalDenominator++;

				}
				if(!allTheLabels[i+1][j+16].getText().startsWith("N")){
					ballot+=Double.parseDouble(allTheLabels[i+1][j+16].getText());
					team+=Double.parseDouble(allTheLabels[i+2+teamRoster.size()][j+16].getText());
					raw+=Double.parseDouble(allTheLabels[i+3+2*teamRoster.size()][j+16].getText());
					denominator++;
					totalBallot+=Double.parseDouble(allTheLabels[i+1][j+16].getText());
					totalTeam+=Double.parseDouble(allTheLabels[i+2+teamRoster.size()][j+16].getText());
					totalRaw+=Double.parseDouble(allTheLabels[i+3+2*teamRoster.size()][j+16].getText());
					totalDenominator++;
				}
				if(denominator==0.0){
					allTheLabels[i+1][j+2]=new JLabel("NA");
					allTheLabels[i+2+teamRoster.size()][j+2]=new JLabel("NA");
					allTheLabels[i+3+2*teamRoster.size()][j+2]=new JLabel("NA");
				}else{
					String ballotText = Double.toString(ballot/denominator);
					String teamText = Double.toString(team/denominator);
					String rawText = Double.toString(raw/denominator);
					int subIndex=4;
					if(ballotText.startsWith("-")){
						subIndex=5;
					}
					if(ballotText.length()<subIndex){
						subIndex=ballotText.length();
					}
					allTheLabels[i+1][j+2]=new JLabel(ballotText.substring(0,subIndex));
					subIndex=4;
					if(teamText.startsWith("-")){
						subIndex=5;
					}
					if(teamText.length()<subIndex){
						subIndex=teamText.length();
					}
					allTheLabels[i+2+teamRoster.size()][j+2]=new JLabel(teamText.substring(0,subIndex));
					subIndex=4;
					if(rawText.length()<4){
						subIndex=rawText.length();
					}
					allTheLabels[i+3+2*teamRoster.size()][j+2]=new JLabel(rawText.substring(0,subIndex));
				}
			}
			String ballotText = Double.toString(totalBallot/totalDenominator);
			String teamText = Double.toString(totalTeam/totalDenominator);
			String rawText = Double.toString(totalRaw/totalDenominator);
			int subIndex=4;
			if(ballotText.startsWith("-")){
				subIndex=5;
			}
			if(ballotText.length()<subIndex){
				subIndex=ballotText.length();
			}
			allTheLabels[i+1][1]=new JLabel(ballotText.substring(0,subIndex));
			subIndex=4;
			if(teamText.startsWith("-")){
				subIndex=5;
			}
			if(teamText.length()<subIndex){
				subIndex=teamText.length();
			}
			allTheLabels[i+2+teamRoster.size()][1]=new JLabel(teamText.substring(0,subIndex));
			subIndex=4;
			if(rawText.length()<4){
				subIndex=rawText.length();
			}
			allTheLabels[i+3+2*teamRoster.size()][1]=new JLabel(rawText.substring(0,subIndex));
		}
		//color code the team and ballot averages
		for(int i=0;i<teamRoster.size();i++){
			for(int j=0;j<7;j++){
				if(allTheLabels[i+1][j+1].getText().startsWith("-")){
					allTheLabels[i+1][j+1].setOpaque(true);
					allTheLabels[i+1][j+1].setBackground(Color.pink);
				}else if(!allTheLabels[i+1][j+1].getText().startsWith("N")){
					allTheLabels[i+1][j+1].setOpaque(true);
					allTheLabels[i+1][j+1].setBackground(Color.green);
				}
				if(allTheLabels[i+2+teamRoster.size()][j+1].getText().startsWith("-")){
					allTheLabels[i+2+teamRoster.size()][j+1].setOpaque(true);
					allTheLabels[i+2+teamRoster.size()][j+1].setBackground(Color.pink);
				}else if(!allTheLabels[i+2+teamRoster.size()][j+1].getText().startsWith("N")){
					allTheLabels[i+2+teamRoster.size()][j+1].setOpaque(true);
					allTheLabels[i+2+teamRoster.size()][j+1].setBackground(Color.green);
				}
				if(!allTheLabels[i+3+2*teamRoster.size()][j+1].getText().startsWith("N")){
					allTheLabels[i+3+2*teamRoster.size()][j+1].setOpaque(true);
					allTheLabels[i+3+2*teamRoster.size()][j+1].setBackground(Color.yellow);
				}
				if(allTheLabels[i+1][j+8].getText().startsWith("-")){
					allTheLabels[i+1][j+8].setOpaque(true);
					allTheLabels[i+1][j+8].setBackground(Color.pink);
				}else if(!allTheLabels[i+1][j+8].getText().startsWith("N")){
					allTheLabels[i+1][j+8].setOpaque(true);
					allTheLabels[i+1][j+8].setBackground(Color.green);
				}
				if(allTheLabels[i+2+teamRoster.size()][j+8].getText().startsWith("-")){
					allTheLabels[i+2+teamRoster.size()][j+8].setOpaque(true);
					allTheLabels[i+2+teamRoster.size()][j+8].setBackground(Color.pink);
				}else if(!allTheLabels[i+2+teamRoster.size()][j+8].getText().startsWith("N")){
					allTheLabels[i+2+teamRoster.size()][j+8].setOpaque(true);
					allTheLabels[i+2+teamRoster.size()][j+8].setBackground(Color.green);
				}
				if(!allTheLabels[i+3+2*teamRoster.size()][j+8].getText().startsWith("N")){
					allTheLabels[i+3+2*teamRoster.size()][j+8].setOpaque(true);
					allTheLabels[i+3+2*teamRoster.size()][j+8].setBackground(Color.yellow);
				}
				if(allTheLabels[i+1][j+15].getText().startsWith("-")){
					allTheLabels[i+1][j+15].setOpaque(true);
					allTheLabels[i+1][j+15].setBackground(Color.pink);
				}else if(!allTheLabels[i+1][j+15].getText().startsWith("N")){
					allTheLabels[i+1][j+15].setOpaque(true);
					allTheLabels[i+1][j+15].setBackground(Color.green);
				}
				if(allTheLabels[i+2+teamRoster.size()][j+15].getText().startsWith("-")){
					allTheLabels[i+2+teamRoster.size()][j+15].setOpaque(true);
					allTheLabels[i+2+teamRoster.size()][j+15].setBackground(Color.pink);
				}else if(!allTheLabels[i+2+teamRoster.size()][j+15].getText().startsWith("N")){
					allTheLabels[i+2+teamRoster.size()][j+15].setOpaque(true);
					allTheLabels[i+2+teamRoster.size()][j+15].setBackground(Color.green);
				}
				if(!allTheLabels[i+3+2*teamRoster.size()][j+15].getText().startsWith("N")){
					allTheLabels[i+3+2*teamRoster.size()][j+15].setOpaque(true);
					allTheLabels[i+3+2*teamRoster.size()][j+15].setBackground(Color.yellow);
				}
			}
		}
		//add labels to the JFrame
		for(int i=0;i<(3+3*teamRoster.size());i++){
			constraints.gridy=i;
			for(int j=0;j<22;j++){
				constraints.gridx=j;
				allTheLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				gbl.setConstraints(allTheLabels[i][j],constraints);
				statsPanel.add(allTheLabels[i][j]);
			}
		}
		add(topPanel,BorderLayout.NORTH);
		add(statsPanel,BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
}
