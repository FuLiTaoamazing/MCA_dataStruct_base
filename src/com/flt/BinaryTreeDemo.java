package com.flt;

import java.awt.*;
import java.time.temporal.ValueRange;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName: BinaryTreeDemo.java
 * @author: Cheems
 * @description:
 * @createTime: 2021年07月28日 22:25:00
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(Integer.MAX_VALUE);
        Node node4 = new Node(1);
        node1.left = node2;
        node1.right = node3;
        printTree(node1);
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //不使用递归的先序遍历
    public static void pre(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("pre-order");
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            System.out.println(stack.peek().value);
            if (stack.peek().right != null) {
                stack.push(stack.peek().right);
            }
            if (stack.peek().left != null) {
                stack.push(stack.peek().left);
            }
            stack.pop();
        }
    }

    //不使用递归的中序遍历
    public static void in(Node root) {
        System.out.println("in-order");
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root.left);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.println(root.value);
                root = root.right;
            }
        }
    }

    //不使用递归的后序遍历
    public static void pos2(Node h) {
        System.out.println("pos-order");
        if (h != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.println(stack.pop().value);
                    h = c;
                }
            }
        }
    }

    public static void level(Node root) {
        //层序遍历
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.value);
            queue.add(node.left);
            queue.add(node.right);
        }
    }

    public static int maxWidthLevel(Node root) {
        if (root == null) {
            return -1;
        }
        Node curEnd = root;
        Node nextEnd = null;
        int max = 0;
        int curLevelNodes = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curEnd = nextEnd;
                curLevelNodes = 0;
            }
        }

        return max;
    }


    //二叉树的前序序列化
    public static Queue<String> serializableTree(Node root) {
        Queue<String> queue = new LinkedList<>();
        pres(root, queue);
        return queue;
    }

    public static void pres(Node root, Queue<String> ans) {
        if (root == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(root.value));
            pres(root.left, ans);
            pres(root.right, ans);
        }
    }

    public static Node buildByPreList(Queue<String> preList) {
        if (preList == null || preList.size() == 0) {
            return null;
        }
        return preb(preList);
    }

    public static Node preb(Queue<String> preList) {
        //取出第一个值
        String value = preList.poll();
        if (value == null) {
            //当value等于null的时候说明构建空节点
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }


    public static Queue<String> levelSerializableTree(Node root) {
        Queue<String> ans = new LinkedList<>();
        if (root == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(root.value));
            Queue<Node> levelQueue = new LinkedList<>();
            levelQueue.add(root);
            while (!levelQueue.isEmpty()) {
                Node cur = levelQueue.poll();
                if (cur.left != null) {
                    levelQueue.add(cur.left);
                    ans.add(String.valueOf(cur.left.value));
                } else {
                    ans.add(null);
                }
                if (cur.right != null) {
                    levelQueue.add(cur.right);
                    ans.add(String.valueOf(cur.right.value));
                } else {
                    ans.add(null);
                }
            }
        }


        return ans;
    }


    public static Node deserializableLevelList(Queue<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        Node head = generatorNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            Node left = generatorNode(levelList.poll());
            Node right = generatorNode(levelList.poll());
            node.left = left;
            node.right = right;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }

        }

        return head;
    }


    public static Node generatorNode(String value) {
        if (value == null) {
            return null;
        }
        Node node = new Node(Integer.parseInt(value));

        return node;
    }

    //打印二叉树
    public static void printTree(Node head) {
        System.out.println("Binary Tree");
        printInTo(head, 0, "H", 17);
        System.out.println();
    }


    public static void printInTo(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInTo(head.right, height + 1, "∨", len);
        String value = to + String.valueOf(head.value) + to;
        int lengL = (len - value.length()) / 2;
        int lengR = (len - lengL);
        System.out.println(getSpace(height * len) + getSpace(lengL) + value + getSpace(lengR));
        printInTo(head.left, height + 1, "∧", len);


    }

    public static String getSpace(int len) {
        StringBuilder sb = new StringBuilder();
        while (len > 0) {
            sb.append(" ");
            len--;
        }
        return sb.toString();
    }

    public static void printAllfolds(int n) {
        printProcess(1, n, true);
    }

    /*
     * @Description:
     * @Author: FULITAO
     * @param: i, 代表从第及层开始打印
     * @param: n, 代表树有多高
     * @param: down; 打印down还是up
     * @Return: void
     * @Date: 2021/8/3 22:12
     **/
    public static void printProcess(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        //这三行代码就是模拟了树的中序遍历
        //因为这棵树比较特殊他的左子树永远是 down 右子树永远是up
        printProcess(i + 1, n, true);
        System.out.println(down ? "down" : "up");
        printProcess(i + 1, n, false);
    }
}
