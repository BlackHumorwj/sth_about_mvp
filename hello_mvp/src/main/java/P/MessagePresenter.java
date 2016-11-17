package P;

import C.MessageContract;
import C.MessageContract.MessageView;

/**
 * @author ryujin
 * @version $Rev$
 * @time 2016/11/17 23:01
 * @updateAuthor $Author$
 * @updateDate $Date$
 */

public class MessagePresenter implements MessageContract.Presenter {
    MessageView view;
    public MessagePresenter(MessageView view){
        this.view = view;
    }



    @Override
    public void start() {
        //调用接口获取数据，接受回调的数据 进行处理
        loadData();


    }

    private void loadData() {

    }
}
