package com.traffic.pyp.traffic3.Activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Application.App;
import com.traffic.pyp.traffic3.Dialog.RechargeDialog;
import com.traffic.pyp.traffic3.Fragment.AccountFragment;
import com.traffic.pyp.traffic3.Fragment.BusInquiryFragment;
import com.traffic.pyp.traffic3.Fragment.DataAnalysisFragment;
import com.traffic.pyp.traffic3.Fragment.LifeAssistFragment;
import com.traffic.pyp.traffic3.Fragment.PersonalcenterFragment;
import com.traffic.pyp.traffic3.Fragment.RoadStatusFragment;
import com.traffic.pyp.traffic3.Fragment.TrafficLightManagementFragment;
import com.traffic.pyp.traffic3.R;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout menuLayout;
    private ImageButton menu;
    private TextView title, batchRecharge, rechargeRecord;
    private FragmentTransaction fragmentTransaction;
    private AccountFragment accountFragment;
    private TextView AccountFragment, BusInquiryFragment, TrafficLightManagementFragment, RouteConditionFragment, LifeAssistantFragment, DataAnalysisFragment, PersonalcenterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //取消标题栏
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        accountFragment = new AccountFragment();
        switchFragment(true, AccountFragment.getText().toString(), accountFragment);
    }

    private void initView() {
        MyClick myClick = new MyClick();
        drawerLayout = findViewById(R.id.drawerLayout);
        menuLayout = findViewById(R.id.menuLayout);
        menu = findViewById(R.id.menu);
        menu.setOnClickListener(myClick);
        title = findViewById(R.id.title);
        batchRecharge = findViewById(R.id.batchRecharge);
        batchRecharge.setOnClickListener(myClick);
        rechargeRecord = findViewById(R.id.rechargeRecord);
        rechargeRecord.setOnClickListener(myClick);
        AccountFragment = findViewById(R.id.AccountFragment);
        AccountFragment.setOnClickListener(myClick);
        BusInquiryFragment = findViewById(R.id.BusInquiryFragment);
        BusInquiryFragment.setOnClickListener(myClick);
        TrafficLightManagementFragment = findViewById(R.id.TrafficLightManagementFragment);
        TrafficLightManagementFragment.setOnClickListener(myClick);
        RouteConditionFragment = findViewById(R.id.RouteConditionFragment);
        RouteConditionFragment.setOnClickListener(myClick);
        LifeAssistantFragment = findViewById(R.id.LifeAssistantFragment);
        LifeAssistantFragment.setOnClickListener(myClick);
        DataAnalysisFragment = findViewById(R.id.DataAnalysisFragment);
        DataAnalysisFragment.setOnClickListener(myClick);
        PersonalcenterFragment = findViewById(R.id.PersonalcenterFragment);
        PersonalcenterFragment.setOnClickListener(myClick);
    }

    private class MyClick implements View.OnClickListener {

        @SuppressLint("CommitTransaction")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menu:
                    drawerLayout.openDrawer(menuLayout);
                    break;
                case R.id.batchRecharge:
                    if (App.getRechargeCarId() == null || App.getRechargeCarId().size() == 0) {
                        App.showAlertDialog(MainActivity.this, "警告", "请选择需要批量充值的充值项", null);
                    } else {
                        RechargeDialog rechargeDialog = new RechargeDialog(MainActivity.this, App.getRechargeCarId(), App.getRechargePlate());
                        rechargeDialog.setTargetFragment(accountFragment, 0);
                        rechargeDialog.show(accountFragment.getFragmentManager(), "Recharge");
                    }
                    break;
                case R.id.rechargeRecord:
                    switchFragment(false, PersonalcenterFragment.getText().toString(), new PersonalcenterFragment(1));
                    break;
                case R.id.AccountFragment:
                    switchFragment(true, AccountFragment.getText().toString(), accountFragment);
                    break;
                case R.id.BusInquiryFragment:
                    switchFragment(false, BusInquiryFragment.getText().toString(), new BusInquiryFragment());
                    break;
                case R.id.TrafficLightManagementFragment:
                    switchFragment(false, TrafficLightManagementFragment.getText().toString(), new TrafficLightManagementFragment());
                    break;
                case R.id.RouteConditionFragment:
                    switchFragment(false, RouteConditionFragment.getText().toString(), new RoadStatusFragment());
                    break;
                case R.id.LifeAssistantFragment:
                    switchFragment(false, LifeAssistantFragment.getText().toString(), new LifeAssistFragment());
                    break;
                case R.id.DataAnalysisFragment:
                    switchFragment(false, DataAnalysisFragment.getText().toString(), new DataAnalysisFragment());
                    break;
                case R.id.PersonalcenterFragment:
                    switchFragment(false, PersonalcenterFragment.getText().toString(), new PersonalcenterFragment());
                    break;
            }
        }
    }

    private void switchFragment(Boolean VISIBLE, String mtitle, Fragment fragment) {
        if (VISIBLE) {
            batchRecharge.setVisibility(View.VISIBLE);
            rechargeRecord.setVisibility(View.VISIBLE);
        } else {
            batchRecharge.setVisibility(View.GONE);
            rechargeRecord.setVisibility(View.GONE);
        }
        title.setText(mtitle);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
        drawerLayout.closeDrawer(menuLayout);
    }
}

