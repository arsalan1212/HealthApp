package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fragments.macroNutrientFragment;
import fragments.mineralFragment;
import fragments.vitaminsFragment;

/**
 * Created by Arsalan khan on 10/29/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if(position==0){
            return new vitaminsFragment();
        }
        else if(position==1){
            return new mineralFragment();
        }
        else if(position==2){
            return new macroNutrientFragment();
        }else{
            return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if(position == 0){
            return "Vitamins";
        }
        else if(position == 1){
            return "Minerals";
        }
        else if(position == 2){
            return "Macronutrient";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
