package searchTree;

import java.util.ArrayList;

public class SearchTree {
    Node root;
    static class Node{
        int level;
        int value;
        Node left;
        Node right;
        public Node(int value,int level){
            this.value=value;
            this.level=level;
        }
    }

    private void searchByLevel(Node tek,ArrayList<Node> nd, int level){
        if(tek.level==level){
            nd.add(tek);
        }
        else{
            if(tek.left!=null && tek.right!=null){
                searchByLevel(tek.left,nd,level);
                searchByLevel(tek.right,nd,level);
            }
            else if(tek.right==null&&tek.left==null){
                return;
            }
            else if(tek.left==null){
                searchByLevel(tek.right,nd,level);
            }
            else{
                searchByLevel(tek.left,nd,level);
            }
        }
    }

    private ArrayList<Node> searchByLevel(int level){
        ArrayList<Node> nd = new ArrayList<>();
        searchByLevel(root,nd,level);
        return nd;
    }

    private int getHeight(Node nd){
        if(nd.left==null && nd.right==null){
            return nd.level;
        }
        else{
            if(nd.right==null){
                return getHeight(nd.left);
            }
            else if(nd.left==null){
                return getHeight(nd.right);
            }
            else{
                int a = getHeight(nd.left);
                int b = getHeight(nd.right);
                if(a<=b){
                    return b;
                }
                else{
                    return a;
                }
            }
        }

    }
    public int getHeight(){
        return getHeight(root);
    }


    Node leftRotation(Node old, Node nnd){
        Node temp;
        temp=old;
        old=nnd;
        temp.right=nnd.left;
        nnd.left = temp;
        return old;
    }

    Node rightRotation(Node old, Node nnd){
        Node temp;
        temp=old;
        old = nnd;
        temp.left=nnd.right;
        nnd.right=temp;
        return old;
    }


    private void findAndRotate(Node nd, int value){
        if(value< nd.value){
            if(value<nd.left.value){
                if(nd.left.left!=null && value==nd.left.left.value){
                    nd.left=rightRotation(nd.left,nd.left.left);
                    indexation(nd,nd.level);
                }
                else{
                    if(nd.left.left!=null){
                        findAndRotate(nd.left,value);
                    }
                }
            }
            else {
                if(nd.left.right!=null &&value==nd.left.right.value){
                    nd.left=leftRotation(nd.left,nd.left.right);
                    indexation(nd,nd.level);
                }
                else{
                    if(nd.left.right!=null){
                        findAndRotate(nd.left,value);
                    }

                }
            }
        }
        else{
            if(value<nd.right.value){
                if(nd.right.left!=null && value==nd.right.left.value){
                    nd.right=rightRotation(nd.right,nd.right.left);
                    indexation(nd,nd.level);
                }
                else{
                    if(nd.right.left!=null){
                        findAndRotate(nd.right,value);
                    }
                }
            }
            else {
                if(nd.right.right!=null &&value==nd.right.right.value){
                    nd.right=leftRotation(nd.right,nd.right.right);
                    indexation(nd,nd.level);
                }
                else{
                    if(nd.right.right!=null){
                        findAndRotate(nd.right,value);
                    }

                }
            }
        }
//        if(value< nd.value){
//            if(nd.left.value!=value){
//                findAndRotate(nd.left,value);
//            }
//            else{
//                rightRotation(nd,nd.left);
//                startIndexation();
//            }
//        }
//        else{
//            if(nd.right.value!=value){
//                findAndRotate(nd.right,value);
//            }
//            else{
//                leftRotation(nd,nd.right);
//                startIndexation();
//            }
//        }

    }

    public void rootInsertion(int value){
        if(root!=null && root.value==value){
            return;
        }
        else if(root==null){
            insertion(value);
        }
        else{
            insertion(value);
            int a=getHeight()-1;
            for(int i=0;i<a;i++){
                findAndRotate(root,value);
            }
            if(value<root.value){
                Node temp,temp1;
                temp=root.left.right;
                temp1=root;
                root=temp1.left;
                temp1.left=temp;
                root.right=temp1;
            }
            else if(value>root.value){
                Node temp,temp1;
                temp=root.right.left;
                temp1=root;
                root=temp1.right;
                temp1.right=temp;
                root.left=temp1;
            }
            startIndexation();
        }
    }


    private void indexation(Node nd,int level){
        nd.level = level;
        if(nd.left!=null){
            indexation(nd.left,level+1);
        }
        if(nd.right!=null){
            indexation(nd.right,level+1);
        }
    }

    public void startIndexation(){
        indexation(root,0);
    }

    private Node insertion(Node tek,int level, int value){
        if(tek==null){
            tek=new Node(value,level+1);
        }
        else{
            if(value<=tek.value){
                tek.left = insertion(tek.left,tek.level,value);
            }
            else{
                tek.right = insertion(tek.right,tek.level,value);
            }
        }
        return tek;
    }

    public void insertion(int  value){
        root = insertion(root,-1,value);
    }

    public void print(){
        ArrayList<Node> nd;
        for(int i=0;i<=getHeight();i++){
            nd = searchByLevel(i);
            for(int j=0;j<nd.size();j++){
                System.out.print(nd.get(j).value+" ");
            }
            System.out.println();
        }
    }
}
