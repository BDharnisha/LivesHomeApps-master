package com.example.liveshomeapps;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static java.lang.Thread.sleep;
import static java.nio.file.Paths.get;

public class MainActivity extends AppCompatActivity {
    ListView lvdon;
    ArrayList<HashMap<String, String>> donlist;
    ArrayList<HashMap<String, String>> cartlist;
    ArrayList<HashMap<String, String>> orderhistorylist;
    double total,totalhistory;
    String userid,name,phone;
    Dialog myDialogCart,myDialogHistory;
    Spinner spItemCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvdon = findViewById(R.id.listviewDon);
        cartlist = new ArrayList<>();
        orderhistorylist= new ArrayList<>();
        spItemCategory = findViewById(R.id.spinner);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        name = bundle.getString("name");
        phone = bundle.getString("phone");

        loadDonation(spItemCategory.getSelectedItem().toString());
        lvdon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, DonationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("donationid", donlist.get(position).get("donationid"));
                bundle.putString("donationname", donlist.get(position).get("donationname"));
                bundle.putString("donorname", donlist.get(position).get("donorname"));
                bundle.putString("donorphone", donlist.get(position).get("donorphone"));
                bundle.putString("donorlocation", donlist.get(position).get("donorlocation"));
                bundle.putString("itemcategory", donlist.get(position).get("itemcategory"));
                bundle.putString("userid",userid);
                bundle.putString("userphone",phone);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        spItemCategory.setSelection(0, false);
        spItemCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadDonation(spItemCategory.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void loadDonation(final String ic) {
        class LoadDonation extends AsyncTask<Void, Void, String> {

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("itemcategory", ic);
                RequestHandler rh = new RequestHandler();
                donlist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("https://dharnisha250665.000webhostapp.com/load_donation.php", hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                donlist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray donarray = jsonObject.getJSONArray("don");
                    Log.e("NISHA", jsonObject.toString());
                    for (int i = 0; i < donarray.length(); i++) {
                        JSONObject c = donarray.getJSONObject(i);
                        String did = c.getString("donationid");
                        String dname = c.getString("donationname");
                        String dnrname = c.getString("donorname");
                        String dnrphone = c.getString("donorphone");
                        String dnrlocation = c.getString("donorlocation");
                        String icategory = c.getString("itemcategory");
                        HashMap<String, String> donlisthash = new HashMap<>();
                        donlisthash.put("donationid", did);
                        donlisthash.put("donationname", dname);
                        donlisthash.put("donorname", dnrname);
                        donlisthash.put("donorphone", dnrphone);
                        donlisthash.put("donorlocation", dnrlocation);
                        donlisthash.put("itemcategory", icategory);
                        donlist.add(donlisthash);
                    }
                } catch (final JSONException e) {
                    Log.e("JSONERROR", e.toString());
                }

                ListAdapter adapter = new CustomAdapter(
                        MainActivity.this, donlist,
                        R.layout.custom_list_don, new String[]
                        {"donationname", "donorname","donorphone","donorlocation","itemcategory"}, new int[]
                        {R.id.textView,R.id.textView1,R.id.textView2, R.id.textView3,R.id.textView4});
                lvdon.setAdapter(adapter);
            }

        }
        LoadDonation loadDonation = new LoadDonation();
        loadDonation.execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mycart:
                loadCartData();
                return true;

            case R.id.myprofile:
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("userid",userid);
                bundle.putString("username",name);
                bundle.putString("phone",phone);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            case R.id.logout:
                Intent intentlogout = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intentlogout);
                return true;

