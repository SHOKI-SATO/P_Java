package projava;

public class TreeSample {
//    Nodeクラスは抽象クラス、値としてint型でvalフィールドに保持
    static abstract class Node{
        int val;

        Node(int val){
            this.val = val;
        }
//        スーパークラスになるようなクラスはなるべく抽象クラスにする
//        集計を行うメソッドはsumメソッド（これは抽象メソッドにしている）
        abstract int sum();
    }
//    LeafクラスはNodeクラスを継承
    static class Leaf extends Node{
        public Leaf(int val){
            super(val);
        }

//        Leafクラスのsumメソッドでは値をそのまま返す
        @Override
        int sum() {
            return val;
        }
    }

//    BranchクラスもNodeクラスを継承し、左右のNodeを保持
    static class Branch extends Node{
        Node left;
        Node right;

//        left、rightフィールドでは、Nodeを継承したLeafオブジェクト、Branchオブジェクトを扱える
//        Branchクラスのsumメソッドは、そのBranch自身が持つ値と、左ノードがあれば左ノードの集計、右ノードがあれば右ノードの集計を足していく
        Branch(int val, Node left, Node right){
            super(val);
            this.left = left;
            this.right = right;
        }

        @Override
        int sum(){
            int result = val;
            if (left != null )result += left.sum();
            if (right != null )result += right.sum();
            return result;
        }
    }

//    ツリー構造のデータを用意している
    public static void main (String[]args){
        Node root =
                new Branch(5,
                        new Branch(2,
                                new Leaf(4),
                                null),
                                new Branch(7,
                                        new Leaf(6),
                                        new Leaf(8)));
                System.out.println(root.sum());
    }
}
