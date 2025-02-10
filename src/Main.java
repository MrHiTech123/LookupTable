import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Set<Integer> myLookerUpper = new IntLookupTable();
        myLookerUpper.add(-1);
        
        myLookerUpper.add(2);
        myLookerUpper.add(3);
        myLookerUpper.add(4);
        
        for (int i : myLookerUpper) {
            System.out.println(i);
        }
        
        ArrayList<Integer> toRemove = new ArrayList<Integer>(List.of(new Integer[]{1, 2, 3}));
        myLookerUpper.removeAll(toRemove);
        
        for (int i : myLookerUpper) {
            System.out.println(i);
        }
        
    }
}
