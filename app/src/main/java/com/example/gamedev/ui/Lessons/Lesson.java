package com.example.gamedev.ui.Lessons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.gamedev.R;

public class Lesson extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        @LayoutRes int LayoutID = intent.getIntExtra("layoutID",0);
        String title = intent.getStringExtra("title");
        setContentView(LayoutID);

        Toolbar tb = findViewById(R.id.tb_basicLessonToolbar);
        setActionBar(tb);

        TextView tv_title = findViewById(R.id.tv_lessonTitle);
        tv_title.setText(title);
    }
}
