package com.example.comp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerViewList;
    SearchAdapter adapter;
    ArrayList<ProductsModel> dataList;
    private ImageView image_pr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerViewList = view.findViewById(R.id.recycle_view_search);
        firebaseFirestore = FirebaseFirestore.getInstance();


        recyclerViewList.setHasFixedSize(true);
        recyclerViewList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        dataList = new ArrayList<>();
        adapter = new SearchAdapter(dataList);
        recyclerViewList.setAdapter(adapter);

        firebaseFirestore.collection("Products").get().
                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot d : list) {
                            ProductsModel obj=d.toObject(ProductsModel.class);
                            dataList.add(obj);
                        }
                        adapter.notifyDataSetChanged();
                    }

                });


        return view;
    }
}
