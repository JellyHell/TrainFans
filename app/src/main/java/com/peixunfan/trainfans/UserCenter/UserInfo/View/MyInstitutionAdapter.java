package com.peixunfan.trainfans.UserCenter.UserInfo.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.SectionHeaderHolder;
import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class MyInstitutionAdapter  extends
        SectionedRecyclerViewAdapter<SectionHeaderHolder,MyInstitutionContentViewHolder,MyInstitutionFooterViewholder,RecyclerView.ViewHolder> {

    private Context mContext;

    public ArrayList<String> mMyInstitution = new ArrayList<>();
    public ArrayList<String> mJoinedInstitution = new ArrayList<>();

    public MyInstitutionAdapter(Context context,ArrayList<String> myInstitution,ArrayList<String> joinedInstitution) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMyInstitution = myInstitution;
        mJoinedInstitution = joinedInstitution;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getSectionCount() {
        return 2;
    }

    @Override
    protected int getItemCountForSection(int section) {
        if(section == 0){
            return mMyInstitution.size();
        }else{
            return mJoinedInstitution.size();
        }
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        if (section == 0){
            if(mMyInstitution.size()>0)
            {
                return false;
            }else{
                return true;
            }
        }else{
            if(mJoinedInstitution.size()>0)
            {
                return false;
            }else{
                return true;
            }
        }
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_myinstitution_content_layout;
    }

    @Override
    protected SectionHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new SectionHeaderHolder(mInflater.inflate(R.layout.viewholder_base_title_layout,parent,false));
    }

    @Override
    protected MyInstitutionFooterViewholder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return new MyInstitutionFooterViewholder(mInflater.inflate(R.layout.viewhodler_institution_footer_layout,parent,false));
    }

    @Override
    protected MyInstitutionContentViewHolder onCreateItemViewHolder(View arg0) {
        return new MyInstitutionContentViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(SectionHeaderHolder holder, int section) {
        if (section == 0){
            holder.titleView.setText("我的机构");
        }else if(section == 1){
            holder.titleView.setText("我加入的机构");
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(MyInstitutionFooterViewholder holder, int section) {
        if (section == 0){
//            mInstitutionHeaderImg.
        }else if(section == 1){

        }
    }

    @Override
    protected void onBindItemViewHolder(final MyInstitutionContentViewHolder holder, int section, int position) {
        //Cell信息显示
        if (section == 0){
            ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",holder.mInstitutionHeaderImg);
        }else{
            ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",holder.mInstitutionHeaderImg);
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mMyInstitution.size()-1){
            isSectionLastLine = true;
        }else if(section == 1 && position == mJoinedInstitution.size()-1){
            isSectionLastLine = true;
        }

        RelativeLayout.LayoutParams bottomLps = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        if (isSectionLastLine) {
            bottomLps.setMargins(0, 0, 0, 0);
        }else {
            bottomLps.setMargins(AppUtil.dip2px(mContext, 12), 0, 0, 0);
        }
    }

}