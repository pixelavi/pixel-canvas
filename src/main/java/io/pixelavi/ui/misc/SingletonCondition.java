package io.pixelavi.ui.misc;

/**
 * Created: 20.12.2021 01:27
 * Author: Twitter @niffyeth
 **/

public class SingletonCondition {

    private boolean condition;

    public boolean configure(boolean b) {
        if (b) condition = true;
        return b;
    }

    public boolean isMet() {
        return condition;
    }
}
