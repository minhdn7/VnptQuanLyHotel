package com.vnpt.hotel.manager.ui.fragment;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.common.Constants;
import com.vnpt.hotel.manager.domain.model.room.RoomModel;
import com.vnpt.hotel.manager.ui.adapter.RoomAdapter;
import com.vnpt.hotel.manager.ui.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListRoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListRoomFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view_room)
    RecyclerView recyclerView;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;
    private List<Object> listRoom;
    private RoomAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListRoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListRoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListRoomFragment newInstance(String param1, String param2) {
        ListRoomFragment fragment = new ListRoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_room, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomBar.setTabTitleTextAppearance(R.style.bottomBarTextView);
        bottomBar.setActiveTabColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
        bottomBar.setInActiveTabColor(getActivity().getResources().getColor(R.color.colorMenu));
        initializeData();
        GridLayoutManager linearLayout = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, dpToPx(5), true));
        adapter = new RoomAdapter(listRoom, getContext());
        recyclerView.setAdapter(adapter);
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void initializeData() {
        listRoom = new ArrayList<>();
        listRoom.add(new RoomModel.Room(1, "Phòng VIP", "101", "", Constants.ROOM_STATUS.EMPTY));
        listRoom.add(new RoomModel.Room(2, "Phòng bình dân", "102", "", Constants.ROOM_STATUS.CLEANING));
        listRoom.add(new RoomModel.Room(3, "Phòng giường đôi", "201", "", Constants.ROOM_STATUS.REPAIRING));
        listRoom.add(new RoomModel.Room(4, "Phòng bình dân", "202", "", Constants.ROOM_STATUS.USING));
        listRoom.add(new RoomModel.Room(5, "Phòng giường đơn", "301", "", Constants.ROOM_STATUS.EMPTY));
        listRoom.add(new RoomModel.Room(6, "Phòng VIP", "302", "", Constants.ROOM_STATUS.USING));
        listRoom.add(new RoomModel.Room(7, "Phòng bình dân", "401", "", Constants.ROOM_STATUS.USING));
        listRoom.add(new RoomModel.Room(8, "Phòng giường đôi", "402", "", Constants.ROOM_STATUS.REPAIRING));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
