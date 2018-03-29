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
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseFragment;
import com.vnpt.hotel.manager.app.utils.AppDef;
import com.vnpt.hotel.manager.common.Constants;
import com.vnpt.hotel.manager.domain.model.LoginUser;
import com.vnpt.hotel.manager.domain.model.motel.ListRoomResponse;
import com.vnpt.hotel.manager.domain.model.room.RoomModel;
import com.vnpt.hotel.manager.ui.adapter.RoomAdapter;
import com.vnpt.hotel.manager.ui.presenter.motel.ListRoomPresenter;
import com.vnpt.hotel.manager.ui.view.motel.ListRoomView;
import com.vnpt.hotel.manager.ui.widget.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
public class ListRoomFragment extends BaseFragment implements ListRoomView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view_room)
    RecyclerView recyclerView;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private List<Object> listRoom = new ArrayList<>();
    private RoomAdapter adapter;
    private Integer idHotel = 0;

    // TODO: inject presenter:
    @Inject
    ListRoomPresenter listRoomPresenter;

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

        addControls();
    }

    private void addControls() {
        idHotel = getArguments().getInt("HOTEL_ID");
        bottomBar.setTabTitleTextAppearance(R.style.bottomBarTextView);
        bottomBar.setActiveTabColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
        bottomBar.setInActiveTabColor(getActivity().getResources().getColor(R.color.colorMenu));
        GridLayoutManager linearLayout = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, dpToPx(5), true));
        adapter = new RoomAdapter(listRoom, getContext());
        recyclerView.setAdapter(adapter);
        showProgress();
        listRoomPresenter.setView(this);
        listRoomPresenter.onViewCreate();
        listRoomPresenter.getList(AppDef.TOKEN_USER, idHotel);
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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

    @Override
    public void onLoadLoginUser(LoginUser loginUser) {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_list_room;
    }

    @Override
    public void onListRoomSuccess(ListRoomResponse response) {
        dismissProgress();
        if (response.getRoomList() != null && response.getRoomList().size() > 0) {
            listRoom.addAll(response.getRoomList());
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
        }else {

        }
    }

    @Override
    public void onListRoomFailed(String message) {
        dismissProgress();
        showDialog(message);
    }

    @Override
    public void onListRoomError(Throwable e) {
        dismissProgress();
        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
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
