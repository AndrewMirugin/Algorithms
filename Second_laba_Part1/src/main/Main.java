package main;

import searchTree.SearchTree;

public class Main {
    public static void main(String[] args) {
        SearchTree tree = new SearchTree();
        tree.insertion(16);
        tree.insertion(6);
        tree.insertion(7);
        tree.insertion(3);
        tree.insertion(4);
        tree.insertion(2);
        tree.insertion(13);
        tree.insertion(9);
        tree.insertion(15);
        tree.insertion(18);
        tree.insertion(17);
        tree.insertion(20);
        tree.rootInsertion(8);
        //tree.startIndexation();

        tree.print();

    }
}
