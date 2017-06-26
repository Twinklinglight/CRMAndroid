package com.wtcrmandroid.dialog.popupwindow.addressselection;

import com.baidu.location.BDLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * AreaModule
 * Area数据操作类
 * Created by Mr-Zhang on 2016/3/24.
 */
public interface IAreaModule {
    void getAreaFromServer(OnGetAreaListener onGetAreaListener);

    Area getAreaById(int areaId);

    List<Area> getAllProvince();

    List<Area> selectByProvince(String province);

    List<Area> selectByCity(String province, String city);

    Area selectAreaByPct(String province, String city, String town);

    Area selectAreaByPc(String province, String city);

    void saveAreaToDatabase(ArrayList<Area> areaArrayList);

    Area convertLocation2Area(BDLocation bdLocation);

//    void convertLatLng2Area(LatLng latLng, OnConvertListener onConvertListener);

    interface OnConvertListener {
        void Success(Area area);

        void Failed();
    }

    interface OnGetAreaListener {
        void Success(ArrayList<Area> areaArrayList);

        void Success(Area area);

        void Failed();
    }
}
