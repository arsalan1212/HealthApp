package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codec.healthapp.R;

import java.util.ArrayList;

import model.HealthBenefitDetailModel;

/**
 * Created by Arsalan khan on 10/30/2017.
 */

public class HealthBenefitDetailAdapter extends RecyclerView.Adapter<HealthBenefitDetailAdapter.MyViewHolder> {

    private ArrayList<HealthBenefitDetailModel> arraylist_healthBenefit;
    private Context context;

    public HealthBenefitDetailAdapter(Context context,ArrayList<HealthBenefitDetailModel> arrayList){

        this.context = context;
        arraylist_healthBenefit = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.single_row_health_benefit_detail,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tvBenefits.setText(arraylist_healthBenefit.get(position).getBenefits());
        holder.tvRichSource.setText(arraylist_healthBenefit.get(position).getRichSource());

    }

    @Override
    public int getItemCount() {
        return arraylist_healthBenefit.size();
    }


    class  MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvBenefits,tvRichSource;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvBenefits = itemView.findViewById(R.id.tvBenefits);
            tvRichSource = itemView.findViewById(R.id.tvRichSource);
        }
    }
}
