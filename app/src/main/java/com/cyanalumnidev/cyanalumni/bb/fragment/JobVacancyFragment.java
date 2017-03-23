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
import com.cyanalumnidev.cyanalumni.adapter.CustomAdapterJobVacancy;

/**
 * Created by Pandu on 07/03/2017.
 */

public class JobVacancyFragment extends Fragment {
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
    protected CustomAdapterJobVacancy mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset, mDataset2;
    protected int[] mDataset3;

    int [] jobVacancy_pic = {
            R.drawable.job_vacancy,
            R.drawable.job_vacancy,
            R.drawable.job_vacancy,
            R.drawable.job_vacancy,
            R.drawable.job_vacancy,
            R.drawable.job_vacancy,
            R.drawable.job_vacancy};
    String [] jobVacancy_title = {
            "Lowongan Manager Marketing PT. Telkom Indonesia",
            "Lowongan jadi orang sukses",
            "Lowongan menjadi Juara Rakyat",
            "Lowongan hehe",
            "Lowongan Gan?",
            "Lowongan huhu:(",
            "Lowongan ter-Cinta"};
    String [] jobVacancy_desc = {
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",
            "Lowongan Kerja adalah sebuah kata yang sangat umum didengar dalam percapakan orang dewasa. Dua kata tersebut seakan telah menjadi bahan pembicaraan yang wajib diutarakan dalam setiap kesempatan. Tak hanya orang dewasa, para remaja pun sering membicarakan mengenai Lowongan Pekerjaan dalam percakapannya bersama teman-temannya. Namun tahukah anda definisi mengenai Lowongan Kerja?",};
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
        View rootView = inflater.inflate(R.layout.fragment_job_vacancy, container, false);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_jobVacancy);

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

        mAdapter = new CustomAdapterJobVacancy(mDataset,mDataset2,mDataset3);
        // Set CustomAdapterJobVacancy as the adapter for RecyclerView.
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
        mDataset = new String[jobVacancy_title.length];
        mDataset2 = new String[jobVacancy_desc.length];
        mDataset3 = new int[jobVacancy_pic.length];
        for (int i = 0; i < jobVacancy_title.length; i++) {
            mDataset[i] = jobVacancy_title[i];
            mDataset2[i] = jobVacancy_desc[i];
            mDataset3[i] = jobVacancy_pic[i];
        }
    }
}
