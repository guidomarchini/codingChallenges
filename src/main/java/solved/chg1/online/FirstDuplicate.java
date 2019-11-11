package solved.chg1.online;

import java.util.*;

public class FirstDuplicate {
    private List<Integer> elements = new LinkedList<Integer>();

    public void add(Integer element) {
        elements.add(element);
    }

    // O(N²)
    public Integer firstDuplicate() {
        List<Integer> complementary = new LinkedList<Integer>(elements);

        for (Integer toSearch : elements) {
            complementary.remove(0);
            for (Integer currentNumber : complementary) {
                if (toSearch == currentNumber) {
                    elements.remove(toSearch);
                    return toSearch;
                }
            }
        }

        throw new RuntimeException("there'are no duplicates");
    }

    // O(N), memory 2N
    public Integer firstDuplicate2() {
        Set<Integer> cache = new HashSet<Integer>();

        for (Integer toSearch : elements) {
            if (cache.contains(toSearch)) {
                elements.remove(toSearch);
                return toSearch;
            } else {
                cache.add(toSearch);
            }
        }

        throw new RuntimeException("there'are no duplicates");
    }

    private List<Integer> arrayElements = new ArrayList<Integer>();
    // (1, 2, 3, 4, 2, 4, 5)
    // (1, 2, 2, 3, 4, 4, 5)
    public Integer firstDuplicate3() {
        Collections.sort(arrayElements); // O(n log n)

        for(int index = 0;
            index < arrayElements.size() - 1;
            index ++) {

            Integer toSearch = arrayElements.get(index);

            if (toSearch == arrayElements.get(index+1)) {
                arrayElements.remove(index);
                return toSearch;
            }
        }

        throw new RuntimeException("there'are no duplicates");
    }


    private Set<Integer> nonDuplicated = new HashSet<Integer>();
    private LinkedList<Integer> duplicated = new LinkedList<Integer>();
    // O(1)
    public void add4(Integer element) {
        if (nonDuplicated.contains(element)) {
            duplicated.add(element);
        } else {
            nonDuplicated.add(element);
        }
    }

    // O(1)
    public Integer firstDuplicate4() {
        Integer result = duplicated.removeFirst();
        return result;
    }
}
