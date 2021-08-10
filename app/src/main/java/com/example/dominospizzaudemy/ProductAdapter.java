package com.example.dominospizzaudemy;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    Context context;

    public ProductAdapter(Context context, List<Product> products) {
        super(context, R.layout.product_item, products);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.product_item, null);

        TextView title = v.findViewById(R.id.title);
        TextView desc = v.findViewById(R.id.desc);
        TextView price = v.findViewById(R.id.price);
        RelativeLayout rlt = v.findViewById(R.id.relativeLayout);

        Product product = getItem(position);

        title.setText(product.getProductTitle());
        desc.setText(product.getProductDescription());
        price.setText("Rs: " + product.getProductPrice());
        Glide.with(context).load(product.getProductImg()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                rlt.setBackground(resource);
            }
        });

        String[] sizeData = {"Regular", "Medium", "Large"};
        String[] crustData = {"Classic Hand Tossed", "White Thin Crust", "Cheese Burst", "Cheese Float"};

        Spinner size = v.findViewById(R.id.spinnerSize);
        Spinner crust = v.findViewById(R.id.spinnerCrust);

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, sizeData);
        ArrayAdapter<String> crustAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, crustData);

        size.setAdapter(sizeAdapter);
        crust.setAdapter(crustAdapter);


        return v;
    }
}
