/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author 14818
 */
public class HashTable {

    topic[] Table;

    HashTable(int s) {
        int size = s + (s / 3);
        if (!checkPrime(size)) {
            size = size + 1;
        }
        Table = new topic[size];
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
        int sum = 0;
        for (int i = 0; i < v.length(); i++) {
            sum = sum + (int) (((int) v.charAt(i)) * Math.pow(3, i));
        }
        return sum;
    }

    private int Hash(String v) {
        v = v.toLowerCase();
        int index = (int) strToInt(v) % Table.length;
        return index;
    }

    // linear probing
    private int LRehash(String v, int x) {
        int index = Math.abs(Hash(v));
        index = (index + x) % Table.length;
        return index;
    }

    public void insert(topic t) {
        int index = Math.abs(Hash(t.name));
        if (Table[index] == null) {
            Table[index] = t;
        } else {
            int col = 0;
            int i = 1;
            while (Table[index] != null) {
                col++;
                index = Math.abs(LRehash(t.name, i));
                i++;
            }
            Table[index] = t;
            //System.out.println(v + ", " + col);
        }
    }

    public void displayTable() {
        String str = "";
        for (int i = 0; i < Table.length; i++) {
            if (Table[i] != null) {
                str = str + Table[i] + "\n";
            }
        }
        System.out.println(str);
    }

    public topic search(String v) {
        int col = 0;
        int index = Math.abs(Hash(v));
        int indexTemp = index;
        if (Table[index] != null && Table[index].name.equalsIgnoreCase(v)) {
            //System.out.println("cols " + col);
            return Table[index];
        } else {
            int i = 1;
            do {
                col++;
                index = Math.abs(LRehash(v, i));
                //System.out.println(index);
                if (Table[index] != null && Table[index].name.equalsIgnoreCase(v)) {
                    //System.out.println("cols " + col);
                    return Table[index];
                }
                i++;
            } while (index != indexTemp && Table[index] != null);
        }
        //System.out.println("cols " + col);
        return Table[index];
    }

}
