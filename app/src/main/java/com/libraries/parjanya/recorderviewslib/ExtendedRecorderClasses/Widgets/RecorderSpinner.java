package com.libraries.parjanya.recorderviewslib.ExtendedRecorderClasses.Widgets;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.libraries.parjanya.recorderviewslib.Constants;
import com.libraries.parjanya.recorderviewslib.RecorderEvents.SpinnerItemSelectedEvent;
import com.libraries.parjanya.recorderviewslib.Utils.Utils;
import com.libraries.parjanya.recorderviewslib.XMLHandler.XMLCreator;

/**
 * Created by parjanya on 10/3/18.
 */

public class RecorderSpinner extends AppCompatSpinner implements ParentRecorderView {
    XMLCreator xmlCreator;
    String viewId;
    boolean userTouched = false;

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public String getViewId() {
        return viewId;
    }

    @Override
    public void setOnItemSelectedListener(final OnItemSelectedListener onItemSelectedListener) {
        super.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (userTouched) {
                    ListView parentListView = Utils.getParentListView(RecorderSpinner.this);
                    RecyclerView parentRecyclerView = Utils.getParentRecyclerView(RecorderSpinner.this);
                    View listItemParentView = Utils.getParentListItemView(RecorderSpinner.this);
                    View recyclerItemView = Utils.getParentRecyclerItemView(RecorderSpinner.this);
                    int listViewItemId = Constants.NO_LIST_VIEW;
                    String parentId = Constants.NO_ID;

                    if (parentListView != null) {
                        parentId = Utils.getViewIdStringFromView(parentListView);
                        listViewItemId = parentListView.getPositionForView(listItemParentView);
                    }
                    else if (parentRecyclerView != null) {
                        parentId = Utils.getViewIdStringFromView(parentRecyclerView);
                        listViewItemId = parentRecyclerView.getLayoutManager().getPosition(recyclerItemView);
                    }

                    SpinnerItemSelectedEvent spinnerItemSelectedEvent = new SpinnerItemSelectedEvent(viewId,
                            Constants.SPINNER_EVENT_TYPE_ATTRIBUTE, parentId, listViewItemId, xmlCreator, i);
                    spinnerItemSelectedEvent.saveEvent();
                    userTouched = false;
                }
                onItemSelectedListener.onItemSelected(adapterView, view, i, l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                onItemSelectedListener.onNothingSelected(adapterView);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        userTouched = true;
        return super.dispatchTouchEvent(ev);
    }

    public RecorderSpinner(Context context) {
        super(context);
        xmlCreator = new XMLCreator(context);
        viewId = Utils.getViewIdStringFromView(this);
    }
    public RecorderSpinner(Context context, int mode) {
        super(context, mode);
        xmlCreator = new XMLCreator(context);
        viewId = Utils.getViewIdStringFromView(this);
    }
    public RecorderSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        xmlCreator = new XMLCreator(context);
        viewId = Utils.getViewIdStringFromView(this);
    }
    public RecorderSpinner(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        xmlCreator = new XMLCreator(context);
        viewId = Utils.getViewIdStringFromView(this);
    }
    public RecorderSpinner(Context context, AttributeSet attributeSet, int defStyleAttr, int mode) {
        super(context, attributeSet, defStyleAttr, mode);
        xmlCreator = new XMLCreator(context);
        viewId = Utils.getViewIdStringFromView(this);
    }
    public RecorderSpinner(Context context, AttributeSet attributeSet, int defStyleAttr, int mode, Resources.Theme theme) {
        super(context, attributeSet, defStyleAttr, mode, theme);
        xmlCreator = new XMLCreator(context);
        viewId = Utils.getViewIdStringFromView(this);
    }
}
