package app.techland.zainmobiles.ui.keypadphone;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.techland.zainmobiles.BrandNamesModel;
import app.techland.zainmobiles.R;
import app.techland.zainmobiles.ui.smartphone.SmartPhoneFragment;
import app.techland.zainmobiles.ui.smartphone.SmartPhoneProductListFragment;

public class KeypadPhoneFragment extends Fragment {
    RecyclerView mRecView;
    DatabaseReference mDbRf;
    ProgressDialog progressDialog;
    private KeypadPhoneViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_keypadphone, container, false);


        mRecView = root.findViewById(R.id.mRecView);

        progressDialog = new ProgressDialog(getContext()) ;
        progressDialog.setMessage("Going Good(Loading)...");
        mDbRf = FirebaseDatabase.getInstance().getReference("BrandName(KeypadPhone)");
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

                viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String BrandName=model.getCompanyname();

                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment,new KeypadPhoneProductListFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        getActivity().getIntent().putExtra("brand_name", BrandName);


                    }
                });
                progressDialog.dismiss();

            }

            @NonNull
            @Override
            public SPModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dummy_keypad_phone , parent, false);
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