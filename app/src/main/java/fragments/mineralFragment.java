package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codec.healthapp.R;
import com.codec.healthapp.RichFoodDetailAcivity;

public class mineralFragment extends Fragment implements View.OnClickListener {


    private TextView tv_calcuim,tv_copper,tv_iron,tv_phosphorus,tv_potassium,tv_magnesium,
                     tv_sodium,tv_zinc;
    public mineralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mineral, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_calcuim = getActivity().findViewById(R.id.tv_calcuim);
        tv_copper = getActivity().findViewById(R.id.tv_copper);
        tv_iron = getActivity().findViewById(R.id.tv_iron);
        tv_phosphorus = getActivity().findViewById(R.id.tv_phosphorus);
        tv_potassium = getActivity().findViewById(R.id.tv_potassium);
        tv_magnesium = getActivity().findViewById(R.id.tv_magnesium);
        tv_sodium = getActivity().findViewById(R.id.tv_sodium);
        tv_zinc = getActivity().findViewById(R.id.tv_zinc);

        tv_calcuim.setOnClickListener(this);
        tv_copper.setOnClickListener(this);
        tv_iron.setOnClickListener(this);
        tv_phosphorus.setOnClickListener(this);
        tv_potassium.setOnClickListener(this);
        tv_magnesium.setOnClickListener(this);
        tv_sodium.setOnClickListener(this);
        tv_zinc.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent=null;
        switch (view.getId()){

            case R.id.tv_calcuim:
                Toast.makeText(getActivity().getApplicationContext(), "Calcuim", Toast.LENGTH_SHORT).show();


                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_copper:
                Toast.makeText(getActivity().getApplicationContext(), "Copper", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_iron:
                Toast.makeText(getActivity().getApplicationContext(), "Iron", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_phosphorus:
                Toast.makeText(getActivity().getApplicationContext(), "Phosphorus", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_potassium:
                Toast.makeText(getActivity().getApplicationContext(), "Potassium", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_magnesium:
                Toast.makeText(getActivity().getApplicationContext(), "Magnesium", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_sodium:
                Toast.makeText(getActivity().getApplicationContext(), "Sodium", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;

            case R.id.tv_zinc:
                Toast.makeText(getActivity().getApplicationContext(), "Zinc", Toast.LENGTH_SHORT).show();

                LunchRichFoodDetailAcivity();
                break;
        }
    }

    private void LunchRichFoodDetailAcivity() {
        Intent intent;
        intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
