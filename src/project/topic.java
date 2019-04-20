/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.Arrays;

/**
 *
 * @author moizs
 */
public class topic {

    String name;
    String[][] ayahs;
    topic[] subTopic;

    public topic(String name, int noOfAyah, int noOfSub) {
        this.name = name;
        if (noOfAyah != 0) {
            ayahs = new String[noOfAyah][2];
        }
        int size = (noOfSub + (noOfSub / 3));
        if (!checkPrime(size)) {
            size = size + 1;
        }
        subTopic = new topic[size];
    }

    public topic(String name, int noOfAyah) {
        this.name = name;
        ayahs = new String[noOfAyah][2];
    }

    public boolean ayatExist() {
        return ayahs != null;
    }
    
    public boolean subTopicExist() {
        return subTopic != null;
    }

    public void addSubTopic(String name, int noOfAyah) {
        if (this.subTopic == null) {
            System.out.println("No subtopics");
        } else {
            int index = Math.abs(hash(name));
            if (this.subTopic[index] == null) {
                subTopic[index] = new topic(name, noOfAyah);
            } else {

                int i = 1;
                while (subTopic[index] != null) {
                    index = Math.abs(LRehash(name, i));
                    i++;
                }
                subTopic[index] = new topic(name, noOfAyah);
            }
        }
    }

    public int findSubTopic(String name) {
        int index = Math.abs(hash(name));
        if (this.subTopic[index] == null) {
            return -1;
        } else if (this.subTopic[index].name.equalsIgnoreCase(name)) {
            return index;
        } else {
            int i = 1;
            while (subTopic[index] != null && !subTopic[index].name.equalsIgnoreCase(name)) {
                index = Math.abs(LRehash(name, i));
                i++;
            }
            return index;
        }
    }

    public topic subTopicAyah(String topic) {
        int index = findSubTopic(topic);
        String print = "";
        if (index != -1) {
            topic subTopic = this.subTopic[index];
            return subTopic;
        } else {
            return null;
        }
    }

    public String toString() {
        String str = "";
        if (ayahs != null && subTopic != null) {
            str = "Topic: " + name + " Ayahs: " + ayahs.length + " Subs: " + Arrays.toString(subTopic);
        } else if (subTopic == null && ayahs != null) {
            str = "Topic: " + name + " Ayahs: " + ayahs.length;
        } else {
            str = "Topic: " + name + " Subs: " + Arrays.toString(subTopic);
        }
        return str;
    }

    //TOOLS//
    private int LRehash(String v, int x) {
        int index = hash(v);
        index = (index + x) % subTopic.length;
        return index;
    }

    private boolean checkPrime(int size) {
        boolean prime = true;
        int i = 2;
        while (prime == true && i < size) {
            if (size % i == 0) {
                prime = false;
            }
            i++;
        }
        return prime;
    }

    private long strToInt(String v) {
        v = v.replace(" ", "");
        long sum = 0;
        for (int i = 0; i < v.length(); i++) {
            sum = sum + (int) (((int) v.charAt(i)) * Math.pow(3, i));
        }
        return sum;
    }

    private int hash(String v) {
        int index = (int) strToInt(v) % subTopic.length;
        return index;
    }

}
