package de.gankopi;

public class App 
{
    public static void main(String[] args)
    {
        BinaryTreeList<String> btl1 = new BinaryTreeList<>();
        btl1.add("a");
        btl1.add("b");
        btl1.add("c");
        btl1.add("d");
        btl1.add("e");
        btl1.add("f");
        btl1.add("g");

        BinaryTreeList<String> btl2 = new BinaryTreeList<>();
        btl2.add("g");
        btl2.add("f");
        btl2.add("e");
        btl2.add("d");
        btl2.add("c");
        btl2.add("b");
        btl2.add("a");

        BinaryTreeList<String> btl3 = new BinaryTreeList<>();
        btl3.add("d");
        btl3.add("f");
        btl3.add("e");
        btl3.add("g");
        btl3.add("b");
        btl3.add("c");
        btl3.add("a");

        BinaryTreeList<String> btl4 = new BinaryTreeList<>();
        btl4.add("e");
        btl4.add("d");
        btl4.add("b");
        btl4.add("c");
        btl4.add("g");
        btl4.add("f");
        btl4.add("a");

        System.out.println(btl1);
        System.out.println(btl1.getTreeString());
        System.out.println();

        System.out.println(btl2);
        System.out.println(btl2.getTreeString());
        System.out.println();

        System.out.println(btl3);
        System.out.println(btl3.getTreeString());
        System.out.println();

        System.out.println(btl4);
        System.out.println(btl4.getTreeString());
        System.out.println();
    }
}
