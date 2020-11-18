    package app.techland.zainmobiles.ui.home;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import app.techland.zainmobiles.Adapter.MyAdapter;
import app.techland.zainmobiles.R;
import app.techland.zainmobiles.SplashActivity;
import app.techland.zainmobiles.Transformer.DepthPageTransformer;
import app.techland.zainmobiles.ui.keypadphone.KeypadPhoneFragment;
import app.techland.zainmobiles.ui.mobileaccessories.MobileAccessoriesFragment;
import app.techland.zainmobiles.ui.smartphone.SmartPhoneFragment;
import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment implements IFirebaseLoadDone {
    CardView mSmartPhoneCV, mKeypadPhoneCV, mMobileAccessoriesCV;
    ProgressDialog progressDialog;
    DatabaseReference dbRef;
    StorageReference storageReference;

    ViewPager viewPager;
    MyAdapter adapter;
    IFirebaseLoadDone iFirebaseLoadDone;
    List<HomeViewModel> imgList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mSmartPhoneCV = root.findViewById(R.id.mSmartPhoneCV);
        mKeypadPhoneCV = root.findViewById(R.id.mKeypadPhoneCV);
        mMobileAccessoriesCV = root.findViewById(R.id.mMobileAccessoriesCV);
         checkConnection();

        dbRef = FirebaseDatabase.getInstance().getReference("ViewFlipperMain");
        storageReference = FirebaseStorage.getInstance().getReference("VFPictures");

        iFirebaseLoadDone = this;
        loadBikes();

        viewPager = root.findViewById(R.id.view_pager);
        //for transformation when image slided
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setCurrentItem(0);




        mSmartPhoneCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new SmartPhoneFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        mKeypadPhoneCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new KeypadPhoneFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        mMobileAccessoriesCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, new MobileAccessoriesFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return root;
    }

    private void loadBikes() {

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot bikeSnapShot : dataSnapshot.getChildren())
                    imgList.add(bikeSnapShot.getValue(HomeViewModel.class));
                iFirebaseLoadDone.onFirebaseLoadSuccess(imgList);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Something wrong ....", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<HomeViewModel> imgList) {
        adapter = new MyAdapter(getContext(), imgList);
        viewPager.setAdapter(adapter);

        //for circle indicator
        CircleIndicator indicator = (CircleIndicator) getView().findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }

    public void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info==null){
            Toast.makeText(getActivity(), "Please Check Your Internet Connection and Try Again", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setTitle("No Internet");
            dialog.setMessage("Please Make Sure that your device is connected to the internet and Try Again");
            dialog.setIcon(R.drawable.signal);
            dialog.setCancelable(false);

            dialog.setPositiveButton("Got It", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Good", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), SplashActivity.class));
                }
            });
            dialog.show();

        }else {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Going Good...");
            progressDialog.setMessage("It takes Just a few Seconds(Loading)... ");
            progressDialog.setIcon(R.drawable.happy);
            progressDialog.setIcon(R.drawable.happy);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

}