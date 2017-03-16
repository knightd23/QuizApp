package com.example.android.quizapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_WORDS;

/**
 * Created by tsf on 14/03/2017.
 */

public class My_EditText extends EditText {

    private String valueString = "";
    private int valueInt = -1;

    public My_EditText(Context context) {
        super(context);
    }

    public My_EditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public My_EditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public My_EditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /*Overrrides functions*/
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (getInputType() == TYPE_CLASS_NUMBER) {
            try {
                if (Integer.parseInt(getText().toString()) >= 0)
                    valueInt = Integer.parseInt(getText().toString());
                else
                    valueInt = -1;
            } catch (Exception n) {
//                Log.i("InputTypeonTextChanged", n.getMessage());
                valueInt = -1;
            }
        } else if (getInputType() == (TYPE_TEXT_FLAG_CAP_WORDS + 1)) {
            if (getText().length() <= 0)
                valueString = "";
            else
                valueString = text.toString();
//            Log.i("Edit name onTextChanged", "" + valueString);
        } else {
//            Log.i("InputTypeonTextChanged", "Input Type: OTHER");
            valueString = "";
//            Log.i("Edit name onTextChanged", "" + valueString);
        }
    }


    @Override
    public void onEditorAction(int actionCode) {
        super.onEditorAction(actionCode);

        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            Log.i("EditorActionListener", "Enter pressed");
            clearFocus();
        }
        if (actionCode == EditorInfo.IME_ACTION_PREVIOUS) {
            Log.i("EditorActionListener", "Return pressed");
            clearFocus();
        }
    }

    /*Getters and Setters functions*/
    public String getString_value() {
        return valueString;
    }

    public int getInt_value() {
        return valueInt;
    }

}
