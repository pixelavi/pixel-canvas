package io.pixelavi.ui.color;

/**
 * Created: 21.12.2021 14:19
 * Author: Twitter @niffyeth
 **/

public enum ColorValueType {
    RGB(in -> in.matches("[0-9]+") && in.length() <= 4 && Integer.parseInt(in) <= 255),
    HEX(in -> in.matches("^#[0-9A-f]{6}$"));

    private final ColorValueValidator validator;

    ColorValueType(ColorValueValidator validator) {
        this.validator = validator;
    }

    public boolean validate(String in) {
        return validator.validate(in);
    }
}
