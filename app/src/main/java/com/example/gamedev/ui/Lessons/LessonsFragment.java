package com.example.gamedev.ui.Lessons;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gamedev.R;

public class LessonsFragment extends Fragment  {

    Fragment selectedFragment;
    TextView lessonsTypeText;
    @LayoutRes int LayoutId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_lessons,container,false);
        lessonsTypeText = v.findViewById(R.id.lessontype_text);

        BottomNavigationView LessonsView = v.findViewById(R.id.lessonsMenu);
        LessonsView.setOnNavigationItemSelectedListener(navListener);

        LayoutId = R.layout.lessons_basic_prog;

        Bundle args = new Bundle();
        args.putInt("Layout", LayoutId);

        Fragment startFragment = new BasicLessons();
        startFragment.setArguments(args);
        lessonsTypeText.setText("Программирование");

        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.lessonsFragment, startFragment).commit();

        return v;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            selectedFragment = null;

            switch (menuItem.getItemId())
            {
                case R.id.navigation_basicLessons:

                    Bundle args = new Bundle();
                    args.putInt("Layout", LayoutId);
                    selectedFragment = new BasicLessons();
                    selectedFragment.setArguments(args);

                    lessonsTypeText.setVisibility(View.VISIBLE);

                    break;

                case R.id.navigation_speciallessons:
                    selectedFragment = new SpecialLessons();
                    lessonsTypeText.setVisibility(View.INVISIBLE);
                    break;
            }

            getFragmentManager().beginTransaction().replace(R.id.lessonsFragment, selectedFragment).commit();
            return true;
        }
    };

    public void OnItemSelect(int ItemId)
    {
        switch (ItemId)
        {
            case R.id.lessonstype_prog:

                LayoutId = R.layout.lessons_basic_prog;

                Bundle args = new Bundle();
                args.putInt("Layout", LayoutId);
                selectedFragment = new BasicLessons();
                selectedFragment.setArguments(args);

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.lessonsFragment, selectedFragment).commit();
                lessonsTypeText.setText("Программирование");
                break;

            case R.id.lessonstype_game:
                LayoutId = R.layout.lessons_basic_game;

                Bundle args1 = new Bundle();
                args1.putInt("Layout", LayoutId);
                selectedFragment = new BasicLessons();
                selectedFragment.setArguments(args1);

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.lessonsFragment, selectedFragment).commit();
                lessonsTypeText.setText("Игровые движки");
                break;

            case R.id.lessonstype_3d:

                LayoutId = R.layout.lessons_basic_3d;

                Bundle args2 = new Bundle();
                args2.putInt("Layout", LayoutId);
                selectedFragment = new BasicLessons();
                selectedFragment.setArguments(args2);

                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().replace(R.id.lessonsFragment, selectedFragment).commit();
                lessonsTypeText.setText("Моделлирование");
                break;
        }
    }
}
