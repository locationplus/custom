package bluesky.amthucdanang.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import bluesky.amthucdanang.R;
import bluesky.amthucdanang.activity.LoginActivity;
import bluesky.amthucdanang.activity.MainActivity;
import bluesky.amthucdanang.activity.MapsActivity;
import bluesky.amthucdanang.activity.QuananyeuthichActivity;
import bluesky.amthucdanang.activity.RegisterActivity;
import bluesky.amthucdanang.activity.ThongtincanhanActivity;
import bluesky.amthucdanang.activity.XemgandayActivity;

/**
 * Created by manhnc on 19/05/2015.
 */
public class CustomNavigation extends BaseAdapter {
    String[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public CustomNavigation(MainActivity mainActivity, String[] prgmNameList, int[] prgmImages) {
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        final SessionManager session;
        final String userName;
        rowView = inflater.inflate(R.layout.list_row, null);
        holder.tv = (TextView) rowView.findViewById(R.id.text);
        holder.img = (ImageView) rowView.findViewById(R.id.image);
        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
        session = new SessionManager(inflater.getContext());
        HashMap<String, String> info = session.getUserDetails();
        userName = info.get(SessionManager.KEY_NAME);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (userName != null) {
                    if (position == 0) {
                        Intent intent = new Intent(context, QuananyeuthichActivity.class);
                        context.startActivity(intent);
                    }
                    if (position == 1) {
                        Intent intent = new Intent(context, XemgandayActivity.class);
                        context.startActivity(intent);
                    }
                    if (position == 2) {
                        GPSService gpsService = new GPSService(context);
                        final LocationManager manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
                        if (!manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                            gpsService.askUserToOpenGPS();
                        } else {
                            Intent intent = new Intent(context, MapsActivity.class);
                            context.startActivity(intent);
                        }
                    }
                   if(position==3){
                      Intent intent = new Intent(context, ThongtincanhanActivity.class);
                       context.startActivity(intent);
                   }
                    if (position == 4) {
                        ((Activity) context).finish();
                        session.logoutUser();
                    }
                } else {
                    if (position == 0) {
                        GPSService gpsService = new GPSService(context);
                        final LocationManager manager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
                        if (!manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                            gpsService.askUserToOpenGPS();
                        } else {
                            Intent intent = new Intent(context, MapsActivity.class);
                            context.startActivity(intent);
                        }
                    }
                    if (position == 1) {
                        Intent intent = new Intent(context, XemgandayActivity.class);
                        context.startActivity(intent);
                    }
                    if (position == 2) {
                        Intent intent = new Intent(context, RegisterActivity.class);
                        context.startActivity(intent);
                    }
                    if (position == 3) {
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }

                }

            }
        });
        return rowView;
    }

}
