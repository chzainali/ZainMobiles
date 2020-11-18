package app.techland.zainmobiles;

public class BrandNamesModel {
    String companyname,id;

    public BrandNamesModel() {
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getId() {
        return id;
    }

    public BrandNamesModel(String companyname, String id) {
        this.companyname = companyname;
        this.id = id;
    }
}
