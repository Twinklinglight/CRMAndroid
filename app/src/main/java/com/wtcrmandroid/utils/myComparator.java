package com.wtcrmandroid.utils;

import android.icu.text.CollationKey;
import android.icu.text.Collator;

import java.util.Comparator;

/**
 * Created by 1363655717 on 2017-06-09.
 */

public class myComparator implements Comparator {

    //关于Collator。
    private Collator collator = Collator.getInstance();//点击查看中文api详解

    public myComparator() {
    }



    public int compare(Object o1, Object o2) {

        //把字符串转换为一系列比特，它们可以以比特形式与 CollationKeys 相比较
        CollationKey key1=collator.getCollationKey(o1.toString());//要想不区分大小写进行比较用o1.toString().toLowerCase()
        CollationKey key2=collator.getCollationKey(o2.toString());

        return key1.compareTo(key2);//返回的分别为1,0,-1 分别代表大于，等于，小于。要想按照字母降序排序的话 加个“-”号
    }
}