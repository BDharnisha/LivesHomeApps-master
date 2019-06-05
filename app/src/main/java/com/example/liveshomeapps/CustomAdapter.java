package com.example.liveshomeapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends SimpleAdapter {

    private Context mContext;
    public LayoutInflater inflater=null;
    public CustomAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        try{
            if(convertView==null)
                vi = inflater.inflate(R.layout.custom_list_don, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView tvdonationname = vi.findViewById(R.id.textView);
            TextView tvdonorname = vi.findViewById(R.id.textView1);
            TextView tvdonorphone = vi.findViewById(R.id.textView2);
            TextView tvdonorlocation = vi.findViewById(R.id.textView3);
            TextView tvitemcategory = vi.findViewById(R.id.textView4);
            CircleImageView imgdon =vi.findViewById(R.id.imageView2);
            String ddonationname = (String) data.get("donationname");
            String ddonorname =(String) data.get("donorname");
            String ddonorphone =(String) data.get("donorphone");
            String ddonorloc =(String) data.get("donorlocation");
            String ditmcategory =(String) data.get("itemcategory");
            String ddid=(String) data.get("donationid");
            tvdonationname.setText(ddonationname);
            tvdonorname.setText(ddonorname);
            tvdonorphone.setText(ddonorphone);
            tvdonorlocation.setText(ddonorloc);
            tvitemcategory.setText(ditmcategory);


            String image_url = "https://dharnisha250665.000webhostapp.com/images/"+ddid+".jpg";
            Picasso.with(mContext).load(image_url)
                    .fit().into(imgdon);

        }catch (IndexOutOfBoundsException e){

        }

        return vi;
    }
}








