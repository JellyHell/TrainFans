/*
 * Copyright (C) 2015 Tomás Ruiz-López.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.peixunfan.trainfans.Base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.recyclerview.swipe.touch.SwipeMenuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuLayout;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuView;

/**
 * An extension to RecyclerView.Adapter to provide sections with headers and footers to a
 * RecyclerView. Each section can have an arbitrary number of items.
 *
 * @param <H> Class extending RecyclerView.ViewHolder to hold and bind the header view
 * @param <VH> Class extending RecyclerView.ViewHolder to hold and bind the items view
 * @param <F> Class extending RecyclerView.ViewHolder to hold and bind the footer view
 */
public abstract class SectionedRecyclerViewAdapter<H extends RecyclerView.ViewHolder,
        VH extends RecyclerView.ViewHolder,
        F extends RecyclerView.ViewHolder, V>
        extends SwipeMenuAdapter<RecyclerView.ViewHolder> {

    public static final int TYPE_SECTION_HEADER = -1;
    public static final int TYPE_SECTION_FOOTER = -2;
    public static final int TYPE_ITEM = -3;

    public static final int TYPE_ITEM_CLASS = -5;

    protected LayoutInflater mInflater;
    private int[] sectionForPosition = null;
    private int[] positionWithinSection = null;
    private boolean[] isHeader = null;
    private boolean[] isFooter = null;
    private int count = 0;

    public SectionedRecyclerViewAdapter() {
        super();
        registerAdapterDataObserver(new SectionDataObserver());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        setupIndices();
    }
    
    /**
     * Returns the sum of number of items for each section plus headers and footers if they
     * are provided.
     */
    @Override
    public int getItemCount() {
        setupIndices();
        return count;
    }

    private void setupIndices(){
        count = countItems();
        allocateAuxiliaryArrays(count);
        precomputeIndices();
    }

    private int countItems() {
        int count = 0;
        int sections = getSectionCount();

        for(int i = 0; i < sections; i++){
            count += 1 + getItemCountForSection(i) + (hasFooterInSection(i) ? 1 : 0);
        }
        return count;
    }

    private void precomputeIndices(){
        int sections = getSectionCount();
        int index = 0;

        for(int i = 0; i < sections; i++){
            setPrecomputedItem(index, true, false, i, 0);
            index++;

            for(int j = 0; j < getItemCountForSection(i); j++){
                setPrecomputedItem(index, false, false, i, j);
                index++;
            }

            if(hasFooterInSection(i)){
                setPrecomputedItem(index, false, true, i, 0);
                index++;
            }
        }
    }

    private void allocateAuxiliaryArrays(int count) {
        sectionForPosition = new int[count];
        positionWithinSection = new int[count];
        isHeader = new boolean[count];
        isFooter = new boolean[count];
    }

    private void setPrecomputedItem(int index, boolean isHeader, boolean isFooter, int section, int position) {
        this.isHeader[index] = isHeader;
        this.isFooter[index] = isFooter;
        sectionForPosition[index] = section;
        positionWithinSection[index] = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if(isSectionHeaderViewType(viewType)){
            viewHolder = onCreateSectionHeaderViewHolder(parent, viewType);
        }else if(isSectionFooterViewType(viewType)){
            viewHolder = onCreateSectionFooterViewHolder(parent, viewType);
        }else{
            View convertView;
            convertView = mInflater.inflate(getResourceId(), parent, false);
            if (mSwipeMenuCreator != null) {
                SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) LayoutInflater.from(parent.getContext()).inflate(com.peixunfan.androidlib.R.layout.yanzhenjie_item_default, parent, false);

                SwipeMenu swipeLeftMenu = new SwipeMenu(swipeMenuLayout, viewType);
                SwipeMenu swipeRightMenu = new SwipeMenu(swipeMenuLayout, viewType);

                mSwipeMenuCreator.onCreateMenu(swipeLeftMenu, swipeRightMenu, viewType);

                int leftMenuCount = swipeLeftMenu.getMenuItems().size();
                if (leftMenuCount > 0) {
                    SwipeMenuView swipeLeftMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(com.peixunfan.androidlib.R.id.swipe_left);
                    swipeLeftMenuView.setOrientation(swipeLeftMenu.getOrientation());
                    swipeLeftMenuView.bindMenu(swipeLeftMenu, SwipeMenuRecyclerView.LEFT_DIRECTION);
                    swipeLeftMenuView.bindMenuItemClickListener(mSwipeMenuItemClickListener, swipeMenuLayout);
                }

                int rightMenuCount = swipeRightMenu.getMenuItems().size();
                if (rightMenuCount > 0) {
                    SwipeMenuView swipeRightMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(com.peixunfan.androidlib.R.id.swipe_right);
                    swipeRightMenuView.setOrientation(swipeRightMenu.getOrientation());
                    swipeRightMenuView.bindMenu(swipeRightMenu, SwipeMenuRecyclerView.RIGHT_DIRECTION);
                    swipeRightMenuView.bindMenuItemClickListener(mSwipeMenuItemClickListener, swipeMenuLayout);
                }

                if (leftMenuCount > 0 || rightMenuCount > 0) {
                    ViewGroup aViewGroup = (ViewGroup) swipeMenuLayout.findViewById(com.peixunfan.androidlib.R.id.swipe_content);
                    aViewGroup.addView(convertView);
                    convertView = swipeMenuLayout;
                }
            }
            viewHolder = onCreateItemViewHolder(convertView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int section = sectionForPosition[position];
        int index = positionWithinSection[position];

        if(isSectionHeaderPosition(position)){
            onBindSectionHeaderViewHolder((H) holder, section);
        }else if(isSectionFooterPosition(position)){
            onBindSectionFooterViewHolder((F) holder, section);
        }else{
            //绑定SwipeMenuView
            View itemView = holder.itemView;

            // && section == 1
            if (itemView instanceof SwipeMenuLayout) {
                SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) itemView;
                int childCount = swipeMenuLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childView = swipeMenuLayout.getChildAt(i);
                    if (childView instanceof SwipeMenuView) {
                        ((SwipeMenuView) childView).bindAdapterViewHolder(holder);
                    }
                }
            }

            onBindItemViewHolder((VH) holder, section, index);
        }

    }

    @Override
    public int getItemViewType(int position) {

        if(sectionForPosition == null){
            setupIndices();
        }

        int section = sectionForPosition[position];
        int index = positionWithinSection[position];

        if(isSectionHeaderPosition(position)){
            return getSectionHeaderViewType(section);
        }else if(isSectionFooterPosition(position)){
            return getSectionFooterViewType(section);
        }else{
            return getSectionItemViewType(section, index);
        }

    }

    protected int getSectionHeaderViewType(int section){
        return TYPE_SECTION_HEADER;
    }

    protected int getSectionFooterViewType(int section){
        return TYPE_SECTION_FOOTER;
    }

    protected int getSectionItemViewType(int section, int position){
        if (section == 1){
            return TYPE_ITEM_CLASS;
        }
        return TYPE_ITEM;
    }

    /**
     * Returns true if the argument position corresponds to a header
     */
    public boolean isSectionHeaderPosition(int position){
        if(isHeader == null){
            setupIndices();
        }
        return isHeader[position];
    }

    /**
     * Returns true if the argument position corresponds to a footer
     */
    public boolean isSectionFooterPosition(int position){
        if(isFooter == null){
            setupIndices();
        }
        return isFooter[position];
    }

    protected boolean isSectionHeaderViewType(int viewType){
        return viewType == TYPE_SECTION_HEADER;
    }

    protected boolean isSectionFooterViewType(int viewType){
        return viewType == TYPE_SECTION_FOOTER;
    }

    /**
     * Returns the number of sections in the RecyclerView
     */
    protected abstract int getSectionCount();

    /**
     * Returns the number of items for a given section
     */
    protected abstract int getItemCountForSection(int section);

    /**
     * Returns true if a given section should have a footer
     */
    protected abstract boolean hasFooterInSection(int section);

    /**
     * Creates a ViewHolder of class H for a Header
     */
    protected abstract H  onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType);

    /**
     * Creates a ViewHolder of class F for a Footer
     */
    protected abstract F  onCreateSectionFooterViewHolder(ViewGroup parent, int viewType);

    /**
     * Creates a ViewHolder of class VH for an Item
     */
    protected abstract VH onCreateItemViewHolder(View arg0);

    /**
     * Binds data to the header view of a given section
     */
    protected abstract void onBindSectionHeaderViewHolder(H holder, int section);

    /**
     * Binds data to the footer view of a given section
     */
    protected abstract void onBindSectionFooterViewHolder(F holder, int section);

    /**
     * Binds data to the item view for a given position within a section
     */
    protected abstract void onBindItemViewHolder(VH holder, int section, int position);

    class SectionDataObserver extends RecyclerView.AdapterDataObserver{
        @Override
        public void onChanged() {
            setupIndices();
        }
    }

    public int getItemPosition(int position) {
        return positionWithinSection[position];
    }

    protected abstract int getResourceId();
}
