package com.app.tarsus.projetozetaapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

final class SheetCards extends ImageView {
    public SheetCards(Context context) {
        super(context);
    }

    public SheetCards(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SheetCards(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SheetCards(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int bothDimensionsSpec = widthMeasureSpec;
        super.onMeasure(bothDimensionsSpec, bothDimensionsSpec);
    }
}
