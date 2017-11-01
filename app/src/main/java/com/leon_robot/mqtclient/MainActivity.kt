package com.leon_robot.mqtclient

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.net.wifi.WifiInfo
import android.util.Log
import android.widget.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("WifiManagerLeak", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wifiimg.setOnClickListener {
            var wifiname: String=""
            //获取wifi信息
            val wifiManager = getSystemService(Context.WIFI_SERVICE) as WifiManager
            //wifi 当前是否可用
            if(wifiManager.isWifiEnabled)
            {
                val wifiinfo: WifiInfo=wifiManager.connectionInfo
                wifiinfo.hiddenSSID
                wifiname= wifiinfo.ssid

            }
            var dialog: Dialog = Dialog(this@MainActivity,android.R.style.Theme_Holo_Dialog_NoActionBar)
            dialog.setContentView(R.layout.smart_config)
            dialog.setCanceledOnTouchOutside(false)
            var dialogClose: ImageView = dialog.findViewById(R.id.dialogClose)
            dialogClose.setOnClickListener {
                dialog.dismiss()
            }
            //设置wifi 名称
            var ssidname: TextView=dialog.findViewById(R.id.ssidName)
            ssidname.setText("SSID:"+wifiname)
            dialog.show()
            val wificonfirm: Button=dialog.findViewById(R.id.wificonfirm)
            wificonfirm.setOnClickListener {
                val password: EditText=dialog.findViewById(R.id.password)
                Log.v("SSID",wifiname)
                Log.v("password",password.text.toString())
                var switchStatus: Int = 0
                val switch: Switch=dialog.findViewById(R.id.ssidhide)
                if(switch.isChecked)
                {
                    switchStatus=1
                }
                Log.v("switchStatus",switchStatus.toString())
                val resultcount: EditText=dialog.findViewById(R.id.resultcount)
                Log.v("resultcount",resultcount.text.toString())
            }
        }
    }
}
