package bluesky.amthucdanang.custom;

/**
 * Created by manhnc on 13/05/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bluesky.amthucdanang.R;
import bluesky.amthucdanang.activity.ChitietQuangandayActivity;
import bluesky.amthucdanang.activity.ChitietQuanyeuthichActivity;
import bluesky.amthucdanang.activity.ChitietquanActivity;
// custom list view hi?n th?
public class CustomAdapter extends BaseAdapter {
    String[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public CustomAdapter(ChitietquanActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomAdapter(ChitietQuangandayActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CustomAdapter(ChitietQuanyeuthichActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.list_row, null);
        holder.tv = (TextView) rowView.findViewById(R.id.text);
        holder.img = (ImageView) rowView.findViewById(R.id.image);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
/*
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
*/
            }
        });
        return rowView;
    }

}