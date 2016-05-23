package com.izutov.buildtree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Tree class contains tree structure of char data 
 * with interface (printing, filling from table)
 * @author roman.izutov@gmail.com
 */  
////////////////////////////////////////////////////////////////
public class Tree{
    private static final int NUM_SPACES = 32;
    
    private Node root;             // first node of tree
    // referenct to main class as it holds souce data
    ArrayList<ComIzutovBuildTree.Pair> pairs = new ArrayList<ComIzutovBuildTree.Pair>();    
    
    // constructors
    public Tree(){ root = null; }            // no nodes in tree yet
    public Tree(char rt, ComIzutovBuildTree bldClass){
        root = new Node(rt); 
        pairs = bldClass.getPairs();
    }
    /**
     * filling the tree
     */
    void fillTree(){
        if (root == null || root.cData == ' '){
            System.out.println("Please set the root for the tree!");
            return;
        }
        fillRecursive(root);            
    }
    /**
     * printing the tree
     */
    public void printTree(){
        int numSpaces = NUM_SPACES;
        boolean isRowEmpty = false;
        Stack<Node> globalStack = new Stack<Node>();
        
        globalStack.push(root);
        
        System.out.println(
                "......................................................");
        while (isRowEmpty == false){
            Stack<Node> localStack = new Stack<Node>();
            isRowEmpty = true;
            
            for (int i=0; i< numSpaces; i++){
                System.out.print(' ');
            }
            
            while(!globalStack.isEmpty()){
                
                Node temp = globalStack.pop();
                
                if (temp != null){
                    System.out.print(temp.cData);
                    Node left = temp.leftChild;
                    Node right = temp.rightChild;
                    localStack.push(left);
                    localStack.push(right);

                    if (left != null || right != null){
                        isRowEmpty = false;
                    }                
                }else{
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                
                for (int i=0;i<numSpaces*2 - 2; i++){
                    System.out.print(' ');
                }
            }
            
            System.out.println();
            numSpaces /=2;
            
            while(!localStack.isEmpty()){
                globalStack.push(localStack.pop());
            }
        }
        System.out.println(
                "......................................................");
    }
    
    private void fillRecursive(Node parent){
        boolean isLeftChildFilled = false;
        for (ComIzutovBuildTree.Pair p : pairs){
            if (p.isChildOfParent(parent.cData)){
                if (!isLeftChildFilled){
                    Node left = new Node(p.getChild());
                    parent.leftChild = left;
                    isLeftChildFilled = true;
                    fillRecursive(left);
                }else{
                    Node right = new Node(p.getChild());
                    parent.rightChild = right;
                    fillRecursive(right);
                }
            }
        }
    }
}
