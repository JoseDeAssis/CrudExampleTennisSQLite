package com.example.crudexampletennissqlite;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class SecondFragment extends Fragment {

    TennisModel tennisModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText tennisName = view.findViewById(R.id.tennisNameEditTxt);
        TextInputEditText tennisPrice = view.findViewById(R.id.tennisPriceEditTxt);
        ImageView img = view.findViewById(R.id.secondFragmentTenisImg);
        String image = "steam.png";

        MaterialButton materialButton = view.findViewById(R.id.secondFragmentButton);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboardFrom(getContext(), view);
                try{
                    if(tennisName.getText() != null && tennisPrice.getText() != null) {
                        TennisDao tennisDao = new TennisDao(getContext());

                       /* if(tennisModel == null)
                            tennisModel = new TennisModel();

                        tennisModel.setTennisName(tennisName.getText().toString());
                        tennisModel.setTennisPrice(Double.parseDouble(tennisPrice.getText().toString()));*/

                        ContentValues cv = new ContentValues();
                        cv.put("TENNIS_TABLE_NAME", tennisName.getText().toString());
                        cv.put("TENNIS_TABLE_PRICE", Double.parseDouble(tennisPrice.getText().toString()));
                        if(getArguments().getString("type").equals("CREATE")) {
                            if(tennisDao.insert(TennisDao.TENNIS_TABLE, cv) != -1) {
                                NavHostFragment.findNavController(SecondFragment.this)
                                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                            } else {
                                Toast.makeText(getContext(), "Não deu certo", Toast.LENGTH_LONG).show();
                            }
                        } else if(tennisDao.update(TennisDao.TENNIS_TABLE, tennisModel.getTennisId(), cv) != -1) {
                            NavHostFragment.findNavController(SecondFragment.this)
                                    .navigate(R.id.action_SecondFragment_to_FirstFragment);
                        }
                    }
                } catch(Exception e) {
                    Toast.makeText(getContext(), "Falha ao inserir elemento no banco de dados. Erro: " + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        if(getArguments() != null && getArguments().getString("type").equals("EDIT")) {
            tennisModel = (TennisModel) getArguments().getSerializable("tennisModel");

            image = tennisModel.getTennisName() + ".png";

            DecimalFormat df = new DecimalFormat("#.00");
            String price = Double.valueOf(df.format(tennisModel.getTennisPrice())) + "";

            tennisName.setText(tennisModel.getTennisName());
            tennisPrice.setText(price);

            materialButton.setText(R.string.update);
        }

        try {
            // get input stream
            InputStream ims = getContext().getAssets().open(image);
            // load image as Drawable
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            img.setImageBitmap(bitmap);
            ims.close();
        } catch (IOException ex) {
            Toast.makeText(getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}