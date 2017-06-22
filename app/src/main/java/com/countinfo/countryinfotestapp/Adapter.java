package com.countinfo.countryinfotestapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import static com.countinfo.countryinfotestapp.R.id.web_view;

public class Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private List<Item> items;

    public Adapter(Activity activity, List<Item> items){
        this.activity=activity;
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView ==null){
            convertView=inflater.inflate(R.layout.custom_layout,null);
        }

        TextView name= (TextView) convertView.findViewById(R.id.tv_name);
        TextView capital= (TextView) convertView.findViewById(R.id.tv_capital);
        TextView region= (TextView) convertView.findViewById(R.id.tv_region);
        Item item=items.get(position);
        WebView webView = (WebView) convertView.findViewById(web_view);
        webView.loadData("<img height=\"60px\" width=\"60px\" src=\"" + item.getFlag() + "\" />", "text/html", "utf-8");

        name.setText(item.getName());
        capital.setText("Capital: " + String.valueOf(item.getCapital()));
        region.setText("Region: " + String.valueOf(item.getRegion()));

        return convertView;
    }

}
