package com.example.aslsignlanguage.Controlar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aslsignlanguage.R;

import java.util.List;

import com.example.aslsignlanguage.Model.Subscription;

public class AdapterSubscription extends RecyclerView.Adapter<AdapterSubscription.ViweHolder>{

    Context context;
    List<Subscription> subscriptionList;

    public AdapterSubscription(Context context, List<Subscription> subscriptionList) {
        this.context = context;
        this.subscriptionList = subscriptionList;
    }

    @NonNull
    @Override
    public AdapterSubscription.ViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viwe = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription, parent, false);
        return new ViweHolder(viwe);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterSubscription.ViweHolder holder, int position) {

        Subscription subscription = subscriptionList.get(position);
        holder.tital.setText(subscription.getTital());
        holder.discount.setText("Save "+subscription.getDiscount()+"%");
        holder.subscribe.setText(subscription.getPrice()+"/per month");

        holder.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(subscription.getLinkPay()));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subscriptionList.size();
    }

    public class ViweHolder extends RecyclerView.ViewHolder{
        TextView tital,discount;
        Button subscribe;

        public ViweHolder(@NonNull View itemView) {
            super(itemView);
            tital = itemView.findViewById(R.id.textView22);
            discount = itemView.findViewById(R.id.textView27);
            subscribe = itemView.findViewById(R.id.button4);
        }
    }

}
