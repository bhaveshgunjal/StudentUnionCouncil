package rait.bhavesh.studentunioncouncil.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import rait.bhavesh.studentunioncouncil.R;

/**
 * Created by Archana on 6/9/2016.
 */
public class MainHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView image;
    TextView venue;
    TextView date;
    public MainHolder(View itemView) {
        super(itemView);
        name= (TextView) itemView.findViewById(R.id.textView);
        venue=(TextView) itemView.findViewById(R.id.textView);
        date=(TextView) itemView.findViewById(R.id.textView);
        image=(ImageView) itemView.findViewById(R.id.imageView);

    }

}
