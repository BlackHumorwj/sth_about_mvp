package base;

/**
 * @author ryujin
 * @version $Rev$
 * @time 2016/11/17 22:10
 * @updateAuthor $Author$
 * @updateDate $Date$
 */

public interface BasePresenter {
    /**
     * @call View在OnCreate()方法中调用
     * @desc 具体的实现类在此方法中，获取数据
     */
    void start();
}
