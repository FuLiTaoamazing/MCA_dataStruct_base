package com.flt.tire;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TireDemo.java
 * @author: Cheems
 * @description:前缀树
 * @createTime: 2021年07月22日 20:17:00
 */
public class TireTreeDemo {

    public static void main(String[] args) {
        //构造一个前缀树
        Tree2 tree = new Tree2();
        String[] strArr = {"abc", "cbd", "abd", "abc"};
        for (String str : strArr) {
            tree.insert(str);
        }
        System.out.println(tree.search("abc"));
        System.out.println(tree.prefixNumber("ab"));
        tree.delete("abc");
        System.out.println(tree.search("abc"));
    }


    //思想:先构造一个空的节点作为头结点，从空节点往下找字符的路是否存在不存在就
    //创建 存在就复用
    //这种树的每个节点都存的是char类型的字符

    public static class Node1 {
        //代表这个节点通过了多少次  即多少个字符串以这个几点为prefix
        public int pass;
        //代表这个插入的字符串有多少个以这个节点为suffix
        public int end;
        //这个数组存的就是往下走的节点
        public Node1[] nexts;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            //规定不管大写，只管小写的字母a~z一共有26个 他撑死都不回超过这个数组
            this.nexts = new Node1[26];
        }
    }

    public static class Tree1 {
        //代表前缀树的根节点
        public Node1 root;

        public Tree1() {
            //构造一个空节点作为结构树的根节点  一切插入操作穿过这棵树的的根节点pass都++
            this.root = new Node1();
        }

        public void insert(String str) {
            if (str == null) {
                return;
            }
            //遍历字符串的每个字符
            char[] chars = str.toCharArray();
            //构建一个用于操作每个节点的变量
            //插入操作只要执行根节点的root的pass++
            Node1 node = root;
            node.pass++;
            //这个index就是用于标记该走那条路  因为我们用数组来存储他的路的方向
            // 用char-'a'的asii码 比如'a'-'a'=0即认为数组nexts[0]的位置就是a的路
            //'b'-'a'=1认为nexts[1]的位置就是b的路 等等
            int index = 0;

            for (int i = 0; i < chars.length; i++) {
                //从左往右遍历每个char
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    //不存在路的话就创建一个新的节点
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            //因为for循环出来就到了字符串的结尾的节点 直接end++
            node.end++;
        }


        public void delete(String str) {
            //插入的时候就是沿途的每个节点的pass++ 然后最后的节点end++
            //删除就是倒着来
            //但是要注意的情况 就是当node.pass--之后=0 代表他往后没有节点了
            //直接使得node.next[index]==null即可
            if (str == null) {
                return;
            }
            //删除前必须先查下这个str是否存在这个tree中 不然会导致遍历的时候突然发现没了
            if (search(str) > 0) {
                int index = 0;
                char[] chars = str.toCharArray();
                Node1 node = root;
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    //pass为0的情况就是当前没有字符串复用这个字符节点直接给为0并把他设置成null;
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        //这个方法是用于查找字符串加入了几次  其实作为List<String>的contains方法很像
        public int search(String str) {
            if (str == null) {
                return 0;
            }
            //用于标记走哪条路
            int index = 0;
            //从头结点往下找
            Node1 node = root;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                //要是路走不通了直接return 0
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        //查找这个字符串是多少个字符串的前缀 即返回这个字符串在前缀树种最后一个节点的 pass值
        public int prefixNumber(String str) {
            if (str == null) {
                return 0;
            }
            Node1 node = root;
            int index = 0;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }

            return node.pass;
        }
    }

    //使用HashMap代表节点的路 节省多余的数组空间
    public static class Node2 {
        public int pass;
        public int end;
        public Map<Integer, Node2> nexts;

        public Node2() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new HashMap<>();
        }

    }

    public static class Tree2 {
        public Node2 root;

        public Tree2() {
            this.root = new Node2();
        }

        public void insert(String str) {
            if (str == null) {
                return;
            }
            int index = 0;
            Node2 node = root;
            node.pass++;
            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts.get(index) == null) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }


        public void delete(String str) {
            if (str == null) {
                return;
            }
            if (search(str) > 0) {
                int index = 0;
                char[] chars = str.toCharArray();
                Node2 node = root;
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }


        //判断这个字符串是否存在这个前缀树种
        public int search(String str) {
            if (str == null) {
                return 0;
            }
            int index = 0;
            char[] chars = str.toCharArray();
            Node2 node = root;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts.get(index) == null) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            int index = 0;
            Node2 node = root;
            char[] chars = pre.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts.get(index) == null) {
                    return 0;
                }
                node = node.nexts.get(index);
            }

            return node.pass;
        }
    }


}
