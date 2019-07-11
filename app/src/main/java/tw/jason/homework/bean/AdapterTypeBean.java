package tw.jason.homework.bean;

public class AdapterTypeBean {


    /**
     * 0   user + content
     * 1   img one
     * 2   img many
     * 3   time
     * 4   comments
     * 5   line
     */
    int type = 0;


    public AdapterTypeBean() {
    }

    public AdapterTypeBean(int type) {
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
