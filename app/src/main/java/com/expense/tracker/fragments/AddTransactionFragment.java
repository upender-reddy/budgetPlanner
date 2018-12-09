package com.expense.tracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.expense.tracker.MainActivity;
import com.expense.tracker.R;

public class AddTransactionFragment extends Fragment {

    private AppCompatSpinner spinner;

    private static final String[] spinnerItems = {"Expense", "MoneyIn", "MoneyOut","Transfer"};
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_transaction, container, false);
        bindToResouces();
        setListeners();
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.menu_add).setVisible(false);
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (menu != null) {

            menu.findItem(R.id.menu_add).setVisible(false);
        }
    }
    private void bindToResouces() {
        spinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
//        editLrNo = (EditText) view.findViewById(R.id.edit_lr);
//        editDeliveryNo = (EditText) view.findViewById(R.id.edit_dlvry_no);
//        editReference = (EditText) view.findViewById(R.id.edit_ref);
    }
    private void setListeners() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,spinnerItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setBackButton();
            ((MainActivity) getActivity()).setTiltle("Add Transaction");
        }
    }

    @Override
    public void onDestroy() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).disableBackButton();
            ((MainActivity) getActivity()).setTiltle("Expense Tracker");
        }
        super.onDestroy();
    }
}
