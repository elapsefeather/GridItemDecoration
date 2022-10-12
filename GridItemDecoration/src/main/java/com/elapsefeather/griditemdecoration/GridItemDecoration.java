package com.elapsefeather.griditemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = GridItemDecoration.class.getSimpleName();

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    public static final int INSIDEALL = 2;
    public static final int ROUNDALL = 3;

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
        if (orientation != HORIZONTAL && orientation != VERTICAL && orientation != INSIDEALL && orientation != ROUNDALL) {
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
            case ROUNDALL:
            case INSIDEALL:
                drawVertical(c, parent);
                drawHorizontal(c, parent);
                break;
            case VERTICAL:
                drawVertical(c, parent);
                break;
            default:
                drawHorizontal(c, parent);
                break;
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();

        int left;
        int right;

        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();

        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int leftItems = childCount % ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
            if (leftItems == 0) {
                leftItems = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
            }
            childCount -= leftItems;
        }

        if (mOrientation == ROUNDALL) {
            //最上面那條
            View child = parent.getChildAt(0);
            if (child == null) return;

            parent.getDecoratedBoundsWithMargins(child, mBounds);
            int bottom = dividerSize;
            int top = 0;
            canvas.drawRect(left, top, right, bottom, mPaint);
        } else {
            childCount -= 1;
        }

        for (int i = 0; i <= childCount; i++) {
            View child = parent.getChildAt(i);
            if (child == null) return;

            parent.getDecoratedBoundsWithMargins(child, mBounds);
            int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            int top = bottom - dividerSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }

        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();

        int top;
        int bottom;

        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        int childCount = parent.getChildCount();
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            childCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        }

        if (mOrientation == ROUNDALL) {
            //最左邊那條
            View child = parent.getChildAt(0);
            if (child == null) return;

            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            int right = dividerSize;
            int left = 0;
            canvas.drawRect(left, top, right, bottom, mPaint);
        } else {
            childCount -= 1;
        }

        for (int i = 0; i <= childCount - 1; i++) {
            View child = parent.getChildAt(i);
            if (child == null) return;
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            int right = mBounds.right + Math.round(child.getTranslationX());
            int left = right - dividerSize;
            canvas.drawRect(left, top, right, bottom, mPaint);
        }

        canvas.restore();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (dividerSize == 0) {
            outRect.set(0, 0, 0, 0);
        } else if (mOrientation == VERTICAL) {
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
