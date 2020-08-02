package com.example.gamedev.ui.News;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.gamedev.R;

public class PopularNews extends Fragment
{
    Fragment SelectedFragment;
    public View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.news_popular_fragment,container,false);

        final Spinner timeType = (Spinner) v.findViewById(R.id.time_type);

        Bundle args = new Bundle();
        args.putInt("LayoutID", 1);

        Fragment startFragment = new NewsList();
        startFragment.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.fragment_container_popularNews, startFragment).commit();


        timeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Bundle args = new Bundle();
                SelectedFragment = new NewsList();

                switch (timeType.getItemAtPosition(position).toString())
                {
                    case "Сутки": args.putInt("LayoutID", 1); break;
                    case "Неделя": args.putInt("LayoutID", 2); break;
                    case "Месяц": args.putInt("LayoutID", 3); break;
                    case "Год": args.putInt("LayoutID", 4); break;
                }

                SelectedFragment.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_popularNews, SelectedFragment).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                return;
            }
        });

        return v;
    }



}
