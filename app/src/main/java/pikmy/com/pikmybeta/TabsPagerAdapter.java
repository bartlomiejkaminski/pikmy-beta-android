package pikmy.com.pikmybeta;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bartek on 17.02.2017.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // List piks fragment activity
                return new ListPiks();
            case 1:
                // Add pik fragment activity
                return new Add();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
