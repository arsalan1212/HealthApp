package com.codec.healthapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.innodroid.expandablerecycler.ExpandableRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import model.HomeRemSubCatModel;
import model.HomeRemediesModel;

/**
 * Created by RNSolution on 2/20/2017.
 */

public class LHV_Visit_History_Adapter extends ExpandableRecyclerAdapter<LHV_Visit_History_Adapter.PeopleListItem> {
    public static final int TYPE_PERSON = 1001;
    ArrayList<HomeRemediesModel> parent;
    ArrayList<ArrayList<HomeRemSubCatModel>> child;
    Context context;
    Home_Remedies_Pro fragment;

    public LHV_Visit_History_Adapter(Context context, ArrayList<HomeRemediesModel> parent,
                                     ArrayList<ArrayList<HomeRemSubCatModel>> child,
                                     Home_Remedies_Pro fragment
                                     ) {
        super(context);
        this.context=context;
        this.parent=parent;
        this.fragment=fragment;
        this.child=child;
        setItems(getSampleItems());

    }

    public static class PeopleListItem extends ExpandableRecyclerAdapter.ListItem {
        public String Text1;
        public String Text2;
        public String Text3;
        public String Text4;
        public String Text5;

        public PeopleListItem(String id,String sub_id,String name ) {
            super(TYPE_HEADER);
            Text1 = id ;
            Text2 = sub_id ;
            Text3 = name ;

        }
        public PeopleListItem(String d_id, String s_id, String title,String description,String image) {
            super(TYPE_PERSON);

            Text1 = d_id ;
            Text2 = s_id;
            Text3 = title;
            Text4 = description;
            Text5 = image;
        }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView name_txt;
        public HeaderViewHolder(View view) {
            super(view, (ImageView) view.findViewById(R.id.item_arrow));
            name_txt = (TextView) view.findViewById(R.id.title);
        }

        public void bind(final int position) {
            super.bind(position);
            name_txt.setText(visibleItems.get(position).Text3);


        }
    }

    public class PersonViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView visit_instruction_id;
        TextView visit_name;
        TextView instruction_title;
        public PersonViewHolder(View view) {
            super(view);

            visit_instruction_id = (TextView) view.findViewById(R.id.visit_instruction_id);
            visit_name =(TextView) view.findViewById(R.id.visit_name);
            instruction_title =(TextView) view.findViewById(R.id.instruction_title);

        }

        public void bind(final int position) {
            visit_instruction_id.setText(visibleItems.get(position).Text3);
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new LHV_Visit_History_Adapter.HeaderViewHolder(inflate(R.layout.home_remedies_parent_history_adapter, parent));
            case TYPE_PERSON:
            default:
                return new LHV_Visit_History_Adapter.PersonViewHolder(inflate(R.layout.home_remediess_child_adapter, parent));
        }
    }
    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((LHV_Visit_History_Adapter.HeaderViewHolder) holder).bind(position);
                break;
            case TYPE_PERSON:
            default:
                ((LHV_Visit_History_Adapter.PersonViewHolder) holder).bind(position);
                break;
        }
    }
    private List<PeopleListItem> getSampleItems() {
        List<PeopleListItem> items = new ArrayList<>();

        for(int i=0;i<parent.size();i++)
        {
            items.add(new LHV_Visit_History_Adapter.PeopleListItem(parent.get(i).getCategoryID(),
                    parent.get(i).getSubCategoryID(),
                    parent.get(i).getCategoryName()));


            ArrayList<HomeRemSubCatModel> mod=new ArrayList<>();
            try {

                 mod= child.get(i);
                 Log.e("arsalan","Size of Child: "+mod.size());

            }catch (Exception e){

                Log.e("child Error","Error: "+e.getMessage());
            }
                for (int a = 0; a < mod.size(); a++) {
                    items.add(new LHV_Visit_History_Adapter.PeopleListItem(
                            mod.get(a).getDetailID(),
                            mod.get(a).getSubID(),
                            mod.get(a).getTitleHeading(),
                            mod.get(a).getDescription(),
                            mod.get(a).getImage()));

                    Log.e("arsalan","Title of Child: "+mod.get(a).getTitleHeading());
                }


        }
        return items;
    }
}

