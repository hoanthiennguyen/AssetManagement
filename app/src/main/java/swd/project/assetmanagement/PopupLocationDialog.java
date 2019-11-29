package swd.project.assetmanagement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

public class PopupLocationDialog extends AppCompatDialogFragment {
    private HandleData handleData;
    Spinner spinnerBlock, spinnerFloor,spinnerRoom;
    String block,floor,room;

    public PopupLocationDialog(HandleData handleData) {
        this.handleData = handleData;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_location_dialog,null);
        spinnerBlock = view.findViewById(R.id.spinnerBlock);
        spinnerFloor = view.findViewById(R.id.spinnerFloor);
        spinnerRoom = view.findViewById(R.id.spinnerRoom);
        String[] blockList = {"A","B","C"};
        ArrayAdapter<String> blockAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, blockList);
        spinnerBlock.setAdapter(blockAdapter);
        String[] floorList = {"1","2","3"};
        ArrayAdapter<String> floorAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, floorList);
        spinnerFloor.setAdapter(floorAdapter);
        String[] roomList = {"101","102","103"};
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, roomList);
        spinnerRoom.setAdapter(roomAdapter);

        spinnerBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                block = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setView(view)
                .setTitle("Change Location")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handleData.processData(block,floor,room);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
    public interface HandleData{
        void processData(String block, String floor, String room);
    }
}
