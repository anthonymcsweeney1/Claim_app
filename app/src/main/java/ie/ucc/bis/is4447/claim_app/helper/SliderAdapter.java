package ie.ucc.bis.is4447.claim_app.helper;

//Code taken from my IS4447 CA2 app

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import ie.ucc.bis.is4447.claim_app.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {
            // Images for each slide
            R.drawable.welcome,
          R.drawable.approvalflow,
            R.drawable.requestinfo,
            R.drawable.invoice

    };

    int headings[]={
            // Headers for each slide
            R.string.welcome_slide,
            R.string.first_slide,
            R.string.second_slide,
            R.string.third_slide

    };

    int descriptions[] ={
            // Description for each slide
            R.string.welcome_desc,
            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc
    };

    @Override
    public int getCount() {
        // number of slides based on headings
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

        ImageView imageView = view.findViewById(R.id.IVslide);
        TextView heading = view.findViewById(R.id.tvHeader);
        TextView desc = view.findViewById(R.id.tvslideDes);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }


}
