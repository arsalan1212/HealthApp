package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codec.healthapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import model.RichFoodDetailModel;

/**
 * Created by Arsalan khan on 11/2/2017.
 */

public class RichFoodDetailAdapter extends RecyclerView.Adapter<RichFoodDetailAdapter.MyRichFoodViewHolder> {

    private Context context;
    private ArrayList<RichFoodDetailModel> arrayList;

    public RichFoodDetailAdapter(Context context, ArrayList<RichFoodDetailModel> arrayList){

        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public MyRichFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_row_rich_food_detail,parent,false);

        return new MyRichFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRichFoodViewHolder holder, int position) {

        Picasso.with(context).load(arrayList.get(position).getImagePath()).placeholder(R.drawable.placeholder)
                               .into(holder.imageViewRichFood);

        holder.tvRichFood.setText(arrayList.get(position).getRichFoodText());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class MyRichFoodViewHolder extends RecyclerView.ViewHolder{

        private TextView tvRichFood;
        private CircleImageView imageViewRichFood;

        public MyRichFoodViewHolder(View itemView) {
            super(itemView);

            tvRichFood = itemView.findViewById(R.id.tvRichFoodLabel);
            imageViewRichFood = itemView.findViewById(R.id.imageRichFoodDetail);
        }
    }
}
