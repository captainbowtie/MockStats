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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI extends JFrame {
	private Season theSeason=new Season();
	//Variables for initial window
	private JPanel startupPanel = new JPanel();
	private JButton[] startupButtons = {new JButton("New Season"),new JButton("Load Season")};
	//Variables for creating new season
	private JPanel yearPanel;
	private JLabel yearPrompt;
	private JTextField yearField;
	private JButton yearContinue;
	//Variables for initial roster input
	private JPanel initialRosterPanel;
	private JTextField initialRosterName;
	private JButton[] initialRosterButton = new JButton[2];
	//Variables for initial witness input
	private JPanel initialWitnessPanel;
	private JTextField initialWitnessName;
	private JButton[] initialWitnessButton = new JButton[2];
	//Main window buttons
	private JButton[] mainButtons = new JButton[5];
	//Roster Display Variables
	private JPanel[] rosterDisplayPanel;
	private JLabel[] rosterDisplayLabel;
	private JTextField rosterDisplayInput;
	private JButton[] rosterDisplayButton=new JButton[2];
	//Witness display variables
	private JPanel[] witnessDisplayPanel;
	private JLabel[] witnessDisplayLabel;
	private JTextField witnessDisplayInput;
	private JButton[] witnessDisplayButton=new JButton[2];
	//Score entry variables
	private JPanel[] scoreEntryPanel;
	private JComboBox scoreEntrySelector;
	private JCheckBox scoreEntryPostCheck;
	private RoundInputPanel[] scoreEntryRoundPanel;
	private JButton[] scoreEntryButton=new JButton[2];
	private int scoreEntryDisplayedTournamentIndex;
	//New tournament entry variables
	private JPanel newTournamentPanel;
	private JTextField newTournamentName;
	private JButton[] newTournamentButton = new JButton[2];
	//Data analysis menu variables
	private JButton[] analysisMenuButton = new JButton[4];
	//Tournament Analysis variables
	private JPanel[] tournamentAnalysisPanels = new JPanel[3];
	private JComboBox tournamentAnalysisSelector;
	private ColorCodedRoundAnalysis[] roundAnalysisPanel = new ColorCodedRoundAnalysis[4];
	private JButton[] tournamentAnalysisButton = new JButton[2];
	private TournamentStats statsWindow=null;
	//Member analysis variables
	private JPanel[] memberStatsPanel;
	private MemberStatisticsPanel memberStats=null;
	private JCheckBox[] prePostChecks;
	private JButton[] memberStatsButton=new JButton[1];;
	private JScrollPane centerPane;
	//part analysis variables
	private JPanel partAnalysisPanel;
	private JButton partAnalysisButton=null;
	
	public GUI(){
		setVisible(false);
		setTitle("Mock Stats");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().removeAll();
		for(int i=0;i<2;i++){
			startupButtons[i].addActionListener(new ButtonListener());
			startupPanel.add(startupButtons[i]);
		}
		setLayout(new FlowLayout());
		add(startupPanel);
		pack();
		setVisible(true);
	}
	public void newSeason(){
		setVisible(false);
		getContentPane().removeAll();
		yearPanel = new JPanel();
		yearPrompt = new JLabel("Season Year:");
		yearField=new JTextField(4);
		yearContinue=new JButton("Continue");
		yearPanel.add(yearPrompt);
		yearPanel.add(yearField);
		yearContinue.addActionListener(new ButtonListener());
		setLayout(new BorderLayout());
		add(yearPanel,BorderLayout.CENTER);
		add(yearContinue,BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	public void initialRosterInput(){
		setVisible(false);
		setTitle("Roster Entry");
		theSeason.setYear(yearField.getText());
		getContentPane().removeAll();
		initialRosterPanel = new JPanel();
		initialRosterName = new JTextField("Name",8);
		initialRosterButton[0]=new JButton("Next Name");
		initialRosterButton[1]=new JButton("Done");
		initialRosterPanel.add(initialRosterName);
		for(int i=0;i<initialRosterButton.length;i++){
			initialRosterButton[i].addActionListener(new ButtonListener());
			initialRosterPanel.add(initialRosterButton[i]);
		}
		setLayout(new FlowLayout());
		add(initialRosterPanel);
		pack();
		setVisible(true);
	}
	public void addInitialRoster(){
		theSeason.createMember(initialRosterName.getText());
		initialRosterName.setText("");
	}
	public void initialWitnessInput(){
		setVisible(false);
		setTitle("Witness Entry");
		getContentPane().removeAll();
		initialWitnessPanel = new JPanel();
		initialWitnessName = new JTextField("Witness",8);
		initialWitnessButton[0]=new JButton("Next Witness");
		initialWitnessButton[1]=new JButton("Done");
		initialWitnessPanel.add(initialWitnessName);
		for(int i=0;i<initialWitnessButton.length;i++){
			initialWitnessButton[i].addActionListener(new ButtonListener());
			initialWitnessPanel.add(initialWitnessButton[i]);
		}
		setLayout(new FlowLayout());
		add(initialWitnessPanel);
		pack();
		setVisible(true);	
	}
	public void addInitialWitness(){
		theSeason.createWitness(initialWitnessName.getText());
		initialWitnessName.setText("");
	}
	public void mainWindow(){
		setVisible(false);
		setTitle("Mock Stats");
		getContentPane().removeAll();
		mainButtons[0]=new JButton("Data Analysis");
		mainButtons[1]=new JButton("Tournament Entry");
		mainButtons[2]=new JButton("Roster");
		mainButtons[3]=new JButton("Witnesses");
		mainButtons[4]=new JButton("Save Season");
		setLayout(new GridLayout(mainButtons.length,1));
		for(int i=0;i<mainButtons.length;i++){
			mainButtons[i].addActionListener(new ButtonListener());
			add(mainButtons[i]);
		}
		pack();
		setVisible(true);
	}
	public void rosterDisplay(){
		setVisible(false);
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		rosterDisplayPanel=new JPanel[2];
		rosterDisplayPanel[0]=new JPanel();
		rosterDisplayPanel[1]=new JPanel();
		ArrayList<String> roster = theSeason.getRoster();
		rosterDisplayPanel[0].setLayout(new GridLayout(roster.size(),1));
		rosterDisplayLabel=new JLabel[roster.size()];
		for(int i=0;i<roster.size();i++){
			rosterDisplayLabel[i]=new JLabel(roster.get(i));
			rosterDisplayPanel[0].add(rosterDisplayLabel[i]);
		}
		rosterDisplayInput = new JTextField(8);
		rosterDisplayButton[0] = new JButton("Add Member");
		rosterDisplayButton[1] = new JButton("Main Menu");
		rosterDisplayPanel[1].add(rosterDisplayInput);
		for(int i=0;i<rosterDisplayButton.length;i++){
			rosterDisplayButton[i].addActionListener(new ButtonListener());
			rosterDisplayPanel[1].add(rosterDisplayButton[i]);
		}
		add(rosterDisplayPanel[0],BorderLayout.CENTER);
		add(rosterDisplayPanel[1],BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	public void addToRoster(){
		theSeason.createMember(rosterDisplayInput.getText());
		rosterDisplay();
	}
	public void witnessDisplay(){
		setVisible(false);
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		witnessDisplayPanel=new JPanel[2];
		witnessDisplayPanel[0]=new JPanel();
		witnessDisplayPanel[1]=new JPanel();
		ArrayList<String> witnessList = theSeason.getWitnesses();
		witnessDisplayPanel[0].setLayout(new GridLayout(witnessList.size(),0));
		witnessDisplayLabel=new JLabel[witnessList.size()];
		for(int i=0;i<witnessList.size();i++){
			witnessDisplayLabel[i]=new JLabel(witnessList.get(i));
			witnessDisplayPanel[0].add(witnessDisplayLabel[i]);
		}
		witnessDisplayInput=new JTextField(8);
		witnessDisplayButton[0]=new JButton("Add Witness");
		witnessDisplayButton[1]=new JButton("Main Menu");
		witnessDisplayPanel[1].add(witnessDisplayInput);
		for(int i=0;i<witnessDisplayButton.length;i++){
			witnessDisplayButton[i].addActionListener(new ButtonListener());
			witnessDisplayPanel[1].add(witnessDisplayButton[i]);
		}
		add(witnessDisplayPanel[0],BorderLayout.CENTER);
		add(witnessDisplayPanel[1],BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	public void addWitness(){
		theSeason.createWitness(witnessDisplayInput.getText());
		witnessDisplay();
	}
	public void scoreInput(int index){
		setVisible(false);
		getContentPane().removeAll();
		if(theSeason.getNumberOfTournaments()==0){
			newTournamentInput();
		}
		else{
			scoreEntryDisplayedTournamentIndex=index;
			setLayout(new BorderLayout());
			scoreEntryPanel=new JPanel[3];
			for(int i=0;i<scoreEntryPanel.length;i++){
				scoreEntryPanel[i]=new JPanel();
			}
			String[] tournNames = new String[theSeason.getNumberOfTournaments()];
			for(int i=0;i<theSeason.getNumberOfTournaments();i++){
				tournNames[i]=theSeason.getTournament(i).getTournamentName();
			}
			scoreEntrySelector = new JComboBox(tournNames);
			scoreEntrySelector.setSelectedIndex(index);
			scoreEntrySelector.addItemListener(new ComboListener());
			scoreEntryPostCheck = new JCheckBox("Post Stack",theSeason.getTournament(index).isPostStack());
			scoreEntryRoundPanel=new RoundInputPanel[4];
			for(int i=0;i<4;i++){
				int[] r = theSeason.getTournament(index).getRound(i).getAll();
				scoreEntryRoundPanel[i]=new RoundInputPanel(i+1,theSeason.getRoster(),theSeason.getWitnesses(),r);
			}
			scoreEntryButton[0]=new JButton("New Tournament");
			scoreEntryButton[1]=new JButton("Main Menu");
			scoreEntryPanel[0].add(scoreEntrySelector);
			scoreEntryPanel[0].add(scoreEntryPostCheck);
			for(int i=0;i<scoreEntryRoundPanel.length;i++){
				scoreEntryPanel[1].add(scoreEntryRoundPanel[i]);
			}
			for(int i=0;i<scoreEntryButton.length;i++){
				scoreEntryButton[i].addActionListener(new ButtonListener());
				scoreEntryPanel[2].add(scoreEntryButton[i]);
			}
			JScrollPane scroller = new JScrollPane(scoreEntryPanel[1]);
			add(scoreEntryPanel[0],BorderLayout.NORTH);
			add(scroller,BorderLayout.CENTER);
			add(scoreEntryPanel[2],BorderLayout.SOUTH);
			pack();
			setVisible(true);
		}
	}
	public void scoreInputFromDropdown(){
		scoreEntryDisplayedTournamentIndex=scoreEntrySelector.getSelectedIndex();
		getContentPane().removeAll();
		scoreEntryPanel=new JPanel[3];
		for(int i=0;i<scoreEntryPanel.length;i++){
			scoreEntryPanel[i]=new JPanel();
		}
		String[] tournNames = new String[theSeason.getNumberOfTournaments()];
		for(int i=0;i<theSeason.getNumberOfTournaments();i++){
			tournNames[i]=theSeason.getTournament(i).getTournamentName();
		}
		scoreEntrySelector = new JComboBox(tournNames);
		scoreEntrySelector.setSelectedIndex(scoreEntryDisplayedTournamentIndex);
		scoreEntrySelector.addItemListener(new ComboListener());
		scoreEntryPostCheck = new JCheckBox("Post Stack",theSeason.getTournament(scoreEntryDisplayedTournamentIndex).isPostStack());
		scoreEntryRoundPanel=new RoundInputPanel[4];
		for(int i=0;i<4;i++){
			int[] r = theSeason.getTournament(scoreEntryDisplayedTournamentIndex).getRound(i).getAll();
			scoreEntryRoundPanel[i]=new RoundInputPanel(i+1,theSeason.getRoster(),theSeason.getWitnesses(),r);
		}
		scoreEntryButton[0]=new JButton("New Tournament");
		scoreEntryButton[1]=new JButton("Main Menu");
		scoreEntryPanel[0].add(scoreEntrySelector);
		scoreEntryPanel[0].add(scoreEntryPostCheck);
		for(int i=0;i<scoreEntryRoundPanel.length;i++){
			scoreEntryPanel[1].add(scoreEntryRoundPanel[i]);
		}
		for(int i=0;i<scoreEntryButton.length;i++){
			scoreEntryButton[i].addActionListener(new ButtonListener());
			scoreEntryPanel[2].add(scoreEntryButton[i]);
		}
		JScrollPane scroller = new JScrollPane(scoreEntryPanel[1]);
		add(scoreEntryPanel[0],BorderLayout.NORTH);
		add(scroller,BorderLayout.CENTER);
		add(scoreEntryPanel[2],BorderLayout.SOUTH);
		repaint();
		validate();
	}
	public void newTournamentInput(){
		setVisible(false);
		getContentPane().removeAll();
		setLayout(new FlowLayout());
		newTournamentPanel = new JPanel();
		newTournamentName = new JTextField("Tour. Name",10);
		newTournamentButton[0]=new JButton("Main Menu");
		newTournamentButton[1]=new JButton("Continue");
		newTournamentPanel.add(newTournamentName);
		for(int i=0;i<newTournamentButton.length;i++){
			newTournamentButton[i].addActionListener(new ButtonListener());
			newTournamentPanel.add(newTournamentButton[i]);
		}
		add(newTournamentPanel);
		pack();
		setVisible(true);
	}
	public void newTournamentSetup(){
		theSeason.createTournament(newTournamentName.getText());
		scoreInput(theSeason.getNumberOfTournaments()-1);
	}
	public void saveTournamentData(int index){
		theSeason.getTournament(index).setPostStack(scoreEntryPostCheck.isSelected());;
		for(int i=0;i<4;i++){
			theSeason.getTournament(index).getRound(i).setAll(scoreEntryRoundPanel[i].getRoundInformation());
		}
	}
	public void loadSeason(){
		setVisible(false);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int chooserReturnValue = fileChooser.showOpenDialog(new JPanel());
		if(chooserReturnValue==JFileChooser.APPROVE_OPTION){
			int tryRead=theSeason.loadSeason(fileChooser.getSelectedFile());
			if(tryRead==0){
				mainWindow();
			}else{
				setVisible(true);
				NotificationWindow cantRead = new NotificationWindow("Unable to read season file");
			}
		}
	}
	public void saveSeason(){
		setVisible(false);
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int chooserReturnValue = fileChooser.showSaveDialog(new JPanel());
		if(chooserReturnValue==JFileChooser.APPROVE_OPTION){
			theSeason.saveSeason(fileChooser.getSelectedFile());
		}
		setVisible(true);
	}
	public void dataAnalysisMenu(){
		setVisible(false);
		getContentPane().removeAll();
		setLayout(new GridLayout(4,1));
		analysisMenuButton[0]=new JButton("Tournament Statistics");
		analysisMenuButton[1]=new JButton("Member Statistics");
		analysisMenuButton[2]=new JButton("Part Statistics");
		analysisMenuButton[3]=new JButton("Main Menu");
		for(int i=0;i<analysisMenuButton.length;i++){
			analysisMenuButton[i].addActionListener(new ButtonListener());
			add(analysisMenuButton[i]);
		}
		pack();
		setVisible(true);
	}
	public void tournamentAnalysis(int tournamentIndex){
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		for(int i=0;i<tournamentAnalysisPanels.length;i++){
			tournamentAnalysisPanels[i]=new JPanel();
		}
		String[] tournamentNames = new String[theSeason.getNumberOfTournaments()];
		for(int i=0;i<tournamentNames.length;i++){
			tournamentNames[i]=theSeason.getTournament(i).getTournamentName();
		}
		tournamentAnalysisSelector=new JComboBox(tournamentNames);
		tournamentAnalysisSelector.setSelectedIndex(tournamentIndex);
		tournamentAnalysisSelector.addItemListener(new ComboListener());
		tournamentAnalysisPanels[0].add(tournamentAnalysisSelector);
		tournamentAnalysisPanels[1].setLayout(new GridLayout(2,2));
		for(int i=0;i<roundAnalysisPanel.length;i++){
			roundAnalysisPanel[i]=new ColorCodedRoundAnalysis(i+1,theSeason.getRoster(),theSeason.getWitnesses(),theSeason.getTournament(tournamentIndex).getRound(i).getAll());
			tournamentAnalysisPanels[1].add(roundAnalysisPanel[i]);
		}
		tournamentAnalysisButton[0]=new JButton("Statistics");
		tournamentAnalysisButton[1]=new JButton("Analysis Menu");
		for(int i=0;i<tournamentAnalysisButton.length;i++){
			tournamentAnalysisButton[i].addActionListener(new ButtonListener());
			tournamentAnalysisPanels[2].add(tournamentAnalysisButton[i]);
		}
		JScrollPane scroller = new JScrollPane(tournamentAnalysisPanels[1]);
		add(tournamentAnalysisPanels[0],BorderLayout.NORTH);
		add(scroller,BorderLayout.CENTER);
		add(tournamentAnalysisPanels[2],BorderLayout.SOUTH);
		pack();
	}
	public void tournamentStatistics(){
		statsWindow=new TournamentStats(theSeason.getTournament(tournamentAnalysisSelector.getSelectedIndex()),theSeason.getRoster());
	}
	public void memberStatistics(){
		setVisible(false);
		getContentPane().removeAll();
		setLayout(new BorderLayout());
		memberStatsPanel = new JPanel[2];
		for(int i=0;i<memberStatsPanel.length;i++){
			memberStatsPanel[i]=new JPanel();
		}
		prePostChecks=new JCheckBox[2];
		prePostChecks[0]=new JCheckBox("Pre-Stack",true);
		prePostChecks[1]=new JCheckBox("Post-Stack",true);
		for(int i=0;i<prePostChecks.length;i++){
			prePostChecks[i].addActionListener(new CheckBoxListener());
			memberStatsPanel[0].add(prePostChecks[i]);
		}
		memberStatsButton=new JButton[1];
		memberStatsButton[0]=new JButton("Analysis Menu");
		for(int i=0;i<memberStatsButton.length;i++){
			memberStatsButton[i].addActionListener(new ButtonListener());
			memberStatsPanel[1].add(memberStatsButton[i]);
		}
		memberStats=new MemberStatisticsPanel(theSeason,true,true);
		centerPane = new JScrollPane(memberStats);
		add(memberStatsPanel[0],BorderLayout.NORTH);
		add(centerPane,BorderLayout.CENTER);
		add(memberStatsPanel[1],BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	public void updateMemberStats(){
		getContentPane().remove(centerPane);
		centerPane.removeAll();
		memberStats=new MemberStatisticsPanel(theSeason,prePostChecks[0].isSelected(),prePostChecks[1].isSelected());
		centerPane=new JScrollPane(memberStats);
		add(centerPane,BorderLayout.CENTER);
		pack();
	}
	public void partStatistics(){
		setVisible(false);
		getContentPane().removeAll();
		partAnalysisPanel = new JPanel();
		partAnalysisButton = new JButton("Analysis Menu");
		partAnalysisButton.addActionListener(new ButtonListener());
		partAnalysisPanel.add(partAnalysisButton);
		PartAnalysisPane partAnalysis=new PartAnalysisPane(theSeason);
		setLayout(new BorderLayout());
		add(partAnalysis,BorderLayout.CENTER);
		add(partAnalysisPanel,BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==startupButtons[0]){
				newSeason();
			}else if(e.getSource()==startupButtons[1]){
				loadSeason();
			}else if(e.getSource()==yearContinue){
				initialRosterInput();
			}else if(e.getSource()==initialRosterButton[0]){
				addInitialRoster();
			}else if(e.getSource()==initialRosterButton[1]){
				addInitialRoster();
				initialWitnessInput();
			}else if(e.getSource()==initialWitnessButton[0]){
				addInitialWitness();
			}else if(e.getSource()==initialWitnessButton[1]){
				addInitialWitness();
				mainWindow();
			}else if(e.getSource()==mainButtons[0]){
				dataAnalysisMenu();
			}
			else if(e.getSource()==mainButtons[1]){
				scoreInput(0);
			}else if(e.getSource()==mainButtons[2]){
				rosterDisplay();
			}else if(e.getSource()==mainButtons[3]){
				witnessDisplay();
			}else if(e.getSource()==mainButtons[4]){
				saveSeason();
			}else if(e.getSource()==rosterDisplayButton[0]){
				addToRoster();
			}else if(e.getSource()==rosterDisplayButton[1]){
				mainWindow();
			}else if(e.getSource()==witnessDisplayButton[0]){
				addWitness();
			}else if(e.getSource()==witnessDisplayButton[1]){
				mainWindow();
			}else if(e.getSource()==scoreEntryButton[0]){
				if(scoreEntryRoundPanel[0].correctValues()!=0 || scoreEntryRoundPanel[1].correctValues()!=0 || scoreEntryRoundPanel[2].correctValues()!=0 || scoreEntryRoundPanel[3].correctValues()!=0){
					NotificationWindow scoreInput=new NotificationWindow("Please ensure all scores, witnesses, and students are correctly selected");
				}else{
					saveTournamentData(scoreEntrySelector.getSelectedIndex());
					newTournamentInput();
				}
			}else if(e.getSource()==scoreEntryButton[1]){
				if(scoreEntryRoundPanel[0].correctValues()!=0 || scoreEntryRoundPanel[1].correctValues()!=0 || scoreEntryRoundPanel[2].correctValues()!=0 || scoreEntryRoundPanel[3].correctValues()!=0){
					NotificationWindow scoreInput=new NotificationWindow("Please ensure all scores, witnesses, and students are correctly selected");
				}else{
				saveTournamentData(scoreEntrySelector.getSelectedIndex());
				mainWindow();
				}
			}else if(e.getSource()==newTournamentButton[0]){
				mainWindow();
			}else if(e.getSource()==newTournamentButton[1]){
				newTournamentSetup();
			}else if(e.getSource()==analysisMenuButton[0]){
				tournamentAnalysis(0);
			}else if(e.getSource()==analysisMenuButton[1]){
				memberStatistics();
			}else if(e.getSource()==analysisMenuButton[2]){
				partStatistics();
			}else if(e.getSource()==analysisMenuButton[3]){
				mainWindow();
			}else if(e.getSource()==tournamentAnalysisButton[0]){
				tournamentStatistics();
			}else if(e.getSource()==tournamentAnalysisButton[1]){
				dataAnalysisMenu();
			}else if(e.getSource()==memberStatsButton[0]){
				dataAnalysisMenu();
			}else if(e.getSource()==partAnalysisButton){
				dataAnalysisMenu();
			}
		}
	}
	private class ComboListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED && e.getSource()==scoreEntrySelector){
				if(scoreEntryRoundPanel[0].correctValues()!=0 || scoreEntryRoundPanel[1].correctValues()!=0 || scoreEntryRoundPanel[2].correctValues()!=0 || scoreEntryRoundPanel[3].correctValues()!=0){
					NotificationWindow scoreInput=new NotificationWindow("Please ensure all scores, witnesses, and students are correctly selected");
				}else{
				saveTournamentData(scoreEntryDisplayedTournamentIndex);
				scoreInputFromDropdown();
				}
			}else if(e.getStateChange()==ItemEvent.SELECTED && e.getSource()==tournamentAnalysisSelector){
				tournamentAnalysis(tournamentAnalysisSelector.getSelectedIndex());
				if(statsWindow!=null){
					if(statsWindow.isVisible()){
						statsWindow.dispose();
						statsWindow=new TournamentStats(theSeason.getTournament(tournamentAnalysisSelector.getSelectedIndex()),theSeason.getRoster());
					}
				}
			}
		}
	}
	private class CheckBoxListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateMemberStats();	
		}
		
	}
}
