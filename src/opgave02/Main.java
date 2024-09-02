package opgave02;

import bst.BSTreeSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> intComparator = Comparator.comparingInt(Integer::intValue);
        IntBSTreeSet set1 = new IntBSTreeSet(intComparator);
        ArrayList<Integer> list = new ArrayList<>(List.of(45,22,77,11,30,90,15,25,88));
        set1.addAll(list);

        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        System.out.println("Sum of list: " + sum);
        System.out.println("Sum of set: " + set1.sum());
    }
}
