package ru.webanimal.academy_lessons.ui.features.tutorial;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.OnboardingSupportFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.ui.features.digests.DigestsActivity;
import ru.webanimal.academy_lessons.utils.Application;

// https://developer.android.com/reference/android/support/v17/leanback/app/OnboardingSupportFragment
// https://developer.android.com/training/tv/playback/onboarding
public class TutorialFragment extends OnboardingSupportFragment {

    //==============================================================================================
    // Static
    //==============================================================================================

    private final static int[] pictureIds = {
            R.drawable.tutorial_welcome,
            R.drawable.tutorial_list,
            R.drawable.tutorial_details
    };

    private final static int[] titleIds = {
            R.string.fragment_tutorial_page_welcome_text_title,
            R.string.fragment_tutorial_page_list_text_title,
            R.string.fragment_tutorial_page_details_text_title
    };

    private final static int[] descriptionIds = {
            R.string.fragment_tutorial_page_welcome_text_description,
            R.string.fragment_tutorial_page_list_text_description,
            R.string.fragment_tutorial_page_details_text_description
    };

    private final static int PAGES_SIZE = pictureIds.length;
    private final static int LOGO_SPLASH_PAUSE_DURATION_MS = 333;

    public static TutorialFragment create() {
        return new TutorialFragment();
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private ImageView tutorialImage;


    //==============================================================================================
    // OnboardingSupportFragment callbacks
    //==============================================================================================

    @Override
    public int onProvideTheme() {
        return R.style.Tutorial;
    }

    @Nullable
    @Override
    protected Animator onCreateLogoAnimation() {
        final Context context = getContext();
        if (context == null) {
            return super.onCreateLogoAnimation();
        }

        Animator inAnimator = AnimatorInflater.loadAnimator(context, R.animator.animator_tutorial_logo_enter);
        Animator outAnimator = AnimatorInflater.loadAnimator(context, R.animator.animator_tutorial_logo_exit);
        outAnimator.setStartDelay(LOGO_SPLASH_PAUSE_DURATION_MS);

        AnimatorSet logoAnimator = new AnimatorSet();
        logoAnimator.playSequentially(inAnimator, outAnimator);

        return logoAnimator;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLogoResourceId(R.drawable.img_cat_port);
    }

    @Nullable
    @Override
    protected View onCreateBackgroundView(LayoutInflater inflater, ViewGroup container) {
        // null
        return null;
    }

    @Nullable
    @Override
    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        tutorialImage = new ImageView(getContext());
//        tutorialImage.setMaxWidth((int)getResources().getDimension(R.dimen.layout_tutorial_content_image_size));
//        tutorialImage.setMaxHeight((int)getResources().getDimension(R.dimen.layout_tutorial_content_image_size));
        tutorialImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        tutorialImage.setImageDrawable(getPageImage(getCurrentPageIndex()));
        int padd = (int)getResources().getDimension(R.dimen.activity_space_horizontal_normal);
        tutorialImage.setPadding(padd, padd, padd, padd);
        return tutorialImage;
    }

    @Nullable
    @Override
    protected View onCreateForegroundView(LayoutInflater inflater, ViewGroup container) {
        // null
        return null;
    }

    @Override
    protected void onPageChanged(int newPage, int previousPage) {
        tutorialImage.setImageDrawable(getPageImage(getCurrentPageIndex()));
        super.onPageChanged(newPage, previousPage);
    }

    @Override
    protected void onFinishFragment() {
        // Here is the place to save "tutorial completed" in the SP
        startActivity(new Intent(getContext(), DigestsActivity.class));
        super.onFinishFragment();
    }

    @Override
    protected int getPageCount() {
        return PAGES_SIZE;
    }

    @Override
    protected CharSequence getPageTitle(int pageIndex) {
        return getResources().getString(titleIds[getCurrentPageIndex()]);
    }

    @Override
    protected CharSequence getPageDescription(int pageIndex) {
        return getResources().getString(descriptionIds[getCurrentPageIndex()]);
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    @Nullable
    private Drawable getLogoImage() {
        return ContextCompat.getDrawable(getAppContext(), R.drawable.img_cat_port);
    }

    @Nullable
    private Drawable getPageImage(int pageIndex) {
        return ContextCompat.getDrawable(getAppContext(), pictureIds[pageIndex]);
    }

    private Context getAppContext() {
        return Application.provides().context();
    }
}
