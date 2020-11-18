package app.techland.zainmobiles.ui.smartphone;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmartPhoneViewModel extends ViewModel {

    String spinnername;
    String name;
    String falseprice;
    String trueprice;
    String storage;
    String color;
    String screen;
    String fcamera;
    String bcamera;
    String battery;
    String img;
    String id;

    public SmartPhoneViewModel() {
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

    public String getStorage() {
        return storage;
    }

    public String getColor() {
        return color;
    }

    public String getScreen() {
        return screen;
    }

    public String getFcamera() {
        return fcamera;
    }

    public String getBcamera() {
        return bcamera;
    }

    public String getBattery() {
        return battery;
    }

    public String getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public SmartPhoneViewModel(String spinnername, String name, String falseprice, String trueprice, String storage, String color, String screen, String fcamera, String bcamera, String battery, String img, String id) {
        this.spinnername = spinnername;
        this.name = name;
        this.falseprice = falseprice;
        this.trueprice = trueprice;
        this.storage = storage;
        this.color = color;
        this.screen = screen;
        this.fcamera = fcamera;
        this.bcamera = bcamera;
        this.battery = battery;
        this.img = img;
        this.id = id;
    }
}