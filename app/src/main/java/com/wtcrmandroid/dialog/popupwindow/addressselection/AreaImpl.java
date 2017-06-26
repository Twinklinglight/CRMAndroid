package com.wtcrmandroid.dialog.popupwindow.addressselection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baidu.location.BDLocation;
import com.wtcrmandroid.WTDataBaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Area操作接口实现类
 * Created by Mr-Zhang on 2016/3/24.
 */
public class AreaImpl implements IAreaModule {
    private SQLiteDatabase db;
    //private String url = Const.httpsString + Const.wtServerString + "/PostData.ashx?type=GetCityNew&r=" + Math.random() * 100000;
//    private GeoCoder mSearch = GeoCoder.newInstance();//必须在此初始化，否则可能会引起Fragment中无法定位问题

    private Area fetch(Cursor cur) {
        Area area = new Area();
        area.setId(cur.getInt(cur.getColumnIndex("Id")));
        area.setSheng(cur.getString(cur.getColumnIndex("sheng")));
        area.setShi(cur.getString(cur.getColumnIndex("shi")));
        area.setXian(cur.getString(cur.getColumnIndex("xian")));
        area.setLat(cur.getString(cur.getColumnIndex("lat")));
        area.setLng(cur.getString(cur.getColumnIndex("lng")));
        return area;
    }

    public AreaImpl() {
        this.db = WTDataBaseManager.getsInstance().getDbPeiHuoReadOnly();
    }

    /**
     * 从服务端获取省市县数据
     *
     * @param onGetAreaListener 回调
     */
    @Override
    public void getAreaFromServer(OnGetAreaListener onGetAreaListener) {

    }

    /**
     * 通过id从数据库获取Area
     *
     * @param areaId 地区id
     */
    @Override
    public Area getAreaById(int areaId) {
        Area area = new Area();
        String sql = "select * from _wutong_area_new_latlng where Id=" + areaId;
        try {
            Cursor cur = db.rawQuery(sql, null);
            while (cur.moveToNext()) {
                area = fetch(cur);
            }
            cur.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return area;
    }

    /**
     * 保存省市县数据
     *
     * @param areaArrayList Area集合
     */
    @Override
    public void saveAreaToDatabase(ArrayList<Area> areaArrayList) {
    }

    /**
     * 获取数据库中的所有省
     */
    @Override
    public List<Area> getAllProvince() {
        ArrayList<Area> list = new ArrayList<>();
        String sql = "SELECT * FROM _wutong_area_new_latlng WHERE id IN (SELECT MIN(id)AS id FROM _wutong_area_new_latlng  GROUP BY sheng ORDER BY id ASC )";
        try {
            Cursor cur = db.rawQuery(sql, null);
            while (cur.moveToNext()) {
                list.add(fetch(cur));
            }
            cur.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 获取某省的所有市
     *
     * @param province 省得中文名(准确的全称)
     */
    @Override
    public List<Area> selectByProvince(String province) {
        ArrayList<Area> list = new ArrayList<>();
        String sql = "SELECT * FROM _wutong_area_new_latlng WHERE id IN (SELECT MIN(id)AS id FROM _wutong_area_new_latlng where sheng ='"
                + province + "'  GROUP BY shi ORDER BY id ASC )";
        try {
            Cursor cur = db.rawQuery(sql, null);
            while (cur.moveToNext()) {
                list.add(fetch(cur));
            }
            cur.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 获取某省某市的所有县
     *
     * @param province 省的中文名(全称)
     * @param city     市的中文名(全称)
     */
    @Override
    public List<Area> selectByCity(String province, String city) {
        ArrayList<Area> list = new ArrayList<>();
        String sql = "select * from _wutong_area_new_latlng  where sheng ='"
                + province + "' and shi ='" + city + "'";
        try {
            Cursor cur = db.rawQuery(sql, null);
            while (cur.moveToNext()) {
                list.add(fetch(cur));
            }
            cur.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * 根据省市县查Area的id
     *
     * @param province 省全称
     * @param city     市全称
     * @param town     县全称
     */
    @Override
    public Area selectAreaByPct(String province, String city, String town) {
        Area area = new Area();
        String sql = "select * from _wutong_area_new_latlng where sheng =? and shi=? and xian=?";
        try {
            Cursor cur = db.rawQuery(sql, new String[]{province, city, town});
            if (cur != null) {
                if (cur.moveToNext()) {
                    area = fetch(cur);
                }
                cur.close();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return area;
    }

    /**
     * 根据省、市、市辖区查询
     *
     * @param province 省全称
     * @param city     市全称
     */
    @Override
    public Area selectAreaByPc(String province, String city) {
        Area area = new Area();
        String sql = "select * from _wutong_area_new_latlng where sheng =? and shi=? and xian='市辖区'";
        try {
            Cursor cur = db.rawQuery(sql, new String[]{province, city});
            if (cur != null) {
                if (cur.moveToNext()) {
                    area = fetch(cur);
                }
                cur.close();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return area;
    }

    /**
     * 将百度定位转换为本地数据存在的Area对象
     *
     * @param bdLocation 百度定位
     * @return
     */
    @Override
    public Area convertLocation2Area(BDLocation bdLocation) {
        Area area = null;
        String sheng = bdLocation.getProvince();
        String shi = bdLocation.getCity();
        String xian = bdLocation.getDistrict();
        String street = bdLocation.getStreet();
        String streetNumber = bdLocation.getStreetNumber();
        area = selectAreaByPc(sheng, shi);
        if (area == null || area.getId() == 0) {
            area = new Area();
            area.setSheng(sheng);
            area.setShi(shi);
            area.setXian(xian);
            area.setStreet(street);
            area.setStreet_number(streetNumber);
            area.setLat(String.valueOf(bdLocation.getLatitude()));
            area.setLng(String.valueOf(bdLocation.getLongitude()));
        }
        return area;
    }

//    @Override
//    public void convertLatLng2Area(LatLng latLng, final OnConvertListener onConvertListener) {
//        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
//        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//            @Override
//            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
//
//            }
//
//            @Override
//            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
//                if (reverseGeoCodeResult == null
//                        || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
//                    // 没有找到检索结果
//                    onConvertListener.Failed();
//                } else {
//                    // 获取反向地理编码结果
//                    String sheng = reverseGeoCodeResult.getAddressDetail().province;
//                    String shi = reverseGeoCodeResult.getAddressDetail().city;
//                    String xian = reverseGeoCodeResult.getAddressDetail().district;
//                    String street = reverseGeoCodeResult.getAddressDetail().street;
//                    String streetNumber = reverseGeoCodeResult.getAddressDetail().streetNumber;
//                    if (selectAreaByPct(sheng, shi, xian).getSheng() != null) {
//                        onConvertListener.Success(selectAreaByPct(sheng, shi, xian));
//                    } else {
//                        onConvertListener.Success(selectAreaByPc(sheng, shi));
//                    }
//                }
//            }
//        });
//    }

}
