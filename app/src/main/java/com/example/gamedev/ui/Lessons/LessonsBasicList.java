package com.example.gamedev.ui.Lessons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.ArrayRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gamedev.R;

import java.util.ArrayList;


public class LessonsBasicList extends Activity
{
    private int currentID = 0;
    final private int UE4ID = 1;
    final private int U3D = 2;
    final private int CPP = 3;
    final private int PYTHON = 4;

    ListView lessons;
    TextView title;

    private ArrayAdapter<String> titleAdapter;
    public ArrayList<String> titleList = new ArrayList<String>();

    @LayoutRes int layoutID;
    String titleStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        currentID = intent.getIntExtra("lessonID", 0);

        switch (currentID)
        {
            case UE4ID:
                setContentView(R.layout.lessons_list_ue4);

                Button btn_ue4_1_1 = (Button) findViewById(R.id.ue4_1_1);
                btn_ue4_1_1.setOnClickListener(onclc);

                Button btn_ue4_1_2 = (Button) findViewById(R.id.ue4_1_2);
                btn_ue4_1_2.setOnClickListener(onclc);

                Button btn_ue4_1_3 = (Button) findViewById(R.id.ue4_1_3);
                btn_ue4_1_3.setOnClickListener(onclc);

            break;
            case PYTHON:
                setContentView(R.layout.lessons_list_phyton);

            break;
            case CPP:
                setContentView(R.layout.lessons_list_cpp);

            break;

        }
    }

    public Button.OnClickListener onclc = new Button.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(v.getContext(), Lesson.class);
            Button btn = (Button) v;
            titleStr = btn.getText().toString();

            switch(v.getId())
            {
                case R.id.ue4_1_1:
                    layoutID = R.layout.lesson_ue4_1_1; break;

                case R.id.ue4_1_2:
                    layoutID = R.layout.lesson_ue4_1_2; break;

                case R.id.ue4_1_3:
                    layoutID = R.layout.lesson_ue4_1_3; break;
            }

            intent.putExtra("layoutID",layoutID);
            intent.putExtra("title",titleStr);
            startActivity(intent);
        }
    };

}
