package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codec.healthapp.Health_Benefits_Detail_Activity;
import com.codec.healthapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.HealthBenefitsModel;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Arsalan khan on 10/30/2017.
 */

public class HealthBenefitsAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private Context context;
    private ArrayList<HealthBenefitsModel> benefitsModelArrayList;

    public HealthBenefitsAdapter(Context context, ArrayList<HealthBenefitsModel> arrayList){

        this.context = context;
        benefitsModelArrayList = arrayList;

        //sorting the Arraylist
        Collections.sort(benefitsModelArrayList, new Comparator<HealthBenefitsModel>() {
            @Override
            public int compare(final HealthBenefitsModel object1, final HealthBenefitsModel object2) {
                return object1.getCategoryName().compareTo(object2.getCategoryName());
            }
        });
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

        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/sansation_light.ttf");

        String headerText = "" + benefitsModelArrayList.get(position).getCategoryName().subSequence(0, 1).charAt(0);

       holder.text.setTypeface(typeface);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return benefitsModelArrayList.get(position).getCategoryName().subSequence(0, 1).charAt(0);
    }

    @Override
    public int getCount() {
        return benefitsModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return benefitsModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
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
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/sansation_light.ttf");
        holder.text.setTypeface(typeface);
        holder.text.setText(benefitsModelArrayList.get(i).getCategoryName());
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Health_Benefits_Detail_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("sub_category_id",benefitsModelArrayList.get(position).getSubCategoryID());
                intent.putExtra("category_name",benefitsModelArrayList.get(position).getCategoryName());
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


    public void setFilter(ArrayList<HealthBenefitsModel> newList){

        benefitsModelArrayList =new ArrayList<>();
        benefitsModelArrayList.clear();
        benefitsModelArrayList.addAll(newList);
        notifyDataSetChanged();
    }
}
