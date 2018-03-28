package com.vnpt.hotel.manager.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.domain.model.staff.StaffModel;
import com.vnpt.hotel.manager.ui.adapter.StaffAdapter;

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
 * Use the {@link ListStaffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListStaffFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.recycler_view_staff)
    RecyclerView recyclerView;
    private List<Object> listStaff;
    private StaffAdapter adapter;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListStaffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListStaffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListStaffFragment newInstance(String param1, String param2) {
        ListStaffFragment fragment = new ListStaffFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_staff, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeData();
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayout);
        adapter = new StaffAdapter(listStaff, getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void initializeData() {
        listStaff = new ArrayList<>();
        listStaff.add(new StaffModel.Staff(1, "Nguyễn Hoàng Việt", "Nhân viên lễ tân", "0918 666 096"));
        listStaff.add(new StaffModel.Staff(2, "Nguyễn Tất Tiến", "Nhân viên lễ tân", "0968 555 678"));
        listStaff.add(new StaffModel.Staff(3, "Nguyễn Bá Lợi", "Nhân viên dọn dẹp", "01234 567 890"));
        listStaff.add(new StaffModel.Staff(4, "Nguyễn Đăng Đông", "Nhân viên hậu cần", "0918 666 096"));
        listStaff.add(new StaffModel.Staff(5, "Đặng Nhật Minh", "Nhân viên dọn dẹp", "0918 666 096"));
        listStaff.add(new StaffModel.Staff(6, "Lê Khánh Linh", "Nhân viên xếp xe", "0918 666 096"));
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
