package basic.dataStructure.trie;

import java.util.HashMap;

class MapSum {

    private class Node {
        private int val;
        private Node[] next;

        public Node() {
            val = 0;
            next = new Node[26];
        }
    }

    HashMap<String, Integer> map;
    private Node root;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        map = new HashMap();
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for (char c : key.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node();
            }
            cur = cur.next[c - 'a'];
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            cur = cur.next[c - 'a'];
            if (cur == null) {
                return 0;
            }
        }
        return sum(cur);
    }

    private int sum(Node cur) {
        if (cur == null) {
            return 0;
        }
        int res = cur.val;
        for (Node node : cur.next) {
            if (node == null) {
                continue;
            }
            res += sum(node);
        }

        return res;
    }

    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("aa", 3);
        System.out.println(obj.sum("a"));
        obj.insert("aa", 2);
        System.out.println(obj.sum("a"));
        obj.sum("a");
        obj.sum("aa");
        obj.insert("aaa",3);;
        System.out.println(obj.sum("aaa"));
        System.out.println(obj.sum("bbb"));
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */