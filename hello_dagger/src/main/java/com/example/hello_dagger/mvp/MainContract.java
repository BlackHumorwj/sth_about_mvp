package com.example.hello_dagger.mvp;

import java.util.List;

/**
 * @author pikachu
 * @time 2016/7/7 14:53
 * @desc
 */
public interface MainContract {

     interface Presenter{
        void loadData();
        void loadData2();

     }

     interface View{
        void  updateUI(String data);
         void updateUI2(List list);
    }

}
