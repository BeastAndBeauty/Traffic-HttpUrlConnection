package com.traffic.pyp.traffic3.ChildFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.traffic.pyp.traffic3.Application.App;
import com.traffic.pyp.traffic3.R;

public class ThresholdSettingFragment extends Fragment {

    private TextView threshold_tv;
    private EditText threshold_ed;
    private Button btn_thresholdsetting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_threshold_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        threshold_tv = view.findViewById(R.id.threshold_tv);
        threshold_ed = view.findViewById(R.id.threshold_ed);
        btn_thresholdsetting = view.findViewById(R.id.btn_thresholdsetting);
        threshold_tv.setText(App.getAlerting() + "");
        btn_thresholdsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    App.setAlerting(Integer.parseInt(threshold_ed.getText().toString()));
                    App.showAlertDialog(getContext(), "提醒", "设置成功", null);
                    threshold_tv.setText(App.getAlerting() + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
