package com.peixunfan.trainfans.ERP.StudentList.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/22.
 */

public class StudentTypeSelectPW {
    Context mContext;

    View mView;

    PopupWindow popupWindow;

    RecyclerView recyclerView;

    ArrayList<String> mStudentTypes = new ArrayList<>();

    StudentTypeSelectAdapter mStudentTypeSelectAdapter;

    public StudentTypeSelectPW(Context context,ArrayList<String> studentTypes){
        mContext = context;
        mStudentTypes.addAll(studentTypes);
        initUI();
    }

    private void initUI(){
        mView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_selectstudent_type,null);
        recyclerView =(RecyclerView)mView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mStudentTypeSelectAdapter = new StudentTypeSelectAdapter(mContext,mStudentTypes);
        recyclerView.setAdapter(mStudentTypeSelectAdapter);
    }

    public void show(View view){
        popupWindow = new PopupWindow(mView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), (Bitmap) null));
        popupWindow.showAsDropDown(view,-AppUtil.dip2px(mContext,18),0);
    }

    public class StudentTypeSelectAdapter extends BaseAdapter<String>{

        public StudentTypeSelectAdapter(Context context, ArrayList<String> datas) {
            super(context, datas);
        }

        @Override
        protected int getResourceId() {
            return R.layout.adapter_type_select_item;
        }

        @Override
        protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
            return new ItemViewHolder(arg0, this.mContext);
        }

        @Override
        protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;

            if (mItemClickListener != null) {
                aItemViewHolder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
            }

            if(position == mDatas.size() - 1)
            {
                aItemViewHolder.bottomLine.setVisibility(View.GONE);
            }else{
                aItemViewHolder.bottomLine.setVisibility(View.VISIBLE);
            }

            aItemViewHolder.mTypeTv.setText(mDatas.get(position));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_type)
        TextView mTypeTv;
        @Bind(R.id.iv_bottom_line)
        View bottomLine;

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }

}
