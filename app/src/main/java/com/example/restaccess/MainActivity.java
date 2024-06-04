package com.example.restaccess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<HashMap<String,String>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.mylist);
        dataList = new ArrayList<>();
        Log.d("REST01","Start");
        new getJSON().execute();
    }

    private class getJSON extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected void onPostExecute(Void unused) {
//            super.onPostExecute(unused);
//            ListAdapter a = new SimpleAdapter(MainActivity.this,
//                    dataList,R.layout.list_item,
//                    new String[]{"id","nama","email"},
//                    new int[]{R.id.id,R.id.nama,R.id.email});
//            lv.setAdapter(a);
//        }

        @Override
        protected Void doInBackground(Void... voids) {
            HTTPHandler h = new HTTPHandler();
// kode untuk GET method
//            String u = "http://jsonplaceholder.typicode.com/users";
//            String s = h.getAccess(u);
//            Log.d("REST01","result: "+s );
//            if (s!=null) {
//                try {
//                    JSONArray js = new JSONArray(s);
//                    for (int i=0;i<js.length();i++) {
//                        JSONObject jo = js.getJSONObject(i);
//                        String nama = jo.getString("name");
//                        int id = jo.getInt("id");
//                        String email = jo.getString("email");
//                        Log.d("REST01","Nama : "+nama+ " email: "+email);
//                        HashMap<String,String> hm = new HashMap<>();
//                        hm.put("id",Integer.toString(id));
//                        hm.put("nama",nama);
//                        hm.put("email",email);
//                        dataList.add(hm);
//                    }
//
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            //kode untuk POST method
            String u = "https://jsonplaceholder.typicode.com/posts";
            ArrayList<String> mykey = new ArrayList<String>();
            ArrayList myvalue = new ArrayList<>();

            mykey.add("title");
            myvalue.add("this is a title");
            mykey.add("body");
            myvalue.add("this is a post content");
            mykey.add("userId");
            myvalue.add(1);
            String s = h.postAccess(u,mykey,myvalue);
            Log.d("REST01","result: "+s );
            if (s!=null) {
                try {
                    JSONObject jo = new JSONObject(s);
                    String title = jo.getString("title");
                    int id = jo.getInt("id");
                    Log.d("REST01","title: "+title+" id: "+id);
//                        HashMap<String,String> hm = new HashMap<>();
//                        hm.put("id",Integer.toString(id));
//                        hm.put("nama",nama);
//                        hm.put("email",email);
//                        dataList.add(hm);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            return null;
        }
    }
}