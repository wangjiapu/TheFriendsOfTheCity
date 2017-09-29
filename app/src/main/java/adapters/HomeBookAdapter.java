package adapters;

import android.content.Context;
import android.icu.text.IDNA;
import android.net.Uri;
import android.nfc.cardemulation.HostNfcFService;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import beans.Book;
import beans.BookInfo;
import beans.InfoLists;
import fragemt.HomeFragment;
import utils.GlideUtil;

/**
 * Created by 江婷婷 on 2017/9/21.
 */

public class HomeBookAdapter extends RecyclerView.Adapter<HomeBookAdapter.ViewHolder>{
    private List<BookInfo> mBooks;
    ImageView bookImage;




    Context mContext;
    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.home_book_image);
        }
    }

    public HomeBookAdapter(List<BookInfo> books) {
        mBooks = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.home_book_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        for (BookInfo book : InfoLists.SameBInfos) {
            GlideUtil.loadImag(mContext, bookImage, book.getCoverImg());
//            bookName.setText(book.getBookName());
//            bookAuthor.setText(book.getAuthor());


            Log.d("4444", "onBindViewHolder: " + book.getAuthor());
        }

    }


    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}
