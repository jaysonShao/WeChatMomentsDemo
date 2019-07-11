package tw.jason.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import tw.jason.homework.R;
import tw.jason.homework.bean.TweetsBean;
import tw.jason.homework.databinding.ItemTweetsBinding;
import tw.jason.homework.utils.GlideUtils;

public class TweetsAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<TweetsBean> mlist;

    public TweetsAdapter(@NonNull Context mcontext,@NonNull List<TweetsBean> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TweetsHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),R.layout.item_tweets,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  TweetsHolder){
            ((TweetsHolder)holder).updata();
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class TweetsHolder extends RecyclerView.ViewHolder{
        ItemTweetsBinding binding;

        public TweetsHolder(@NonNull ItemTweetsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void updata(){
            int position = getAdapterPosition();
            binding.setBean(mlist.get(position));
        }
    }
}
