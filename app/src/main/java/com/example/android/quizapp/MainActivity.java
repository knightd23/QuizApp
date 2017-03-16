package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by sumit.saurabh on 11/10/16.
 */


public class MainActivity extends AppCompatActivity {

    public My_EditText EText_name;
    public My_EditText EText_age;
    public My_EditText EText_qt5;

    String message = "";
    String person_name = "";
    String question5_answer = "";
    int person_age = 0;
    int genre_type = -1;
    int question1_answer = 0b00000000;
    int question2_answer = 0b00000000;
    int question3_answer = -1;
    int question4_answer = -1;
    int question6_answer = -1;
    int flag_showResult = 0;


    /**
     * @param save
     */
    @Override
    public void onSaveInstanceState(Bundle save) {

        /**Store values*/
        save.putString("person_name", person_name);
        save.putInt("person_age", person_age);
        save.putInt("genre_type", genre_type);
        save.putInt("question1_answer", question1_answer);
        save.putInt("question2_answer", question2_answer);
        save.putInt("question3_answer", question3_answer);
        save.putInt("question4_answer", question4_answer);
        save.putString("question5_answer", question5_answer);
        save.putInt("question6_answer", question6_answer);
        save.putInt("flag_showResult", flag_showResult);

        super.onSaveInstanceState(save);
    }

