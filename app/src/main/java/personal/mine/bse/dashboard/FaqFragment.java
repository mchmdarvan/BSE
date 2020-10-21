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

        listDataFaq.add("1. Bagaimana mencari konten pembelajaran yang diperlukan?");
        listDataFaq.add("2. Bagaimana mengunduh konten Buku Sekolah Elektronik?");
        listDataFaq.add("3. Bagaimana mencari lokasi file yang sudah diunduh?");
        listDataFaq.add("4. Bagaimana ekstensi hasil unduhan konten Buku Sekolah Elektronik?");
        listDataFaq.add("5. Bagaimana menjalankan konten sumber belajar yang sudah diunduh");
        listDataFaq.add("6. Bagaimana cara melakukan LOGIN?");
        listDataFaq.add("7. Bagaimana cara mengubah profil?");


        List<String> pertanyaan1 = new ArrayList<>();
        pertanyaan1.add("Pengguna bisa mencari buku yang ingin dibaca dengan memilih jenjang pendidikan yang tersedia di Beranda");
        List<String> pertanyaan2 = new ArrayList<>();
        pertanyaan2.add("Pilih buku yang diinginkan, lalu di halaman detail buku tersedia tombol download");
        List<String> pertanyaan3 = new ArrayList<>();
        pertanyaan3.add("-");
        List<String> pertanyaan4 = new ArrayList<>();
        pertanyaan4.add("File hasil unduhan akan berbentuk PDF");
        List<String> pertanyaan5 = new ArrayList<>();
        pertanyaan5.add("File hasil unduhan yang berupa PDF bisa dibuka melalui aplikasi pembaca PDF");
        List<String> pertanyaan6 = new ArrayList<>();
        pertanyaan6.add("Login cukup dengan menggunakan Facebook saja");
        List<String> pertanyaan7 = new ArrayList<>();
        pertanyaan7.add("Profil pada aplikasi BSE tidak bisa diubah, karena menggunakan fitur login melalui facebook");




        listHashMap.put(listDataFaq.get(0), pertanyaan1);
        listHashMap.put(listDataFaq.get(1), pertanyaan2);
        listHashMap.put(listDataFaq.get(2), pertanyaan3);
        listHashMap.put(listDataFaq.get(3),pertanyaan4 );
        listHashMap.put(listDataFaq.get(4), pertanyaan5);
        listHashMap.put(listDataFaq.get(5), pertanyaan6);
        listHashMap.put(listDataFaq.get(6), pertanyaan7);

    }

}
