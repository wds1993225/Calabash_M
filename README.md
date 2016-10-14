# Calabash

[![Platform](https://img.shields.io/badge/platform-Android-blue.svg)](https://github.com/wds1993225)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)]()

![icon](https://github.com/marktony/ZhiHuDaily/blob/master/screenshots/icon.png)

calabash 是一款娱乐性的app，包括阅读资讯，星座八卦等内容。

----

##界面截图

部分界面仿照了豆瓣一刻，one一个，中华万年历的部分界面。
![图片1](http://huaban.com/pins/851437160/)
![图片1](http://huaban.com/pins/851437160/)
![图片1](http://huaban.com/pins/851437160/)

----

## API使用声明

>[@izzyleung](https://github.com/izzyleung/ZhihuDailyPurify)
 [@marktony](https://github.com/marktony/ZhiHuDaily)
  &nbsp; 感谢以上两位优秀github贡献者
 
* [聚合数据](https://www.juhe.cn/)
* [知乎日报api分析](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)
* [豆瓣一刻](http://www.doubanyike.com/#/)
* [one一个](http://wufazhuce.com/)
* [中华万年历](http://www.zhwnl.cn/)

以上部分 **API** 和**数据**由本人采取非正常手段获取。获取与共享之行为或有侵犯您权益的嫌疑。若被告知需停止共享与使用，本人会及时删除此页面与整个项目，此致。

----

## 下载

* [fim](http://fir.im/6mh3)
* [calabash.apk](http://fir.im/6mh3)

----

## 开源项目
名称 | 介绍
--------- | --------
[Picasso](https://github.com/square/picasso) | 使用Picasso进行列表中图片的加载.
[Retrofit2](https://github.com/square/retrofit) | 进行网络请求
[Fresco](https://github.com/facebook/fresco) | 在加载**One一个** 的图片时，使用Picasso会报OOM，使用Fresco问题消失
[Xrecyclerview](https://github.com/jianghejie/XRecyclerView) | 下拉刷新，只需替换Recycleview，不需要重写Adapter
[Sweetalert](https://github.com/pedant/sweet-alert-dialog) | 为了使应用效果更美观，使用此替换原始的Dialog
[Photodraweeview](https://github.com/pedant/shttps://github.com/ongakuer/PhotoDraweeView) | 这个库被[PhotoView](https://github.com/chrisbanes/PhotoView)推荐配合Fresco使用，来查看图片
[WaveView](https://github.com/john990/WaveView) | 实现点击动画
[Circularprogressbar](https://github.com/lopspower/CircularProgressBar) | 实现背景动画
[Shimmer](https://github.com/RomainPiel/Shimmer-android) | 用来实现部分效果，在activity结束后需要调用cancel方法，否则会内存泄漏
[Ticker](https://github.com/robinhood/ticker) | 用来实现倒计时功能
[Leakcanary](https://github.com/square/leakcanary) | 用其检测到了Bitmap没有及时回收和Shimmer的动画没有结束而引发的内存泄漏
[Blur](https://github.com/PomepuyN/BlurEffectForAndroidDesign) | 背景的毛玻璃效果的实现，注意需要将原始Bitmap缩小，否则会报OOM
[SlackLoadingView ](https://github.com/JeasonWong/SlackLoadingView) | 一个非常好的动画效果，我将其封装成了Dialog用在了项目中，感谢作者@JeasonWong
[SwipeBackLayout ](https://github.com/ikew0ng/SwipeBackLayout)|滑动关闭Activity，我将Dialog封装到了其中作为BaseActivity
[BugHD](http://bughd.com/)|App 崩溃信息收集


----

##Todo List

* 使用MVP模式重新架构项目
* 学习使用RxJava
* 提升应用的稳定性
* 加入其他功能

----


##注意
* 不要点击里面的广告，也不要点击关闭广告。在点击关闭广告按钮后，事件会穿透按钮，到达广告的容器上，使得点击到广告上；第二次点击关闭按钮才会响应。（@。@）(同学，你这个想法很危险啊~)
* 你问我项目为什么这么大，我只能说我往里面放了一个7M的字体。。。


----

##再次感谢

[@izzyleung](https://github.com/izzyleung/ZhihuDailyPurify)
[@marktony](https://github.com/marktony/ZhiHuDaily)
谢谢你们的项目，使我借鉴得以学习
感谢以上优秀的开源项目，使我得以快速完成此项目
[@tomoyodxy](https://github.com/tomoyodxy)
感谢你帮我重新封装网络层以及其他技术指导


----

## 开源许可

    Copyright 2016 Wuliu <wds1993225@126.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
