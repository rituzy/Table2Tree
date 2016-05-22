package com.izutov.buildtree;

/**
 * Clas-node of tree
 * @author roman
 */
 ////////////////////////////////////////////////////////////////
class Node{
    public char cData;             // data item (key)    
    public Node leftChild;         // this node's left child
    public Node rightChild;        // this node's right child

    public Node(char ch){
        cData = ch;
        leftChild = null;
        rightChild = null;
    }
    // display ourself
    public void displayNode(){
       System.out.print("{");
       System.out.print(cData);
       System.out.print("} ");
    }
}  // end class Node