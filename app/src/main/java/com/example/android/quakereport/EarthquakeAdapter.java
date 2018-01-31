package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by praty on 21-Dec-17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>
{
    private static final String LOCATION_SEPARATOR = " of ";
    public EarthquakeAdapter(Context context, List<Earthquake> arrayList)
    {
        super(context,0,arrayList);
    }
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Earthquake currentEarthquake=getItem(position);
        TextView magnitudeTextview=(TextView) listItemView.findViewById(R.id.magnitude_text_view);
        String formattedMagnitude=formatMagnitude(currentEarthquake.getmMagnitude());
        magnitudeTextview.setText(formattedMagnitude);
        String originalLocation=currentEarthquake.getmLocation();
        String primaryLocation,locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView primaryLocationTextview=(TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationTextview.setText(primaryLocation);
        TextView offsetLocationTextvew=(TextView)listItemView.findViewById(R.id.location_offset);
        offsetLocationTextvew.setText(locationOffset);
        TextView dateTextview=(TextView) listItemView.findViewById(R.id.date_text_view);
        TextView timeTextView=(TextView)listItemView.findViewById(R.id.time_text_view);
        Date dateObject=new Date(currentEarthquake.getmDate());
        String formatedDate=formatDate(dateObject);
        String formatedTime=formatTime(dateObject);
        dateTextview.setText(formatedDate);
        timeTextView.setText(formatedTime);
        GradientDrawable magnitudeCircle=(GradientDrawable)magnitudeTextview.getBackground();
        int magnitudeColor=getMagnitudeColor(currentEarthquake.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        return listItemView;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
