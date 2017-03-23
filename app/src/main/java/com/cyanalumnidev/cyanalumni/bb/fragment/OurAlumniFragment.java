package com.cyanalumnidev.cyanalumni.bb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cyanalumnidev.cyanalumni.R;
import com.cyanalumnidev.cyanalumni.adapter.CustomAdapter;

/**
 * Created by Pandu on 07/03/2017.
 */

public class OurAlumniFragment extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60; // menampilkan data sebanyak value

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset, mDataset2, mDataset3, mDataset4;
    protected int[] mDataset5;

    int [] photo = {
            R.drawable.pandu,
            R.drawable.bill,
            R.drawable.byan,
            R.drawable.fadhil,
            R.drawable.fikri,
            R.drawable.gilar,
            R.drawable.ida,
            R.drawable.irul,
            R.drawable.muthe,
            R.drawable.naim,
            R.drawable.noval,
            R.drawable.rafli,
            R.drawable.thea,
            R.drawable.yuki,
            R.drawable.zainal};
    String [] alumni_name = {
            "Rd. Panji Erdinanda Pandu J. S.Kom",
            "Bill Power Sinaga S.Kom",
            "Byan Fernando S.Kom",
            "Fadhil Muhammad Rizal S.Kom",
            "Fasya Dzul Fikri Akbar S.Kom",
            "Gilar Prmayanto S.Kom",
            "Ida Mahmudah S.Kom",
            "Shahmirul Hafizullah S.Kom",
            "Mutmainah Adnan Lanure S.Kom",
            "Zulkarnain Sahlan S.Kom",
            "Muhammad Naufal Kesuma P. S.Kom",
            "Rafliansyah Ruslan S.Kom",
            "Thea'nisaa Andi Saffanah S.Kom",
            "Yukiyoshi Acdhiyat Macdar S.Kom",
            "Zainal Mutaqqin S.Kom"};
    String [] alumni_occ = {
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst",
            "IT Analyst"};
    String [] alumni_company = {
            "SAP Corp.",
            "PT Telkom Indonesia",
            "PT. Tokopedia",
            "SAP Corp.",
            "PT Telkom Indonesia",
            "PT. Tokopedia",
            "SAP Corp.",
            "PT Telkom Indonesia",
            "PT. Tokopedia",
            "SAP Corp.",
            "PT Telkom Indonesia",
            "PT. Tokopedia",
            "SAP Corp.",
            "PT Telkom Indonesia",
            "PT. Tokopedia"};
    String [] alumni_year = {
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014",
            "Sistem Informasi, 2014"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_our_alumni, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_ourAlumni);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new CustomAdapter(mDataset,mDataset2,mDataset3,mDataset4,mDataset5);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }
    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        mDataset = new String[alumni_name.length];
        mDataset2 = new String[alumni_occ.length];
        mDataset3 = new String[alumni_company.length];
        mDataset4 = new String[alumni_year.length];
        mDataset5 = new int[photo.length];
        for (int i = 0; i < alumni_name.length; i++) {
            mDataset[i] = alumni_name[i];
            mDataset2[i] = alumni_occ[i];
            mDataset3[i] = alumni_company[i];
            mDataset4[i] = alumni_year[i];
            mDataset5[i] = photo[i];
        }
    }
}
