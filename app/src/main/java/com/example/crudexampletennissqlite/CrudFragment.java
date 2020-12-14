package com.example.crudexampletennissqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CrudFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crud, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText tennisId = view.findViewById(R.id.tennisIdCRUDEditTxt);
        TextInputEditText tennisName = view.findViewById(R.id.tennisNameCRUDEditTxt);
        TextInputEditText tennisPrice = view.findViewById(R.id.tennisPriceCRUDEditTxt);

        Button searchButton = view.findViewById(R.id.searchButtonCrudFragment);
        Button createButton = view.findViewById(R.id.createButtonCrudFragment);
        Button updateButton = view.findViewById(R.id.updateButtonCrudFragment);
        Button deleteButton = view.findViewById(R.id.deleteButtonCrudFragment);

        TennisDao tennisDao = new TennisDao(getContext());

        searchButton.setOnClickListener(view1 -> {
            try{
                long id = Integer.parseInt(tennisId.getText().toString());
                Cursor cursor = tennisDao.get(TennisDao.TENNIS_TABLE, id);
                tennisName.setText(cursor.getString(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_NAME)));
                tennisPrice.setText(cursor.getString(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_PRICE)));
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        createButton.setOnClickListener(view1 -> {
            try{
                ContentValues cv = new ContentValues();
                cv.put("TENNIS_TABLE_ID", Integer.parseInt(tennisId.getText().toString()));
                cv.put("TENNIS_TABLE_NAME", tennisName.getText().toString());
                cv.put("TENNIS_TABLE_PRICE", Double.parseDouble(tennisPrice.getText().toString()));

                if(tennisDao.insert(TennisDao.TENNIS_TABLE, cv) != -1) {
                    Toast.makeText(getContext(), "Element inserted successfully", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        updateButton.setOnClickListener(view1 -> {
            try{
                ContentValues cv = new ContentValues();
                long id = Integer.parseInt(tennisId.getText().toString());
                cv.put("TENNIS_TABLE_ID", id);
                cv.put("TENNIS_TABLE_NAME", tennisName.getText().toString());
                cv.put("TENNIS_TABLE_PRICE", Double.parseDouble(tennisPrice.getText().toString()));

                if(tennisDao.update(TennisDao.TENNIS_TABLE, id, cv) != -1) {
                    Toast.makeText(getContext(), "Element updated successfully", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        deleteButton.setOnClickListener(view1 -> {
            try{
                long id = Integer.parseInt(tennisId.getText().toString());

                if(tennisDao.delete(TennisDao.TENNIS_TABLE, id) != -1) {
                    Toast.makeText(getContext(), "Element deleted successfully", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}