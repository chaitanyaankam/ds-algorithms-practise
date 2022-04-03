package com.learning.tree;

import java.util.HashMap;
import java.util.Map;

public class SerializeAndDeserialize {
    public static void main(String arg[]) {
        Tree1 right = new Tree1("right", null, null);
        Tree1 leftOfleft = new Tree1("left.left", null, null);
        Tree1 left = new Tree1("left", leftOfleft, null);
        Tree1 root = new Tree1("root", left, right);
        String res = serialize(root);
        System.out.println(res);
        Tree1 treeDs = deSerialize(res);
        System.out.println(treeDs.left.left.val);
    }

    public static Tree1 deSerialize(String s) {
        Map<String, Tree1> map = new HashMap<>();
        String[] sArr = s.split("->");

        for(String k: sArr) {
            Tree1 node = new Tree1(k, null, null);
            map.put(k, node);

            if(k.equals("root"))
                continue;

            int lastIndex = k.lastIndexOf(".");
            if(lastIndex < 0) {
                Tree1 root = map.get("root");
                if("left".equals(k)) root.left = node;
                else root.right = node;
            } else {
                String prefix = k.substring(0, lastIndex);
                String suffix = k.substring(lastIndex + 1, k.length());
                Tree1 n = map.get(prefix);
                if("left".equals(suffix)) n.left = node;
                else n.right = node;
            }
        }
        return map.get("root");
    }

    public static String serialize(Tree1 node) {
        if(node == null) return "";
        String l = serialize(node.left);
        String r = serialize(node.right);
        String n = node.val;

        StringBuilder sb = new StringBuilder();
        sb.append(n);
        if(!l.isEmpty()) sb.append("->"+l);
        if(!r.isEmpty()) sb.append("->"+r);

        return sb.toString();
    }
}

class Tree1 {
    String val;
    Tree1 left;
    Tree1 right;

    public Tree1(String val, Tree1 l, Tree1 r) {
        this.val = val;
        this.left = l;
        this.right = r;
    }
}