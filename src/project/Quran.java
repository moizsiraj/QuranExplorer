/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author moizs
 */
public class Quran {

    private final String[][] arabic;
    private final String[][] english;
    private final String[][] urdu;
    private final int[] noOfAyah;
    final HashTable topics;

    public Quran() throws FileNotFoundException, UnsupportedEncodingException, IOException {

        arabic = new String[114][];
        english = new String[114][];
        urdu = new String[114][];
        noOfAyah = new int[]{7, 286, 200, 176, 120, 165, 206, 75, 129, 109, 123, 111, 43, 52, 99, 128, 111, 110, 98, 135, 112, 78, 118, 64, 77, 227, 93, 88, 69, 60, 34, 30, 73, 54, 45, 83, 182, 88, 75, 85, 54, 53, 89, 59, 37, 35, 38, 29, 18, 45, 60, 49, 62, 55, 78, 96, 29, 22, 24, 13, 14, 11, 11, 18, 12, 12, 30, 52, 52, 44, 28, 28, 20, 56, 40, 31, 50, 40, 46, 42, 29, 19, 36, 25, 22, 17, 19, 26, 30, 20, 15, 21, 11, 8, 8, 19, 5, 8, 8, 11, 11, 8, 3, 9, 5, 4, 7, 3, 6, 3, 5, 4, 5, 6};
        topics = new HashTable(300);

        for (int i = 0; i < 114; i++) {
            arabic[i] = new String[noOfAyah[i]];
            english[i] = new String[noOfAyah[i]];
            urdu[i] = new String[noOfAyah[i]];
        }
        for (int i = 0; i < 114; i++) {
            FileInputStream fstream = new FileInputStream("D://Project//Arabic//S1 (" + (i + 1) + ").txt");
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "unicode"));
            String strLine;
            FileInputStream fustream = new FileInputStream("D://Project//Urdu//U (" + (i + 1) + ").txt");
            DataInputStream inu = new DataInputStream(fustream);
            BufferedReader bru = new BufferedReader(new InputStreamReader(inu, "unicode"));
            String strLineu;
            FileInputStream festream = new FileInputStream("D://Project//English//E (" + (i + 1) + ").txt");
            DataInputStream ine = new DataInputStream(festream);
            BufferedReader bre = new BufferedReader(new InputStreamReader(ine));
            String strLinee;
            int j = 0;

