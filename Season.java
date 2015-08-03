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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Season {
	String year;
	ArrayList<String> roster = new ArrayList<String>();
	ArrayList<String> witnessList = new ArrayList<String>();
	ArrayList<Tournament> tournaments = new ArrayList<Tournament>();
	public void setYear(String y){
		year=y;
	}
	public String getYear(){
		return year;
	}
	public void createMember(String name){
		roster.add(name);
	}
	public void createWitness(String name){
		witnessList.add(name);
	}
	public void createTournament(String name){
		tournaments.add(new Tournament(name));
	}
	public ArrayList<String> getRoster(){
		return roster;
	}
	public ArrayList<String> getWitnesses(){
		return witnessList;
	}
	public Tournament getTournament(int index){
		return tournaments.get(index);
	}
	public int getNumberOfTournaments(){
		return tournaments.size();
	}
	public void saveSeason(File output){
		String fileName = output.getName();
		PrintWriter seasonFile=null;
		try {
			if(fileName.endsWith(".mts")){
				seasonFile=new PrintWriter(output);
			}else{
				seasonFile=new PrintWriter(output+".mts");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		seasonFile.println(year);
		seasonFile.println(roster.size());
		for(int i=0;i<roster.size();i++){
			seasonFile.println(roster.get(i));
		}
		seasonFile.println(witnessList.size());
		for(int i=0;i<witnessList.size();i++){
			seasonFile.println(witnessList.get(i));
		}
		seasonFile.println(tournaments.size());
		for(int i=0;i<tournaments.size();i++){
			seasonFile.println(tournaments.get(i).getTournamentName());
			seasonFile.println(tournaments.get(i).isPostStack());
			for(int j=0;j<4;j++){
				for(int k=0;k<85;k++){
					seasonFile.println(tournaments.get(i).getRound(j).getAll()[k]);
				}
			}
		}
		seasonFile.close();
	}
	public int loadSeason(File file){
		try{
			Scanner seasonFile = new Scanner(file);
			year=seasonFile.nextLine();
			int rosterSize=seasonFile.nextInt();
			seasonFile.nextLine();
			for(int i=0;i<rosterSize;i++){
				roster.add(seasonFile.nextLine());
			}
			int witNumber=seasonFile.nextInt();
			seasonFile.nextLine();
			for(int i=0;i<witNumber;i++){
				witnessList.add(seasonFile.nextLine());
			}
			int tournNumber=seasonFile.nextInt();
			for(int i=0;i<tournNumber;i++){
				seasonFile.nextLine();
				tournaments.add(new Tournament(seasonFile.nextLine()));
				tournaments.get(i).setPostStack(seasonFile.nextBoolean());
				for(int j=0;j<4;j++){
					int[] roundInfo=new int[85];
					for(int k=0;k<85;k++){
						int inte=seasonFile.nextInt();
						roundInfo[k]=inte;
						
					}
					tournaments.get(i).getRound(j).setAll(roundInfo);
				}
			}
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 1;
		}
	}
}
