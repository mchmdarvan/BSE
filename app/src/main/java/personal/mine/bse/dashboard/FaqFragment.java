package personal.mine.bse.dashboard;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import personal.mine.bse.FaqAdapter;
import personal.mine.bse.R;

public class FaqFragment extends Fragment {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataFaq;
    private HashMap<String,List<String>> listHashMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_faq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = (ExpandableListView) view.findViewById(R.id.elv_faq);
        initData();
        listAdapter = new FaqAdapter(getActivity(), listDataFaq, listHashMap);
        listView.setAdapter(listAdapter);
    }

    private void initData() {
        listDataFaq = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataFaq.add("Pertanyaan 1");
        listDataFaq.add("Pertanyaan 2");
        listDataFaq.add("Pertanyaan 3");
        listDataFaq.add("Pertanyaan 4");

        List<String> pertanyaanSatu = new ArrayList<>();
        pertanyaanSatu.add("Jawaban 1");

        List<String> pertanyaanDua = new ArrayList<>();
        pertanyaanDua.add("Jawaban 2");

        List<String> pertanyaanTiga = new ArrayList<>();
        pertanyaanTiga.add("Jawaban 3");

        List<String> pertanyaanEmpat = new ArrayList<>();
        pertanyaanEmpat.add("Jawaban 4");


        listHashMap.put(listDataFaq.get(0), pertanyaanSatu);
        listHashMap.put(listDataFaq.get(1), pertanyaanDua);
        listHashMap.put(listDataFaq.get(2), pertanyaanTiga);
        listHashMap.put(listDataFaq.get(3), pertanyaanEmpat);
    }

}
