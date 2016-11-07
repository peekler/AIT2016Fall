package hu.ait.demos.placestovisit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import hu.ait.demos.placestovisit.adapter.PlacesAdapter;
import hu.ait.demos.placestovisit.data.Place;
import hu.ait.demos.placestovisit.touch.PlacesListTouchHelperCallback;


public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_NEW_PLACE = 101;
    public static final int REQUEST_EDIT_PLACE = 102;
    public static final String KEY_EDIT = "KEY_EDIT";
    private PlacesAdapter placesAdapter;
    private CoordinatorLayout layoutContent;
    private DrawerLayout drawerLayout;
    private Place placeToEditHolder;
    private int placeToEditPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*List<Place> placesList = new ArrayList<Place>();
        placesList.add(new Place("London", "Nice city",
                new Date(), Place.PlaceType.CITY));
        placesList.add(new Place("Statue of Liberty", "Statue",
                new Date(), Place.PlaceType.BUILDING));
        placesList.add(new Place("Copacabana", "I'd like to go there",
                new Date(), Place.PlaceType.LANDSCAPE));*/
        List<Place> placesList = Place.listAll(Place.class);

        placesAdapter = new PlacesAdapter(placesList, this);
        RecyclerView recyclerViewPlaces = (RecyclerView) findViewById(
                R.id.recyclerViewPlaces);
        recyclerViewPlaces.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPlaces.setAdapter(placesAdapter);

        PlacesListTouchHelperCallback touchHelperCallback = new PlacesListTouchHelperCallback(
                placesAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(
                touchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerViewPlaces);

        layoutContent = (CoordinatorLayout) findViewById(
                R.id.layoutContent);

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreatePlaceActivity();
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.action_add:
                                showCreatePlaceActivity();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_about:
                                showSnackBarMessage(getString(R.string.txt_about));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_help:
                                showSnackBarMessage(getString(R.string.txt_help));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                        }

                        return false;
                    }
                });

        setUpToolBar();
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void showCreatePlaceActivity() {
        Intent intentStart = new Intent(MainActivity.this,
                CreatePlaceActivity.class);
        startActivityForResult(intentStart, REQUEST_NEW_PLACE);
    }

    public void showEditPlaceActivity(Place placeToEdit, int position) {
        Intent intentStart = new Intent(MainActivity.this,
                CreatePlaceActivity.class);
        placeToEditHolder = placeToEdit;
        placeToEditPosition = position;

        intentStart.putExtra(KEY_EDIT, placeToEdit);
        startActivityForResult(intentStart, REQUEST_EDIT_PLACE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == REQUEST_NEW_PLACE) {
                    Place place = (Place) data.getSerializableExtra(
                            CreatePlaceActivity.KEY_PLACE);

                    placesAdapter.addPlace(place);
                    showSnackBarMessage(getString(R.string.txt_place_added));
                } else if (requestCode == REQUEST_EDIT_PLACE) {
                    Place placeTemp = (Place) data.getSerializableExtra(
                            CreatePlaceActivity.KEY_PLACE);

                    placeToEditHolder.setPlaceName(placeTemp.getPlaceName());
                    placeToEditHolder.setDescription(placeTemp.getDescription());
                    placeToEditHolder.setPlaceType(placeTemp.getPlaceType());

                    if (placeToEditPosition != -1) {
                        placesAdapter.updatePlace(placeToEditPosition, placeToEditHolder);
                        placeToEditPosition = -1;
                    }else {
                        placesAdapter.notifyDataSetChanged();
                    }
                    showSnackBarMessage(getString(R.string.txt_place_edited));
                }
                break;
            case RESULT_CANCELED:
                showSnackBarMessage(getString(R.string.txt_add_cancel));
                break;
        }
    }


    private void showSnackBarMessage(String message) {
        Snackbar.make(layoutContent,
                message,
                Snackbar.LENGTH_LONG
        ).setAction(R.string.action_hide, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                showCreatePlaceActivity();
                return true;
            default:
                showCreatePlaceActivity();
                return true;
        }
    }

}

