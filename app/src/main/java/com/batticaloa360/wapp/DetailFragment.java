package com.batticaloa360.wapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class DetailFragment extends Fragment {


    private  String value ="";
    private TextView tvWord;
    private ImageButton btnVolume,btnBookmark;
    private WebView tvWordTranslate;

    private DBHelper mDBHelper;
    private int mDicType;

    public DetailFragment() {
        // Required empty public constructor
    }



    public static DetailFragment getNewinstance(String value,DBHelper dbHelper, int dicType) {
        DetailFragment fragment = new DetailFragment();
        fragment.value = value;
        fragment.mDBHelper = dbHelper;
        fragment.mDicType = dicType;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvWord = (TextView)view.findViewById(R.id.tvWord);
        btnBookmark = (ImageButton) view.findViewById(R.id.btnBookmark);
        btnVolume = (ImageButton) view.findViewById(R.id.btnVolume);
        tvWordTranslate = (WebView) view.findViewById(R.id.tvWordTranslate);

       final Word word =  mDBHelper.getWord(value,mDicType);
        tvWord.setText(word.key);
        tvWordTranslate.loadDataWithBaseURL("",word.value,"text/html","utf-8",null);


      Word bookMarkWord = mDBHelper.getWordFromBookmark(value);
      int isMark = bookMarkWord == null? 0:1;
      btnBookmark.setTag(isMark);

      int icon = bookMarkWord == null ? R.drawable.ic_bookmark_border : R.drawable.ic_bookmark_fill;
      btnBookmark.setImageResource(icon);

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             int i =   (int)btnBookmark.getTag();

             if(i==0)
             {
                 btnBookmark.setImageResource(R.drawable.ic_bookmark_fill);
                 btnBookmark.setTag(1);
                 mDBHelper.addBookmark(word);
             }
             else
             {
                 btnBookmark.setImageResource(R.drawable.ic_bookmark_border);
                 btnBookmark.setTag(0);
                 mDBHelper.removeBookmark(word);
             }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
