package org.beatonma.lib.ui.style;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;

public class Color {
    public static int removeAlpha(final int color) {
        return android.graphics.Color.rgb(android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color));
    }

    public static int setAlpha(final int color, @IntRange(from=0, to=255) final int alpha) {
        return android.graphics.Color.argb(alpha, android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color));
    }

    public static int setAlpha(final int color, @FloatRange(from=0.0, to=1.0) final float alpha) {
        return android.graphics.Color.argb((int) (alpha * 255f), android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color));
    }

    public static String toHex(final int color) {
        return String.format("%06X", (0xFFFFFF & color));
    }

    public static float[] toHsv(final int color) {
        final float hsv[] = new float[3];
        android.graphics.Color.colorToHSV(color, hsv);
        return hsv;
    }

    public static float[] toHsv(@IntRange(from=0, to=255) final int red,
                                @IntRange(from=0, to=255) final int green,
                                @IntRange(from=0, to=255) final int blue) {
        return toHsv(android.graphics.Color.rgb(red, green, blue));
    }

    public static int lighten(final int color,
                              @FloatRange(from=0.0, to=1.0) final float amount) {
        final float hsv[] = toHsv(color);
        hsv[1] = Math.max(0f, Math.min(1f, hsv[1] - amount));    // saturation
        hsv[2] = Math.max(0f, Math.min(1f, hsv[2] + amount));    // brightness
        return android.graphics.Color.HSVToColor(hsv);
    }

    public static int darken(final int color,
                             @FloatRange(from=0.0, to=1.0) final float amount) {
        final float hsv[] = toHsv(color);
        hsv[1] = Math.max(0f, Math.min(1f, hsv[1] + amount));    // saturation
        hsv[2] = Math.max(0f, Math.min(1f, hsv[2] - amount));   // brightness
        return android.graphics.Color.HSVToColor(hsv);
    }
}
