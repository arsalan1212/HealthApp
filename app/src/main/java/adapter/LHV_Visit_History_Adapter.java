package adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codec.healthapp.HomeRemediesSubCatDetails;
import com.codec.healthapp.R;

import java.util.ArrayList;

import model.HomeRemediesModel;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by RNSolution on 2/20/2017.
 */

public class LHV_Visit_History_Adapter extends BaseAdapter implements StickyListHeadersAdapter {
    ArrayList<HomeRemediesModel> parentArraylist;
    Context context;

    public LHV_Visit_History_Adapter(ArrayList<HomeRemediesModel> arrayListParent,Context context){
        this.context = context;
        parentArraylist =arrayListParent;
    }

    public void setFilter(ArrayList<HomeRemediesModel> newList){

        parentArraylist =new ArrayList<>();
        parentArraylist.clear();
        parentArraylist.addAll(newList);
        notifyDataSetChanged();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.home_remedies_header_single_character, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.tvHomeRemHeading);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name

        String headerText = "" + parentArraylist.get(position).getCategoryName().subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return parentArraylist.get(position).getCategoryName().subSequence(0, 1).charAt(0);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return parentArraylist.size();
    }

    @Override
    public Object getItem(int i) {
        return parentArraylist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return  i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view ==null)
        {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.home_remedies_parent_history_adapter,viewGroup,false);

            holder.text = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }


        final int position =i;
        holder.text.setText(parentArraylist.get(i).getCategoryName());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,HomeRemediesSubCatDetails.class);
                intent.putExtra("sub_cat_id",parentArraylist.get(position).getSubCategoryID());
                intent.putExtra("category_name",parentArraylist.get(position).getCategoryName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return view;
    }
    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }
    @Override
    public int getItemViewType(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return parentArraylist.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

