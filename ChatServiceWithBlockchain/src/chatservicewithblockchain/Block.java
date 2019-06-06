/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatservicewithblockchain;

import java.util.Date;

/**
 *
 * @author blade
 */
public class Block {

    private String vote;
    private String hash;
    private String previousHash;
    private long timeStamp;

    public Block() {
        vote = "0";
        timeStamp = new Date().getTime();
        previousHash = "";
        hash = hashValue(previousHash + Long.toString(timeStamp) + vote);

    }

    public Block(String data, Block previous) {
        this.vote = data;
        timeStamp = new Date().getTime();
        previousHash = previous.hash;
        hash = hashValue(previousHash + Long.toString(timeStamp) + data);
    }

    public Block(String data, Block previous, String hash) {
        this.vote = data;
        timeStamp = new Date().getTime();
        previousHash = previous.hash;
        this.hash = hash;
    }

    public Block(String vote, long timeStamp, String hash) {
        this.vote = vote;
        this.timeStamp = timeStamp;
        this.hash = hash;
        this.previousHash = "";
    }

    public void setVote(String balsas) {
        this.vote = balsas;
    }

    public String getVote() {
        return vote;
    }

    public void setHash(String newHash) {
        this.hash = newHash;
    }

    public String getHash() {
        return hash;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setPreviousHash(String newHash) {
        this.previousHash = newHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public static String hashValue(String data) {
        return Hash.hashing(data);
    }

    @Override
    public String toString() {
        return "vote for:" + vote + ", this block hash: " + hash + ", time stamp: " + timeStamp + ", previous block hash:" + previousHash;
    }
}
