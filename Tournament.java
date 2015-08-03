package Temp;
public class Tournament {
	private String tournamentName;
	private boolean postStack=false;
	private Round[] tournamentRound = new Round[4];
	public Tournament(String n){
		tournamentName=n;
		for(int i=0;i<tournamentRound.length;i++){
			tournamentRound[i]=new Round();
		}
	}
	public String getTournamentName(){
		return tournamentName;
	}
	public boolean isPostStack(){
		return postStack;
	}
	public void setPostStack(boolean b){
		postStack = b;
	}
	public Round getRound(int index){
		return tournamentRound[index];
	}
}
