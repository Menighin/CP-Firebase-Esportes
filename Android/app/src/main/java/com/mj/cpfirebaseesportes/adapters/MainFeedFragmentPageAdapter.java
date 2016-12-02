package com.mj.cpfirebaseesportes.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import com.mj.cpfirebaseesportes.R;

import java.util.List;

/**
 * Created by Menighin on 01/12/2016.
 */

public class MainFeedFragmentPageAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] {"Feed", "Mapa"};
    private int icons[] = new int[] {R.mipmap.ic_view_list_white_24dp, R.mipmap.ic_room_white_24dp};
    private Context context;
    private List<Fragment> fragments;

    public MainFeedFragmentPageAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable icon = context.getResources().getDrawable(icons[position]);

        SpannableStringBuilder sb = new SpannableStringBuilder(" ");
        try {
            icon.setBounds(5, 5, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(icon, DynamicDrawableSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return sb;
    }


}