            case R.id.about:
                Intent intentabout = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intentabout);
                return true;

            case R.id.myhistory:
                loadHistoryOrderData();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadHistoryOrderData() {
        class LoadOrderData extends AsyncTask<Void,String,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("userid",phone);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("https://dharnisha250665.000webhostapp.com/load_order_history.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                orderhistorylist.clear();
                totalhistory = 0;
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray ordarray = jsonObject.getJSONArray("history");

                    for (int i=0;i<ordarray  .length();i++) {
                        JSONObject c = ordarray  .getJSONObject(i);
                        String jsorderid = c.getString("orderid");
                        String jstotal = c.getString("total");
                        String jsdate = c.getString("date");
                        HashMap<String,String> histlisthash = new HashMap<>();
                        histlisthash.put("orderid",jsorderid);
                        histlisthash.put("total",jstotal);
                        histlisthash.put("date",convertime24h(jsdate));
                        orderhistorylist.add(histlisthash);
                        totalhistory = Double.parseDouble(jstotal) + totalhistory;
                    }
                }catch (JSONException e){}
                super.onPostExecute(s);
                if (orderhistorylist.size()>0){
                    loadHistoryWindow();
                }else{
                    Toast.makeText(MainActivity.this, "No order history", Toast.LENGTH_SHORT).show();
                }
            }
        }
        LoadOrderData loadOrderData = new LoadOrderData();
        loadOrderData.execute();
    }

    private void loadHistoryWindow() {
        myDialogHistory = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        myDialogHistory.setContentView(R.layout.hist_window);
        myDialogHistory.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView lvhist = myDialogHistory.findViewById(R.id.lvhistory);
        TextView tvtotal = myDialogHistory.findViewById(R.id.textViewTotal);
        Button btnclose = myDialogHistory.findViewById(R.id.btnClose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogHistory.dismiss();
            }
        });
        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, orderhistorylist,
                R.layout.hist_order_list, new String[]
                {"orderid","total","date"}, new int[]
                {R.id.textView,R.id.textView2,R.id.textView3});
        lvhist.setAdapter(adapter);
        tvtotal.setText("RM"+totalhistory);
        myDialogHistory.show();
    }
    public String convertime24h(String value) {
        String _12hourformat = "";
        try {
            //Log.e("DATE", value);
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            Date date = dt.parse(value.substring(0, 16));
            SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            return _12hourformat = dt1.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _12hourformat;
    }

    private void loadCartWindow() {
        myDialogCart = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        myDialogCart.setContentView(R.layout.cart_window);
        myDialogCart.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView lvcart = myDialogCart.findViewById(R.id.lvmycart);
        TextView tvtotal = myDialogCart.findViewById(R.id.textViewTotal);
        TextView tvorderid = myDialogCart.findViewById(R.id.textOrderId);
        Button btnpay = myDialogCart.findViewById(R.id.btnPay);
        Log.e("NISHA","SIZE:"+cartlist.size());
        lvcart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogDeleteDonationItem(position);
                return false;
            }
        });
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPay();
            }
        });
        ListAdapter adapter = new CustomAdapterCart(
                MainActivity.this, cartlist, R.layout.user_cart_list, new String[]
                {"itemname","itemprice", "itemcondition", "quantity", "status"}, new int[]
                {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5});
        lvcart.setAdapter(adapter);
        tvtotal.setText("RM "+total);
        tvorderid.setText(cartlist.get(0).get("orderid"));
        myDialogCart.show();
    }
    private void dialogDeleteDonationItem(final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete Donation Item "+cartlist.get(position).get("itemname")+"?");
        alertDialogBuilder
                .setMessage("Are you sure")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // Toast.makeText(MainActivity.this, cartlist.get(position).get("foodname"), Toast.LENGTH_SHORT).show();
                        deleteCartDonationItem(position);
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
    private void deleteCartDonationItem(final int position) {
        class DeleteCartDonationItem extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                String donationitemid = cartlist.get(position).get("donationitemid");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("donationitemid", donationitemid);
                hashMap.put("userid", userid);
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendPostRequest("https://dharnisha250665.000webhostapp.com/delete_cart.php", hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")){
                    myDialogCart.dismiss();
                    loadCartData();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        DeleteCartDonationItem deleteCartDonationItem = new DeleteCartDonationItem();
        deleteCartDonationItem.execute();
    }

    private void dialogPay() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Proceed with payment?");

        alertDialogBuilder
                .setMessage("Are you sure")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this,PaymentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("userid",userid);
                        bundle.putString("name",name);
                        bundle.putString("phone",phone);
                        bundle.putString("total", String.valueOf(total));
                        bundle.putString("orderid", cartlist.get(0).get("orderid"));
                        intent.putExtras(bundle);
                        myDialogCart.dismiss();
                        startActivity(intent);
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
    private void loadCartData() {
        class LoadCartData extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("userid",phone);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("https://dharnisha250665.000webhostapp.com/load_cart.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                cartlist.clear();
                total = 0;
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray cartarray = jsonObject.getJSONArray("cart");

                    for (int i=0;i<cartarray .length();i++) {
                        JSONObject c = cartarray .getJSONObject(i);
                        String jdid = c.getString("donationitemid");
                        String jin = c.getString("itemname");
                        String jip = c.getString("itemprice");
                        String jic = c.getString("itemcondition");
                        String jiq = c.getString("quantity");
                        String jst = c.getString("status");
                        String joid = c.getString("orderid");
                        HashMap<String,String> cartlisthash = new HashMap<>();
                        cartlisthash .put("donationitemid",jdid);
                        cartlisthash .put("itemname",jin);
                        cartlisthash .put("itemprice","RM "+jip);
                        cartlisthash .put("itemcondition",jic);
                        cartlisthash .put("quantity",jiq+" item");
                        cartlisthash .put("status",jst);
                        cartlisthash .put("orderid",joid);
                        cartlist.add(cartlisthash);

                        total = total + (Double.parseDouble(jip) * Double.parseDouble(jiq));
                    }

                }catch (JSONException e){}
                super.onPostExecute(s);
                if (total>0){
                    loadCartWindow();
                }else{
                    Toast.makeText(MainActivity.this, "Cart is feeling empty", Toast.LENGTH_SHORT).show();
                }
            }
        }
        LoadCartData loadCartData = new LoadCartData();
        loadCartData.execute();
    }
}

