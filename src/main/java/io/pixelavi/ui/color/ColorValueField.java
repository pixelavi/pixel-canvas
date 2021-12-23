package io.pixelavi.ui.color;

import io.pixelavi.ui.Theme;
import io.pixelavi.ui.input.Textfield2D;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created: 21.12.2021 14:19
 * Author: Twitter @niffyeth
 **/

public class ColorValueField extends Textfield2D implements MouseListener {

    private final static Color INVALID = new Color(237, 66, 69);

    public ColorValueField(ColorInputCallback callback, ColorValueType type) {
        this.setFocusable(false);
        this.addMouseListener(this);
        this.setHorizontalAlignment(JTextField.CENTER);
        this.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                validate();
            }

            public void removeUpdate(DocumentEvent e) {
                validate();
            }

            public void insertUpdate(DocumentEvent e) {
                validate();
            }

            public void validate() {
                boolean valid = type.validate(getText());
                setBackground(valid ? Theme.LIGHT_COMPONENT.getColor() : INVALID);
                if (isFocusOwner() && valid) {
                    callback.forward(type);
                }
            }
        });
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setFocusable(true);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setFocusable(false);
    }
}
