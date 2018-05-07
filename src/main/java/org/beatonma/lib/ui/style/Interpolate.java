package org.beatonma.lib.ui.style;

import android.animation.TimeInterpolator;

import org.beatonma.lib.ui.style.interpolate.FastOutLinearInInterpolator;
import org.beatonma.lib.ui.style.interpolate.FastOutSlowInInterpolator;
import org.beatonma.lib.ui.style.interpolate.LinearOutSlowInInterpolator;

public class Interpolate {

    /**
     * Interpolator used for introducing content
     */
    public static TimeInterpolator getContentInInterpolator() {
        return getEnterInterpolator();
    }

    /**
     * Interpolator used for removing content
     */
    public static TimeInterpolator getContentOutInterpolator() {
        return getExitInterpolator();
    }

    /**
     * Interpolator used for introducing an object from offscreen
     */
    public static TimeInterpolator getEnterInterpolator() {
        return new LinearOutSlowInInterpolator();
    }

    /**
     * Interpolator used for removing an object from the screen
     */
    public static TimeInterpolator getExitInterpolator() {
        return new FastOutLinearInInterpolator();
    }

    /**
     * Interpolator used for on-screen motion - resizing, moving, that kind of thing
     */
    public static TimeInterpolator getMotionInterpolator() {
        return new FastOutSlowInInterpolator();
    }
}
