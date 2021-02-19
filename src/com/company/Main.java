package com.company;

import java.util.Scanner;

class Node{
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Tree{
    Node root;
    Tree(int data){
        root = new Node(data);
    }

    // insert
    public void insertNode(Node newNode){
        Node tmp = root;

        while(tmp != null){
            if(newNode.data > tmp.data){ // to the right
                if(tmp.right != null) {
                    tmp = tmp.right;
                }else{
                    tmp.right = newNode;
                    break;
                }
            }else if(newNode.data == tmp.data){
                System.out.println("Duplicate entries!\n");
                break;
            } else{                     // to the left
                if(tmp.left != null) {
                    tmp = tmp.left;
                }else{
                    tmp.left = newNode;
                    break;
                }
            }
        }
    }

    // delete
    public Node deleteNode(Node node, int data){
        if(node == null){
            return null;
        }

        if(data > node.data){
            node.right = deleteNode(node.right, data);
        }else if(data < node.data){
            node.left = deleteNode(node.left, data);
        }else{
            if(node.left == null && node.right == null){    // 0 child
                node = null;
            }else if(node.right != null){                   // has right child
                node.data = minSubtree(node.right);
                node.right = deleteNode(node.right, node.data);
            }else{                                          // 1 left child
                node.data = maxSubtree(node.left);
                node.left = deleteNode(node.left, node.data);
            }
        }
        return node;
    }

    public int minSubtree(Node node){
        int min = node.data;

        while(node.left != null){
            node = node.left;
            min = node.data;
        }
        return min;
    }

    public int maxSubtree(Node node){
        int max = node.data;

        while(node.right != null){
            node = node.right;
            max = node.data;
        }
        return max;
    }

    // search
    public void preorder(Node root){
        if(root == null) {
            return;
        }

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
    public void inorder(Node root){
        if(root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);

    }
    public void postorder(Node root){
        if(root == null) {
            return;
        }

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

//        System.out.print("[Create Tree] input root node: ");
//        int rootData = input.nextInt();
//        Tree tree = new Tree(rootData); // tree 생성
        Tree tree = new Tree(7); // tree 생성
        tree.insertNode(new Node(5));
        tree.insertNode(new Node(9));
        tree.insertNode(new Node(3));
        tree.insertNode(new Node(6));
        tree.insertNode(new Node(8));
        tree.insertNode(new Node(11));
        tree.insertNode(new Node(1));
        tree.insertNode(new Node(12));

        System.out.println("\nMENU (1: insert | 2: delete | 3: preorder | 4: inorder | 5: postorder | -1: quit)\n");
        int check;
        do{
            System.out.print("what do you want? ");
            check = input.nextInt();

            // node 삽입
            if(check == 1) {
                System.out.print("Input node data: ");
                int data = input.nextInt();
                tree.insertNode(new Node(data));
            } // node 삭제
            else if(check == 2) {
                System.out.print("Input delete node: ");
                int data = input.nextInt();
                Node tmp = tree.deleteNode(tree.root, data);
                if(tmp == null){
                    System.out.println("No data\n");
                }else {
                    System.out.println("deleted: " + tmp.data);
                }
            } // preorder
            else if(check == 3){
                tree.preorder(tree.root);
                System.out.println();
            } // inorder
            else if(check == 4){
                tree.inorder(tree.root);
                System.out.println();
            } // postorder
            else if(check == 5){
                tree.postorder(tree.root);
                System.out.println();
            } // 종료
            else if(check == -1){
                System.out.println("exit...");
            }
            else{
                System.out.println("Command not found.\n");
            }

        } while(check != -1);
    }
}
