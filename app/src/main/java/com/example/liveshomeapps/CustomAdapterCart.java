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

public class CustomAdapterCart extends SimpleAdapter {

    private Context mContext;
    public LayoutInflater inflater=null;
    public CustomAdapterCart(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        try{
            if(convertView==null)
                vi = inflater.inflate(R.layout.user_cart_list, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView tvitemname = vi.findViewById(R.id.textView);
            TextView tvitemprice = vi.findViewById(R.id.textView2);
            TextView tvitemcondition = vi.findViewById(R.id.textView3);
            TextView tvquantity = vi.findViewById(R.id.textView4);
            TextView tvstatus = vi.findViewById(R.id.textView5);
            CircleImageView imgdonitem =vi.findViewById(R.id.imageView2);
            String dditemname = (String) data.get("itemname");//hilang
            String dditemprice = (String) data.get("itemprice");
            String dditemcondition =(String) data.get("itemcondition");
            String ditemquan =(String) data.get("quantity");
            String ditemid=(String) data.get("donationitemid");
            String dfst=(String) data.get("status");
            String ddorderid=(String) data.get("orderid");
            tvitemname.setText(dditemname);
            tvitemprice.setText(dditemprice);
            tvitemcondition.setText(dditemcondition);
            tvquantity.setText(ditemquan);
            tvstatus.setText(dfst);
            String image_url = "https://dharnisha250665.000webhostapp.com/donation_item_images/"+ditemid+".jpg";
            Picasso.with(mContext).load(image_url)
                    .fit().into(imgdonitem);
//                   .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)

        }catch (IndexOutOfBoundsException e){

        }
        return vi;
    }
}

