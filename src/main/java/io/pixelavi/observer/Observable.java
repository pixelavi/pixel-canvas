package io.pixelavi.observer;

/**
 * Created: 20.12.2021 23:51
 * Author: Twitter @niffyeth
 **/

public interface Observable<T> {
    void addObserver(T t);

    void notifyObserver();
}
