package com.example.gamedev.ui.News;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.gamedev.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsList extends Fragment {

    String urlMain;
    ListView lv;
    ProgressBar pb;

    View v;
    View defItem;

    public Elements content;
    public Elements timeContent;
    private ArrayAdapter<String> titleAdapter;

    public ArrayList<String> Urls = new ArrayList<String>();
    public ArrayList<String> titleList = new ArrayList<String>();
    public ArrayList<String> timeList = new ArrayList<String>();

    @LayoutRes int LayoutID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        assert getArguments() != null;
        LayoutID = getArguments().getInt("LayoutID");
        v = inflater.inflate(R.layout.news_popular_day, container,false);

        lv = v.findViewById(R.id.news_popular_week_list);
        pb = v.findViewById(R.id.news_popular_pb_week);

        switch (LayoutID) {

            case 1:
                urlMain = "https://habr.com/ru/top/";
                break;

            case 2:
                urlMain = "https://habr.com/ru/top/weekly/";
                break;

            case 3:
                urlMain = "https://habr.com/ru/top/monthly/";
                break;

            case 4:
                urlMain = "https://habr.com/ru/top/yearly/";
                break;

        }

        new PopularNewsThread().execute();
        titleAdapter = new ArrayAdapter<String>(getActivity(), R.layout.news_list_item, R.id.pro_item, titleList);
        defItem = inflater.inflate(R.layout.news_list_item,null);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String url = Urls.get(position);

                if((url != null) && (!url.equals("")))
                {
                    Intent intent = new Intent(getContext(),NewsScreen.class);
                    intent.putExtra("urlName", url);
                    startActivity(intent);
                }
                else
                {
                    Log.d("TAG", "onItemClick: ITS NOT FUCKING WORK");
                }
            }
        });


        return v;
    }

    public class PopularNewsThread extends AsyncTask<String, Void, String> {
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {

            try {
                String url = urlMain;
                Document doc = Jsoup.connect(url).get();
                content = doc.select(".post__title_link");
                timeContent = doc.select(".post__time");

                titleList.clear();

                for (Element contents : content)
                {
                    titleList.add(contents.text());
                    Urls.add(contents.absUrl("href"));
                }

                pb.setVisibility(View.INVISIBLE);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

           lv.setAdapter(titleAdapter);

        }
    }
}
