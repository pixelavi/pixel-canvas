package io.pixelavi;

import java.awt.*;

/**
 * Created: 20.12.2021 21:15
 * Author: Twitter @niffyeth
 **/

public class ColorConverter {

    public static float getHue(Color color) {
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0];
    }

    public static Color fromHue(float hue) {
        return Color.getHSBColor(hue, 1.0f, 1.0f);
    }

    public static int[] convertToRGB(Color color) {
        int argb = color.getRGB();
        return new int[]{(argb >> 16) & 0xFF, (argb >> 8) & 0xFF, (argb) & 0xFF};
    }

    public static int[] convertToHSV(float[] hsb) {
        return new int[]{Math.round(hsb[0] * 360f), Math.round(hsb[1] * 100f), Math.round(hsb[2] * 100f)};
    }

    public static float[] convertToHSB(int[] hsv) {
        return new float[]{hsv[0] / 360f, hsv[1] / 100f, hsv[2] / 100f};
    }
}
