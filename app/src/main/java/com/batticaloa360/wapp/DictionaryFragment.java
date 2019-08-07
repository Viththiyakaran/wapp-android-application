package com.batticaloa360.wapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;


public class DictionaryFragment extends Fragment {



    private String value = "Hello Everyone";
    private FragmentListener listener;
    ArrayAdapter<String> adapter;
    ListView dicList;
    private ArrayList<String> mSource = new ArrayList<String>();


    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dictionary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Button myButton = (Button)view.findViewById(R.id.myBtn);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)
                    listener.onItemClick(value);
            }
        });*/



         dicList = view.findViewById(R.id.dictionaryList);
         adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,mSource);
        dicList.setAdapter(adapter);

        dicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(listener!=null)
                    listener.onItemClick(mSource.get(position));
            }
        });
    }



    public  void resetDataSource(ArrayList<String> source)
    {
        mSource = source;
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,mSource);
        dicList.setAdapter(adapter);

    }
    public  void fiterValue(String value)
    {
          //  adapter.getFilter().filter(value);
            int size = adapter.getCount();
            for(int i = 0; i < size ; i++)
            {
                if(adapter.getItem(i).startsWith(value))
                {
                    dicList.setSelection(i);
                    break;
                }
            }

    }
    /*String[] getListOfWords()
    {
        String[] source = new String[]{
                "a",
                "an",
                "ability",
                "able",
                "about",
                "above",
                "abroad",
                "absent",
                "absolutely",
                "awful",
                "accent",
                "accept",
                "access",
                "accident",
                "accommodation",
                "accompany",
                "according to",
                "account",
                "accountant",
                "accurate",
                "ache"

        };
        return source;
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public  void setOnFragmentListener(FragmentListener listener)
    {
            this.listener = listener;
    }
}
