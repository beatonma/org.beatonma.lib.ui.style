package org.beatonma.lib.ui.style;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

import java.util.ArrayList;

public class Animation {
    public final static int DURATION = 300;
    public final static int DURATION_HALF = DURATION / 2;
    public final static int DURATION_THIRD = DURATION / 3;
    public final static int DURATION_QUARTER = DURATION / 4;

    public final static int DURATION_ENTER = 300;
    public final static int DURATION_EXIT = 160;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator circularReveal(final View view,
                                          final Point center,
                                          final float startRadius,
                                          final float endRadius) {
        return ViewAnimationUtils.createCircularReveal(view, center.x, center.y, startRadius, endRadius);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator circularReveal(final View view,
                                          final Point center,
                                          final float startRadius) {
        return circularReveal(view, center, startRadius, Math.max(view.getWidth(), view.getHeight()));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator circularHide(final View view,
                                          final Point center,
                                          final float startRadius) {
        return circularReveal(view, center, startRadius, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Animator circularReveal(final View view) {
        final int centerX = view.getWidth() / 2;
        final int centerY = view.getHeight() / 2;

        return ViewAnimationUtils.createCircularReveal(view, centerX, centerY, 0, Math.max(view.getWidth(), view.getHeight()));
    }

    /**
     * Create an {@link AnimatorSet} that plays multiple Animators together, ignoring
     * any given Animator that is null.
     */
    public static AnimatorSet playTogether(final Animator... animators) {
        final ArrayList<Animator> trimmed = new ArrayList<>();
        for (final Animator a : animators) {
            if (a != null) {
                trimmed.add(a);
            }
        }

        final AnimatorSet combined = new AnimatorSet();
        combined.playTogether(trimmed);
        return combined;
    }

    /**
     * Create an {@link AnimatorSet} that plays multiple Animators together, ignoring
     * any given Animator that is null.
     */
    public static AnimatorSet playSequentially(final Animator... animators) {
        final ArrayList<Animator> trimmed = new ArrayList<>();
        for (final Animator a : animators) {
            if (a != null) {
                trimmed.add(a);
            }
        }

        final AnimatorSet combined = new AnimatorSet();
        combined.playSequentially(trimmed);
        return combined;
    }
}
