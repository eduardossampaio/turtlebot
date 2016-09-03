package com.apps.esampaio.turtlebot.view.acttivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.apps.esampaio.turtlebot.R;
import com.apps.esampaio.turtlebot.core.connection.Connection;
import com.apps.esampaio.turtlebot.core.connection.ConnectionFactory;
import com.apps.esampaio.turtlebot.core.connection.bluetooth.BluetoothUtils;
import com.apps.esampaio.turtlebot.core.devices.Device;
import com.apps.esampaio.turtlebot.core.devices.DeviceFactory;
import com.apps.esampaio.turtlebot.core.log.Log;
import com.apps.esampaio.turtlebot.core.robots.Turtlebot;
import com.apps.esampaio.turtlebot.view.acttivities.listeners.HeadMovementJoystickListener;
import com.apps.esampaio.turtlebot.view.acttivities.listeners.MovementJoystickListener;
import com.apps.esampaio.turtlebot.view.dialogs.DialogBuilder;
import com.apps.esampaio.turtlebot.view.dialogs.ListDeviceDialog;
import com.jmedeisis.bugstick.Joystick;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Device selectedDevice = null;
    private Turtlebot turtlebot = null;
    private MovementJoystickListener movementJoystickListener;
    private HeadMovementJoystickListener headMovementJoystickListener;
    private ToggleButton ledsButton;
    private ToggleButton automaticButton;
    private Joystick joystick;
    private Joystick joystickHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.info("starting activity");
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        movementJoystickListener = new MovementJoystickListener();
        headMovementJoystickListener = new HeadMovementJoystickListener();

        joystick = (Joystick) findViewById(R.id.joystick);
        joystick.setJoystickListener(movementJoystickListener);

        joystickHead = (Joystick) findViewById(R.id.joystick_head);
        joystickHead.setJoystickListener(headMovementJoystickListener);

        refreshActionBar();
        ledsButton = (ToggleButton) findViewById(R.id.led_button);
        automaticButton = (ToggleButton) findViewById(R.id.auto_button);
        ledsButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked) {
                        if (turtlebot != null)
                            Log.info("turning on leds");
                            turtlebot.ledsOn();
                    } else {
                        if (turtlebot != null)
                            Log.info("turning off leds");
                            turtlebot.ledsOff();
                    }
                } catch (Exception e) {
                    Log.error("Error",e);
                }
            }

        });
        if(savedInstanceState == null)
            checkBluetooth();
        disableViews();

    }

    private void checkBluetooth(){
        if(!BluetoothUtils.isEnabled()){
            BluetoothUtils.requestEnableBluetooth(this);
        }
    }

    private void refreshActionBar() {
        if (turtlebot == null) {
            getSupportActionBar().setSubtitle(getString(R.string.device_not_selected));
        } else {
            getSupportActionBar().setSubtitle(getString(R.string.device_selected) + selectedDevice.getName());
        }
    }

    private void disableViews(){
        ledsButton.setEnabled(false);
        automaticButton.setEnabled(false);
        joystick.setEnabled(false);
        joystickHead.setEnabled(false);
    }
    private void enableViews(){
        ledsButton.setEnabled(true);
        automaticButton.setEnabled(true);
        joystick.setEnabled(true);
        joystickHead.setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (turtlebot == null) {
            menu.findItem(R.id.menu_connect_to_device).setVisible(true);
            menu.findItem(R.id.menu_disconnect).setVisible(false);
        } else {
            menu.findItem(R.id.menu_connect_to_device).setVisible(false);
            menu.findItem(R.id.menu_disconnect).setVisible(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_connect_to_device:
                openListDevicesDialog();
                return true;
            case R.id.menu_disconnect:
                disconnect();
                return true;
            case R.id.menu_configuration:
                startConfigurationActivity();
                return true;
            case R.id.menu_about:
                showAbout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startConfigurationActivity() {
        Intent intent = new Intent(this,ConfigurationActivity.class);
        startActivity(intent);
    }

    private void showAbout() {
        DialogBuilder.createSimpleAlertDialog(this,getString(R.string.app_name),getString(R.string.about_message));
    }

    private void connect(Device device) throws Exception{

            Log.info("Connecting to device: "+device.getName());
            Connection connection = ConnectionFactory.createConnection(device);
            connection.connect(device);
            turtlebot = new Turtlebot(connection);
            movementJoystickListener.setTurtlebot(turtlebot);
            headMovementJoystickListener.setTurtlebot(turtlebot);
            Log.info("connected");

    }

    private void disconnect() {
        try {
            Log.info("disconnecting");
            this.turtlebot.shutdown();
            this.turtlebot = null;
            movementJoystickListener.setTurtlebot(null);
            headMovementJoystickListener.setTurtlebot(null);
            refreshActionBar();
            disableViews();
            Log.info("disconnected");
        } catch (Exception e) {
            Log.error("Error while disconnecting",e);
        }
    }

    private void openListDevicesDialog() {
        try {
            Log.info("getting devices");
            final List<Device> devices = DeviceFactory.getDevices();
            if (devices.isEmpty()) {
                Log.info("No devices found");
                DialogBuilder.createSimpleAlertDialog(this, getString(R.string.app_name), getString(R.string.dialog_no_devices_found));
            } else {
                ListDeviceDialog listDeviceDialog = new ListDeviceDialog(this, devices);
                listDeviceDialog.create();
                listDeviceDialog.setOnClickListenner(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedDevice = devices.get(position);
                        new LoadingAsyncTask().execute(selectedDevice);
                    }
                });
                listDeviceDialog.show();
                Log.info("devices found");
            }
        } catch (Exception e) {
            DialogBuilder.createSimpleErrorDialog(this, getString(R.string.app_name), e);
        }
    }

    private class LoadingAsyncTask extends AsyncTask<Device,Void,Exception>{
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(MainActivity.this, MainActivity.this.getString(R.string.app_name),
                   MainActivity.this.getString(R.string.dialog_connecting_message), true);
        }

        @Override
        protected Exception doInBackground(Device... params) {
            Device device = params[0];
            try{
                connect(device);
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Exception e) {
            dialog.dismiss();
            if(e != null){
                DialogBuilder.createSimpleErrorDialog(MainActivity.this, getString(R.string.error_open_connection), e);
            }
            refreshActionBar();
            enableViews();
        }
    }

}
