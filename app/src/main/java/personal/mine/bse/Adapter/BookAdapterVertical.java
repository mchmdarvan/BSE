package personal.mine.bse.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import personal.mine.bse.Model.Book;
import personal.mine.bse.R;

public class BookAdapterVertical extends RecyclerView.Adapter<BookAdapterVertical.ViewHolder> {
    List<Book> bookList;
    public OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    public BookAdapterVertical(List<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapterVertical.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_buku_vertical, parent, false);
        return new BookAdapterVertical.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookAdapterVertical.ViewHolder holder, int position) {
        holder.tvJudul.setText(bookList.get(position).getTitle());
        holder.tvDeskripsi.setText(bookList.get(position).getDescription());
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
        return bookList.size();
    }

    public OnItemClickCallback getOnItemClickCallback() {
        return onItemClickCallback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDeskripsi;
        ImageView ivCover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvJudul = itemView.findViewById(R.id.tvTitleBook);
            tvDeskripsi = itemView.findViewById(R.id.tvBookDescription);
            ivCover = itemView.findViewById(R.id.ivBookCover);
        }
    }

    public interface OnItemClickCallback{
        void OnItemClick(Book data);
    }
}
