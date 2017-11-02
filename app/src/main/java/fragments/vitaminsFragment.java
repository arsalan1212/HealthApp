package fragments;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codec.healthapp.R;
import com.codec.healthapp.RichFoodDetailAcivity;

public class vitaminsFragment extends Fragment implements View.OnClickListener {


    private TextView tv_vitmin_b12,tv_vitmin_b9,tv_vitmin_b6,tv_vitmin_a,tv_vitmin_c,
                     tv_vitmin_k,tv_vitmin_e,tv_vitmin_d,tv_vitmin_lycopene,tv_vitmin_folic_acid,
                     tv_vitmin_choline,tv_vitmin_cryptoxanthin,tv_vitmin_carotene,tv_vitmin_betaine;
    public vitaminsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vitamins, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_vitmin_b12 =getActivity().findViewById(R.id.tv_vitmin_B12);
        tv_vitmin_b9 =getActivity().findViewById(R.id.tv_vitmin_B9);
        tv_vitmin_b6 =getActivity().findViewById(R.id.tv_vitmin_B6);
        tv_vitmin_a = getActivity().findViewById(R.id.tv_vitmin_A);
        tv_vitmin_c = getActivity().findViewById(R.id.tv_vitmin_C);
        tv_vitmin_k = getActivity().findViewById(R.id.tv_vitmin_K);
        tv_vitmin_e = getActivity().findViewById(R.id.tv_vitmin_E);
        tv_vitmin_d = getActivity().findViewById(R.id.tv_vitmin_D);
        tv_vitmin_lycopene= getActivity().findViewById(R.id.tv_lycopene);
        tv_vitmin_folic_acid= getActivity().findViewById(R.id.tv_folic_acid);
        tv_vitmin_choline= getActivity().findViewById(R.id.tv_choline);
        tv_vitmin_cryptoxanthin= getActivity().findViewById(R.id.tv_cryptoxanthin);
        tv_vitmin_carotene= getActivity().findViewById(R.id.tv_carotene);
        tv_vitmin_betaine= getActivity().findViewById(R.id.tv_betaine);

        settingTextSubscriptSpan();

        tv_vitmin_a.setOnClickListener(this);
        tv_vitmin_b12.setOnClickListener(this);
        tv_vitmin_b6.setOnClickListener(this);
        tv_vitmin_b9.setOnClickListener(this);
        tv_vitmin_c.setOnClickListener(this);
        tv_vitmin_k.setOnClickListener(this);
        tv_vitmin_e.setOnClickListener(this);
        tv_vitmin_d.setOnClickListener(this);
        tv_vitmin_lycopene.setOnClickListener(this);
        tv_vitmin_folic_acid.setOnClickListener(this);
        tv_vitmin_choline.setOnClickListener(this);
        tv_vitmin_cryptoxanthin.setOnClickListener(this);
        tv_vitmin_carotene.setOnClickListener(this);
        tv_vitmin_betaine.setOnClickListener(this);



    }

    private void settingTextSubscriptSpan() {
        SubscriptSpan subscriptSpan = new SubscriptSpan();

        SpannableStringBuilder spannableB12 = new SpannableStringBuilder("B12");

        //for subscript
        spannableB12.setSpan(subscriptSpan,1,3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // make the text twice as large
        spannableB12.setSpan(new RelativeSizeSpan(0.5f), 1, 3, 0);

        // make text bold
        spannableB12.setSpan(new StyleSpan(Typeface.NORMAL),1, 3, 0);

        tv_vitmin_b12.setText(spannableB12);

        //FOR Vitmin B9
        SpannableStringBuilder spannableB9 =new SpannableStringBuilder("B9");

        //for subscript
        spannableB9.setSpan(subscriptSpan,1,2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // make the text twice as large
        spannableB9.setSpan(new RelativeSizeSpan(0.5f), 1, 2, 0);

        // make text bold
        spannableB9.setSpan(new StyleSpan(Typeface.NORMAL),1, 2, 0);

        tv_vitmin_b9.setText(spannableB9);

        //Vitmin B6
        SpannableStringBuilder spannableB6 = new SpannableStringBuilder("B6");

        //for subscript
        spannableB6.setSpan(subscriptSpan,1,2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //for font size
        spannableB6.setSpan(new RelativeSizeSpan(0.5f),1,2,0);

        //for font style
        spannableB6.setSpan(new StyleSpan(Typeface.NORMAL),1,2,0);
        tv_vitmin_b6.setText(spannableB6);
    }

    @Override
    public void onClick(View view) {

        Intent intent =null;
        switch (view.getId()){

            case R.id.tv_vitmin_A:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin A", Toast.LENGTH_SHORT).show();
                 intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.tv_vitmin_B12:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin B12", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.tv_vitmin_B6:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin B6", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.tv_vitmin_B9:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin B9", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_vitmin_C:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin C", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_vitmin_E:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin E", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.tv_vitmin_D:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin D", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_vitmin_K:
                Toast.makeText(getActivity().getApplicationContext(), "Vitmin K", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.tv_cryptoxanthin:
                Toast.makeText(getActivity().getApplicationContext(), "Cryptoxanthin", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_lycopene:
                Toast.makeText(getActivity().getApplicationContext(), "Lycopene", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_folic_acid:
                Toast.makeText(getActivity().getApplicationContext(), "Folic Acid", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_choline:
                Toast.makeText(getActivity().getApplicationContext(), "Choline", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_carotene:
                Toast.makeText(getActivity().getApplicationContext(), "Carotene", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;

            case R.id.tv_betaine:
                Toast.makeText(getActivity().getApplicationContext(), "Betaine", Toast.LENGTH_SHORT).show();

                intent = new Intent(getActivity().getApplicationContext(), RichFoodDetailAcivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
