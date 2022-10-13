package com.elapsefeather.griditemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = GridItemDecoration.class.getSimpleName();

    public interface DecorationStyle {
        int HORIZONTAL = LinearLayout.HORIZONTAL;
        int VERTICAL = LinearLayout.VERTICAL;
        int INSIDEALL = 2;
        int ROUNDALL = 3;
    }

    //    private mDivider;
    private int mOrientation = LinearLayout.VERTICAL;
    private Rect mBounds = new Rect();
    private int dividerColor;
    private int dividerSize;
    private Paint mPaint;
    private Builder mBuilder;

    /**
     * Creates a divider [RecyclerView.ItemDecoration] that can be used with a
     * [LinearLayoutManager].
     *
     * @param orientation Divider orientation. Should be [.HORIZONTAL] or [.VERTICAL] or [.INSIDEALL] or [.ROUNDALL]
     */
    public GridItemDecoration(int orientation) {
        setOrientation(orientation);
        initPaint();
    }

    public GridItemDecoration(int orientation, int color) {
        this.dividerColor = color;
        setOrientation(orientation);
        initPaint();
    }

    public GridItemDecoration(Builder builder) {
        if (builder != null) {
            this.mBuilder = builder;

            if (builder.orientation != null) setOrientation(builder.orientation);
            if (builder.color != null) dividerColor = builder.color;
            if (builder.size != null) dividerSize = builder.size;
        }
        initPaint();
    }

    public void setDividerColor(int color) {
        this.dividerColor = color;
        initPaint();
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * [RecyclerView.LayoutManager] changes orientation.
     *
     * @param orientation [.HORIZONTAL] or [.VERTICAL] or [.INSIDEALL] or [.ROUNDALL]
     */
    public void setOrientation(int orientation) {
        if (orientation != DecorationStyle.HORIZONTAL && orientation != DecorationStyle.VERTICAL
                && orientation != DecorationStyle.INSIDEALL && orientation != DecorationStyle.ROUNDALL) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(dividerColor);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() == null) return;

        switch (mOrientation) {
            case DecorationStyle.ROUNDALL:
            case DecorationStyle.INSIDEALL:
                drawVertical(c, parent);
                drawHorizontal(c, parent);
                break;
            case DecorationStyle.VERTICAL:
                drawVertical(c, parent);
                break;
            case DecorationStyle.HORIZONTAL:
            default:
                drawHorizontal(c, parent);
                break;
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();

        int childCount = parent.getChildCount();
        int spanCount = -1;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        } else if (mOrientation != DecorationStyle.ROUNDALL
                && parent.getLayoutManager() instanceof LinearLayoutManager
                && ((LinearLayoutManager) parent.getLayoutManager()).getOrientation() == RecyclerView.HORIZONTAL) {
            return;
        }

        for (int i = 0; i <= childCount; i++) {
            View child = parent.getChildAt(i);
            if (child == null) return;

            parent.getDecoratedBoundsWithMargins(child, mBounds);
            int left = mBounds.left;
            int right = mBounds.right;

//                最上面那條
            if (mOrientation == DecorationStyle.ROUNDALL
                    && (spanCount == -1 || spanCount != -1 && i < childCount - childCount % spanCount)) {
                int bottom = dividerSize;
                int top = 0;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
//            最下面的，除非是 ROUNDALL 不然不畫
            if (mOrientation == DecorationStyle.ROUNDALL
                    || (mOrientation != DecorationStyle.ROUNDALL
                    && ((spanCount == -1 && i != childCount - 1)
                    || (spanCount != -1 && childCount > spanCount && i < childCount - childCount % spanCount)))) {
                int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                int top = bottom - dividerSize;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }

        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();

        int childCount = parent.getChildCount();
        int spanCount = -1;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        } else if (mOrientation != DecorationStyle.ROUNDALL
                && parent.getLayoutManager() instanceof LinearLayoutManager
                && ((LinearLayoutManager) parent.getLayoutManager()).getOrientation() == RecyclerView.VERTICAL) {
            return;
        }

        for (int i = 0; i <= childCount - 1; i++) {
            View child = parent.getChildAt(i);
            if (child == null) return;
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            int top = mBounds.top;
            int bottom = mBounds.bottom;

//                最左邊那條
            if (mOrientation == DecorationStyle.ROUNDALL
                    && (spanCount == -1 || spanCount != -1 && i % spanCount == 0)) {
                int right = dividerSize;
                int left = 0;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
//            最右邊的，除非是 ROUNDALL 不然不畫
            if (mOrientation == DecorationStyle.ROUNDALL
                    || (mOrientation != DecorationStyle.ROUNDALL
                    && ((spanCount == -1 && i != childCount - 1)
                    || spanCount != -1 && i % spanCount != 2))) {
                int right = mBounds.right + Math.round(child.getTranslationX());
                int left = right - dividerSize;
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }

        canvas.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (dividerSize == 0) {
            outRect.set(0, 0, 0, 0);
        } else if (mOrientation == DecorationStyle.VERTICAL) {
            outRect.set(0, 0, 0, dividerSize);
        } else {
            outRect.set(0, 0, dividerSize, 0);
        }
    }

    public static class Builder {
        Integer color;
        Integer size;
        Integer orientation;

        public Builder orientation(int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public GridItemDecoration build() {
            return new GridItemDecoration(this);
        }
    }
}
