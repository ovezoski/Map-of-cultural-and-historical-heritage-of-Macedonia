package org.dians;
import java.util.ArrayList;
import java.util.List;

public class Pipe<T> {
    private List<Filter<T>> filters = new ArrayList<Filter<T>>();
    public void addFilter(Filter<T> filter){
        filters.add(filter);}
    public T runFilters(T input) throws ElementNotFoundException {
        for (Filter<T> filter : filters) {
            input = filter.execute(input);}
        return input;
    }
}
