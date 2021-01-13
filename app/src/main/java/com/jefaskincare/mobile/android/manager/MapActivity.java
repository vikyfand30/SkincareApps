//package com.jefaskincare.mobile.android.manager;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.Status;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
//import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
//import com.jefaskincare.mobile.android.R;
//import com.jefaskincare.mobile.android.adapter.AddressAdapter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
//
//    private static final String TAG = "MapActivity";
//
//    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
//    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
//    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
//    private static final float DEFAULT_ZOOM = 15f;
//
//    //private AutoCompleteTextView mSearchText;
//    private EditText mSearchText;
//    private ImageView ic_magnify;
//    private ImageView mGps;
//    private ImageView ivBackMap;
//    private Button btnSelectLocation;
//    private RecyclerView rvMapAddress;
//    private androidx.appcompat.widget.SearchView svAddress;
//    private ListMapAddressAdapter adapter;
//
//    private Boolean mLocationPermissionsGranted = false;
//    private GoogleMap mMap;
//    private FusedLocationProviderClient mFusedLocationProviderClient;
//    //private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
//    private GoogleApiClient mGoogleApiClient;
//    //private PlaceInfo mPlace;
//    private AutocompleteSupportFragment autocompleteFragment;
//    private Marker mMarker;
//    private LatLng center;
//    private LatLng lastLoc;
//    private boolean first = false;
//    private int ctr = 0;
//    private Address address;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
////        ic_magnify = findViewById(R.id.ic_magnify);
//        mGps = findViewById(R.id.ic_gps);
//        btnSelectLocation = findViewById(R.id.btnSelectLocation);
//
//        getLocationPermission();
//
//
////        init();
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        //Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "onMapReady: map is ready");
//        mMap = googleMap;
//
//        if (mLocationPermissionsGranted) {
//            getDeviceLocation();
//
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
//                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            mMap.setMyLocationEnabled(true);
//            mMap.getUiSettings().setMyLocationButtonEnabled(false);
////
//            init();
//        }
//    }
//
//    private void getDeviceLocation() {
//        Log.d(TAG, "getDeviceLocation: getting the devices current location");
//
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        try {
//            if (mLocationPermissionsGranted) {
//
//                final Task location = mFusedLocationProviderClient.getLastLocation();
//                location.addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "onComplete: found location!");
//                            Location currentLocation = (Location) task.getResult();
//                            lastLoc = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
//                            moveCamera(lastLoc,
//                                    DEFAULT_ZOOM,
//                                    "My Location");
//
//                        } else {
//                            Log.d(TAG, "onComplete: current location is null");
//                            Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        } catch (SecurityException e) {
//            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
//        }
//    }
//
//    private void init() {
//        Log.d(TAG, "init: initializing");
//
////        mSearchText.setOnEditorActionListener((v, actionId, event) -> {
////            if(actionId == EditorInfo.IME_ACTION_SEARCH
////                    || actionId == EditorInfo.IME_ACTION_DONE
////                    || event.getAction() == KeyEvent.ACTION_DOWN
////                    || event.getAction() == KeyEvent.KEYCODE_ENTER) {
////
////                geoLocate();
////            }
////            return false;
////        });
//
//        mGps.setOnClickListener(view -> {
//            Log.d(TAG, "onClick: clicked gps icon");
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLoc, DEFAULT_ZOOM));
//            mMarker.setPosition(lastLoc);
//        });
//
//        btnSelectLocation.setOnClickListener(v -> {
//            Intent data = new Intent();
//            data.putExtra("address", address.getAddressLine(0));
//            data.putExtra("latitude", address.getLatitude());
//            data.putExtra("longitude", address.getLongitude());
//            setResult(RESULT_OK, data);
//            finish();
//        });
//
////        ic_magnify.setOnClickListener(v -> geoLocate());
//
//        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
//            @Override
//            public void onCameraMove() {
//                Log.d("Moving", "move move move move move move move");
//                center = mMap.getCameraPosition().target;
//                mMarker.setPosition(center);
//                ctr = 0;
//            }
//        });
//
//        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
//            @Override
//            public void onCameraIdle() {
//                Log.d("Idle", "idle idle idle idle idle idle idle idle");
//                ctr++;
//                if (ctr == 1) {
//                    Log.d("Idle", "idle idle idle idle idle idle idle idle");
//                    center = mMap.getCameraPosition().target;
//                    geoLocateMove(center);
//                }
//
//            }
//        });
//
////        svAddress = findViewById(R.id.searchView);
////        svAddress.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////                geoLocateQuery(query);
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                return false;
////            }
////        });
//
////        rvMapAddress = findViewById(R.id.rvMapAddress);
////        adapter = new ListMapAddressAdapter(getApplicationContext(), null);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
////        rvMapAddress.setLayoutManager(layoutManager);
////        rvMapAddress.setAdapter(adapter);
//
//        ivBackMap = findViewById(R.id.ivBackMap);
//        ivBackMap.setOnClickListener(view -> this.onBackPressed());
//
//
//        initAutoComplete();
//
//    }
//
//    private void initAutoComplete() {
//        String apiKey = "AIzaSyDVhuphA3Kcf1rNr2AOXBKuOAYN_gN7gCM";
//
//        if (!Places.isInitialized()) {
//            Places.initialize(getApplicationContext(), apiKey);
//        }
//
//        // Initialize the AutocompleteSupportFragment.
//        autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME));
//
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//                //final LatLng latLng = place.getLatLng();
//                geoLocate(place.getName());
//            }
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
//
//    }
//
////    private void geoLocateQuery(String location) {
////        Log.d(TAG, "geoLocate: geolocating");
////
////        Geocoder geocoder = new Geocoder(MapActivity.this);
////        List<Address> list = new ArrayList<>();
////        try {
////            list = geocoder.getFromLocationName(location, 5);
////        } catch (IOException e) {
////            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
////        }
////
////        if (list.size() > 0) {
////            adapter.updateList(list);
////        }else{
////            adapter.updateList(null);
////        }
////    }
//
//    private void geoLocate(String location) {
//        Log.d(TAG, "geoLocate: geolocating");
//
//        Geocoder geocoder = new Geocoder(MapActivity.this);
//        List<Address> list = new ArrayList<>();
//        try {
//            list = geocoder.getFromLocationName(location, 1);
//        } catch (IOException e) {
//            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
//        }
//
//        if (list.size() > 0) {
//            address = list.get(0);
//
//            Log.d(TAG, "geoLocate: found a location: " + address.toString());
//            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
//
//            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
//                    address.getAddressLine(0));
//        }
//    }
//
//    private void geoLocateMove(LatLng latLng) {
//        Log.d(TAG, "geoLocate: geolocating");
//
//        //String searchString = mSearchText.getText().toString();
//
//        Geocoder geocoder = new Geocoder(MapActivity.this);
//        List<Address> list = new ArrayList<>();
//        try {
//            //list = geocoder.getFromLocationName(searchString, 1);
//            list = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
//        } catch (IOException e) {
//            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
//        }
//
//        if (list.size() > 0) {
//            address = list.get(0);
//
//            Log.d(TAG, "geoLocate: found a location: " + address.toString());
//            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
//
//            updateCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
//
//        }
//    }
//
////    private void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo){
////        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
////        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
////
////        mMap.clear();
////
////        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapActivity.this));
////
////        if(placeInfo != null){
////            try{
////                String snippet = "ListAddress: " + placeInfo.getAddress() + "\n" +
////                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
////                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
////                        "Price Rating: " + placeInfo.getRating() + "\n";
////
////                MarkerOptions options = new MarkerOptions()
////                        .position(latLng)
////                        .title(placeInfo.getName())
////                        .snippet(snippet);
////                mMarker = mMap.addMarker(options);
////
////            }catch (NullPointerException e){
////                Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage() );
////            }
////        }else{
////            mMap.addMarker(new MarkerOptions().position(latLng));
////        }
////
////        hideSoftKeyboard();
////    }
//
//    private void moveCamera(LatLng latLng, float zoom, String title) {
//        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
//
//        if (mMarker == null) {
//            MarkerOptions options = new MarkerOptions()
//                    .position(latLng)
//                    .title(title);
//            mMarker = mMap.addMarker(options);
//        } else {
//            mMarker.setPosition(latLng);
//            mMarker.setTitle(title);
//        }
//
//        hideSoftKeyboard();
//    }
//
//    private void updateCamera(LatLng latLng, float zoom, String title) {
//        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
//        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
//        mMarker.setPosition(latLng);
//        mMarker.setTitle(title);
//        autocompleteFragment.setText(title);
//    }
//
//    private void initMap() {
//        Log.d(TAG, "initMap: initializing map");
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//
//        mapFragment.getMapAsync(MapActivity.this);
//    }
//
//    private void getLocationPermission() {
//        Log.d(TAG, "getLocationPermission: getting location permissions");
//        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION};
//
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                mLocationPermissionsGranted = true;
//                initMap();
//            } else {
//                ActivityCompat.requestPermissions(this,
//                        permissions,
//                        LOCATION_PERMISSION_REQUEST_CODE);
//            }
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    permissions,
//                    LOCATION_PERMISSION_REQUEST_CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        Log.d(TAG, "onRequestPermissionsResult: called.");
//        mLocationPermissionsGranted = false;
//
//        switch (requestCode) {
//            case LOCATION_PERMISSION_REQUEST_CODE: {
//                if (grantResults.length > 0) {
//                    for (int i = 0; i < grantResults.length; i++) {
//                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
//                            mLocationPermissionsGranted = false;
//                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
//                            return;
//                        }
//                    }
//                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
//                    mLocationPermissionsGranted = true;
//                    //initialize our map
//                    initMap();
//                }
//            }
//        }
//    }
//
//    private void hideSoftKeyboard() {
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//    }
//
////    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
////        @Override
////        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////            //hideSoftKeyboard();
////
////            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
////            final String placeId = item.getPlaceId();
////
////            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
////                    .getPlaceById(mGoogleApiClient, placeId);
////            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
////        }
////    };
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//}
//
