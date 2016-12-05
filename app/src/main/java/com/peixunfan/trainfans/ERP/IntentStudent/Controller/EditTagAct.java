package com.peixunfan.trainfans.ERP.IntentStudent.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.infrastructure.ui.supertoast.SuperToast;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.TextUtil;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.IntentStudent.View.TagView;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class EditTagAct extends BaseActivity {

    @Bind(R.id.llv_taglayout)
    LinearLayout mTopTagLayout;

    @Bind(R.id.llv_taglayout_recommand)
    LinearLayout mRecommandTagLayout;

    @Bind(R.id.iv_eidt_recommand)
    ImageView mEditRecommandIv;

    @Bind(R.id.et_username)
    EditText mAddTagEditView;

    @Bind(R.id.bt_edit)
    RelativeLayout mAddTagEdit;




    ArrayList<String> mTopTags = new ArrayList();
    ArrayList<String> mBottomTags = new ArrayList();

    boolean recommandTadEditState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edittag_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("编辑标签");
        setRightManagerTv("完成");
        showBackButton();
        mEditRecommandIv.setOnClickListener(v -> {
            if(recommandTadEditState){
                mEditRecommandIv.setBackgroundResource(R.drawable.icon_edit);
            }else{
                mEditRecommandIv.setBackgroundResource(R.drawable.icon_complete);
            }
            recommandTadEditState = !recommandTadEditState;

            setBottomTagView(recommandTadEditState);
        });


        mAddTagEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTag();
            }
        });
    }


    @Override
    protected void initVariables() {
        mTopTags.add("小鲜肉");
        mTopTags.add("天才");
        mTopTags.add("天生神力");

        mBottomTags.add("小鲜肉");
        mBottomTags.add("天才");
        mBottomTags.add("天生神力");
        mBottomTags.add("有才华的逗逼");
        mBottomTags.add("土豪");
        mBottomTags.add("钢琴世家");
        mBottomTags.add("奥数第一名");
    }

    @Override
    protected void loadData() {
        setTopTagView();
        setBottomTagView(recommandTadEditState);
    }


    public void setTopTagView(){

        mTopTagLayout.removeAllViews();

        int size = mTopTags.size();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        layoutParams.setMargins(10, 0, 10, 0);

        LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        itemParams.setMargins(AppUtil.dip2px(this,4),AppUtil.dip2px(this,12),AppUtil.dip2px(this,4),AppUtil.dip2px(this,0));

        ArrayList<TagView> childBtns = new ArrayList<TagView>();

        int currentWidth = 0;
        for(int i = 0; i < size; i++){

            //1.初始化tagview
            TagView tagView = new TagView(this,mTopTags.get(i),true,i);
            tagView.getView().setLayoutParams(itemParams);
            tagView.getView().setTag(i);
            tagView.getView().setOnClickListener(v -> {
                mTopTags.remove((int)(Integer)v.getTag());
                setTopTagView();
                setBottomTagView(recommandTadEditState);
            });
            childBtns.add(tagView);

            //2.判断当前的长度
            String item = mTopTags.get(i);
            int length= item.length();
            currentWidth = currentWidth + (int)AppUtil.sp2px(this,14)*length + AppUtil.dip2px(this,20);
            currentWidth = currentWidth + AppUtil.dip2px(this,18);

            if(currentWidth > AppUtil.getWidth(this)){
                LinearLayout  horizLL = new LinearLayout(this);
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for(TagView addBtn:childBtns.subList(0,childBtns.size()-1)){
                    horizLL.addView(addBtn.getView());
                }
                mTopTagLayout.addView(horizLL);

                childBtns.clear();
                currentWidth = 0;

                childBtns.add(tagView);
                currentWidth = currentWidth + (int)AppUtil.sp2px(this,14)*length + AppUtil.dip2px(this,20);
            }
        }


        LinearLayout.LayoutParams lastLinelayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        lastLinelayoutParams.setMargins(10, 0, 10, AppUtil.dip2px(this,10));

        //最后一行添加一下
        if(!childBtns.isEmpty()){
            LinearLayout horizLL = new LinearLayout(this);
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(lastLinelayoutParams);

            for(TagView addBtn:childBtns){
                horizLL.addView(addBtn.getView());
            }
            mTopTagLayout.addView(horizLL);
            childBtns.clear();
        }

    }



    public void setBottomTagView(boolean canEdit){

        mRecommandTagLayout.removeAllViews();

        int size = mBottomTags.size();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        layoutParams.setMargins(10, 0, 10, 0);

        LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        itemParams.setMargins(AppUtil.dip2px(this,4),AppUtil.dip2px(this,12),AppUtil.dip2px(this,4),AppUtil.dip2px(this,0));

        ArrayList<TagView> childBtns = new ArrayList<TagView>();

        int currentWidth = 0;
        for(int i = 0; i < size; i++){

            //1.初始化tagview
            TagView tagView = new TagView(this,mBottomTags.get(i),canEdit,i);
            tagView.getView().setLayoutParams(itemParams);
            tagView.getView().setTag(i);
            tagView.getView().setOnClickListener(v -> {
                if(canEdit){
                    mBottomTags.remove((int)(Integer)v.getTag());
                    setBottomTagView(true);
                }else{
                    if(!mTopTags.contains(mBottomTags.get((Integer)v.getTag()))){
                        mTopTags.add(mBottomTags.get((Integer)v.getTag()));
                        setTopTagView();
                        setBottomTagView(recommandTadEditState);
                    }
                }
            });
            childBtns.add(tagView);

            if(mTopTags.contains(mBottomTags.get(i))){
                tagView.setDisabled();
            }

            //2.判断当前的长度
            String item = mBottomTags.get(i);
            int length= item.length();
            currentWidth = currentWidth + (int)AppUtil.sp2px(this,14)*length + AppUtil.dip2px(this,20);
            if(canEdit){
                currentWidth = currentWidth + AppUtil.dip2px(this,18);
            }

            if(currentWidth > AppUtil.getWidth(this)){
                LinearLayout  horizLL = new LinearLayout(this);
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for(TagView addBtn:childBtns.subList(0,childBtns.size()-1)){
                    horizLL.addView(addBtn.getView());
                }
                mRecommandTagLayout.addView(horizLL);

                childBtns.clear();
                currentWidth = 0;

                childBtns.add(tagView);
                currentWidth = currentWidth + (int)AppUtil.sp2px(this,14)*length + AppUtil.dip2px(this,20);
            }
        }


        LinearLayout.LayoutParams lastLinelayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        lastLinelayoutParams.setMargins(10, 0, 10, AppUtil.dip2px(this,10));

        //最后一行添加一下
        if(!childBtns.isEmpty()){
            LinearLayout horizLL = new LinearLayout(this);
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(lastLinelayoutParams);

            for(TagView addBtn:childBtns){
                horizLL.addView(addBtn.getView());
            }
            mRecommandTagLayout.addView(horizLL);
            childBtns.clear();
        }

    }

    private void addTag(){

        String tagStr = mAddTagEditView.getText().toString().trim();

        if(TextUtil.isEmpty(tagStr)){
            return;
        }

        if(mBottomTags.contains(tagStr)){
            SuperToast.show("该标签已存在",this);
            return;
        }

        mBottomTags.add(tagStr);

        setTopTagView();

        recommandTadEditState = false;

        mAddTagEditView.setText("");

        setBottomTagView(recommandTadEditState);

    }


}
