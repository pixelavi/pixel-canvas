package io.pixelavi.observer;

/**
 * Created: 20.12.2021 23:51
 * Author: Twitter @niffyeth
 **/

public interface Observable<T, V> {
    void addObserver(T t);

    void notifyObserver(V v);
}
