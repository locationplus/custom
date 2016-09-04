package bluesky.amthucdanang.custom;

/**
 * Created by manhnc on 10/05/2015.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

import bluesky.amthucdanang.R;
import bluesky.amthucdanang.entity.QuanAn;


public class CustomQuanAn extends BaseAdapter {
    private Context mContext;
    private final ArrayList<QuanAn> quanAn;
    public ImageLoader imageLoader;



    public CustomQuanAn(Context mContext, ArrayList<QuanAn> quanAn) {
        super();
        this.mContext = mContext;
        this.quanAn = quanAn;
        imageLoader=new ImageLoader(mContext.getApplicationContext());
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return quanAn.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return quanAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.customquanan, null);
            holder.tenquanan = (TextView) convertView.findViewById(R.id.tenquanan);
            holder.khuvuc = (TextView) convertView.findViewById(R.id.khuvuc);
            holder.avatar = (ImageView) convertView.findViewById(R.id.list_image);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        QuanAn quanAn1 = quanAn.get(position);
        if (quanAn != null) {
            imageLoader.DisplayImage(quanAn1.getAnh(), holder.avatar);
            holder.tenquanan.setText(quanAn1.getTenQuanAn());
            holder.khuvuc.setText(quanAn1.getAddress());
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tenquanan, khuvuc;
        ImageView avatar;
        DownloadImageTask task;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> { //download image internet
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
