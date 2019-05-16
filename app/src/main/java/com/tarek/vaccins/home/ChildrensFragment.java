package com.tarek.vaccins.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.tarek.vaccins.R;
import com.tarek.vaccins.RetrofitInstance;
import com.tarek.vaccins.model.Children;
import com.tarek.vaccins.service.ChildrenService;
import com.tarek.vaccins.service.FatherService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildrensFragment extends Fragment {

    private View view;
    private List<Children> childrens ;
    private RecyclerView recyclerView ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_childrens,container,false);

         getChildrens();

       // getChildrensByFatherId();


    //    getEnfants();
         viewData();

        return view;
    }


    public void viewData(){



        recyclerView = view.findViewById(R.id.recycle_childrens);
        childrens = new ArrayList<>();


        childrens.add(new Children("farah"));
        childrens.add(new Children("karim"));
        childrens.add(new Children("omar"));
        childrens.add(new Children("samar"));





        ChildrensAdapter childrensAdapter = new ChildrensAdapter(getActivity(),childrens);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(childrensAdapter);
    }


    public  void  getChildrens() {

        FatherService fatherService = RetrofitInstance.getFatherService();

        fatherService.getChildrensById(325).enqueue(new Callback<List<Children>>() {
            @Override
            public void onResponse(Call<List<Children>> call, Response<List<Children>> response) {

                if (response.isSuccessful()){

                    Toast.makeText(getActivity(),"aw yjib : "+response.code(),Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"yjib bessah keyen prblm : "+response.message(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<List<Children>> call, Throwable t) {

                Toast.makeText(getActivity(),"error : "+t.getMessage()+"  ",Toast.LENGTH_LONG).show();

            }
        });

    }


    public void getChildrensByFatherId(){

        FatherService fatherService =  RetrofitInstance.getFatherService();


        fatherService.getChildrensFatherId(325).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),"aw yjib",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"ms keyene prblm ya za7 "+response,Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getActivity(),"error : "+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void getEnfants (){

    String urlString = "http://192.168.1.26:9090/api/peres/325";

    Ion.with(getActivity())
            .load(urlString)
            .asString()
            .setCallback(new FutureCallback<String>(){
    @Override
    public void onCompleted(Exception e, String result){
   // pDialog.dismiss();
         if(result == null){
             Toast.makeText(getActivity(),"empty",Toast.LENGTH_LONG).show();
         }
            else{
             Toast.makeText(getActivity(),"result is : "+result.toString(),Toast.LENGTH_LONG).show();
         }
        }});


        }
}
