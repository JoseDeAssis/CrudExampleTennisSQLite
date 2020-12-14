package com.example.crudexampletennissqlite;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TennisListRVAdapter extends RecyclerView.Adapter<com.example.crudexampletennissqlite.TennisListRVAdapter.TennisListRVViewHolder> implements Serializable {

    private final LayoutInflater mLayoutInflater;
    private List<TennisModel> tennisList;
    private final TennisDetailsListener tennisDetailsListener;
    private final Context context;

    public class TennisListRVViewHolder extends RecyclerView.ViewHolder {

        private final TextView tennisModel, tennisId, tennisPrice;
        private final ImageView tennisImg;

        private TennisListRVViewHolder(View itemView, final TennisDetailsListener listener) {
            super(itemView);

            tennisModel = itemView.findViewById(R.id.recyclerViewTennisModel);
            tennisId = itemView.findViewById(R.id.recyclerViewTennisId);
            tennisPrice = itemView.findViewById(R.id.recyclerViewTennisPrice);
            tennisImg = itemView.findViewById(R.id.recyclerViewTenisImg);
            ImageButton tennisDeleteBtn = itemView.findViewById(R.id.recyclerViewDeleteBtn);
            ImageButton tennisEditBtn = itemView.findViewById(R.id.recyclerViewEditBtn);
            CardView tennisCardView = itemView.findViewById(R.id.recyclerViewTennisCardView);

            tennisCardView.setOnClickListener(view -> {
                TennisModel tennis = tennisList.get(getAdapterPosition());
                listener.updateTennis(tennis);
            });

            tennisEditBtn.setOnClickListener(view -> {
                TennisModel tennis = tennisList.get(getAdapterPosition());
                listener.updateTennis(tennis);
            });

            tennisDeleteBtn.setOnClickListener(view -> {
                long tennisId = tennisList.get(getAdapterPosition()).getTennisId();
                listener.deleteTennis(tennisId);
            });
        }
    }

    public TennisListRVAdapter(Context context, TennisDetailsListener mListener) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.tennisDetailsListener = mListener;
        tennisList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TennisListRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TennisListRVViewHolder(mLayoutInflater.
                inflate(R.layout.app_rv_sneakers_list, parent, false), this.tennisDetailsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TennisListRVViewHolder holder, final int position) {
        if (this.tennisList != null) {
            DecimalFormat df = new DecimalFormat("#,###.00");

            holder.tennisModel.setText(this.tennisList.get(position).getTennisName());
            String tennisId = "Id: " + this.tennisList.get(position).getTennisId();
            holder.tennisId.setText(tennisId);

            String tennisPrice = "$ " + df.format(this.tennisList.get(position).getTennisPrice());
            holder.tennisPrice.setText(tennisPrice);

            try {
                // get input stream
                InputStream ims = context.getAssets().open(this.tennisList.get(position).getTennisName() + ".png");
                // load image as Drawable
                Bitmap bitmap = BitmapFactory.decodeStream(ims);
                holder.tennisImg.setImageBitmap(bitmap);
                ims.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setTennisList(List<TennisModel> tennisList) {
        this.tennisList = tennisList;
        notifyDataSetChanged();
    }

    public void setTennisList(Cursor cursor) {
        while(cursor.moveToNext()) {
            int tennisId = cursor.getInt(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_ID));
            String tennisName = cursor.getString(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_NAME));
            double tennisPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_PRICE));

            TennisModel tennisModel = new TennisModel();
            tennisModel.setTennisName(tennisName);
            tennisModel.setTennisId(tennisId);
            tennisModel.setTennisPrice(tennisPrice);
            this.tennisList.add(tennisModel);
        }

        cursor.close();
    }

    @Override
    public int getItemCount() {
        return tennisList != null ? tennisList.size() : 0;
    }

}
