package com.vnpt.hotel.manager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import com.vnpt.hotel.manager.R;
import com.vnpt.hotel.manager.app.BaseFragment;
import com.vnpt.hotel.manager.domain.model.motel.EmployeModel;
import com.vnpt.hotel.manager.ui.adapter.HotelAdapter;
import java.util.ArrayList;
import java.util.List;

public class ManagerEmployFragment extends BaseFragment {


  @BindView(R.id.recycler_view_employe) RecyclerView recyclerView;
  private List<Object> listEmploye;
  HotelAdapter adapter;

  private void initializeData() {
    listEmploye = new ArrayList<>();
    listEmploye.add(new EmployeModel("Emma Wilson", "23 years old"));
    listEmploye.add(new EmployeModel("Lavery Maiss", "25 years old"));
    listEmploye.add(new EmployeModel("Lillie Watts", "35 years old"));
  }
  public ManagerEmployFragment() {
    // Required empty public constructor
  }


  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    initializeData();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initRecyclerView();
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_manager_employ;
  }
  private void initRecyclerView() {
    LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(linearLayout);
    adapter = new HotelAdapter(listEmploye,getActivity());
    recyclerView.setAdapter(adapter);
  }
  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_add_item,menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_add:
        listEmploye.add(new EmployeModel("Emma Wilson", "23 years old"));
        adapter.notifyItemInserted(listEmploye.size() - 1);
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
