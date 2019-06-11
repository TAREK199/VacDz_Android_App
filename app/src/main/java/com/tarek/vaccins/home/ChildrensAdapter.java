package com.tarek.vaccins.home;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarek.vaccins.R;
import com.tarek.vaccins.SharedPrefManager;
import com.tarek.vaccins.model.Children;
import com.tarek.vaccins.records.RecordsActivity;

import java.util.List;

public class ChildrensAdapter  extends RecyclerView.Adapter<ChildrensAdapter.MyViewHolderChildrens> {


    private Context context;
    List<Children> childrensList ;
    SharedPrefManager sharedPrefManager ;

    public ChildrensAdapter(Context context ,List<Children> childrensList){
        this.context = context;
        this.childrensList = childrensList;
    }

    @NonNull
    @Override
    public MyViewHolderChildrens onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view ;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_childrens,parent,false);
        return new MyViewHolderChildrens(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolderChildrens holder, final int position) {


        sharedPrefManager = new SharedPrefManager(context);

        holder.childName.setText((CharSequence) childrensList.get(position).getPrenom());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RecordsActivity.class));
                sharedPrefManager.childAccess(childrensList.get(position).getId());

            }
        });
    }


    @Override
    public int getItemCount() {
        return childrensList.size();
    }

    public static class MyViewHolderChildrens extends RecyclerView.ViewHolder{

        private TextView childName,childID;
        private CardView cardView ;


        public MyViewHolderChildrens(@NonNull View itemView) {

            super(itemView);
            childName = itemView.findViewById(R.id.txt_child_name);
            childID = itemView.findViewById(R.id.txt_child_id);
            cardView = itemView.findViewById(R.id.card_children_record);

        }
    }
}
