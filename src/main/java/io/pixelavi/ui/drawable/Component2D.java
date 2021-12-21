package io.pixelavi.ui.drawable;

import java.awt.event.MouseEvent;
import java.util.function.Consumer;

/**
 * Created: 21.12.2021 00:25
 * Author: Twitter @niffyeth
 **/

public abstract class Component2D implements Renderable2D {
    private final Consumer<MouseEvent> consumer;
    private boolean status;

    public Component2D(Consumer<MouseEvent> consumer) {
        this.consumer = consumer;
    }

    public Consumer<MouseEvent> getConsumer() {
        return consumer;
    }

    public void toggleStatus() {
        this.status = !status;
    }

    public boolean isStatus() {
        return status;
    }
}
