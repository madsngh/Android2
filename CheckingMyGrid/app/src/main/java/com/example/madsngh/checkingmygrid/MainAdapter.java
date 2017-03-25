package com.example.madsngh.checkingmygrid;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPER_PAGER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int HEADER_PAGER_LAYOUT = 1;

    private final int mImageResID;
    private final int mIconDesc;

    private Context mContext;
    int mPreviousPosition=0;
    List<Item_Name> item_names_List;
    List<String> stringList=new ArrayList<String>();



    private OnGridItemSelectedListener mOnGridItemSelectedListener;

    public MainAdapter(Context context, int imageResID, int mIconDesc, List<Item_Name> item_names_List, List<String> stringList) {
        this.mImageResID = imageResID;
        this.mIconDesc=mIconDesc;
        this.mContext = context;
        this.item_names_List=item_names_List;
        mOnGridItemSelectedListener = (OnGridItemSelectedListener) mContext;
        this.stringList=stringList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPER_PAGER) {
            View v = LayoutInflater.from(parent.getContext()).inflate (R.layout.include_view_pager, parent, false);
            return new HeaderViewHolder (v);
        } else if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home, null);
            return new RecyclerViewHolder(view);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) throws ArrayIndexOutOfBoundsException{

        if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(mContext, mImageResID);
            headerHolder.viewPager.setAdapter(pagerAdapter);
            headerHolder.mIndicator.setViewPager(headerHolder.viewPager);

        } else if(holder instanceof RecyclerViewHolder) {
            try {
                RecyclerViewHolder genericViewHolder = (RecyclerViewHolder) holder;
                Drawable icon = mContext.getResources().obtainTypedArray(mImageResID).getDrawable(position - HEADER_PAGER_LAYOUT);
                String icondesc=mContext.getResources().obtainTypedArray(mIconDesc).getString(position - HEADER_PAGER_LAYOUT);
                genericViewHolder.image.setImageDrawable(icon);
                if(stringList.size()!=0)
                genericViewHolder.textView2.setText(stringList.get(0));
                genericViewHolder.textView.setText(icondesc);
                AnimationUtils.animateSunblind(holder, true);
                if (position > mPreviousPosition) {
                    AnimationUtils.animateSunblind(holder, true);
                    //AnimationUtils.animateSunblind(holder, true);
                } else {
                    AnimationUtils.animateSunblind(holder, false);
                   //AnimationUtils.animate1(holder, true);
                    //AnimationUtils.animate(holder,true);
            //AnimationUtils.animateSunblind(holder, false);
            //AnimationUtils.animate1(holder, false);
          //AnimationUtils.animate(holder, false);
                }
            }
            catch (ArrayIndexOutOfBoundsException e){

            }
            mPreviousPosition = position;
        }
    }
    @Override
    public int getItemViewType (int position) {
        if(isPositionHeader (position)) {
            return TYPER_PAGER;
        }
        return TYPE_ITEM;
    }
    public boolean isPositionHeader(int position) {
        return position == 0;
    }
    @Override
    public int getItemCount() {
        return (mContext.getResources().getIntArray(mImageResID).length) + HEADER_PAGER_LAYOUT;
    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        CirclePageIndicator mIndicator;

        public HeaderViewHolder (View itemView) {
            super (itemView);
            this.viewPager = (ViewPager) itemView.findViewById(R.id.pager);
            this.mIndicator  = (CirclePageIndicator) itemView.findViewById(R.id.indicator);
        }
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView image;
        public TextView textView;
        public  TextView textView2;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            //implementing onClickListener
            itemView.setOnClickListener(this);
            image = (ImageView)itemView.findViewById(R.id.image);
            textView= (TextView) itemView.findViewById(R.id.model);
            textView2= (TextView) itemView.findViewById(R.id.model2);
        }
        @Override
        public void onClick(View view) {
            mOnGridItemSelectedListener.onGridItemClick(view,getAdapterPosition() - HEADER_PAGER_LAYOUT);
        }
    }
}