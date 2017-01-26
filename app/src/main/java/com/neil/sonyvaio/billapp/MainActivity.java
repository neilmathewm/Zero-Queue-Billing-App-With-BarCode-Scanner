package com.neil.sonyvaio.billapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.neil.sonyvaio.billapp.com.google.zxing.integration.android.IntentIntegrator;
import com.neil.sonyvaio.billapp.com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {


    private Button scanBtn;
    private TextView formatTxt, contentTxt,itemTxt,carttxt;
    public int a=-1,i,total=0,item_size=0,no_item=0;

    private String[] items = new String[20];
    public String[] codes = {"8901030554667", "8901361300889", "U1403085","700465747143","U1402016","8901437002853","8901512140708","8902361042021"};
    public String[] codes_name = {"LUX", "SCOTCH BRITE", "NEIL","MAAZA","ARJUN","PHILIPS LAMP","PEANUT BUTTER","NOTEBOOK"};

    ArrayList<String> cart = new ArrayList<String>();
    public String scanContent;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

        //set title bar content and hide icon

        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b>LuPay </b></font>"));
        getActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        final int[] ICONS = new int[] {
                R.mipmap.add1,
                R.mipmap.crt1,

        };
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.

            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setIcon(ICONS[i])

                            .setTabListener(this));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_about) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
//to swipe

