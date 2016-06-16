package rait.bhavesh.studentunioncouncil;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import rait.bhavesh.studentunioncouncil.LoginandRegistration.LoginActivity;
import rait.bhavesh.studentunioncouncil.fragments.SingleEventFragment;
import rait.bhavesh.studentunioncouncil.mFirebase.FirebaseClient;

public class MainActivity extends AppCompatActivity {
    final static String DB_URL="https://suc.firebaseio.com/";
         RecyclerView mRecyclerView;
    FirebaseClient firebaseClient;
    private CollapsingToolbarLayout collapsingToolbar;
    private ViewPagerAdapter viewPageAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    EditText name,venue,date,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.detail_tabs);

        tabLayout.setupWithViewPager(viewPager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
      mRecyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        firebaseClient=new FirebaseClient(this,DB_URL,mRecyclerView);
        firebaseClient.refreshData();
       // shareIt();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void shareIt()
    {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "sharebody" );
        startActivity(Intent.createChooser(intent,"share via"));
    }
    public void loadLoginView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    private void displayDialog()
    {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialoglayout);
        name= (EditText) dialog.findViewById(R.id.name);
        venue= (EditText) dialog.findViewById(R.id.venue);
        date= (EditText) dialog.findViewById(R.id.date);
        url= (EditText) dialog.findViewById(R.id.image);
       Button savebutton= (Button) dialog.findViewById(R.id.savebutton);
        savebutton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              firebaseClient.saveOnline(name.getText().toString(), url.getText().toString(), venue.getText().toString(), date.getText().toString());
                                              name.setText("");
                                              venue.setText("");
                                              date.setText("");
                                              url.setText("");

                                          }
                                      }
        );
        dialog.show();
    }
class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new SingleEventFragment(getResources().getColor(R.color.accent_material_light)), "Events");
        adapter.addFrag(new SingleEventFragment(getResources().getColor(R.color.ripple_material_light)), "Committee");
        adapter.addFrag(new SingleEventFragment(getResources().getColor(R.color.button_material_dark)), "Hot");
        viewPager.setAdapter(adapter);
    }

}
