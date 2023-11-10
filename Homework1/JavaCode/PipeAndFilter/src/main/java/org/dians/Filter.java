package org.dians;

public interface Filter<T> {
    T execute(T input) throws ElementNotFoundException;
}
