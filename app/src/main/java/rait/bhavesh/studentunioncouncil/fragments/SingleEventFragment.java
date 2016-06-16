package rait.bhavesh.studentunioncouncil.fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import rait.bhavesh.studentunioncouncil.DataModel.EventModel;
import rait.bhavesh.studentunioncouncil.MainActivity;
import rait.bhavesh.studentunioncouncil.R;
import rait.bhavesh.studentunioncouncil.RecyclerItemClickListener;
import rait.bhavesh.studentunioncouncil.mFirebase.FirebaseClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleEventFragment extends Fragment {
int color;
    final static String DB_URL="https://suc.firebaseio.com/";
    RecyclerView mRecyclerView;
    FirebaseClient firebaseClient;
    ArrayList<EventModel>  events;
    Context c;
public SingleEventFragment()
{

}

    @SuppressLint("ValidFragment")

    public SingleEventFragment(int color)
    {
      this.color=color;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_single_event, container, false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseClient=new FirebaseClient(getContext(),DB_URL,mRecyclerView);
        firebaseClient.refreshData();


    }
}
