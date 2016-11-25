package com.peixunfan.trainfans.ERP.Courses.Model;

import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;

import com.infrastructure.utils.TextUtil;

/**
 * Created by Administrator on 2016/11/24.
 */

public class Course {
    //基础信息
    public String course_name;
    public String class_type;
    public String teach_type; // 1:一对一  2：一对多
    public String course_length;
    public String charge_type;//1.按课节收费 2.按期收费 3.按课节|按期收费

    //课节单价
    public String course_unit_price;

    //总课节及总课价格
    public String course_count;
    public String course_price;

    //未到学生处理方式
    public String makeup_class_mark;

    public String getTeach_typeStr() {
        if(TextUtil.isEmpty(teach_type)){
            return "选择";
        }else if("1".equals(teach_type)){
            return "一对一";
        }else{
            return "一对多";
        }
    }

    public Spanned getCharge_typeStr() {
        if(TextUtil.isEmpty(charge_type)){
            return Html.fromHtml("<font color='#7b7b81'>选择</font>");
        }else if("1".equals(charge_type)){
            return Html.fromHtml("<font color='#8f6fe9'>按课节收费</font>");
        }else if("2".equals(charge_type)){
            return Html.fromHtml("<font color='#59a0e5'>按期收费</font>");
        }else{
            return Html.fromHtml("<font color='#8f6fe9'>按课节收费</font>&<font color='#59a0e5'>按期收费</font>");
        }
    }
}
