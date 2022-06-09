package com.example.timetablemanager;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class Fragment2 extends Fragment {

    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> notesList;
    DatabaseClass databaseClass;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_2, container, false);

        recyclerView = view.findViewById(R.id.recycler_view2);
        notesList = new ArrayList<>();
        databaseClass = new DatabaseClass(getContext());
        fethAllfromDatabase();

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),getActivity(),notesList);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);


        return view;
    }


    private void fethAllfromDatabase() {
        Cursor cursor = databaseClass.readAllData("MON");
        if(cursor.getCount()==0){
        }else{
            while(cursor.moveToNext()){
                notesList.add(new Model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }
        }
    }


    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();
            Model item = adapter.getList().get(position);

            adapter.removeItem(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(getView(),"Item Deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adapter.restoreItem(item, position);
                            recyclerView.scrollToPosition(position);
                        }
                    }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        @Override
                        public void onDismissed(Snackbar transientBottomBar, int event) {
                            super.onDismissed(transientBottomBar, event);
                            if(!(event==DISMISS_EVENT_ACTION)){
                                DatabaseClass db = new DatabaseClass(getContext());
                                db.deleteSingleItem(item.getId());
                            }
                        }
                    });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

        }
    };


}