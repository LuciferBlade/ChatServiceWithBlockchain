/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatservicewithblockchain;

import java.util.ArrayList;

/**
 *
 * @author blade
 */
public class Vote {

    public ArrayList<Integer> votes;
    public ArrayList<String> person;

    public Vote() {
        votes = new ArrayList<>();
        person = new ArrayList<>();
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < votes.size(); i++) {
            temp = temp + votes.get(i) + " votes for " + person.get(i) + "\n";
        }
        return temp;
    }
    
    public void sortDecreasing(){
        /*ArrayList<Integer> tempStore = new ArrayList<>(votes);
        Collections.sort(tempStore, Collections.reverseOrder());
        for (int n = 0; n < votes.size(); n++){
            tempStore.add(n, votes.indexOf(tempStore.remove(n)));
        }
        Collections.sort(votes, Collections.reverseOrder());
        ArrayList<String> temp2 = new ArrayList<>(person);
        for (int i = 0; i < person.size(); i++){
            person.set(i, temp2.get(tempStore.get(i)));
            
        }*/
        int temp = 0;
        for (int i = 0; i < votes.size(); i++){
            for (int j = i; j < votes.size(); j++){
                if (votes.get(j) >votes.get(temp)){
                    temp = i;
                }
            }
            int temp2 = votes.get(i);
            String temp3 = person.get(i);
            votes.set(i, votes.get(temp));
            person.set(i, person.get(temp));
            votes.set(temp, temp2);
            person.set(temp, temp3);
        }
        
    }
}
