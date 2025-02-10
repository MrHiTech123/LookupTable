import java.util.*;
import java.util.function.Function;

public class LookupTable<T> extends AbstractSet<T> {
    private ArrayList<T> contents;
    private final Function<T, Integer> toWholeNumber;
    
    
    /**
     * @param toWholeNumber a Function object, must return an Integer >= 0 for ALL inputs that the set should be able to hold.
     */
    public LookupTable(Function<T, Integer> toWholeNumber) {
        this.toWholeNumber = toWholeNumber;
        contents = new ArrayList<>();
    }
    
    private int teeToIndex(T toConvert) {
        int toReturn = toWholeNumber.apply(toConvert);
        if (toReturn < 0) {
            System.err.println("Error: toWholeNumber(" + toConvert + ") should return a whole number, returned " + toReturn);
        }
        return toReturn;
    }
    
    private int toIndex(Object object) {
        try {
            T t = (T) object;
            if (t != null) {
                return teeToIndex(t);
            }
            return -1;
        }
        catch (ClassCastException e) {
            System.err.println("Warning: " + object.getClass().getName() + " \"" + object + "\" cannot be in this lookuptable.");
            return -1;
        }
    }
    
    public int size() {
        int toReturn = 0;
        for (T tee : contents) {
            if (tee != null) {
                ++toReturn;
            }
        }
        return toReturn;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
    
    
    public boolean contains(Object object) {
        if (object == null) {
            return false;
        }
        
        int index = toIndex(object);
        
        if (index < 0) {
            return false;
        }
        if (index >= contents.size()) {
            return false;
        }
        
        if (contents.get(index) == null) {
            return false;
        }
        
        return true;
        
    }
    
    private ArrayList<T> toArrayList() {
        ArrayList<T> toReturn = new ArrayList<>();
        for (T toAdd : contents) {
            if (toAdd != null) {
                toReturn.add(toAdd);
            }
        }
        return toReturn;
    }
    
    @Override
    public Iterator<T> iterator() {
        return toArrayList().iterator();
    }
    
    @Override
    public Object[] toArray() {
        return toArrayList().toArray();
    }
    
    @Override
    public boolean add(T toAdd) {
        int index = toIndex(toAdd);
        
        if (index < 0) {
            return false;
        }
        
        if (contents.size() <= index) {
            while (contents.size() <= index) {
                contents.add(null);
            }
        }
        
        contents.set(index, toAdd);
        return true;
    }
    
    @Override
    public boolean remove(Object o) {
        int index = toIndex(o);
        if (index < 0) {
            return false;
        }
        
        if (contents.size() <= index) {
            return false;
        }
        
        if (contents.get(index) == null) {
            return false;
        }
        
        contents.set(index, null);
        return true;
    }
    
    
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T toAdd : c) {
            if (!add(toAdd)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean toReturn = false;
        for (int i = 0; i < contents.size(); ++i) {
            if (!c.contains(contents.get(i))) {
                contents.set(i, null);
                toReturn = true;
            }
        }
        return toReturn;
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean toReturn = false;
        for (Object obj : c) {
            if (remove(obj)) {
                toReturn = true;
            }
        }
        return toReturn;
    }
    
    public void clear() {
        contents = new ArrayList<>();
    }
    
    
    
    
}