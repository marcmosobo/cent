package com.example.valarmorghulis.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServiceFragment extends Fragment {
    MaterialButton buyGrocery;
    MaterialButton buyFood;
    MaterialButton poshoMill;
    MaterialButton buyGas;
    MaterialButton buyCereal;
    MaterialButton shop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,
                        @Nullable     Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_service, container, false);

        buyGrocery = v.findViewById(R.id.grocery);
        buyGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processGroceryRequest();
            }
        });

        buyFood = v.findViewById(R.id.food);
        buyFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processFoodRequest();
            }
        });

        poshoMill = v.findViewById(R.id.poshomill);
        poshoMill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processPoshomillRequest();
            }
        });

        buyGas = v.findViewById(R.id.gas);
        buyGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processGasRequest();
            }
        });

        buyCereal = v.findViewById(R.id.cereal);
        buyCereal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processCerealRequest();
            }
        });

        shop = v.findViewById(R.id.shop);
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processShopRequest();
            }
        });
        return v;
    }

    private void processGroceryRequest(){
        GroceryFragment groceryFragment = new GroceryFragment();
        Bundle bundle = new Bundle();
        groceryFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, groceryFragment)
                .commit();

        return;
    }

    private void processPoshomillRequest(){
        PoshomillFragment poshomillFragment = new PoshomillFragment();
        Bundle bundle = new Bundle();
        poshomillFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, poshomillFragment)
                .commit();

        return;
    }

    private void processFoodRequest(){
        FoodFragment foodFragment = new FoodFragment();
        Bundle bundle = new Bundle();
        foodFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, foodFragment)
                .commit();

        return;
    }

    private void processGasRequest(){
        GasFragment gasFragment = new GasFragment();
        Bundle bundle = new Bundle();
        gasFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, gasFragment)
                .commit();

        return;
    }

    private void processCerealRequest(){
        CerealFragment cerealFragment = new CerealFragment();
        Bundle bundle = new Bundle();
        cerealFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, cerealFragment)
                .commit();

        return;
    }

    private void processShopRequest(){
        ShopFragment shopFragment = new ShopFragment();
        Bundle bundle = new Bundle();
        shopFragment.setArguments(bundle);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction().replace(R.id.frag_container, shopFragment)
                .commit();

        return;
    }
}