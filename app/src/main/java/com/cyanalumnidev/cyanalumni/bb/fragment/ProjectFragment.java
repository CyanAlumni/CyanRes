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
import com.cyanalumnidev.cyanalumni.adapter.CustomAdapterProject;

/**
 * Created by Pandu on 07/03/2017.
 */

public class ProjectFragment extends Fragment {
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
    protected CustomAdapterProject mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset, mDataset2;
    protected int[] mDataset3;

    int [] project_pic = {
            R.drawable.project,
            R.drawable.project,
            R.drawable.project,
            R.drawable.project,
            R.drawable.project,
            R.drawable.project,
            R.drawable.project};
    String [] project_title = {
            "Project Bernilai Puluhan Jt. Rupiah",
            "Project-an Mahasiswa",
            "Project Juara Rakyat",
            "Project hehe",
            "Project Gan?",
            "Project huhu:(",
            "Project ter-Cinta"};
    String [] project_desc = {
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu.",
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu.",
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu.",
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu.",
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu.",
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu.",
            "Manajemen Proyek. Dalam manajemen proyek, proyek merupakan sebagai usaha sementara yang dilakukan untuk menciptakan produk layanan, unik atau hasil. Definisi yang lain adalah pengelolaan lingkungan yang dibuat untuk tujuan memberikan satu atau lebih produk bisnis sesuai dengan kasus bisnis tertentu."};
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
        View rootView = inflater.inflate(R.layout.fragment_project, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_project);

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

        mAdapter = new CustomAdapterProject(mDataset,mDataset2,mDataset3);
        // Set CustomAdapterProject as the adapter for RecyclerView.
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
        mDataset = new String[project_title.length];
        mDataset2 = new String[project_desc.length];
        mDataset3 = new int[project_pic.length];
        for (int i = 0; i < project_title.length; i++) {
            mDataset[i] = project_title[i];
            mDataset2[i] = project_desc[i];
            mDataset3[i] = project_pic[i];
        }
    }
}
