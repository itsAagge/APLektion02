package bst;

import java.util.Comparator;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Comparator<String> stringComparator = (String s1, String s2) -> s1.compareTo(s2);
        // Comparator<String> stringComparator = Comparator.naturalOrder();
        Comparator<String> stringComparator = String::compareTo;
        BSTreeSet<String> set1 = new BSTreeSet<>(stringComparator);
        set1.add("D");
        set1.add("B");
        set1.add("A");
        set1.add("C");
        set1.add("F");
        set1.add("E");
        set1.add("I");
        set1.add("G");
        set1.add("H");
        set1.add("J");
        System.out.println("F inserted again? " + set1.add("F")); // F is duplicate
        System.out.println("String set:");
        System.out.println(set1);

        System.out.println("Contains B? " + set1.contains("B"));
        System.out.println("Contains M? " + set1.contains("M"));

        System.out.println("M removed? " + set1.remove("M"));
        set1.remove("A"); // Removing leaf
        set1.remove("B"); // Removing element with one child
        set1.remove("F"); // Removing element with two children
        set1.remove("D"); // Removing root
        System.out.println("After remove of A, B, F and D: (expected: C E G H I J)");
        System.out.println(set1);
        System.out.println();

        //---------------------------------------------------------------------

        System.out.println("BST of strings, ordered by natural ordering");
        BSTreeSet<String> set2 = new BSTreeSet<>(String::compareTo);
        set2.addAll(List.of("Jens", "Ulla", "Peter", "Ib", "Pia", "Hans"));
        System.out.println(set2);

        System.out.println("BST of strings, ordered by length");
        // Comparator<String> lengthComparator = (String s1, String s2) -> Integer.compare(s1.length(), s2.length());
        // Comparator<String> lengthComparator = Comparator.comparingInt((String s) -> s.length());
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        BSTreeSet<String> set3 = new BSTreeSet<>(lengthComparator);
        set3.addAll(List.of("Jens", "Ulla", "Peter", "Ib", "Pia", "Hans"));
        System.out.println(set3);
        System.out.println();
    }
}
