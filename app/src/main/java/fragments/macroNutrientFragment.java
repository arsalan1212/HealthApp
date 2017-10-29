package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codec.healthapp.R;

public class macroNutrientFragment extends Fragment implements View.OnClickListener {

    private TextView tv_fiber,tv_protein,tv_water,tv_carbohydrates;
    public macroNutrientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_macro_nutrient, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_fiber = getActivity().findViewById(R.id.tv_fiber);
        tv_protein = getActivity().findViewById(R.id.tv_protein);
        tv_water = getActivity().findViewById(R.id.tv_water);
        tv_carbohydrates = getActivity().findViewById(R.id.tv_carbohydrates);


        tv_fiber.setOnClickListener(this);
        tv_protein.setOnClickListener(this);
        tv_water.setOnClickListener(this);
        tv_carbohydrates.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.tv_fiber:
                Toast.makeText(getActivity().getApplicationContext(), "Fiber", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_protein:
                Toast.makeText(getActivity().getApplicationContext(), "Protein", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_water:
                Toast.makeText(getActivity().getApplicationContext(), "Water", Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_carbohydrates:
                Toast.makeText(getActivity().getApplicationContext(), "Carbohydrates", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
