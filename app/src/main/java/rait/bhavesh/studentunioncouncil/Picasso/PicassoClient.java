package rait.bhavesh.studentunioncouncil.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import rait.bhavesh.studentunioncouncil.DataModel.EventModel;
import rait.bhavesh.studentunioncouncil.MainActivity;
import rait.bhavesh.studentunioncouncil.R;

/**
 * Created by Archana on 6/5/2016.
 */
public class PicassoClient {


    public static void downLoadImage(Context c,String url, ImageView imageView)
    {
        if(url !=null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.rait).into(imageView);

        }
        else
        {
            Picasso.with(c).load(R.drawable.rait).into(imageView);

        }
    }
}
