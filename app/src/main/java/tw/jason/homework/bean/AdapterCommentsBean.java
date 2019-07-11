package tw.jason.homework.bean;

import java.util.ArrayList;
import java.util.List;

//type = 4 comments
public class AdapterCommentsBean extends AdapterTypeBean{

    private List<TweetsBean.CommentsBean> comments = new ArrayList<>(0);
    private TweetsBean.SenderBean sender = new TweetsBean.SenderBean();


    public AdapterCommentsBean(List<TweetsBean.CommentsBean> comments, TweetsBean.SenderBean sender) {
        this(4,comments,sender);
    }

    public AdapterCommentsBean(int type, List<TweetsBean.CommentsBean> comments, TweetsBean.SenderBean sender) {
        super(type);
        this.comments = comments;
        this.sender = sender;
    }

    public void setComments(List<TweetsBean.CommentsBean> comments) {
        this.comments = comments;
    }

    public List<TweetsBean.CommentsBean> getComments() {
        return comments;
    }
}
