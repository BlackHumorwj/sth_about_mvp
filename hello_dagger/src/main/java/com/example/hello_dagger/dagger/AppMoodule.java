package com.example.hello_dagger.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author pikachu
 * @time 2016/7/8 14:42
 * @desc
 */
@Module //该类负责提供数据内容
public class AppMoodule {
    @Provides//被注解的方法提供返回参数类型的数据
    String provideHello() {
        return "hello dagger";
    }

    @Provides
    @Named("hi")//如果同一个module中多个provides返回参数类型一致，那么必须加@Named("xxx")以进行区分
    String provideHi() {
        return "hi dagger";
    }

}