    /**
     * Restore UI state from the savedInstanceState.
     * This bundle has also been passed to onCreate.
     *
     * @param savedInstanceState
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        person_name = savedInstanceState.getString("person_name");
        question5_answer = savedInstanceState.getString("question5_answer");
        person_age = savedInstanceState.getInt("person_age");
        genre_type = savedInstanceState.getInt("genre_type");
        question1_answer = savedInstanceState.getInt("question1_answer");
        question2_answer = savedInstanceState.getInt("question2_answer");
        question3_answer = savedInstanceState.getInt("question3_answer");
        question4_answer = savedInstanceState.getInt("question4_answer");
        question6_answer = savedInstanceState.getInt("question6_answer");
        flag_showResult = savedInstanceState.getInt("flag_showResult");

        display_content();
        if (flag_showResult == 1)
            calculate_results();

    }


    public void display_content() {
        EditText name_person = (EditText) findViewById(R.id.name_value);
        EditText age_person = (EditText) findViewById(R.id.age_value);
        CheckBox question1_1 = (CheckBox) findViewById(R.id.checkbox_qt1_1);
        CheckBox question1_2 = (CheckBox) findViewById(R.id.checkbox_qt1_2);
        CheckBox question1_3 = (CheckBox) findViewById(R.id.checkbox_qt1_3);
        CheckBox question1_4 = (CheckBox) findViewById(R.id.checkbox_qt1_4);
        CheckBox question2_1 = (CheckBox) findViewById(R.id.checkbox_qt2_1);
        CheckBox question2_2 = (CheckBox) findViewById(R.id.checkbox_qt2_2);
        CheckBox question2_3 = (CheckBox) findViewById(R.id.checkbox_qt2_3);
        CheckBox question2_4 = (CheckBox) findViewById(R.id.checkbox_qt2_4);
        RadioButton question3_1 = (RadioButton) findViewById(R.id.radiobutton_qt3_1);
        RadioButton question3_2 = (RadioButton) findViewById(R.id.radiobutton_qt3_2);
        RadioButton question3_3 = (RadioButton) findViewById(R.id.radiobutton_qt3_3);
        RadioButton question4_1 = (RadioButton) findViewById(R.id.radiobutton_qt4_1);
        RadioButton question4_2 = (RadioButton) findViewById(R.id.radiobutton_qt4_2);
        RadioButton question4_3 = (RadioButton) findViewById(R.id.radiobutton_qt4_3);
        EditText question5_value = (EditText) findViewById(R.id.edittext_qt5_1);
        TextView question5_CorrectAnswer = (TextView) findViewById(R.id.textview_qt5_2);
        RadioButton question6_1 = (RadioButton) findViewById(R.id.radiobutton_qt6_1);
        RadioButton question6_2 = (RadioButton) findViewById(R.id.radiobutton_qt6_2);
        RadioButton question6_3 = (RadioButton) findViewById(R.id.radiobutton_qt6_3);
        RadioGroup RG_genre = (RadioGroup) findViewById(R.id.radiogroup_genre);
        RadioGroup RG_question3 = (RadioGroup) findViewById(R.id.radiogroup_question3);
        RadioGroup RG_question4 = (RadioGroup) findViewById(R.id.radiogroup_question4);
        RadioGroup RG_question6 = (RadioGroup) findViewById(R.id.radiogroup_question6);


        /*Display the values before the rotation*/
        display_edittext(name_person, person_name, getString(R.string.name_value));
        name_person.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        display_edittext(age_person, Integer.toString(person_age), getString(R.string.age_value));
        age_person.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        display_genretype(genre_type, RG_genre);
        display_checkboxes(question1_answer, question1_1, question1_2, question1_3, question1_4);
        display_checkboxes(question2_answer, question2_1, question2_2, question2_3, question2_4);
        display_radiogroup_3(question3_answer,RG_question3, question3_1, question3_2, question3_3);
        display_radiogroup_3(question4_answer, RG_question4, question4_1, question4_2, question4_3);
        display_edittext(question5_value, question5_answer, getString(R.string.name_value));
        question5_CorrectAnswer.setText("");
        question5_value.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent), PorterDuff.Mode.SRC_ATOP);
        question5_value.getBackground().setColorFilter(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        question5_CorrectAnswer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        display_radiogroup_3(question6_answer, RG_question6, question6_1, question6_2, question6_3);

    }

    private void display_genretype(int value, RadioGroup RG_value) {
        RG_value.clearCheck();
        RadioButton genre_m = (RadioButton) findViewById(R.id.genre_male);
        RadioButton genre_f = (RadioButton) findViewById(R.id.genre_female);

        if (value != -1) {
            switch (value) {
                case 1:
                    genre_m.setChecked(true);
                    Log.i("display_genretype", "Male selected");
                    break;
                case 2:
                    genre_f.setChecked(true);
                    Log.i("display_genretype", "Female selected");
                    break;
            }
        }
    }

    private void display_radiogroup_3(int value, RadioGroup RG_value, RadioButton rbt_1, RadioButton rbt_2, RadioButton rbt_3) {

        RG_value.clearCheck();
        rbt_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        rbt_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        rbt_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        if (value != -1) {
            switch (value) {
                case 1:
                    rbt_1.setChecked(true);
                    Log.i("display_radiogroup_3", "Option 1 selected");
                    break;
                case 2:
                    rbt_2.setChecked(true);
                    Log.i("display_radiogroup_3", "Option 2 selected");
                    break;
                case 3:
                    rbt_3.setChecked(true);
                    Log.i("display_radiogroup_3", "Option 3 selected");
                    break;
            }
        }
    }

    private void display_edittext(EditText Edt_view, String text_data, String hint_data) {

        if (text_data.length() <= 0 || text_data.equals("-1")) {
            Edt_view.setHint(hint_data);
            Edt_view.setText("");
        } else
            Edt_view.setText(text_data);
    }


    private void display_checkboxes(int checkbox_value, CheckBox chb_1, CheckBox chb_2, CheckBox chb_3, CheckBox chb_4) {
        /*Guarantee the the checkboxes are cleared*/
        chb_1.setChecked(false);
        chb_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        chb_2.setChecked(false);
        chb_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        chb_3.setChecked(false);
        chb_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
        chb_4.setChecked(false);
        chb_4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        if ((checkbox_value & 0b00000001) == 0b1)
            chb_1.setChecked(true);
        if ((checkbox_value & 0b00000010) == 0b10)
            chb_2.setChecked(true);
        if ((checkbox_value & 0b00000100) == 0b100)
            chb_3.setChecked(true);
        if ((checkbox_value & 0b00001000) == 0b1000)
            chb_4.setChecked(true);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        EText_name = (EditText) findViewById(R.id.name_value);
        EText_name = (My_EditText) findViewById(R.id.name_value);
        EText_name.setOnFocusChangeListener(Edt_onFocusChange);
//        EditText_init(EText_name, person_name);

        EText_age = (My_EditText) findViewById(R.id.age_value);
        EText_name.setOnFocusChangeListener(Edt_onFocusChange);
//        Log.i("OnCreate", "" + EText_age.getString_value());

        EText_qt5 = (My_EditText) findViewById(R.id.edittext_qt5_1);
        EText_name.setOnFocusChangeListener(Edt_onFocusChange);
//        Log.i("OnCreate", "" + EText_qt5.getString_value());

    }

    private View.OnFocusChangeListener Edt_onFocusChange =  new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            Log.i("onFocusChange", "Inside");
            if (!hasFocus) {
                Log.i("onFocusChange", "Lost focus");
                person_name = EText_name.getString_value();
                person_age = EText_age.getInt_value();
                question5_answer = EText_qt5.getString_value();
            }
        }
    };


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.i("onConfigurationChanged", "Inside");

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
            Log.i("onConfigurationChanged", "Keyboard hidden");
            EText_name.clearFocus();
            EText_age.clearFocus();
            EText_qt5.clearFocus();
        } else if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
            Log.i("onConfigurationChanged", "Keyboard not hidden");
        }

        if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
            Log.i("onConfigurationChanged", "Keyboard not hidden");
        } else if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_YES) {
            Log.i("onConfigurationChanged", "Keyboard hidden");
            EText_name.clearFocus();
            EText_age.clearFocus();
            EText_qt5.clearFocus();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom()) {
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);

                EText_name.clearFocus();
                EText_age.clearFocus();
                EText_qt5.clearFocus();
            }
        }

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

