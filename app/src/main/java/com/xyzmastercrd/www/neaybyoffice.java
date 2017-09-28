package com.xyzmastercrd.www;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.media.Image;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class neaybyoffice extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    final private int REQUEST_CODE_LOC_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neaybyoffice);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ImageView back = (ImageView)findViewById(R.id.back);
    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(neaybyoffice.this,home.class);
            startActivity(i);
        }
    });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//


        if (Build.VERSION.SDK_INT >= 23) {
            locPer();
        } else {
            mMap.setMyLocationEnabled(true);

        }



        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Head Office"));
   //     mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        LatLng s1 = new LatLng(26, 86);
        LatLng s2 = new LatLng(20.540221, 27.246094);
        LatLng s3 = new LatLng(23.150462, 77.519531);
        LatLng s4 = new LatLng(16.878147, 79.277344);
        LatLng s5 = new LatLng(19.340949, 75.563965);
        LatLng s6 = new LatLng(21.277858, 74.509277);
        LatLng s7 = new LatLng(13.996704, 77.629395);
        LatLng s8 = new LatLng(12.327927, 78.596191);
        LatLng s9 = new LatLng(25.114202, 77.937012);
        LatLng s10 = new LatLng(20.744556, 85.144043);
        LatLng s11 = new LatLng(24.635783, 86.813965);
        LatLng s12 = new LatLng(16.413692, 80.617676);
        LatLng s13 = new LatLng(11.424841, 77.233887);
        LatLng s14 = new LatLng(26.302033, 73.146973);
        LatLng s15 = new LatLng(26.302033, 81.145020);
        LatLng s16 = new LatLng(26.538166, 83.430176);
        LatLng s17 = new LatLng(26.538166, 83.430176);
        LatLng s18 = new LatLng(24.715648, 79.826660);
        LatLng s19 = new LatLng(8.655699, 77.849121);
        LatLng s20 = new LatLng(11.726201, 79.519043);
        LatLng s21 = new LatLng(15.017749, 74.333496);

       mMap.addMarker(new MarkerOptions()
                .position(s1)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
               .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s2)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s3)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s4)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s5)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s6)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s7)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s8)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s9)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s10)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s11)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));

        mMap.addMarker(new MarkerOptions()
                .position(s12)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s13)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s14)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s15)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s16)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s17)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s18)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s19)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 60, 108))));
        mMap.addMarker(new MarkerOptions()
                .position(s20)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 70, 118))));
        mMap.addMarker(new MarkerOptions()
                .position(s21)
                .title("XYZ eCommerce")
                .snippet("Branch Office")
                        // .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_s)));
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmap("marker_s", 70, 118))));
    }


    public Bitmap resizeBitmap(String drawableName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(drawableName, "drawable", getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(neaybyoffice.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public void locPer(){
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                showMessageOKCancel("Allow permission to view Offices",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                                        REQUEST_CODE_LOC_PERMISSIONS);
                            }
                        });
                return;
            }
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOC_PERMISSIONS);
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_LOC_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    mMap.setMyLocationEnabled(true);
                } else {
                    // Permission Denied
                    Toast.makeText(neaybyoffice.this, "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
