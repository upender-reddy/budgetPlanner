package com.expense.tracker.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.expense.tracker.MainActivity;
import com.expense.tracker.R;
import com.expense.tracker.constants.Constants;
import com.expense.tracker.models.Categories;
import com.expense.tracker.models.Pockets;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTransactionFragment extends Fragment {

    private AppCompatSpinner spinTrType,spinSrc,spinTarget;
    private TextView txtTargetBlc,txtSrcBlc;
    private EditText edAmt,edRemarks,edDate;

    private static  ArrayList<String> spinnerItemsTrType = Constants.TransactionType.transactionTypes();
    private static  ArrayList<String> spinnerItemsSrc ;
    private static  ArrayList<String> spinnerItemsTarget;
    private int srcPos,targetPos;
    Calendar myCalendar;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_add_transaction, container, false);
        bindToResouces();
        initialize();
        setListeners();
        getActivity().invalidateOptionsMenu();
        return view;
    }

    private void initialize() {
        spinnerItemsTarget=new ArrayList<>();
        spinnerItemsSrc=new ArrayList<>();
        loadTarget(0);
        myCalendar = Calendar.getInstance();
        updateLabel();
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
        spinTrType = view.findViewById(R.id.spin_tr_type);
        spinSrc = view.findViewById(R.id.spin_src);
        spinTarget = view.findViewById(R.id.spin_target);
        edDate=view.findViewById(R.id.dpick_tr);
        txtSrcBlc=view.findViewById(R.id.txt_src_blc);
        txtTargetBlc=view.findViewById(R.id.txt_target_blc);
        edAmt=view.findViewById(R.id.edt_amt_tr);
        edRemarks=view.findViewById(R.id.edt_remarks_tr);
    }

    private void setListeners() {

        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, spinnerItemsTrType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTrType.setAdapter(aa);
        spinTrType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemsSrc.clear();
                loadTarget(position);
                ArrayAdapter arrayAdapter;
                if(position==1){
                    spinnerItemsSrc.add("N/A");

                }
                else {
                    loadPockets();
                }
                arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, spinnerItemsSrc);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinSrc.setAdapter(arrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinSrc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                srcPos=position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinTarget.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                targetPos=position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new DatePickerDialog(getActivity(), date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                       myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/YYYY";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void loadPockets() {
        List<Pockets> pockets=SugarRecord.listAll(Pockets.class);
        for(Pockets pocket:pockets){
            spinnerItemsSrc.add(pocket.getPocket());
        }
    }

    private void loadTarget(int type) {
        spinnerItemsTarget.clear();
        if(type==0){
            List<Categories> categories = SugarRecord.listAll(Categories.class);
            for (Categories category : categories) {
                spinnerItemsTarget.add(category.getCategory());
            }
        }
        else if(type==2){
            spinnerItemsTarget.add("N/A");
        }
        else {
            List<Pockets> pockets = SugarRecord.listAll(Pockets.class);
            for (Pockets pocket : pockets) {
                spinnerItemsTarget.add(pocket.getPocket());
            }
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, spinnerItemsTarget);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTarget.setAdapter(arrayAdapter);
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
