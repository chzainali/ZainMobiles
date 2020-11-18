package app.techland.zainmobiles.ui.home;

import java.util.List;

public interface IFirebaseLoadDone {

    void onFirebaseLoadSuccess(List<HomeViewModel> imgList);
    void onFirebaseLoadFailed(String message);
}
