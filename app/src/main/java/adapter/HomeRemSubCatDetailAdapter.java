package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codec.healthapp.R;

import java.util.ArrayList;

import model.HomeRemSubCatModel;

/**
 * Created by Arsalan khan on 10/27/2017.
 */

public class HomeRemSubCatDetailAdapter  extends RecyclerView.Adapter<HomeRemSubCatDetailAdapter.HomeSubCatViewHolder> {

    Context context;
    ArrayList<HomeRemSubCatModel> homeRemSubCatModelArrayList;

    public HomeRemSubCatDetailAdapter(Context context, ArrayList<HomeRemSubCatModel> arrayList){

        this.context =context;
        homeRemSubCatModelArrayList= arrayList;
    }

    @Override
    public HomeSubCatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(context).inflate(R.layout.single_row_home_sub_cat_detail,parent,false);

        return new HomeSubCatViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(HomeSubCatViewHolder holder, int position) {

        holder.tvQuestion.setText(homeRemSubCatModelArrayList.get(position).getTitleHeading());
        holder.tvAnswer.setText(homeRemSubCatModelArrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return homeRemSubCatModelArrayList.size();
    }

    class HomeSubCatViewHolder extends RecyclerView.ViewHolder{

        TextView tvQuestion;
        TextView tvAnswer;
        public HomeSubCatViewHolder(View itemView) {
            super(itemView);

            tvAnswer = itemView.findViewById(R.id.tv_homeremsubcatdetail_answer);
            tvQuestion = itemView.findViewById(R.id.tv_homeremsubcatdetail_question);
        }
    }
}
