package com.example.agnes.fischlogger;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

/**
 * Created by Agnes on 16.06.2017.
 */

public class ToggleRadioButton extends android.support.v7.widget.AppCompatRadioButton{
    // damit man RadioButtons auch wieder abwählen kann - sodass innerhalb einer RadioGroup keiner ausgewählt ist
    // (denn diese Situation hat in unserem Formular Bedeutung)

    public ToggleRadioButton(Context c) {
            super(c);
        }

    public ToggleRadioButton(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    public ToggleRadioButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

    @Override
    public void toggle() {
        if(isChecked()) {
            if(getParent() instanceof RadioGroup) {
                // setChecked(false) funktioniert hier nicht...
                ((RadioGroup)getParent()).clearCheck();
            }
        } else {
            setChecked(true);
        }
    }
}
