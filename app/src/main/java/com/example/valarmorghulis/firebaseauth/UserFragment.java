package com.example.valarmorghulis.firebaseauth;

import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    MaterialButton userAccount;
    MaterialButton userOrders;
    MaterialButton userBuys;
    MaterialButton userSales;
    MaterialButton userProducts;
    MaterialButton userAnalytics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        userAccount = v.findViewById(R.id.user_account);
        userAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processAccountRequest();
            }
        });

        userOrders = v.findViewById(R.id.user_orders);
        userOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processOrderRequest();
            }
        });

        userBuys = v.findViewById(R.id.user_buys);
        userBuys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processBuyRequest();
            }
        });

        userSales = v.findViewById(R.id.user_sales);
        userSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processSaleRequest();
            }
        });

        userProducts = v.findViewById(R.id.user_products);
        userProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processProductRequest();
            }
        });

        userAnalytics = v.findViewById(R.id.user_analytics);
        userAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processAnalyticRequest();
            }
        });

        return v;
    }

    private void processAccountRequest(){
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        profileFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, profileFragment)
                .commit();

        return;
    }

    private void processOrderRequest(){
        UserOrderFragment userorderFragment = new UserOrderFragment();
        Bundle bundle = new Bundle();
        userorderFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, userorderFragment)
                .commit();

        return;
    }

    private void processBuyRequest(){
        UserBuyFragment userbuyFragment = new UserBuyFragment();
        Bundle bundle = new Bundle();
        userbuyFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, userbuyFragment)
                .commit();

        return;
    }

    private void processSaleRequest(){
        UserSaleFragment usersaleFragment = new UserSaleFragment();
        Bundle bundle = new Bundle();
        usersaleFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, usersaleFragment)
                .commit();

        return;
    }

    private void processProductRequest(){
        UserProductFragment userproductFragment = new UserProductFragment();
        Bundle bundle = new Bundle();
        userproductFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, userproductFragment)
                .commit();

        return;
    }

    private void processAnalyticRequest(){
        UserAnalyticFragment useranalyticFragment = new UserAnalyticFragment();
        Bundle bundle = new Bundle();
        useranalyticFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, useranalyticFragment)
                .commit();

        return;
    }
}