import java.util.function.Function;

public class IntLookupTable extends LookupTable<Integer> {
    public IntLookupTable() {
        this(IntLookupTable::echo);
    }
    public IntLookupTable(Function<Integer, Integer> toWholeNum) {
        super(toWholeNum);
    }
    
    public static int echo(int toReturn) {
        return toReturn;
    }
    
}
