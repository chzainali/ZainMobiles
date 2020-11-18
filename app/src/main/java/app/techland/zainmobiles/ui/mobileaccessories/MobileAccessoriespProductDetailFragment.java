package app.techland.zainmobiles.ui.mobileaccessories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import app.techland.zainmobiles.R;
import app.techland.zainmobiles.ui.contactus.ContactUsFragment;


public class MobileAccessoriespProductDetailFragment extends Fragment {
    TextView mName, mFPrice, mTPrice, mColor, mDescription;
    ImageView mImg;
    Button mContactBtn;
    String mNameStr, mFPriceStr, mTPriceStr, mColorStr, mDescriptionStr;
    String getBrandName,getBrandId;
    DatabaseReference dbRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_mobile_accessoriesp_product_detail, container, false);

        mImg = root.findViewById(R.id.mImg);
        mName = root.findViewById(R.id.mName);
        mFPrice = root.findViewById(R.id.mFPrice);
        mTPrice = root.findViewById(R.id.mTPrice);
        mColor = root.findViewById(R.id.mColor);
        mDescription = root.findViewById(R.id.mDescription);
        mContactBtn = root.findViewById(R.id.mContactBtn);

        getBrandName = getActivity().getIntent().getStringExtra("brand_name");
        getBrandId = getActivity().getIntent().getStringExtra("brand_id");
        dbRef = FirebaseDatabase.getInstance().getReference("PhoneItems(Accessories)").child(getBrandName).child(getBrandId);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String image = dataSnapshot.child("img").getValue(String.class);
                String name = dataSnapshot.child("name").getValue(String.class);
                String falseprice = dataSnapshot.child("falseprice").getValue(String.class);
                String trueprice = dataSnapshot.child("trueprice").getValue(String.class);
                String color = dataSnapshot.child("color").getValue(String.class);
                String description = dataSnapshot.child("description").getValue(String.class);

                Picasso.get().load(image).into(mImg);
                mName.setText(name);
                mFPrice.setText(falseprice);
                mTPrice.setText(trueprice);
                mColor.setText(color);
                mDescription.setText(description);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment,new ContactUsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

   return root;
    }
}