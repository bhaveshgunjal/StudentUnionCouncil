package rait.bhavesh.studentunioncouncil.mFirebase;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

import rait.bhavesh.studentunioncouncil.DataModel.EventModel;
import rait.bhavesh.studentunioncouncil.LoginandRegistration.LoginActivity;
import rait.bhavesh.studentunioncouncil.MainActivity;
import rait.bhavesh.studentunioncouncil.RecyclerItemClickListener;
import rait.bhavesh.studentunioncouncil.mRecycler.MainAdapter;

/**
 * Created by Archana on 6/9/2016.
 */
public class FirebaseClient  {
    Context c;
    String DB_URL;
    RecyclerView rv;
    Firebase firebase;
    ArrayList<EventModel> events =new ArrayList<>();
    MainAdapter mainAdapter;

    public FirebaseClient(Context c, String DB_URL, RecyclerView rv) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.rv = rv;


        firebase.setAndroidContext(c);
        firebase=new Firebase(DB_URL);
    }



    public void saveOnline(String name,String url, String venue, String date)
    {
        EventModel eventModel= new EventModel();
        eventModel.setName(name);
        eventModel.setDate(date);
        eventModel.setVenue(venue);
        eventModel.setImageUrl(url);

        firebase.child("events").push().setValue(eventModel);
    }

    public void refreshData()
    { firebase.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            getUpdates(dataSnapshot);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
              getUpdates(dataSnapshot);
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    });

    }
 private void getUpdates(DataSnapshot dataSnapshot)

 { events.clear();
     for(DataSnapshot da: dataSnapshot.getChildren())
     {  EventModel eventModel= new EventModel();
         eventModel.setName(da.getValue(EventModel.class).getName());
         eventModel.setVenue(da.getValue(EventModel.class).getVenue());
         eventModel.setDate(da.getValue(EventModel.class).getDate());
         eventModel.setImageUrl(da.getValue(EventModel.class).getImageUrl());
         events.add(eventModel);
     }

     if(events.size()>0)
     {
         mainAdapter= new MainAdapter(events,c);
         rv.setAdapter(mainAdapter);
         rv.addOnItemTouchListener(new RecyclerItemClickListener(c, new RecyclerItemClickListener.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 Toast.makeText(c,position,Toast.LENGTH_SHORT).show();

             }
         }) );

     }
     else
     {
         Toast.makeText(c,"no data found!!",Toast.LENGTH_LONG).show();
     }

 }



}
