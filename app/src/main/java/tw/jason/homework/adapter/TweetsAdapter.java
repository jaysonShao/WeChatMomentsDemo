package tw.jason.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import tw.jason.homework.R;
import tw.jason.homework.bean.AdapterCommentsBean;
import tw.jason.homework.bean.AdapterContentBean;
import tw.jason.homework.bean.AdapterImgBean;
import tw.jason.homework.bean.AdapterTypeBean;
import tw.jason.homework.bean.TweetsBean;
import tw.jason.homework.databinding.ItemCommentsBinding;
import tw.jason.homework.databinding.ItemImgManyBinding;
import tw.jason.homework.databinding.ItemImgOneBinding;
import tw.jason.homework.databinding.ItemLineBinding;
import tw.jason.homework.databinding.ItemTimeBinding;
import tw.jason.homework.databinding.ItemTweetsBinding;
import tw.jason.homework.databinding.ItemUserContentBinding;
import tw.jason.homework.utils.GlideUtils;

public class TweetsAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<AdapterTypeBean> mlist;

    public TweetsAdapter(@NonNull Context mcontext,@NonNull List<AdapterTypeBean> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }


    @Override
    public int getItemViewType(int position) {
        return mlist.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0: // user + content
                return new UserContentHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_user_content,parent,false));
            case 1: // img one
                return new ImgOneHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_img_one,parent,false));
            case 2: //  img many
                return new ImgManyHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_img_many,parent,false));
            case 3: // time
                return new TimeHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_time,parent,false));
            case 4: // comments
                return new CommentsHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_comments,parent,false));
            case 5: // line
                return new LineHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_line,parent,false));
                default:
                    return new EmptyHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_empty,parent,false));

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  UserContentHolder){
            ((UserContentHolder)holder).updata();

        }else if (holder instanceof TimeHolder){

        }else if (holder instanceof CommentsHolder){
            ((CommentsHolder)holder).updata();

        }else if (holder instanceof ImgOneHolder){
            ((ImgOneHolder)holder).updata();

        }else if (holder instanceof ImgManyHolder){
            ((ImgManyHolder)holder).updata();

        }else if (holder instanceof LineHolder){

        }else if (holder instanceof EmptyHolder){

        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ImgManyHolder extends RecyclerView.ViewHolder {
        ItemImgManyBinding binding;
        ImgManyAdapter adapter;
        List<String> list;

        public ImgManyHolder(@NonNull ItemImgManyBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
            init();

        }

        private void init() {
            list = new ArrayList<>();
            if (adapter == null)
                adapter = new ImgManyAdapter(mcontext,list);
            binding.recycler.setAdapter(adapter);
            binding.recycler.setLayoutManager(new GridLayoutManager(mcontext,3));

        }

        public void updata(){
            int position = getAdapterPosition();
            List<TweetsBean.ImagesBean> origin_list = ((AdapterImgBean)mlist.get(position)).getImages();
            list.clear();
            for (int i = 0; i <origin_list.size(); i++) {
                list.add(origin_list.get(i).getUrl());
            }
            adapter.notifyDataSetChanged();
        }
    }

    public class ImgOneHolder extends RecyclerView.ViewHolder {
        ItemImgOneBinding binding;

        public ImgOneHolder(@NonNull ItemImgOneBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void updata(){
            int position = getAdapterPosition();
            binding.setUrl(((AdapterImgBean)mlist.get(position)).getImages().get(0).getUrl());
        }
    }

    public class LineHolder extends RecyclerView.ViewHolder {
        ItemLineBinding binding;

        public LineHolder(@NonNull ItemLineBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public class CommentsHolder extends RecyclerView.ViewHolder {
        ItemCommentsBinding binding;
        CommentsChildAdapter adapter;
        List<TweetsBean.CommentsBean> list;

        public CommentsHolder(@NonNull ItemCommentsBinding  itemView) {
            super(itemView.getRoot());
            binding = itemView;
            init();
        }

        private void init() {
            list = new ArrayList<>();
            adapter = new CommentsChildAdapter(mcontext,list);
            binding.comments.setAdapter(adapter);
            binding.comments.setLayoutManager(new GridLayoutManager(mcontext,1));
            binding.comments.setHasFixedSize(true);
        }

        public void updata(){
            int position = getAdapterPosition();
            list.clear();
            list.addAll(((AdapterCommentsBean)mlist.get(position)).getComments());
            adapter.notifyDataSetChanged();
        }
    }

    public class TimeHolder extends RecyclerView.ViewHolder {
        ItemTimeBinding binding;
        public TimeHolder(@NonNull ItemTimeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public class EmptyHolder extends RecyclerView.ViewHolder {
        public EmptyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class UserContentHolder extends RecyclerView.ViewHolder{
        ItemUserContentBinding binding;

        public UserContentHolder(@NonNull ItemUserContentBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void updata(){
            int position = getAdapterPosition();
            binding.setBean(((AdapterContentBean)mlist.get(position)));
        }
    }
}
