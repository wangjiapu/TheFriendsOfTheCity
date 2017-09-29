package fragemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiyou3g.thefriendsofthecity.R;

import java.util.ArrayList;
import java.util.List;

import beans.Book;

/**
 * Created by heshu on 2017/9/23.
 * 个人主页---发布
 */

public class ReleaseFragment extends Fragment {
    private View rootView;
    private List<Book> mBookList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReleaseAdapter mReleaseAdapter;


    class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ViewHolder>{

        private List<Book> mBookList;
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView bookName;
            TextView bookauthorName;
            ImageView bookImageId;
            public ViewHolder(View itemView) {
                super(itemView);
                bookImageId=(ImageView)itemView.findViewById(R.id.peraon_release_book_image);
                bookName = (TextView)itemView.findViewById(R.id.person_book_name);
                bookauthorName= (TextView)itemView.findViewById(R.id.person_book_authorname);
            }
        }

        public  ReleaseAdapter(List<Book> mBookList){
            this.mBookList=mBookList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_person_release_item,parent,false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Book book =mBookList.get(position);
            holder.bookName.setText(book.getBookName());
            holder.bookauthorName.setText(book.getAuthorName());
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBookAtten();
    }

    private void initBookAtten() {
        for(int i=0;i<10;i++){
            Book book = new Book("作者"+i,"主人"+i,"内容"+i);
            mBookList.add(book);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person_release,container,false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.peraon_relsde_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mReleaseAdapter = new ReleaseAdapter(mBookList);
        mRecyclerView.setAdapter(mReleaseAdapter);
    }

}
