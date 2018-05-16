package com.hackathon.nationwide.myapplication.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.plus.PlusOneButton;
import com.hackathon.nationwide.myapplication.Controller.VolleyController;
import com.hackathon.nationwide.myapplication.MainActivity;
import com.hackathon.nationwide.myapplication.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * {@link ATMFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ATMFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ATMFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PlusOneButton mPlusOneButton;
    private AppCompatButton mSearchButton;

    private OnFragmentInteractionListener mListener;

    public ATMFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ATMFragment newInstance(String param1, String param2) {
        ATMFragment fragment = new ATMFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atm, container, false);
        mSearchButton = (AppCompatButton) view.findViewById(R.id.search);
        mSearchButton.setOnClickListener((View.OnClickListener) this);
        //Find the +1 button
        //mPlusOneButton = (PlusOneButton) view.findViewById(R.id.plus_one_button);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh the state of the +1 button each time the activity receives focus.
        //mPlusOneButton.initialize(PLUS_ONE_URL, PLUS_ONE_REQUEST_CODE);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        JsonObjectRequest request1 = new JsonObjectRequest(
                "https://atlas.api.barclays/open-banking/v2.1/atms",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("Hackathon", "Response is: "+ response.toString());
                            List<JSONObject> branches = new ArrayList<JSONObject>();
                            JSONArray availableBranches = response.getJSONArray("data").getJSONObject(0).getJSONArray("Brand").getJSONObject(0).getJSONArray("ATM");

                            List<String> selectedServices = new ArrayList<String>();
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.ctv_atm_balance)).isChecked()) {
                                selectedServices.add("Balance");
                            }
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.ctv_atm_mobile_topup)).isChecked()) {
                                selectedServices.add("MobilePhoneTopUp");
                            }
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.ctv_atm_pin_change)).isChecked()) {
                                selectedServices.add("PINChange");
                            }
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.ctv_atm_withdraw)).isChecked()) {
                                selectedServices.add("CashWithdrawal");
                            }

                            List<String> selectedaccessibility = new ArrayList<String>();
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.acc_wheel)).isChecked()) {
                                selectedaccessibility.add("WheelchairAccess");
                            }
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.acc_auto_doors)).isChecked()) {
                                selectedaccessibility.add("AutomaticDoors");
                            }
                            if(((AppCompatCheckBox)getActivity().findViewById(R.id.acc_level_access)).isChecked()) {
                                selectedaccessibility.add("LevelAccess");
                            }

                            for(int i=0; i<availableBranches.length(); i++){
                                JSONObject branch = availableBranches.getJSONObject(i);
                                JSONArray facilities = availableBranches.getJSONObject(i).optJSONArray("ATMServices");
                                JSONArray accessibility = availableBranches.getJSONObject(i).optJSONArray("Accessibility");

                                boolean optionAvailable = true;

                                if(facilities != null) {
                                    if(selectedServices.contains("Balance")) {
                                        optionAvailable = facilities.toString().contains("Balance");
                                    }
                                    if(selectedServices.contains("MobilePhoneTopUp")) {
                                        optionAvailable = facilities.toString().contains("MobilePhoneTopUp");
                                    }
                                    if(selectedServices.contains("PINChange")) {
                                        optionAvailable = facilities.toString().contains("PINChange");
                                    }
                                    if(selectedServices.contains("CashWithdrawal")) {
                                        optionAvailable = facilities.toString().contains("CashWithdrawal");
                                    }
                                }

                                if (accessibility != null) {
                                    if (selectedaccessibility.contains("WheelchairAccess")) {
                                        optionAvailable = accessibility.toString().contains("WheelchairAccess");
                                    }
                                    if (selectedaccessibility.contains("AutomaticDoors")) {
                                        optionAvailable = accessibility.toString().contains("AutomaticDoors");
                                    }
                                    if (selectedaccessibility.contains("LevelAccess")) {
                                        optionAvailable = accessibility.toString().contains("LevelAccess");
                                    }
                                }

                                if(optionAvailable){
                                    branches.add(branch);
                                }
                                //if (branches.size() == 100) break;
                            }

                            mListener.onFragmentInteraction(branches, MainActivity.ResponseType.ATM);
                            //Log.d("Hackathon", "Filter list size : "+branches.size());
                        }catch (Exception e){
                            Log.e("Hackathon", e.getMessage());
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Something wrong happend!", Toast.LENGTH_LONG).show();
                    }
                }
        );
        VolleyController.getInstance(getActivity()).addToRequestQueue(request1);
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(List<JSONObject> list, MainActivity.ResponseType responseType);
    }

}
