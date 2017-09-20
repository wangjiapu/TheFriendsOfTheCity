package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.List;

import beans.Book;

/**
 * Created by 江婷婷 on 2017/9/21.
 */

public class BookItemAdapter extends RecyclerView.Adapter<BookItemAdapter.ViewHolder>{
    private List<Book> mBooks;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImage;
        TextView bookName;
        TextView bookAuthor;
        TextView dateMarks;
        TextView date;
        public ViewHolder(View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.book_image_view);
            bookName = (TextView) itemView.findViewById(R.id.book_text_view_name);
            bookAuthor = (TextView) itemView.findViewById(R.id.book_text_view_author);
            dateMarks = (TextView) itemView.findViewById(R.id.book_text_view_marks);
            date = (TextView) itemView.findViewById(R.id.book_text_view_date);
        }
    }

    public BookItemAdapter(List<Book> books) {
        mBooks = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mBooks.get(position);
        holder.bookImage.setImageResource(R.mipmap.zsz);
        holder.bookName.setText(book.getBookName());
        holder.bookAuthor.setText(book.getAuthorName());
//        holder.date.setText(book.getCalendar().toString());
    }


    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}
