package com.example.liveshomeapps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DonationActivity extends AppCompatActivity {
    TextView tvddonationname,tvddonorname,tvddonorphone,tvddloc,tvditmcategory;
    ImageView imgDon;
    ListView lvdonitem;
    Dialog myDialogWindow;
    ArrayList<HashMap<String, String>> donationitemlist;
    String userid,donationid,userphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        donationid = bundle.getString("donationid");
        String dname = bundle.getString("donationname");
        String dnrname = bundle.getString("donorname");
        String dnrphone = bundle.getString("donorphone");
        String dnrlocation = bundle.getString("donorlocation");
        String icategory = bundle.getString("itemcategory");
        userid = bundle.getString("userid");
        userphone = bundle.getString("userphone");
        initView();
        tvddonationname.setText(dname);
        tvddonorname.setText(dnrname);
        tvddonorphone.setText(dnrphone);
        tvddloc.setText(dnrlocation);
        tvditmcategory.setText(icategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.with(this).load("https://dharnisha250665.000webhostapp.com/images/"+donationid+".jpg")
                //.memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
                .fit().into(imgDon);

        lvdonitem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDonationItemDetail(position);
            }
        });
        loadDonations(donationid);

    }

    private void showDonationItemDetail(int p) {
        myDialogWindow = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        myDialogWindow.setContentView(R.layout.dialog_window);
        myDialogWindow.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView tvdname,tvdprice,tvdquan,tvdcondition;
        ImageView imgDlogo = myDialogWindow.findViewById(R.id.imageViewDonationLogo);
        final Spinner spquan = myDialogWindow.findViewById(R.id.spinner2);
        Button btnOrder = myDialogWindow.findViewById(R.id.button2);
        tvdname= myDialogWindow.findViewById(R.id.textView12);
        tvdcondition = myDialogWindow.findViewById(R.id.textView18);
        tvdprice = myDialogWindow.findViewById(R.id.textView20);
        tvdquan = myDialogWindow.findViewById(R.id.textView14);
        tvdname.setText(donationitemlist.get(p).get("itemname"));
        tvdprice.setText(donationitemlist.get(p).get ("itemprice"));
        tvdcondition.setText(donationitemlist.get(p).get("itemcondition"));
        tvdquan.setText(donationitemlist.get(p).get("itemquantity"));

        final String donationitemid =(donationitemlist.get(p).get("donationitemid"));
        final String itemname = donationitemlist.get(p).get("itemname");
        final String itemcondition = donationitemlist.get(p).get("itemcondition");
        final String itemprice = donationitemlist.get(p).get("itemprice");

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dquan = spquan.getSelectedItem().toString();
                dialogOrder(donationitemid,itemname,itemcondition,itemprice,dquan);
            }
        });
        int quan = Integer.parseInt(donationitemlist.get(p).get("itemquantity"));
        List<String> list = new ArrayList<String>();
        for (int i = 1; i<=quan;i++){
            list.add(""+i);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spquan.setAdapter(dataAdapter);

        Picasso.with(this).load("https://dharnisha250665.000webhostapp.com/donation_item_images/"+donationitemid+".jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
                .fit().into(imgDlogo);
        myDialogWindow.show();

    }

    private void dialogOrder (final String donationitemid, final String itemname, final String itemcondition,final String itemprice,final String dquan) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Order "+itemname+ " with quantity "+dquan);

        alertDialogBuilder
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        insertCart(donationitemid,itemname,itemcondition,itemprice,dquan);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void insertCart(final String donationitemid, final String itemname,final String itemcondition,final String itemprice, final String dquan) {
        class InsertCart extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("donationitemid",donationitemid);
                hashMap.put("donationid",donationid);
                hashMap.put("itemname",itemname);
                hashMap.put("itemcondition",itemcondition);
                hashMap.put("itemprice",itemprice);
                hashMap.put("quantity",dquan);
                hashMap.put("userid",userphone);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("https://dharnisha250665.000webhostapp.com/insert_cart.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(DonationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    myDialogWindow.dismiss();
                    loadDonations(donationid);
                }else{
                    Toast.makeText(DonationActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        InsertCart insertCart = new InsertCart();
        insertCart.execute();
    }
    private void loadDonations(final String donationid) {
        class LoadDonation extends AsyncTask<Void,Void,String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("donationid",donationid);
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendPostRequest("https://dharnisha250665.000webhostapp.com/load_donationitems.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                donationitemlist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray donationitemarray = jsonObject.getJSONArray("donationitem");
                    for (int i = 0; i < donationitemarray.length(); i++) {
                        JSONObject c = donationitemarray.getJSONObject(i);
                        String jsid = c.getString("donationitemid");
                        String jsitemname = c.getString("itemname");
                        String jsitemcondition = c.getString("itemcondition");
                        String jsitemprice = c.getString("itemprice");
                        String jsquan = c.getString("quantity");
                        HashMap<String,String> donationitemlisthash = new HashMap<>();
                        donationitemlisthash.put("donationitemid",jsid);
                        donationitemlisthash.put("itemname",jsitemname);
                        donationitemlisthash.put("itemcondition",jsitemcondition);
                        donationitemlisthash.put("itemprice",jsitemprice);
                        donationitemlisthash.put("itemquantity",jsquan);
                        donationitemlist.add(donationitemlisthash);
                    }
                }catch(JSONException e){}
                ListAdapter adapter = new CustomAdapterDonation(
                        DonationActivity.this, donationitemlist,
                        R.layout.donation_list_don, new String[]
                        {"itemname","itemcondition","itemprice","itemquantity"}, new int[]
                        {R.id.textView,R.id.textView18,R.id.textView12,R.id.textView14});
                lvdonitem.setAdapter(adapter);
            }
        }
        LoadDonation loadDonation = new LoadDonation();
        loadDonation.execute();
    }
    private void initView() {
        imgDon = findViewById(R.id.imageView3);
        tvddonationname = findViewById(R.id.textView6);
        tvddonorname = findViewById(R.id.textView7);
        tvddonorphone = findViewById(R.id.textView16);
        tvddloc = findViewById(R.id.textView8);
        tvditmcategory = findViewById(R.id.textView9);
        lvdonitem = findViewById(R.id.listviewdonationitem);
        donationitemlist = new ArrayList<>();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DonationActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userid",userid);
                bundle.putString("phone",userphone);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
