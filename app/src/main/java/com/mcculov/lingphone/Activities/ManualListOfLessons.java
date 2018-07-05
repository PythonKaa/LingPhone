package com.mcculov.lingphone.Activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.mcculov.lingphone.R;
import com.mcculov.lingphone.courses.Course;
import com.mcculov.lingphone.courses.Lesson;
import com.mcculov.lingphone.lib.SourceData;

import java.util.ArrayList;

public class ManualListOfLessons extends ListActivity {

    static final int METHOD_DIALOG_ID = 1;
    public ArrayList<String> lessons = new ArrayList<>();
    private int lessonIndex;
    private String textMethod;
    private String soundMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_list_of_lessons);

        SourceData.getInstance().initializeWithContext(this);

        Course course = SourceData.getInstance().getCourse();
        for (Lesson l : course.lessons) {
            lessons.add(l.getNativeName());
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.playlist_item, lessons);

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();
        // listening to single listitem click
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting listitem index
                lessonIndex = position;
                showDialog(METHOD_DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case METHOD_DIALOG_ID:

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View layout = inflater.inflate(R.layout.manual_set_method_dialog, (ViewGroup) findViewById(R.id.root));

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setView(layout);

                // Now configure the AlertDialog
                builder.setTitle(R.string.method_title);

                textMethod = "S";
                soundMethod = "L";

                RadioGroup rg = (RadioGroup) layout.findViewById(R.id.radioGroup_text);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton_WT:
                                textMethod = "";
                                break;
                            case R.id.radioButton_NT:
                                textMethod = "N";
                                break;
                            case R.id.radioButton_ST:
                                textMethod = "S";
                                break;
                        }
                    }
                });

                rg = (RadioGroup) layout.findViewById(R.id.radioGroup_sound);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton_PA:
                                soundMethod = "LP";
                                break;
                            case R.id.radioButton_PB:
                                soundMethod = "PL";
                                break;
                            case R.id.radioButton_RA:
                                soundMethod = "LRC";
                                break;
                            case R.id.radioButton_RB:
                                soundMethod = "RLC";
                                break;
                        }
                    }
                });

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        ManualListOfLessons.this.removeDialog(METHOD_DIALOG_ID);
                    }
                });

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Starting new intent
                        Intent in = new Intent(getApplicationContext(), PlayActivity.class);
                        // Sending songIndex to PlayerActivity
                        in.putExtra("lessonIndex", lessonIndex);
                        in.putExtra("method", textMethod + soundMethod);
                        startActivity(in);

                        // We forcefully dismiss and remove the Dialog, so it cannot be used again
                        ManualListOfLessons.this.removeDialog(METHOD_DIALOG_ID);
                    }
                });

                // Create the AlertDialog and return it
                AlertDialog methodDialog = builder.create();
                methodDialog.setCancelable(false);
                return methodDialog;
        }
        return null;
    }

    private String checkRadioButton(int checkedButtonID, int radioButtonID, String s) {
        if (checkedButtonID == radioButtonID)
            return s;
        else
            return "";
    }

}
