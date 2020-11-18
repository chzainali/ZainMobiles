package app.techland.zainmobiles.ui.mobileaccessories;

public class MobileAccessoriesViewModel {
    String spinnername;
    String name;
    String falseprice;
    String trueprice;
    String color;
    String description;
    String img;
    String id;

    public MobileAccessoriesViewModel() {
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

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public MobileAccessoriesViewModel(String spinnername, String name, String falseprice, String trueprice, String color, String description, String img, String id) {
        this.spinnername = spinnername;
        this.name = name;
        this.falseprice = falseprice;
        this.trueprice = trueprice;
        this.color = color;
        this.description = description;
        this.img = img;
        this.id = id;
    }
}
