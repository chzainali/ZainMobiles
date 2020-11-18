package app.techland.zainmobiles.ui.mobileaccessories;

import android.app.ProgressDialog;
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
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.techland.zainmobiles.BrandNamesModel;
import app.techland.zainmobiles.R;
import app.techland.zainmobiles.ui.smartphone.SmartPhoneFragment;
import app.techland.zainmobiles.ui.smartphone.SmartPhoneProductListFragment;

public class MobileAccessoriesFragment extends Fragment {
    RecyclerView mRecView;
    DatabaseReference mDbRf;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mobile_accessories, container, false);

        mRecView = root.findViewById(R.id.mRecView);

        progressDialog = new ProgressDialog(getContext()) ;
        progressDialog.setMessage("Going Good(Loading)...");
        mDbRf = FirebaseDatabase.getInstance().getReference("BrandName(Accessories)");
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        mRecView.setLayoutManager(layoutManager);
        progressDialog.show();

    return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<BrandNamesModel> options = new FirebaseRecyclerOptions.Builder<BrandNamesModel>()
                .setQuery(mDbRf, BrandNamesModel.class)
                .build();

        final FirebaseRecyclerAdapter<BrandNamesModel, SPModelViewHolder> adapter = new FirebaseRecyclerAdapter<BrandNamesModel, SPModelViewHolder>(options) {
            @Override
            protected void onBindViewHolder (@NonNull SPModelViewHolder viewHolder, final int i, @NonNull BrandNamesModel model){

                viewHolder.mTextView.setText(model.getCompanyname());
                progressDialog.dismiss();

                viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String BrandName=model.getCompanyname();

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment,new MobileAccessoriesProductListFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getActivity().getIntent().putExtra("brand_name", BrandName);


                    }
                });

            }

            @NonNull
            @Override
            public SPModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dummy_mobile_accessories , parent, false);
                SPModelViewHolder viewHolder = new SPModelViewHolder(view);
                return viewHolder;
            }

        };
        mRecView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class SPModelViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public SPModelViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.mTextView);
        }
    }
}