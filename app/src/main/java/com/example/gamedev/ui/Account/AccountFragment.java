package com.example.gamedev.ui.Account;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gamedev.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class AccountFragment extends Fragment {

    public TextView accountName;
    public TextView accountInfo;
    private SharedPreferences mSettings;
    ImageView accountPhoto;
    Bitmap selectedImage;
    String filePath;
    View v;
    String photo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account, container, false);

        accountName = (TextView) v.findViewById(R.id.accountName);
        accountPhoto = v.findViewById(R.id.profile_image);
        accountInfo = v.findViewById(R.id.tv_info);

        mSettings = PreferenceManager.getDefaultSharedPreferences(v.getContext()); //Objects.requireNonNull(getActivity()).getSharedPreferences("mysettings", MODE_PRIVATE);

        accountPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(Intent.ACTION_PICK);
                photoIntent.setType("image/*");
                startActivityForResult(photoIntent, 1);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mSettings.contains("AccountName")) {

            accountName.setText(mSettings.getString("AccountName", "Account"));
        }

        if (mSettings.contains("AccountPhoto"))
        {
            photo = mSettings.getString("AccountPhoto"," ");
            byte[] imageAsBytes = Base64.decode(photo,Base64.CRLF);
            accountPhoto.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));

        }

        if(mSettings.contains("AccountInfo"))
        {
            accountInfo.setText(mSettings.getString("AccountInfo",""));
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("AccountName", accountName.getText().toString());
        editor.putString("AccountPhoto", photo);
        editor.putString("AccountInfo",accountInfo.getText().toString());
        editor.apply();
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("AccountName", accountName.getText().toString());
        editor.putString("AccountPhoto", photo);
        editor.putString("AccountInfo",accountInfo.getText().toString());
        editor.apply();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Log.d("TAG", String.valueOf(resultCode));

        switch (requestCode) {
            case 1:
                if (resultCode == -1) {
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = requireActivity().getContentResolver().openInputStream(Objects.requireNonNull(imageUri));
                        selectedImage = BitmapFactory.decodeStream(imageStream);
                        accountPhoto.setImageBitmap(selectedImage);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        selectedImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] b = baos.toByteArray();
                        photo = Base64.encodeToString(b, Base64.DEFAULT);

                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putString("AccountPhoto",photo);
                        editor.apply();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
        }
    }
}

