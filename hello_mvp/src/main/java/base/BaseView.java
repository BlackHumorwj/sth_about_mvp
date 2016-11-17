package base;

/**
 * @author ryujin
 * @version $Rev$
 * @time 2016/11/17 22:05
 * @updateAuthor $Author$
 * @updateDate $Date$
 */

public interface BaseView<T> {
    /**
     * @desc 作用 View中拿到Presenter对象
     * @call 当Fragment 作为View是 在Presenter的构造方法中获取
     * @param presenter
     */
     void setPresenter(T presenter);
}
