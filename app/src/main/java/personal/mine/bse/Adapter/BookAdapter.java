package personal.mine.bse.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import personal.mine.bse.Model.Book;
import personal.mine.bse.R;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    List<Book> bookList;
    public OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    public OnItemClickCallback getOnItemClickCallback() {
        return onItemClickCallback;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_buku_horizontal, parent, false);
        return new BookAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookAdapter.ViewHolder holder, int position) {
        holder.tvJudul.setText(bookList.get(position).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(R.drawable.banner)
                .into(holder.ivCover);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.OnItemClick(bookList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvPenulis;
        ImageView ivCover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvJudulBuku);
            tvPenulis = itemView.findViewById(R.id.tvPenulis);
            ivCover = itemView.findViewById(R.id.ivCoverBuku);
        }
    }

    public interface OnItemClickCallback{
        void OnItemClick(Book data);
    }
}
