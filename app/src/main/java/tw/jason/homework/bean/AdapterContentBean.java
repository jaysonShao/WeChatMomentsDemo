package tw.jason.homework.bean;


//type = 0  user+content
public class AdapterContentBean extends AdapterTypeBean{


    private String content;
    private TweetsBean.SenderBean sender = new TweetsBean.SenderBean();



    public AdapterContentBean(String content, TweetsBean.SenderBean sender) {
        this(content,sender,0);
    }

    public AdapterContentBean(String content, TweetsBean.SenderBean sender, int type) {
        this.content = content;
        this.sender = sender;
        this.type = type;
    }

    public void setSender(TweetsBean.SenderBean sender) {
        this.sender = sender;
    }

    public TweetsBean.SenderBean getSender() {
        return sender;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
