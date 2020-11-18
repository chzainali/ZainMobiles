package app.techland.zainmobiles.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    String name,image;

    public HomeViewModel() {
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public HomeViewModel(String name, String image) {
        this.name = name;
        this.image = image;
    }
}