            while ((strLine = br.readLine()) != null) {
                strLineu = bru.readLine();
                strLinee = bre.readLine();
                if (strLinee != null) {
                    strLinee = strLinee.replace("\t", "").replace(";", ",");
                }
                arabic[i][j] = strLine;
                urdu[i][j] = strLineu;
                english[i][j] = strLinee;
                j++;
            }
        }
        loadTopics();
        ;
    }

    private void loadTopics() throws IOException {
        FileInputStream fstream = new FileInputStream("D://Project//topics.txt");
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine;
        String print = "";
        String TL = "";
        String AL = "";
        String SL = "";
        topic t = null;

        while ((strLine = br.readLine()) != null) {
            if (strLine.equalsIgnoreCase("next") || strLine.equalsIgnoreCase("end")) {
                //System.out.println(" T:" + TL + " A: " + AL + " SL: " + SL);
                TL = "";
                AL = "";
                SL = "";
                t = null;
            } else if (strLine.equalsIgnoreCase("create")) {
                String[] tempT = TL.split(",");
                String[] tempA = AL.split(",");
                String[] tempS = SL.split(",");
                int noOfSubs = Integer.parseInt(tempS[1].replace(" ", ""));
                t = new topic(tempT[1].replaceFirst(" ", ""), (tempA.length - 1), noOfSubs);
                System.out.println(t.name + "xx" + t.subTopic.length);

                String ayahString = "";
                for (int i = 1; i < tempA.length; i++) {
                    ayahString = ayahString + tempA[i] + ":";
                }
                String[] address = ayahString.split(":");
                for (int i = 0, j = 0; j < address.length - 1; i++, j = j + 2) {
                    t.ayahs[i][0] = address[j].replace(" ", "");
                    t.ayahs[i][1] = address[j + 1].replace(" ", "");
                }
                topics.insert(t);
            } else {
                String[] temp = strLine.split(",");
                switch (temp[0]) {
                    case "T":
                        TL = strLine;
                        break;
                    case "A":
                        AL = strLine;
                        break;
                    case "S":
                        SL = strLine;
                        break;
                    case "ST":
                        strLine = br.readLine();
                        String[] ayahArray = strLine.split(",");
                        String ayahString = "";
                        for (int i = 1; i < ayahArray.length; i++) {
                            ayahString = ayahString + ayahArray[i] + ":";
                        }
                        String[] address = ayahString.split(":");
                        t.addSubTopic(temp[1].replaceFirst(" ", ""), ayahArray.length - 1);
                        int index = t.findSubTopic(temp[1].replaceFirst(" ", ""));
                        for (int i = 0, j = 0; j < address.length - 1; i++, j = j + 2) {
                            t.subTopic[index].ayahs[i][0] = address[j].replace(" ", "");
                            t.subTopic[index].ayahs[i][1] = address[j + 1].replace(" ", "");
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        in.close();
    }

    public String searchTopic(String string) {
        topic t = topics.search(string);
        String str = "";
        if (t == null) {
            return null;
        } else {
            if (t.ayahs != null) {
                str = str + "\t\t\t" + t.name + "\t\t\t" + "\n";
                for (int i = 0; i < t.ayahs.length; i++) {
                    str = str + "********************" + "\n";
                    str = str + getAyah(Integer.parseInt(t.ayahs[i][0]), Integer.parseInt(t.ayahs[i][1])) + "\n";
                }
            }
            return str;
        }
    }
    
    public String searchTopicEnglish(String string) {
        topic t = topics.search(string);
        String str = "";
        if (t == null) {
            return null;
        } else {
            if (t.ayahs != null) {
                str = str + "\t\t\t" + t.name + "\t\t\t" + "\n";
                for (int i = 0; i < t.ayahs.length; i++) {
                    str = str + "********************" + "\n";
                    str = str + getAyahEnglish(Integer.parseInt(t.ayahs[i][0]), Integer.parseInt(t.ayahs[i][1])) + "\n";
                }
            }
            return str;
        }
    }
    
    public String searchTopicUrdu(String string) {
        topic t = topics.search(string);
        String str = "";
        if (t == null) {
            return null;
        } else {
            if (t.ayahs != null) {
                str = str + "\t\t\t" + t.name + "\t\t\t" + "\n";
                for (int i = 0; i < t.ayahs.length; i++) {
                    str = str + "********************" + "\n";
                    str = str + getAyahUrdu(Integer.parseInt(t.ayahs[i][0]), Integer.parseInt(t.ayahs[i][1])) + "\n";
                }
            }
            return str;
        }
    }

    public topic getTopic(String string) {
        topic t = topics.search(string);
        if (t == null) {
            return null;
        } else {
            return t;
        }
    }

    public String searchSubTopic(topic t, String v) {
        String print = "";
        topic subTopic = t.subTopicAyah(v);
        if (subTopic != null) {
            print = print + "\t\t\t" + subTopic.name + "\t\t\t" + "\n";
            for (int i = 0; i < subTopic.ayahs.length; i++) {
                print = print + "********************" + "\n";
                print = print + getAyah(Integer.parseInt(subTopic.ayahs[i][0]), Integer.parseInt(subTopic.ayahs[i][1])) + "\n";
            }
            return print;
        } else {
            System.out.println("topic not found");
            return null;
        }
    }

    public String searchSubTopicEnglish(topic t, String v) {
        String print = "";
        topic subTopic = t.subTopicAyah(v);
        if (subTopic != null) {
            print = print + "\t\t\t" + subTopic.name + "\t\t\t" + "\n";
            for (int i = 0; i < subTopic.ayahs.length; i++) {
                print = print + "********************" + "\n";
                print = print + getAyahEnglish(Integer.parseInt(subTopic.ayahs[i][0]), Integer.parseInt(subTopic.ayahs[i][1])) + "\n";
            }
            return print;
        } else {
            System.out.println("topic not found");
            return null;
        }
    }

    public String searchSubTopicUrdu(topic t, String v) {
        String print = "";
        topic subTopic = t.subTopicAyah(v);
        if (subTopic != null) {
            print = print + "\t\t\t" + subTopic.name + "\t\t\t" + "\n";
            for (int i = 0; i < subTopic.ayahs.length; i++) {
                print = print + "********************" + "\n";
                print = print + getAyahUrdu(Integer.parseInt(subTopic.ayahs[i][0]), Integer.parseInt(subTopic.ayahs[i][1])) + "\n";
            }
            return print;
        } else {
            System.out.println("topic not found");
            return null;
        }
    }

    public String getAyah(int surah, int ayah) {
        return arabic[surah - 1][ayah - 1];
    }

    public String getAyahUrdu(int surah, int ayah) {
        return urdu[surah - 1][ayah - 1];
    }

    public String getAyahEnglish(int surah, int ayah) {
        return english[surah - 1][ayah - 1];
    }

    public int noOfAyah(int surah) {
        return arabic[surah].length;
    }

    public String getSurahEng(int surah) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arabic[surah - 1].length; i++) {
            s.append(english[surah - 1][i]);
            s.append("\n");
        }
        return s.toString();
    }

    public String getSurahUrdu(int surah) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < arabic[surah - 1].length; i++) {
            s.append(urdu[surah - 1][i]);
            s.append("\n");
        }
        return s.toString();
    }

    public String getSurah(int surah) {
        String print = "";
        for (int i = 0; i < arabic[surah - 1].length; i++) {
            print = print + arabic[surah - 1][i] + "\n";
        }
        return print;
    }

}
