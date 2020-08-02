package com.example.gamedev.ui.Lessons;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gamedev.R;

public class BasicLessons extends Fragment {

    final private int UE4ID = 1;
    final private int U3D = 2;
    final private int CPP = 3;
    final private int PYTHON = 4;

    @LayoutRes int LayoutId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        LayoutId = getArguments().getInt("Layout");

        View v = inflater.inflate(LayoutId, container,false);
        Log.d("tag", String.valueOf(LayoutId));

        if(LayoutId == R.layout.lessons_basic_game)
        {
            Button btnUE4 = v.findViewById(R.id.btn_lessons_ue4);
            btnUE4.setOnClickListener(onclc);
        }

        if(LayoutId == R.layout.lessons_basic_prog)
        {
            Button btnPython = v.findViewById(R.id.btn_lessons_python);
            Button btnCpp = v.findViewById(R.id.btn_lessons_cpp);

            btnPython.setOnClickListener(onclc);
            btnCpp.setOnClickListener(onclc);
        }

        return v;

    }

    public Button.OnClickListener onclc = new Button.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            Intent intent = new Intent(getContext(), LessonsBasicList.class);

            switch (v.getId())
            {
                case R.id.btn_lessons_ue4: intent.putExtra("lessonID", UE4ID); break;
                case R.id.btn_lessons_python: intent.putExtra("lessonID",PYTHON); break;
                case R.id.btn_lessons_cpp: intent.putExtra("lessonID", CPP); break;
            }
            startActivity(intent);
        }
    };
}