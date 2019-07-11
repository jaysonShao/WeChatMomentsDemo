package tw.jason.homework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tw.jason.homework.R;
import tw.jason.homework.databinding.ItemManyImgBinding;

public class ImgManyAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<String> mlist;

    public ImgManyAdapter(@NonNull Context mcontext, @NonNull List<String> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImgManyHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext), R.layout.item_many_img,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImgManyHolder) {
            ((ImgManyHolder) holder).updata();
        }

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class ImgManyHolder extends RecyclerView.ViewHolder {
        ItemManyImgBinding binding;

        public ImgManyHolder(@NonNull ItemManyImgBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void updata(){
            int position = getAdapterPosition();
            binding.setUrl(mlist.get(position));
        }
    }
}
