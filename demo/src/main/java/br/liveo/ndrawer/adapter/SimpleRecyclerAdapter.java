package br.liveo.ndrawer.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import br.liveo.ndrawer.R;
import br.liveo.ndrawer.ui.activity.FamilyTrackerEditFrag_New;
import br.liveo.ndrawer.ui.activity.UserTracker;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder> {
    static List<UserTracker> versionModels;
    Boolean isHomeList = false;

    public static List<String> homeActivitiesList = new ArrayList<String>();
    public static List<String> homeActivitiesSubList = new ArrayList<String>();
    private Context context = null;
    OnItemClickListener clickListener;


    public void setHomeActivitiesList(Context context) {
        String[] listArray = context.getResources().getStringArray(R.array.home_activities);
        String[] subTitleArray = context.getResources().getStringArray(R.array.home_activities_subtitle);
        for (int i = 0; i < listArray.length; ++i) {
            homeActivitiesList.add(listArray[i]);
            homeActivitiesSubList.add(subTitleArray[i]);
        }
    }

    public SimpleRecyclerAdapter(Context context) {
        isHomeList = true;
        this.context = context;
        setHomeActivitiesList(context);
    }


    public SimpleRecyclerAdapter(List<UserTracker> versionModels) {
        isHomeList = false;
        this.versionModels = versionModels;

    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
        if (isHomeList) {
            versionViewHolder.childName.setText(homeActivitiesList.get(i));
            versionViewHolder.geofenceName.setText(homeActivitiesSubList.get(i));
        } else {
            versionViewHolder.childName.setText(versionModels.get(i).getTrackerName());


            versionViewHolder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    versionViewHolder.min.setVisibility(View.INVISIBLE);
                    versionViewHolder.EditRl.setVisibility(View.VISIBLE);

                }
            });

            versionViewHolder.save.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    versionViewHolder.childName.setText(versionViewHolder.childNameEdit.getText().toString());

                    FamilyTrackerEditFrag_New.listData.get(i).setTrackerName(versionViewHolder.childNameEdit.getText().toString());

                    versionViewHolder.min.setVisibility(View.VISIBLE);
                    versionViewHolder.EditRl.setVisibility(View.INVISIBLE);

                }

            });




        }
    }

    @Override
    public int getItemCount() {
        if (isHomeList)
            return homeActivitiesList == null ? 0 : homeActivitiesList.size();
        else
            return versionModels == null ? 0 : versionModels.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView childName;
        TextView geofenceName;
        EditText childNameEdit;
        RelativeLayout min, EditRl;

        ImageView edit, delete, geofenceMap, childProfilePic, save ;

        public VersionViewHolder(final View itemView) {
            super(itemView);

            context = itemView.getContext();

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            childName = (TextView) itemView.findViewById(R.id.childName);
            geofenceName = (TextView) itemView.findViewById(R.id.geofenceName);
            childProfilePic = (ImageView) itemView
                    .findViewById(R.id.childProfilePic);
            save = (ImageView) itemView
                    .findViewById(R.id.save_edit);
            childNameEdit = (EditText) itemView.findViewById(R.id.childName_edit);
            min = (RelativeLayout) itemView.findViewById(R.id.min);
            EditRl = (RelativeLayout) itemView.findViewById(R.id.editRl);

            edit = (ImageView) itemView
                    .findViewById(R.id.edittracker);
            delete = (ImageView) itemView
                    .findViewById(R.id.deletetracker);
            geofenceMap = (ImageView) itemView
                    .findViewById(R.id.geofenceMap);
            geofenceMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Set Geofence Activity", Toast.LENGTH_SHORT).show();

                }
            });


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Log.e("Sizzzzz", FamilyTrackerEditFrag_New.listData.size() + "");

                    FamilyTrackerEditFrag_New.listData.remove(getPosition());

                    SimpleRecyclerAdapter.this.notifyDataSetChanged();


                }
            });



            if (isHomeList) {
                itemView.setOnClickListener(this);
            } else {
                geofenceName.setVisibility(View.GONE);
            }

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

}
