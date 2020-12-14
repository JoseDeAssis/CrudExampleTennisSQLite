package com.example.crudexampletennissqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FirstFragment extends Fragment implements TennisDetailsListener {

    private TennisListRVAdapter mAdapter;
    private TennisDao tennisDao;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tennisDao = new TennisDao(getContext());

        RecyclerView mRecyclerView = view.findViewById(R.id.listaTenisRecyclerView);

        mAdapter = new TennisListRVAdapter(getContext(), this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        mAdapter.setTennisList(tennisDao.getAll(TennisDao.TENNIS_TABLE));

        FloatingActionButton mFloatingActionButton = view.findViewById(R.id.tennisListFragmentFAB);
        FloatingActionButton mFloatingActionButtonCRUD = view.findViewById(R.id.tennisListFragmentFABCRUD);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });

        mFloatingActionButton.setOnClickListener(view1 -> {
            Bundle args = new Bundle();
            args.putString("type", "CREATE");
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment, args);
        });

        mFloatingActionButtonCRUD.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_crudFragment);
        });
    }

    @Override
    public void deleteTennis(long id) {
//        tennisDao.delete(TennisDao.TENNIS_TABLE, id);
        Cursor cursor = tennisDao.get(TennisDao.TENNIS_TABLE, id);

        int tennisId = cursor.getInt(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_ID));
        String tennisName = cursor.getString(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_NAME));
        double tennisPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(TennisDao.COLUMN_TENNIS_PRICE));

        String toast = "id: " + tennisId + "\nname: " + tennisName + "\nprice: " + tennisPrice;

        Toast.makeText(getContext(), toast, Toast.LENGTH_LONG).show();

//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateTennis(TennisModel tennisModel) {
        Bundle args = new Bundle();
        args.putString("type", "EDIT");
        args.putSerializable("tennisModel", tennisModel);
        NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment, args);
    }
}