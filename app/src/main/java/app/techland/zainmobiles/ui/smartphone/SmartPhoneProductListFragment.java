package app.techland.zainmobiles.ui.smartphone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import app.techland.zainmobiles.R;

public class SmartPhoneProductListFragment extends Fragment {
    RecyclerView mRecView;
    ProgressDialog progressDialog;
    DatabaseReference mDbRf;
    String getBrandName;
    DatabaseReference dbRef;
    StorageReference storageReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_smart_phone_product_list, container, false);
        progressDialog = new ProgressDialog(getContext()) ;
        progressDialog.setMessage("Going Good(Loading)...");

        mRecView = root.findViewById(R.id.mRecView);
        getBrandName = getActivity().getIntent().getStringExtra("brand_name");

        mDbRf = FirebaseDatabase.getInstance().getReference("PhoneName").child(getBrandName);
        storageReference = FirebaseStorage.getInstance().getReference("PhonePicture");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecView.setLayoutManager(layoutManager);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<SmartPhoneViewModel> options = new FirebaseRecyclerOptions.Builder<SmartPhoneViewModel>()
                .setQuery(mDbRf, SmartPhoneViewModel.class)
                .build();

        final FirebaseRecyclerAdapter<SmartPhoneViewModel, SPModelViewHolder> adapter = new FirebaseRecyclerAdapter<SmartPhoneViewModel, SPModelViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SPModelViewHolder modelViewHolder, int i, @NonNull SmartPhoneViewModel smartPhoneProductListModel) {
               modelViewHolder.mNameTV.setText(smartPhoneProductListModel.getName());
                modelViewHolder.mFPriceET.setText(smartPhoneProductListModel.getFalseprice());
                modelViewHolder.mTPriceET.setText(smartPhoneProductListModel.getTrueprice());
                Glide.with(getActivity()).load(smartPhoneProductListModel.getImg()).into(modelViewHolder.mImageView);
                progressDialog.dismiss();

                modelViewHolder.mMain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String BrandName=smartPhoneProductListModel.getSpinnername();
                        String BrandId= smartPhoneProductListModel.getId();

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment,new SmartPhoneProductDetailFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getActivity().getIntent().putExtra("brand_name", BrandName);
                        getActivity().getIntent().putExtra("brand_id", BrandId);


                    }
                });

            }

            @NonNull
            @Override
            public SPModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dummy_smart_phone_product, parent, false);
                SPModelViewHolder modelViewHolder = new SPModelViewHolder(view);
                return modelViewHolder;
            }
        };
        mRecView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class SPModelViewHolder extends RecyclerView.ViewHolder {
        TextView mNameTV, mFPriceET, mTPriceET;
        ImageView mImageView;
        LinearLayout mMain;

        public SPModelViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.mImageView);
            mNameTV = itemView.findViewById(R.id.mNameTV);
            mFPriceET = itemView.findViewById(R.id.mFPriceET);
            mTPriceET = itemView.findViewById(R.id.mTPriceET);
            mMain = itemView.findViewById(R.id.mMain);
        }
    }
}