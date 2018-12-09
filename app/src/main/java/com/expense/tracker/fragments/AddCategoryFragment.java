package com.expense.tracker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.expense.tracker.R;
import com.expense.tracker.models.Categories;
import com.expense.tracker.models.Pockets;
import com.expense.tracker.util.Utils;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class AddCategoryFragment extends Fragment {
    private View view;
    private TextInputEditText editText;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private List<Categories> categories;
    private AddCategoriesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_add_category, container, false);
        bindToRes();
        initialize();
        setListeners();
        return view;
    }



    private void setListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAdd.setText("Add");
                editText.setVisibility(View.VISIBLE);
                if(Utils.isNonEmpty(String.valueOf(editText.getText()))){
                    insertToDb(String.valueOf(editText.getText()));

                }
                else {
                    editText.setError("Enter Category");
                }
            }
        });
        setUpAdapter(categories);
    }

    private void insertToDb(String category) {
        new Categories(category).save();
        categories.clear();
        categories.addAll(SugarRecord.listAll(Categories.class));
        adapter.notifyDataSetChanged();
    }

    private void bindToRes() {
        editText=view.findViewById(R.id.edt_add_catg);
        btnAdd=view.findViewById(R.id.btn_add_catg);
        recyclerView=view.findViewById(R.id.recy_add_catg);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initialize() {
        categories=new ArrayList<>();
        try {
            categories.addAll(SugarRecord.listAll(Categories.class));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setUpAdapter(List<Categories> categories) {
        adapter = new AddCategoriesAdapter(categories, getActivity());
        recyclerView.setAdapter(adapter);
        //recyclerView.setVisibility(View.VISIBLE);
    }

    class AddCategoriesAdapter extends RecyclerView.Adapter<AddCategoriesAdapter.AddCategoriesViewHolder>{

        private LayoutInflater inflater;
        private List<Categories> categories;
        public AddCategoriesAdapter(List<Categories> categories, Context context) {
            inflater= LayoutInflater.from(context);
            this.categories=categories;
        }

        @NonNull
        @Override
        public AddCategoriesAdapter.AddCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = inflater.inflate(R.layout.add_cat_recy, parent, false);
            return new AddCategoryFragment.AddCategoriesAdapter.AddCategoriesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddCategoryFragment.AddCategoriesAdapter.AddCategoriesViewHolder addCategoriesViewHolder, int i) {
            addCategoriesViewHolder.txtSno.setText(String.valueOf(i+1));
            addCategoriesViewHolder.txtCategory.setText(categories.get(i).getCategory());
        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        class AddCategoriesViewHolder extends RecyclerView.ViewHolder{
            private TextView txtSno;
            private TextView txtCategory;
            public AddCategoriesViewHolder(@NonNull View itemView) {
                super(itemView);
                txtSno=itemView.findViewById(R.id.txt_sno);
                txtCategory=itemView.findViewById(R.id.txt_category);
            }
        }
    }


}
