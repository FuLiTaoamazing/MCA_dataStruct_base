package com.flt.linkedquestion;

/**
 * @ClassName: ListLoopDemo.java
 * @author: Cheems
 * @description:关于链表是否有环的demo
 * @createTime: 2021年07月28日 21:01:00
 */
public class ListLoopDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;
        System.out.println(getLoopNode(node1));

    }

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //这个是用双指针法找到是否有环
    public static boolean isLoopList(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            //慢指针一次走一步
            slow = slow.next;
            //快指针一次走两步
            fast = fast.next.next;
        }

        return false;
    }


    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast != slow) {
            //这句是判断fast是否还能跳两步
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        //快指针从新来到头部 慢指针停在原地
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return fast;
    }

    //第一种情况 两个链表没有环找到他们相交的第一个点
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        //找到他两的最后个节点 即next=null的时候
        while (cur1.next != null) {
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
        }
        //两个节点的最后个节点不相等说明无环
        if (cur1 != cur2) {
            return null;
        }
        int n = 0;
        //开始计算哪条链表比较长
        cur1 = head1;
        cur2 = head2;
        while (cur1 != null) {
            n++;
            cur1 = head1.next;
        }
        while (cur2 != null) {
            n++;
            cur2 = cur2.next;
        }
        //假如n>0就是cur1比cur2长 ，假如小于零就说名cur2比cur1长 n的绝对值就是他两相差的节点数
        //规定 长的那个链表的头是cur1
        cur1 = n >= 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    //因为有种情况两个链表一个有环一个无环他们是不可能相交的
    //这是两个链表都有环的情况
    // loop1是第一个链表的第一个入环节点
    //loop2是第二个链表第一个入环的节点
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            //这两个链表共用一个环直接找到头结点到环这个节点的长度判断哪个大哪个小做无环算法的操作
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n >= 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //两个入环节点不同就得分别考虑
            // 默认不相交
            boolean isIntersect = false;
            //loop1往下走
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }

            return null;

        }
    }


    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if(loop1!=null&&loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
    }
}
