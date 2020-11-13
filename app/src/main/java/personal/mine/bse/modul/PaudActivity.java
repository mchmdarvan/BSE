package personal.mine.bse.modul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import personal.mine.bse.Adapter.BookAdapterVertical;
import personal.mine.bse.Model.Book;
import personal.mine.bse.R;

public class PaudActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    RecyclerView rvPaud;
    List<Book> bookList;
    BookAdapterVertical bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paud);

        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        Log.e("OnResponse", "Response adad : " + sharedPreferences.getString("token", ""));
        rvPaud = findViewById(R.id.rvPaud);
        bookList = new ArrayList<>();
    }

    public void getContents(){

    }
}