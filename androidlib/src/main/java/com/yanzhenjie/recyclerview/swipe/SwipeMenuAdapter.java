/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.recyclerview.swipe;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Yan Zhenjie on 2016/7/27.
 */
public abstract class SwipeMenuAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    /**
     * Swipe menu creator。
     */
    protected SwipeMenuCreator mSwipeMenuCreator;

    /**
     * Swipe menu click listener。
     */
    protected OnSwipeMenuItemClickListener mSwipeMenuItemClickListener;

    /**
     * Set to create menu listener.
     *
     * @param swipeMenuCreator listener.
     */
    void setSwipeMenuCreator(SwipeMenuCreator swipeMenuCreator) {
        this.mSwipeMenuCreator = swipeMenuCreator;
    }

    /**
     * Set to click menu listener.
     *
     * @param swipeMenuItemClickListener listener.
     */
    void setSwipeMenuItemClickListener(OnSwipeMenuItemClickListener swipeMenuItemClickListener) {
        this.mSwipeMenuItemClickListener = swipeMenuItemClickListener;
    }

    /**
     * Create view for item.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new view.
     * @return A new ViewHolder that holds a View of the given view type.
     */
//    public abstract View onCreateContentView(ViewGroup parent, int viewType);

    /**
     * Instead {@link #onCreateViewHolder(ViewGroup, int)}.
     *
     * @param realContentView Is this Item real view, {@link SwipeMenuLayout} or {@link #onCreateContentView(ViewGroup, int)}.
     * @param viewType        The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #onCompatBindViewHolder(RecyclerView.ViewHolder, int, List)
     */
//    public abstract VH onCompatCreateViewHolder(View realContentView, int viewType);

    @Override
    public final void onBindViewHolder(VH holder, int position, List<Object> payloads) {
    }

    /**
     * Instead {@link #onBindViewHolder(RecyclerView.ViewHolder, int, List)}.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @param payloads A non-null list of merged payloads. Can be empty list if requires full update.
     * @see #onCompatBindViewHolder(RecyclerView.ViewHolder, int, List)
     */
    public void onCompatBindViewHolder(VH holder, int position, List<Object> payloads) {
        onBindViewHolder(holder, position);
    }
}