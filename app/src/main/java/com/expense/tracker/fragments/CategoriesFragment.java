package com.expense.tracker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.expense.tracker.R;
import com.expense.tracker.models.Categories;
import com.expense.tracker.models.Transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CategoriesFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private HashMap<String,Integer> categoriesMap;
    private List<Categories> categoriesList;
    private CategoriesAdapter adapter;
    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_categories, container, false);
        bindToRes();
        initialize();
        loadData();
        setUpAdapter();
        return view;
    }


    private void initialize() {
        categoriesMap =new HashMap<>();
        categoriesList=new ArrayList<>();
    }

    private void loadData() {
       List<Transactions> transactions= Transactions.find(Transactions.class,"transaction_Type = ?","0");
       for(Transactions transaction:transactions){
           Integer amount;
           if((amount=categoriesMap.get(transaction.getTarget() ))!=null)
               categoriesMap.put(transaction.getTarget(),amount+transaction.getAmount());
           else {
               categoriesMap.put(transaction.getTarget(), transaction.getAmount());
           }
       }
        for (Map.Entry<String,Integer> entry : categoriesMap.entrySet()) {
            categoriesList.add(new Categories(entry.getKey(), entry.getValue()));
        }
    }




    private void setUpAdapter() {
        adapter = new CategoriesFragment.CategoriesAdapter(categoriesList, getActivity());
        recyclerView.setAdapter(adapter);
    }
    private void bindToRes() {
        recyclerView=view.findViewById(R.id.recy_categories);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    class CategoriesAdapter extends RecyclerView.Adapter<CategoriesFragment.CategoriesAdapter.CategoriesViewHolder>{

        private LayoutInflater inflater;
        private List<Categories> categories;
        public CategoriesAdapter(List<Categories> categories, Context context) {
            inflater= LayoutInflater.from(context);
            this.categories=categories;
        }

        @NonNull
        @Override
        public CategoriesFragment.CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = inflater.inflate(R.layout.recy_catgories, parent, false);
            return new CategoriesFragment.CategoriesAdapter.CategoriesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoriesFragment.CategoriesAdapter.CategoriesViewHolder categoriesViewHolder, int i) {
            categoriesViewHolder.txtSno.setText(String.valueOf(i+1));
            categoriesViewHolder.txtCategory.setText(categories.get(i).getCategory());
            categoriesViewHolder.txtAmt.setText(String.valueOf(categories.get(i).getAmount()));
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        class CategoriesViewHolder extends RecyclerView.ViewHolder{
            private TextView txtSno;
            private TextView txtCategory;
            private TextView txtAmt;
            public CategoriesViewHolder(@NonNull View itemView) {
                super(itemView);
                txtSno=itemView.findViewById(R.id.txt_recy_ctg_sno);
                txtCategory=itemView.findViewById(R.id.recy_txt_catg);
                txtAmt=itemView.findViewById(R.id.recy_txt_ctg_amt);
            }
        }
    }

}
