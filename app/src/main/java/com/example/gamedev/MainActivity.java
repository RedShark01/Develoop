package com.example.gamedev;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gamedev.ui.Account.AccountFragment;
import com.example.gamedev.ui.Lessons.LessonsFragment;
import com.example.gamedev.ui.News.NewsFragment;

public class MainActivity extends AppCompatActivity {

    Menu OptionsMenu;
    Fragment SelectedFragment;
    boolean settingsIsOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsIsOpen = false;

        Toolbar newToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(newToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button btnSettings = (Button) findViewById(R.id.settings_button);

        btnSettings.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!settingsIsOpen) {
                    settingsIsOpen = true;
                    Intent settingsIntent = new Intent(getBaseContext(), Preferences.class);
                    startActivity(settingsIntent);
                }
            }
        });

        Fragment newFragment = new AccountFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment).commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

        if(isFirstRun())
        {
            showFirstRunDialog();
            SetIsFirstRunToFalse();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        settingsIsOpen = false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @SuppressLint("CommitTransaction")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            SelectedFragment = null;

            switch (menuItem.getItemId())
            {
                case R.id.navigation_account:

                    SelectedFragment = new AccountFragment();

                    OptionsMenu.setGroupVisible(R.id.account_menu,true);
                    OptionsMenu.setGroupVisible(R.id.lessons_menu,false);
                    OptionsMenu.setGroupVisible(R.id.news_menu,false);

                    break;

                case R.id.navigation_lessons:

                    SelectedFragment = new LessonsFragment();

                    OptionsMenu.setGroupVisible(R.id.account_menu,false);
                    OptionsMenu.setGroupVisible(R.id.lessons_menu,true);
                    OptionsMenu.setGroupVisible(R.id.news_menu,false);

                    break;

                case R.id.navigation_news:
                    SelectedFragment = new NewsFragment();

                    OptionsMenu.setGroupVisible(R.id.account_menu,false);
                    OptionsMenu.setGroupVisible(R.id.lessons_menu,false);
                    OptionsMenu.setGroupVisible(R.id.news_menu,true);

                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SelectedFragment).commit();
            return true;
        }
    };

    public boolean isFirstRun()
    {
        return getSharedPreferences("PREFERENCES", MODE_PRIVATE).getBoolean("isFirstRun", true);
    }

    public void SetIsFirstRunToFalse()
    {
        getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu,menu);

        OptionsMenu = menu;

        OptionsMenu.setGroupVisible(R.id.account_menu,true);
        OptionsMenu.setGroupVisible(R.id.lessons_menu,false);
        OptionsMenu.setGroupVisible(R.id.news_menu,false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(SelectedFragment == null) {return false;};

        LessonsFragment lessons = (LessonsFragment) SelectedFragment;
        if(lessons != null) { lessons.OnItemSelect(item.getItemId()); return true; }

        AccountFragment account = (AccountFragment) SelectedFragment;
        if(account != null) {return true;}

        NewsFragment news = (NewsFragment) SelectedFragment;
        if(news != null) {return true;}

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public void showFirstRunDialog() {

        final AlertDialog.Builder firstRunDialog = new AlertDialog.Builder(this);
        firstRunDialog.setTitle("Выберите имя аккаунта");

        View v = getLayoutInflater().inflate(R.layout.dialog_firstrun,null);
        final EditText etAccount = (EditText) v.findViewById(R.id.etAccountName);
        firstRunDialog.setView(v);

        firstRunDialog.setPositiveButton("Готово",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which)
                    {
                        TextView accountName = (TextView) findViewById(R.id.accountName);
                        accountName.setText(etAccount.getText());

                        dialog.cancel();
                    }
                });

        firstRunDialog.create();
        firstRunDialog.show();
    }
}