package hu.ait.demos.placestovisit.data;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

import hu.ait.demos.placestovisit.R;

/**
 * Created by Peter on 2015.07.01..
 */
public class Place extends SugarRecord<Place> implements Serializable {
    public enum PlaceType {
        LANDSCAPE(0, R.drawable.landscape),
        CITY(1, R.drawable.city), BUILDING(2, R.drawable.building);

        private int value;
        private int iconId;

        private PlaceType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public int getValue() {
            return value;
        }

        public int getIconId() {
            return iconId;
        }

        public static PlaceType fromInt(int value) {
            for (PlaceType p : PlaceType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return LANDSCAPE;
        }
    }

    private String placeName;
    private String description;
    private Date pickUpDate;
    private PlaceType placeType;

    public Place() {

    }

    public Place(String placeName, String description, Date pickUpDate, PlaceType placeType) {
        this.placeName = placeName;
        this.description = description;
        this.pickUpDate = pickUpDate;
        this.placeType = placeType;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }
}
