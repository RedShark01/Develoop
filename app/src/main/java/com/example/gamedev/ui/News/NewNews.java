package com.example.gamedev.ui.News;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class NewNews extends Fragment {

    public Elements content;
    private ArrayAdapter<String> titleAdapter;

    public ArrayList<String> Urls = new ArrayList<String>();
    public ArrayList<String> titleList = new ArrayList<String>();

    private ListView lv;
    ProgressBar pb;
    public View v;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.news_new_fragment, container, false);

        lv = v.findViewById(R.id.habr_NewsList);
        pb = (ProgressBar) v.findViewById(R.id.news_pb);

        new NewThread().execute();
        titleAdapter = new ArrayAdapter<String>(v.getContext(), R.layout.news_list_item, R.id.pro_item, titleList);

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

    public class NewThread extends AsyncTask<String, Void, String> {
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {

            try {

                Document doc = Jsoup.connect("https://habr.com/ru/all/").get();
                content = doc.select(".post__title_link");
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
