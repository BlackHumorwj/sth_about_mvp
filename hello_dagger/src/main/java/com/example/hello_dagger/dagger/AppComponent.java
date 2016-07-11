package com.example.hello_dagger.dagger;

import dagger.Component;

/**
 * @author pikachu
 * @time 2016/7/8 14:47
 * @desc
 */
@Component(modules = {AppMoodule.class})
public interface AppComponent {
    void inject(SecondActivity activity);
    /**
     * 依赖的注入者，AppComponent在编译时会自动生成 DaggerAppComponent文件，
     * 因为modules = {AppModule.class}，所以DaggerAppComponent可以访问AppModule.class内的内容，
     * 接口内有方法 void inject(MainActivity activity) ，
     * 所以该接口也可以访问MainActivity，因而可以成为MainActivity与AppModule.class之间的桥梁。
     */


}