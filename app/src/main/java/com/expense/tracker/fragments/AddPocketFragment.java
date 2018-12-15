package com.expense.tracker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.expense.tracker.R;
import com.expense.tracker.models.Categories;
import com.expense.tracker.models.Pockets;
import com.expense.tracker.util.Utils;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class AddPocketFragment extends Fragment {
    private View view;
    private TextInputEditText editText;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private List<Pockets> pockets;
    private AddPocketsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_add_pocket, container, false);
        bindToRes();
        initialize();
        setListeners();
        setUpAdapter();
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
    }

    private void insertToDb(String pocket) {
        new Pockets(pocket).save();
        pockets.clear();
        pockets.addAll(SugarRecord.listAll(Pockets.class));
        adapter.notifyDataSetChanged();
    }

    private void bindToRes() {
        editText=view.findViewById(R.id.edt_add_pock);
        btnAdd=view.findViewById(R.id.btn_add_pock);
        recyclerView=view.findViewById(R.id.recy_add_pock);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void initialize() {
        pockets=new ArrayList<>();
        try {
            pockets.addAll(SugarRecord.listAll(Pockets.class));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setUpAdapter() {
        adapter = new AddPocketsAdapter(pockets, getActivity());
        recyclerView.setAdapter(adapter);
    }



    class AddPocketsAdapter extends RecyclerView.Adapter<AddPocketsAdapter.AddPocketsViewHolder>{

        private LayoutInflater inflater;
        private List<Pockets> pockets=null;
        public AddPocketsAdapter(List<Pockets> pockets, Context context) {
            inflater= LayoutInflater.from(context);
            this.pockets=pockets;
        }

        @NonNull
        @Override
        public AddPocketsAdapter.AddPocketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = inflater.inflate(R.layout.add_pock_recy, parent, false);
            return new AddPocketsAdapter.AddPocketsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddPocketsViewHolder addPocketsViewHolder, final int i) {
            addPocketsViewHolder.txtSno.setText(String.valueOf(i+1));
            addPocketsViewHolder.txtPocket.setText(pockets.get(i).getPocket());
            addPocketsViewHolder.tSwitch.setChecked(pockets.get(i).isSpendable());
            addPocketsViewHolder.tSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Pockets pocket=  Pockets.find(Pockets.class,"pocket = ?", pockets.get(i).getPocket()).get(0);
                    pocket.setSpendable(isChecked);
                    pocket.save();
                }
            });
        }

        @Override
        public int getItemCount() {
            return pockets.size();
        }

        class AddPocketsViewHolder extends RecyclerView.ViewHolder{
            private TextView txtSno;
            private TextView txtPocket;
            private Switch tSwitch;
            public AddPocketsViewHolder(@NonNull View itemView) {
                super(itemView);
                txtSno=itemView.findViewById(R.id.txt_sno);
                txtPocket=itemView.findViewById(R.id.txt_pock);
                tSwitch=itemView.findViewById(R.id.switch_spendable);
            }
        }
    }
}
