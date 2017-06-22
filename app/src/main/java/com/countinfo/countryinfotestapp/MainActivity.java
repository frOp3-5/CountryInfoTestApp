package com.countinfo.countryinfotestapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String url = "https://restcountries.eu/rest/v2/all";
    private List<Item> array = new ArrayList<Item>();
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_item);
        adapter=new Adapter(this,array);
        listView.setAdapter(adapter);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject obj=response.getJSONObject(i);
                        Item item=new Item();
                        item.setName(obj.getString("name"));
                        item.setFlag(obj.getString("flag"));
                        item.setCapital(obj.getString("capital"));
                        item.setRegion(obj.getString("region"));
                        array.add(item);
                    }catch(JSONException ex){
                        ex.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getmInstance().addToRequesQueue(jsonArrayRequest);
    }


}