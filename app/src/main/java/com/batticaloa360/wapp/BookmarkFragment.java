package com.batticaloa360.wapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class BookmarkFragment extends Fragment {


    private  FragmentListener listener;
 private  DBHelper mDBHelper;
 private BookmarkAdapter adapter;

    public BookmarkFragment() {
        // Required empty public constructor
    }

public  static BookmarkFragment getNewInstance(DBHelper dbHelper)
{
    BookmarkFragment fragment = new BookmarkFragment();
    fragment.mDBHelper = dbHelper;
    return  fragment;
}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull  View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       setHasOptionsMenu(true);


        ListView bookmarklist = (ListView) view.findViewById(R.id.bookmarklist);
        adapter  = new BookmarkAdapter(getActivity(),mDBHelper.getAllWordFromBookmark());
        bookmarklist.setAdapter(adapter);

        adapter.setOnItemClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
                if(listener!=null)
                    listener.onItemClick(String.valueOf(adapter.getItem(position)));
            }
        });

        adapter.setOnItemDeleteClick(new ListItemListener() {
            @Override
            public void onItemClick(int position) {
                String key = (String) adapter.getItem(position);
                mDBHelper.removeBookmark(key);
              adapter.removeItem(position);
              adapter.notifyDataSetChanged();
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

    public  void setOnFragmentListener(FragmentListener listener)
    {
        this.listener = listener;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_clear,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_clear)
        {
            mDBHelper.clearBookmark();
            adapter.clear();
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }
}
