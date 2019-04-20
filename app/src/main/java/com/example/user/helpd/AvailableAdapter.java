package com.example.user.helpd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AvailableAdapter extends RecyclerView.Adapter< AvailableAdapter.MyViewHolder>{

    Callback callback;
    public AvailableAdapter(Callback callback){
        this.callback= callback;
    }

    private ArrayList<Helper> mHelper;

    public AvailableAdapter(ArrayList<Helper>mHelper){ this.mHelper= mHelper;}

    @NonNull
    @Override
    public AvailableAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,
                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableAdapter.MyViewHolder holder, int position) {
    Helper helper= mHelper.get(position);
    holder.bind(helper);

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            callback.friendCallback();
        }
    });


    }

    @Override
    public int getItemCount() {

        if(mHelper==null)
            return 0;
        return mHelper.size();
    }

    public void setData(ArrayList<Helper> mhHelper){
        this.mHelper= mhHelper;
        notifyDataSetChanged();
    }
class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, service, timing;
    public MyViewHolder(View itemView) {
        super(itemView);
        name= itemView.findViewById(R.id.textViewTitle);
        service= itemView.findViewById(R.id.textViewShortDesc);
        timing= itemView.findViewById(R.id.textViewTiming);
    }

    public void bind(Helper h){
        name.setText(h.getName());
        service.setText(h.getServices());
        timing.setText(String.valueOf(h.getTimings()));
    }
}

public interface Callback{
        public void friendCallback();
}
}
