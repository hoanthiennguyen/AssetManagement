package swd.project.assetmanagement;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemAssetFragment extends Fragment {
    int image;
    String name, type, location, status;

    public ItemAssetFragment() {
        // Required empty public constructor
    }

    public ItemAssetFragment(int image, String name, String type, String location, String status) {
        this.image = image;
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = status;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_asset, container, false);
        ImageView assetImage = view.findViewById(R.id.assetImage);
        TextView assetname = view.findViewById(R.id.assetName);
        TextView assetType = view.findViewById(R.id.assetType);
        TextView assetLocation = view.findViewById(R.id.assetLocation);
        Button assetStatus = view.findViewById(R.id.assetStatus);

        assetImage.setImageResource(image);
        assetname.setText(name);
        assetType.setText(type);
        assetLocation.setText(location);
        assetStatus.setText(status);
        if(status == "Active") {
            assetStatus.setBackgroundColor(Color.GREEN);
        }else if(status == "Prepair") {
            assetStatus.setBackgroundColor(Color.YELLOW);
        }else if(status == "Broken") {
            assetStatus.setBackgroundColor(Color.RED);
        }
        return view;
    }

}
