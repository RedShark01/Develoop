package com.example.gamedev.ui.News;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gamedev.R;


public class NewsFragment extends Fragment {

    public boolean isNewNews = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_news,container,false);

        Button btnNewNews = (Button) v.findViewById(R.id.btn_new_news);
        Button btnPopularNews = (Button) v.findViewById(R.id.btn_popular_news);

        Fragment newsFragment = new NewNews();
        getFragmentManager().beginTransaction().replace(R.id.news_fragmentContainer,newsFragment).commit();
        isNewNews = true;

        btnNewNews.setOnClickListener(oclBtnNewNews);
        btnPopularNews.setOnClickListener(oclBtnPopularNews);

        return v;

    }

    View.OnClickListener oclBtnNewNews = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if((getFragmentManager() != null) && !isNewNews )
            {
                Fragment newsFragment = new NewNews();
                getFragmentManager().beginTransaction().replace(R.id.news_fragmentContainer,newsFragment).commit();
                isNewNews = true;
            }
        }
    };

    View.OnClickListener oclBtnPopularNews = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if((getFragmentManager() != null) && isNewNews) {
                Fragment popNewsFragment = new PopularNews();
                getFragmentManager().beginTransaction().replace(R.id.news_fragmentContainer, popNewsFragment).commit();
                isNewNews = false;
            }
        }
    };


}