//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//        // When the given tab is selected, switch to the corresponding page in
//        // the ViewPager.
//        mViewPager.setCurrentItem(tab.getPosition());
//    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        //Called when a tab is selected
        int nTabSelected = tab.getPosition();
        switch (nTabSelected) {
            case 0:
                mViewPager.setCurrentItem(tab.getPosition());
                setContentView(R.layout.actionbar_tab_1);

                formatTxt = (TextView)findViewById(R.id.scan_format);
                contentTxt = (TextView) findViewById(R.id.scan_content);
                itemTxt = (TextView) findViewById(R.id.scan_item);
                carttxt = (TextView)findViewById(R.id.cart_content);



                break;
            case 1:
                mViewPager.setCurrentItem(tab.getPosition());
                setContentView(R.layout.actionbar_tab_2);
                tab2();


                break;
            case 2:
                mViewPager.setCurrentItem(tab.getPosition());
                setContentView(R.layout.activity_main2);


                break;
            case 3:
                mViewPager.setCurrentItem(tab.getPosition());
                setContentView(R.layout.actionbar_tab_4);
                break;

        }
    }


    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "  ADD PRODUCT";
                case 1:
                    return "  CART";

            }
            return null;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            scanContent = scanningResult.getContents();
           // String scanFormat = scanningResult.getFormatName();
            if(scanContent==null)
            {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "No Product Added To Cart", Toast.LENGTH_SHORT);
                toast.show();

            }
          else {
                //  Resources res = getResources();
// For Alert Diaolog
                int p = 0;
                for (i = 0; i < codes.length; i++) {
                    if (scanContent.equals(codes[i])) {
                        p = 1;
                        no_item=no_item+1;

                       // contentTxt.setText("CONTENT: " + scanContent);
                        items[item_size++] = codes_name[i];
                        Toast toast = Toast.makeText(getApplicationContext(),
                                codes_name[i] + "  Added To Cart", Toast.LENGTH_SHORT);
                        toast.show();
                        if(no_item>0) {
                            ActionBar.Tab mTab = super.getSupportActionBar().getTabAt(1);
                            mTab.setText(" CART  " +no_item);
                        }

/*
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                        builder1.setMessage("ARE YOU SURE YOU WANT TO ADD  : " + codes_name[i]);
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();

                                        //formatTxt.setText("FORMAT: " + items[0]);

                                        contentTxt.setText("CONTENT: " + scanContent);
                                        items[item_size++] = codes_name[i];
                                        // carttxt.setText(codes_name[i]+"----\n");

                                        //itemTxt.setText("CONTENT: " + items[1]);
                                    }
                                });

                        builder1.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert11 = builder1.create();
                        alert11.show();  */

                        break;
                    }

                }
                if (p == 0) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                    builder1.setMessage("No Product Found With Code : " + scanContent);
                    builder1.setCancelable(true);


                    builder1.setNegativeButton(
                            "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do something when click the negative button
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }


            }


        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();


        }
    }
    public void tab1(View view)
    {
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }
    public void tab11(View view) {

        final TextView tv = (TextView) findViewById(R.id.tv);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        // String array for alert dialog multi choice items
        String[] colors = new String[]{
                "COLGATE",
                "LUX",
                "PEARS",
                "PEPSODENT",
                "SANTOOR"
        };


        // Boolean array for initial selected items
        final boolean[] checkedColors = new boolean[]{
                false, // Red
                false, // Green
                false, // Blue
                false, // Purple
                false // Olive

        };

        // Convert the color array to list
        final List<String> colorsList = Arrays.asList(colors);

        // Set multiple choice items for alert dialog
                /*
                    AlertDialog.Builder setMultiChoiceItems(CharSequence[] items, boolean[]
                    checkedItems, DialogInterface.OnMultiChoiceClickListener listener)
                        Set a list of items to be displayed in the dialog as the content,
                        you will be notified of the selected item via the supplied listener.
                 */
                /*
                    DialogInterface.OnMultiChoiceClickListener
                    public abstract void onClick (DialogInterface dialog, int which, boolean isChecked)

                        This method will be invoked when an item in the dialog is clicked.

                        Parameters
                        dialog The dialog where the selection was made.
                        which The position of the item in the list that was clicked.
                        isChecked True if the click checked the item, else false.
                 */
        builder.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                // Update the current focused item's checked status
                checkedColors[which] = isChecked;

                // Get the current focused item
                String currentItem = colorsList.get(which);

                // Notify the current action
               // Toast.makeText(getApplicationContext(),
                 //       currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        // Specify the dialog is not cancelable
        builder.setCancelable(false);

        // Set a title for alert dialog
        //builder.setTitle("Your preferred colors?");

        // Set the positive/yes button click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click positive button
                //tv.setText("Your preferred colors..... \n");
                for (int i = 0; i<checkedColors.length; i++){
                    boolean checked = checkedColors[i];
                    if (checked) {

                        cart.add(colorsList.get(i));
                        //tv.setText(tv.getText() + colorsList.get(i) + "\n");
                    }
                }
            }
        });

        // Set the negative/no button click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the negative button
            }
        });

        // Set the neutral/cancel button click listener
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when click the neutral button
            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();



    }
    public void tab2()
    {
        int[] price = new int[]{
                35,
                250,
                42,
                20,
                250,
                65
        };
        carttxt = (TextView)findViewById(R.id.cart_content);
        int size = cart.size();
total=0;
        carttxt.setText("\n-----------ITEMS ON YOUR CART----------- \n\n\n");
//        for(int i=0;i<size;i++) {
//            total=total+price[i];
//            carttxt.setText(carttxt.getText() +cart.get(i)+"----"+price[i]+"\n");
//        }
        for(int i=0;i<item_size;i++)
        {

            carttxt.setText(carttxt.getText() +items[i]+"-----"+price[i]+"\n");
            total=total+price[i];
        }
        carttxt.setText(carttxt.getText()+"----------------------------------------\n");
        carttxt.setText(carttxt.getText()+" Total  = Rs "+total);
        }
    public void proceed_payment(View view)
    {

        Intent intent = new Intent(this, payement.class);
        startActivity(intent);
    }
    public void offer_page(View view)
    {
        Intent intent = new Intent(this, offerpage.class);
        startActivity(intent);
    }
    public void about_page(View view)
    {
        Intent intent = new Intent(this, aboutpage.class);
        startActivity(intent);
    }

}
