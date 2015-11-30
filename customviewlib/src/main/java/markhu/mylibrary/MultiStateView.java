package markhu.mylibrary;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class MultiStateView extends View implements View.OnClickListener {

    private static final String TAG = "MultiStateView";

    private static final int DEFAULT_NUM_STATES = 2;

    private static final int MAX_NUM_STATES = 0xFF;

    private int mNumStates;
    private int mCurrentState;
    private int mStateDiv;

    public MultiStateView(Context context){
        super(context);
        init(null);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MultiStateView(Context context, AttributeSet attrs, int defStyleAttr, int mNumStates) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if(isInEditMode()){
            return;
        }
        mNumStates = getAttributeNum(attrs);
        if(mNumStates > MAX_NUM_STATES){
            mNumStates = MAX_NUM_STATES;
        }
        setOnClickListener(this);
        mCurrentState = 0;
        setStateColor();
        mStateDiv = MAX_NUM_STATES / mNumStates;
    }

    @Override
    public void onClick(View v) {
        mCurrentState += mStateDiv;

        if(mCurrentState == (mStateDiv*mNumStates)){
            mCurrentState = 0;
        }
        setStateColor();
    }

    private void setStateColor(){
        int colorState = MAX_NUM_STATES - mCurrentState;
        setBackgroundColor(Color.rgb(colorState, colorState, colorState));
    }

    private int getAttributeNum(AttributeSet attrs){
        int numAttributes = attrs.getAttributeCount();
        for (int i = 0; i < numAttributes; i++){
            if(attrs.getAttributeNameResource(i) == R.attr.numStates){
                return attrs.getAttributeIntValue(i,DEFAULT_NUM_STATES);
            }
        }
        return DEFAULT_NUM_STATES;
    }
}
