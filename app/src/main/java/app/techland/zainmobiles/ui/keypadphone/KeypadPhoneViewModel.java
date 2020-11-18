package app.techland.zainmobiles.ui.keypadphone;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KeypadPhoneViewModel extends ViewModel {

    String spinnername;
    String name;
    String falseprice;
    String trueprice;
    String battery;
    String color;
    String sim;
    String camera;
    String img;
    String id;

    public KeypadPhoneViewModel() {
    }

    public String getSpinnername() {
        return spinnername;
    }

    public String getName() {
        return name;
    }

    public String getFalseprice() {
        return falseprice;
    }

    public String getTrueprice() {
        return trueprice;
    }

    public String getBattery() {
        return battery;
    }

    public String getColor() {
        return color;
    }

    public String getSim() {
        return sim;
    }

    public String getCamera() {
        return camera;
    }

    public String getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public KeypadPhoneViewModel(String spinnername, String name, String falseprice, String trueprice, String battery, String color, String sim, String camera, String img, String id) {
        this.spinnername = spinnername;
        this.name = name;
        this.falseprice = falseprice;
        this.trueprice = trueprice;
        this.battery = battery;
        this.color = color;
        this.sim = sim;
        this.camera = camera;
        this.img = img;
        this.id = id;
    }
}