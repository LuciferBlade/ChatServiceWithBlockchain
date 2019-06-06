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
public class Blockchain {

    public ArrayList<Block> blockchain;

    public Blockchain() {
        blockchain = new ArrayList<>();
        blockchain.add(new Block());
    }

    public Boolean add(String data) {
        blockchain.add(new Block(data, blockchain.get(blockchain.size() - 1)));
        if (validate() == false) {
            blockchain.remove(blockchain.size() - 1);
            return false;
        }
        return true;
    }

    public void add(Block block) {
        blockchain.add(block);
    }

    public Boolean validate() {
        for (int i = 1; i < blockchain.size(); i++) {
            if (!blockchain.get(i - 1).getHash().equals(blockchain.get(i).getPreviousHash())) {
                return false;
            }
        }
        for (int i = 1; i < blockchain.size(); i++) {
            if (!blockchain.get(i).getHash().equals(hashValue(blockchain.get(i).getPreviousHash()
                    + Long.toString(blockchain.get(i).getTimeStamp()) + blockchain.get(i).getVote()))) {
                return false;
            }
        }
        return true;
    }

    public int count() {
        return blockchain.size();
    }

    public void Replace(Blockchain bchain) {
        this.blockchain = bchain.blockchain;
    }

    public Boolean reHash() {
        for (int i = 0; i < blockchain.size(); i++) {
            if (i == 0) {
                blockchain.get(i).setHash(hashValue(blockchain.get(i).getVote()));
            } else {
                blockchain.get(i).setPreviousHash(blockchain.get(i - 1).getHash());
                blockchain.get(i).setHash(hashValue(blockchain.get(i).getPreviousHash() + blockchain.get(i).getTimeStamp() + blockchain.get(i).getVote()));
            }
        }
        return true;
    }

    public Vote votingCounting() {
        Vote balsas = new Vote();
        if (!validate()) {
            return balsas;
        }
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Integer> temp2 = new ArrayList<>();
        Boolean count = false;
        for (int i = 0; i < count(); i++) {
            for (int j = 0; j < balsas.person.size(); j++) {
                if (blockchain.get(i).getVote().equals(balsas.person.get(j))) {
                    balsas.votes.set(j, balsas.votes.get(j) + 1);
                    count = true;
                }
            }
            if (count == false) {
                balsas.person.add(blockchain.get(i).getVote());
                balsas.votes.add(1);
            } else {
                count = false;
            }
        }
        for (int i = 0; i < balsas.person.size(); i++) {
            if (balsas.person.get(i).length() < 2) {
                balsas.person.remove(i);
                balsas.votes.remove(i);
            }
        }

        return balsas;
    }

    public static String hashValue(String data) {
        return Hash.hashing(data);
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < blockchain.size(); i++) {
            temp += blockchain.get(i).toString() + "\n";
        }
        return temp;
    }
}
