/*
 * Building tree from flat table
 */
package com.izutov.buildtree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
  * Main class, where:
  * - source table is loaded
  * - the root is calculated
  * - the tree is build and printed
  * @author roman.izutov@gmail.com
 */
public class ComIzutovBuildTree {
    private static final char NONE = ' ';
    ArrayList<Pair> pairs = new ArrayList<Pair>();    

    void initPairs(String path){        
        /*
        pairs.add(new Pair('a','b'));
        pairs.add(new Pair('b','c'));
        pairs.add(new Pair('a','d'));
        pairs.add(new Pair('c','g'));
        */
        readFile(path);
    }
    
    public ArrayList<Pair>  getPairs(){
        return new ArrayList<>(pairs);
    }
    /**
     * finding a root from the pairs of source table
     * @return char
     */
    char figureRoot(){
        char root=NONE;
        HashSet<Character> parents = new HashSet<>();
        HashSet<Character> childs = new HashSet<>();
        
        for (Pair p : pairs){
            parents.add(p.getParent());
            childs.add(p.getChild());
        }
        
        for (Character cp : parents){
            if (!childs.contains(cp)){
                root = cp;
                break;
            }
        }
        return root;               
    }
    /**
     * internal class holding one pair
     */
    final public class Pair{
        char parent;
        char child;
        Pair (char p, char c){
            parent = p;
            child = c;
        }
        
        char getParent(){
            return parent;
        }
        
        char getChild(){
            return child;
        }
        
        public boolean isParentOfChild(char chld){
           return child==chld; 
        }
        
        public boolean isChildOfParent(char prnt){
           return parent==prnt; 
        }
        
    }
    
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ComIzutovBuildTree bt = new ComIzutovBuildTree();
        // fill pairs table
        String path = "C:\\Temp\\testingTree.txt";
        bt.initPairs(path);
        // print the table
        bt.printtable();
        // finding root of the table
        char root = bt.figureRoot();
        System.out.println("The root of the tree is: " + root); 
        // creating tree
        Tree t = new Tree(root, bt);
        System.out.println("Tree before filling:");
        //printing tree
        t.printTree();
        //filling tree
        t.fillTree();
        System.out.println("Tree after filling:");
        t.printTree();        
    }
    
    private void printtable(){
        for (Pair p: pairs){
            System.out.println("Parent : " + p.getParent() + " - Child : "
                    + p.getChild());
        }
    }
    
    private void readFile(String path){
    	BufferedReader br = null;

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(path));
            if (br == null) {
                System.out.println("File does not exists or corrupted!");
            }
            while ((sCurrentLine = br.readLine()) != null) {
                    pairs.add(new Pair(getFirstChar(sCurrentLine), 
                            getSecondChar(sCurrentLine)));
            }
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private char getFirstChar(String str){
        String[] resArr = str.split("/", 0);
        if (resArr == null || resArr.length < 2) {
            System.out.println("Rows in the file should "
                    + "contain two chars divieded by / : a/b");
            return ' ';
        }
        return resArr[0].charAt(0);
    }
    
    private char getSecondChar(String str){
        String[] resArr = str.split("/", 0);
        if (resArr == null || resArr.length < 2) {
            System.out.println("Rows in the file should "
                    + "contain two chars divieded by / : a/b");
            return ' ';
        }
        return resArr[1].charAt(0);
    }
    
}