//            Log.i("dispatchTouchEvent", "Save values");
            person_name = EText_name.getString_value();
            person_age = EText_age.getInt_value();
            question5_answer = EText_qt5.getString_value();

//            Log.i("dispatchTouchEvent", "person_name: " + person_name);
//            Log.i("dispatchTouchEvent", "person_age: " + person_age);
//            Log.i("dispatchTouchEvent", "question5_answer: " + question5_answer);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void show_result(View view) {
        //show a toast with the number of correct questions
        message = calculate_results();
        flag_showResult = 1;

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        LinearLayout layout = (LinearLayout) toast.getView();
        if (layout.getChildCount() > 0) {
            TextView tv = (TextView) layout.getChildAt(0);
            tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        }
        toast.show();

    }

    public void genre_type(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.genre_male:
                if (checked)
                    genre_type = 1;
                break;
            case R.id.genre_female:
                if (checked)
                    genre_type = 2;
                break;
            default:
                genre_type = -1;
        }
    }

    public void checkbox_qt1_1(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt1_1);
        if (ch.isChecked()) {
            question1_answer |= 0b00000001;
        } else {
            question1_answer &= 0b11111110;
        }

    }

    public void checkbox_qt1_2(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt1_2);
        if (ch.isChecked()) {
            question1_answer |= 0b00000010;
        } else {
            question1_answer &= 0b11111101;
        }
    }

    public void checkbox_qt1_3(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt1_3);
        if (ch.isChecked()) {
            question1_answer |= 0b00000100;
        } else {
            question1_answer &= 0b11111011;
        }
    }

    public void checkbox_qt1_4(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt1_4);
        if (ch.isChecked()) {
            question1_answer |= 0b00001000;
        } else {
            question1_answer &= 0b11110111;
        }

    }

    public void checkbox_qt2_1(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt2_1);
        if (ch.isChecked()) {
            question2_answer |= 0b00000001;
        } else {
            question2_answer &= 0b11111110;
        }
    }

    public void checkbox_qt2_2(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt2_2);
        if (ch.isChecked()) {
            question2_answer |= 0b00000010;
        } else {
            question2_answer &= 0b11111101;
        }
    }

    public void checkbox_qt2_3(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt2_3);
        if (ch.isChecked()) {
            question2_answer |= 0b00000100;
        } else {
            question2_answer &= 0b11111011;
        }
    }

    public void checkbox_qt2_4(View view) {
        CheckBox ch = (CheckBox) findViewById(R.id.checkbox_qt2_4);
        if (ch.isChecked()) {
            question2_answer |= 0b00001000;
        } else {
            question2_answer &= 0b11110111;
        }
    }

    public void radiobutton_qt3(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radiobutton_qt3_1:
                if (checked)
                    question3_answer = 1;
                break;
            case R.id.radiobutton_qt3_2:
                if (checked)
                    question3_answer = 2;
                break;
            case R.id.radiobutton_qt3_3:
                if (checked)
                    question3_answer = 3;
                break;
            default:
                question3_answer = -1;
        }
    }

    public void radiobutton_qt4(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radiobutton_qt4_1:
                if (checked)
                    question4_answer = 1;
                break;
            case R.id.radiobutton_qt4_2:
                if (checked)
                    question4_answer = 2;
                break;
            case R.id.radiobutton_qt4_3:
                if (checked)
                    question4_answer = 3;
                break;
            default:
                question4_answer = -1;
                break;
        }
    }

    public void radiobutton_qt6(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radiobutton_qt6_1:
                if (checked)
                    question6_answer = 1;
                break;
            case R.id.radiobutton_qt6_2:
                if (checked)
                    question6_answer = 2;
                break;
            case R.id.radiobutton_qt6_3:
                if (checked)
                    question6_answer = 3;
                break;
            default:
                question6_answer = -1;
                break;
        }
    }


    public String calculate_results() {

        String message = "";
        CheckBox question1_1 = (CheckBox) findViewById(R.id.checkbox_qt1_1);
        CheckBox question1_2 = (CheckBox) findViewById(R.id.checkbox_qt1_2);
        CheckBox question1_3 = (CheckBox) findViewById(R.id.checkbox_qt1_3);
        CheckBox question1_4 = (CheckBox) findViewById(R.id.checkbox_qt1_4);
        CheckBox question2_1 = (CheckBox) findViewById(R.id.checkbox_qt2_1);
        CheckBox question2_2 = (CheckBox) findViewById(R.id.checkbox_qt2_2);
        CheckBox question2_3 = (CheckBox) findViewById(R.id.checkbox_qt2_3);
        CheckBox question2_4 = (CheckBox) findViewById(R.id.checkbox_qt2_4);
        RadioButton question3_1 = (RadioButton) findViewById(R.id.radiobutton_qt3_1);
        RadioButton question3_2 = (RadioButton) findViewById(R.id.radiobutton_qt3_2);
        RadioButton question3_3 = (RadioButton) findViewById(R.id.radiobutton_qt3_3);
        RadioButton question4_1 = (RadioButton) findViewById(R.id.radiobutton_qt4_1);
        RadioButton question4_2 = (RadioButton) findViewById(R.id.radiobutton_qt4_2);
        RadioButton question4_3 = (RadioButton) findViewById(R.id.radiobutton_qt4_3);
        EditText question5_value = (EditText) findViewById(R.id.edittext_qt5_1);
        TextView question5_CorrectAnswer = (TextView) findViewById(R.id.textview_qt5_2);
        String text = question5_value.getText().toString();
        RadioButton question6_1 = (RadioButton) findViewById(R.id.radiobutton_qt6_1);
        RadioButton question6_2 = (RadioButton) findViewById(R.id.radiobutton_qt6_2);
        RadioButton question6_3 = (RadioButton) findViewById(R.id.radiobutton_qt6_3);


        if (person_name == null || person_age <= 0 || person_age >= 150 || genre_type == -1) {
            if (question5_answer == null || question1_answer <= 0 || question2_answer <= 0 || question3_answer <= 0 || question4_answer <= 0 || question6_answer <= 0) {
                message = getString(R.string.toast_all_field);
            } else {
                if (person_age <= 0 || person_age >= 150)
                    message = getString(R.string.toast_personalinfo_correct);
                else
                    message = getString(R.string.toast_personalinfo_complete);
            }
        } else {
            if (question5_answer == null || question1_answer <= 0 || question2_answer <= 0 || question3_answer <= 0 || question4_answer <= 0 || question6_answer <= 0) {
                message = getString(R.string.toast_questionfileds);
            } else {
                if (question1_answer == 0b00001001) {
                    message += getString(R.string.toast_question1_correct);
                    question1_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question1_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question1_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question1_4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                } else {
                    message += getString(R.string.toast_question1_incorrect);

                    question1_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question1_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question1_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question1_4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));

                }
                if (question2_answer == 0b00000011) {
                    message += getString(R.string.toast_question2_correct);
                    question2_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question2_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question2_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question2_4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                } else {
                    message += getString(R.string.toast_question2_incorrect);

                    question2_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question2_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question2_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question2_4.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                }
                if (question3_answer == 3) {
                    message += getString(R.string.toast_question3_correct);

                    question3_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question3_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question3_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                } else {
                    message += getString(R.string.toast_question3_incorrect);

                    question3_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question3_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question3_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                }
                if (question4_answer == 2) {
                    message += getString(R.string.toast_question4_correct);

                    question4_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question4_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question4_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

                } else {
                    message += getString(R.string.toast_question4_incorrect);

                    question4_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question4_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question4_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                }

                if (text.equalsIgnoreCase(getString(R.string.question5_textview_value))) {
                    message += getString(R.string.toast_question5_correct);
                    question5_CorrectAnswer.setText("");
                    question5_CorrectAnswer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));

                } else {
                    message += getString(R.string.toast_question5_incorrect);
                    question5_CorrectAnswer.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question5_CorrectAnswer.setText(getString(R.string.question5_textview_value));
                }
                if (question6_answer == 1) {
                    message += getString(R.string.toast_question6_correct);
                    question6_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question6_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                    question6_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));
                } else {
                    message += getString(R.string.toast_question6_incorrect);
                    question6_1.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_1));
                    question6_2.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                    question6_3.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBackground_2));
                }
            }
        }

        return message;

    }

    public void reset_result(View view) {

        person_name = "";
        question5_answer = "";
        message="";
        person_age = -1;
        genre_type = -1;
        question1_answer = 0b00000000;
        question2_answer = 0b00000000;
        question3_answer = -1;
        question4_answer = -1;
        question6_answer = -1;
        flag_showResult = 0;

        display_content();
    }

    public void submitResult(View view) {

        if(message.isEmpty()) {
            Toast toast = Toast.makeText(this, R.string.toast_send_message_wrong, Toast.LENGTH_SHORT);
            LinearLayout layout = (LinearLayout) toast.getView();
            if (layout.getChildCount() > 0) {
                TextView tv = (TextView) layout.getChildAt(0);
                tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            }
            toast.show();
        }else{
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.send_summary_email_subject, person_name));
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

    }
}
