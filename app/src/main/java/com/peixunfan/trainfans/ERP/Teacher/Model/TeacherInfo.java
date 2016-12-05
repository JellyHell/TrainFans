package com.peixunfan.trainfans.ERP.Teacher.Model;

import android.text.Html;
import android.text.Spanned;

import com.infrastructure.utils.TextUtil;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class TeacherInfo {
    public String teacherName;
    public String teacherSex;
    public String teacherSkill;
    public String teacherMobile;

    public String teacherSeparateType = "1";//分成方式 1：固定比例 2：固定金额  3：固定比例&固定金额
    public String teacherSeparatePercent;
    public String teacherSeparateCount;

    public String courseFluctuateType = "1";//课价浮动 1:固定金额 2 固定比例
    public String courseFluctuatePercent;
    public String courseFluctuateCount;


    public Spanned getSeparateTypeStr() {
        if("1".equals(teacherSeparateType)){
            return Html.fromHtml("<font color='#f67d53'>固定比例</font>");
        }else if("2".equals(teacherSeparateType)){
            return Html.fromHtml("<font color='#feb747'>固定金额</font>");
        }else{
            return Html.fromHtml("<font color='#f67d53'>固定比例</font>&<font color='#feb747'>固定金额</font>");
        }
    }

    public Spanned getFluctuateStr(){
        if("1".equals(courseFluctuateType)){
            return Html.fromHtml("<font color='#529ce7'>固定金额</font>");
        }else{
            return Html.fromHtml("<font color='#15b98b'>固定比例</font>");
        }
    }

}
