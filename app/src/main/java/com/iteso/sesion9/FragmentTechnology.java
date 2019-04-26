package com.iteso.sesion9;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.sesion9.DatBase.DatabaseHandler;
import com.iteso.sesion9.DatBase.ItemProductControl;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.tools.Constant;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTechnology extends Fragment {


    RecyclerView recyclerView;
    ArrayList<ItemProduct> products;
    AdapterProduct adapterProduct;

    public FragmentTechnology() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom, container, false);
        recyclerView =
                view.findViewById(R.id.fragment_recycler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);


        DatabaseHandler dh = DatabaseHandler.getInstance(MainActivity.context);


        products = ItemProductControl.getItemProductsByCategory(1,dh);
        //products.add(new ItemProduct(1, "Mac", "BestBuy", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_MAC));
        //products.add(new ItemProduct(2, "Alienware", "DELL", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_ALIENWARE));
        //products.add(new ItemProduct(3, "Lanix", "Saint Jhonny", "Zapopan", "3312345678", "Lorem Ipsum ....", Constant.TYPE_ALIENWARE));


        adapterProduct = new AdapterProduct(Constant.FRAGMENT_TECHNOLOGY, getActivity(), products);
        recyclerView.setAdapter(adapterProduct);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ItemProduct itemProduct = data.getParcelableExtra(Constant.EXTRA_PRODUCT);
        Iterator<ItemProduct> iterator = products.iterator();
        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                products.set(position, itemProduct);
                break;
            }
            position++;
        }
        adapterProduct.notifyDataSetChanged();

    }

}
