package org.beatonma.lib.ui.style;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressWarnings("unused")
public class Views {
    public static void hideIfEmpty(final TextView... views) {
        for (final TextView v : views) {
            if (v != null) {
                v.setVisibility(TextUtils.isEmpty(v.getText())
                        ? View.GONE
                        : View.VISIBLE);
            }
        }
    }

    public static void getCenter(final View v, final Point outPoint) {
        outPoint.x = v.getWidth() / 2;
        outPoint.y = v.getHeight() / 2;
    }

    /**
     * Return the width:height aspect ratio as a float.
     * The View is assumed to have been measured before this method is called.
     */
    public static float getRatio(final View view) {
        float width = view.getWidth();
        float height = view.getHeight();
        return width / height;
    }



    public static void setPaddingHorizontal(final View view, final float padding) {
        view.setPaddingRelative((int) padding,
                view.getPaddingTop(),
                (int) padding,
                view.getPaddingBottom());
    }

    public static void setPaddingVertical(final View view, final float padding) {
        view.setPaddingRelative(view.getPaddingStart(),
                (int) padding,
                view.getPaddingEnd(),
                (int) padding);
    }

    public static void setPadding(final View view,
                                  final Float paddingStart,
                                  final Float paddingTop,
                                  final Float paddingEnd,
                                  final Float paddingBottom) {
        view.setPaddingRelative(
                (int) (paddingStart == null ? view.getPaddingStart() : paddingStart),
                (int) (paddingTop == null ? view.getPaddingTop() : paddingTop),
                (int) (paddingEnd == null ? view.getPaddingEnd() : paddingEnd),
                (int) (paddingBottom == null ? view.getPaddingBottom() : paddingBottom));
    }

    public static void setPaddingStart(final View view, final float paddingStart) {
        view.setPaddingRelative((int) paddingStart,
                view.getPaddingTop(),
                view.getPaddingEnd(),
                view.getPaddingBottom());
    }

    public static void setPaddingTop(final View view, final float paddingTop) {
        view.setPaddingRelative(view.getPaddingStart(),
                (int) paddingTop,
                view.getPaddingEnd(),
                view.getPaddingBottom());
    }

    public static void setPaddingEnd(final View view, final float paddingEnd) {
        view.setPaddingRelative(view.getPaddingStart(),
                view.getPaddingTop(),
                (int) paddingEnd,
                view.getPaddingBottom());
    }

    public static void setPaddingBottom(final View view, final float paddingBottom) {
        view.setPaddingRelative(view.getPaddingStart(),
                view.getPaddingTop(),
                view.getPaddingEnd(),
                (int) paddingBottom);
    }

    public static void setWidth(final int width, final View... views) {
        for (final View v : views) {
            final ViewGroup.LayoutParams lp = v.getLayoutParams();
            lp.width = width;
            v.setLayoutParams(lp);
        }
    }

    public static void setHeight(final int height, final View... views) {
        for (final View v : views) {
            final ViewGroup.LayoutParams lp = v.getLayoutParams();
            lp.height = height;
            v.setLayoutParams(lp);
        }
    }

    public static void setWidthAndHeight(final int width, final int height, final View... views) {
        for (final View v : views) {
            final ViewGroup.LayoutParams lp = v.getLayoutParams();
            lp.width = width;
            lp.height = height;
            v.setLayoutParams(lp);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setElevation(final int elevation, final View... views) {
        for (final View v : views) {
            v.setElevation(elevation);
        }
    }

    public static void setMargins(final int left, final int top, final int right, final int bottom, final View... views) {
        for (final View v : views) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            if (left >= 0) {
                lp.leftMargin = left;
            }
            if (top >= 0) {
                lp.topMargin = top;
            }
            if (right >= 0) {
                lp.rightMargin = right;
            }
            if (bottom >= 0) {
                lp.bottomMargin = bottom;
            }

            v.setLayoutParams(lp);
        }
    }

    public static void setVisibility(final int visibility, final View... views) {
        for (final View v : views) {
            if (v != null) {
                v.setVisibility(visibility);
            }
        }
    }

    public static void setVisibilityAnimated(final int visibility, final View... views) {
        for (final View v : views) {
            if (v != null) {
                if (v.getVisibility() == visibility) {
                    continue;
                }

                final ObjectAnimator alpha;
                final ObjectAnimator translationY;
                final AnimatorSet set = new AnimatorSet();
                if (visibility == View.VISIBLE) {
                    alpha = ObjectAnimator.ofFloat(v, "alpha", 0, 1f);
                    translationY = ObjectAnimator.ofFloat(v, "translationY", -v.getHeight(), 0);
                    set.setInterpolator(Interpolate.getEnterInterpolator());
                    set.setDuration(Animation.DURATION_ENTER);
                }
                else {
                    alpha = ObjectAnimator.ofFloat(v, "alpha", 1f, 0);
                    translationY = ObjectAnimator.ofFloat(v, "translationY", 0, -v.getHeight());
                    set.setInterpolator(Interpolate.getExitInterpolator());
                    set.setDuration(Animation.DURATION_EXIT);
                }

                set.playTogether(alpha, translationY);
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        super.onAnimationEnd(animation);
                        v.setVisibility(visibility);
                    }
                });
                set.start();
            }
        }
    }

    public static void toggleVisibility(final View... views) {
        for (final View v : views) {
            if (v == null) {
                continue;
            }
            if (v.getVisibility() == View.VISIBLE) {
                v.setVisibility(View.GONE);
            }
            else {
                v.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void setBackgroundColor(final int color, final View... views) {
        for (final View v : views) {
            if (v != null) {
                v.setBackgroundColor(color);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setBackgroundColor(final ColorStateList stateList, final View... views) {
        for (final View v : views) {
            if (v != null) {
                v.setBackgroundTintList(stateList);
            }
        }
    }

    public static void setTextColor(final int color, final TextView... views) {
        for (final TextView v : views) {
            if (v != null) {
                v.setTextColor(color);
            }
        }
    }

    public static void setTextColor(final ColorStateList stateList, final TextView... views) {
        for (final TextView v : views) {
            if (v != null) {
                v.setTextColor(stateList);
            }
        }
    }
}
