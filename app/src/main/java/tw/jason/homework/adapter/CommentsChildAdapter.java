package tw.jason.homework.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.security.PublicKey;
import java.util.List;

import tw.jason.homework.R;
import tw.jason.homework.bean.AdapterCommentsBean;
import tw.jason.homework.bean.TweetsBean;
import tw.jason.homework.databinding.ItemCommentsChildBinding;

public class CommentsChildAdapter extends RecyclerView.Adapter {
    private Context mcontext;
    private List<TweetsBean.CommentsBean> mlist;

    public CommentsChildAdapter(@NonNull Context mcontext,@NonNull List<TweetsBean.CommentsBean> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentsChilHodler(DataBindingUtil.inflate(LayoutInflater.from(mcontext), R.layout.item_comments_child,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentsChilHodler)
            ((CommentsChilHodler)holder).updata();

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class CommentsChilHodler extends RecyclerView.ViewHolder {
        ItemCommentsChildBinding binding;

        public CommentsChilHodler(@NonNull ItemCommentsChildBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }


        public void updata(){
            int position = getAdapterPosition();
            SpannableStringBuilder string = new SpannableStringBuilder();
            string.append(mlist.get(position).getSender().getNick());
            string.append("：");
            string.append(mlist.get(position).getContent());
            string.setSpan(new StyleSpan(Typeface.BOLD), 0, mlist.get(position).getSender().getNick().length() + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            string.setSpan(new ForegroundColorSpan(Color.parseColor("#595994")), 0, mlist.get(position).getSender().getNick().length() + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            binding.text.setText(string);
        }
    }
}
