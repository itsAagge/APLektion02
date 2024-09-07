package opgave01;

import bst.BSTreeSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> intComparator = Comparator.comparingInt(Integer::intValue);
        BSTreeSet<Integer> set1 = new BSTreeSet<>(intComparator);
        ArrayList<Integer> list = new ArrayList<>(List.of(45,22,77,11,30,90,15,25,88));
        set1.addAll(list);

        System.out.println("Greater than 45: " + set1.greaterThanSet(45));
        System.out.println("Size: " + set1.greaterThanCount(45));
        System.out.println();
        System.out.println("Greater than 22: " + set1.greaterThanSet(22));
        System.out.println("Size: " + set1.greaterThanCount(22));
        System.out.println();

        System.out.println("Height of tree: " + set1.height());
        System.out.println("Height of 2: " + set1.sameHeightCount(2));
        System.out.println("Height of 3: " + set1.sameHeightCount(3));
    }
}
