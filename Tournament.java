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
