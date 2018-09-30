## 项目介绍：

####    快递小哥为我参加浙江省新苗项目接下的新苗项目，目的是降低快递运输所花费的时间成本，提高配送效率，运算主要任务是后端，后端采用VRP问题经典的算法——蚁群算法。对多个快递员和多个快递进行合理分配，然后推送到APP端，通知用户，进行快递配送。

### 快递小哥是一款智能路径规划的路线制定APP，可以方便快递公司提高配送效率，让快递员可以在相同的时间内配送更多的快递。快递小哥配有天气，导航，地图，快递查看，快递配送，消息推送等诸多功能。
#### 【快递任务推送】服务器定时推送快递配送任务，帮助快递员节省时间
#### 【天气出行】打开APP就可以看到，简单易用
#### 【路径导航】点击详情就可以看到配送点位置，清晰明了
#### 【快递查询】一键查看快递信息，即时跟踪
#### 【消息推送】即时告诉你动态变化
#### 【附近站点】附近哪里可以寄件，只需要一个电话，足够了

####    快递小哥我采用的是MVP架构模式，逻辑和界面分离，降低耦合度，避免activity过于臃肿，减小activity的负荷，来提高界面的流畅度。快递小哥主要是运用高德地图API进行路径展示，将快递配送点在地图上进行标识，让用户一目了然，快递小哥界面采用MD风格，让用户体验更佳，界面清新，操作简单
  
  
###  下面我简单介绍一下MVP架构模式:

#### 对于一个应用而言我们需要对它抽象出各个层面，而在MVP架构中它将UI界面和数据进行隔离，所以我们的应用也就分为三个层次。

####  View: 对于View层也是视图层，在View层中只负责对数据的展示，提供友好的界面与用户进行交互。在Android开发中通常将Activity或者Fragment作为View层。
#### Model: 对于Model层也是数据层。它区别于MVC架构中的Model，在这里不仅仅只是数据模型。在MVP架构中Model它负责对数据的存取操作，例如对数据库的读写，网络的数据的请求等。
#### Presenter:对于Presenter层他是连接View层与Model层的桥梁并对业务逻辑进行处理。在MVP架构中Model与View无法直接进行交互。所以在Presenter层它会


###### 从Model层获得所需要的数据，进行一些适当的处理后交由View层进行显示。这样通过Presenter将View与Model进行隔离，使得View和Model之间不存在耦合，同时也将业务逻辑从View中抽离。

#### MVP架构模式图

![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/09/30/b00eb1e5408a5e6880361b4b977727d4.png)

  
####    开发采用诸多流行框架，用响应式开发，Rxjava+Retrofit一起用，然后不够就用Volley一起。VOlley不够就用OkHttp。

##### 快递小哥后台我采用PHP和JAVA Spring开发，php进行数据传输，java进行算法运算，后台链接：


#### JAVA后端：[JAVA后端](https://github.com/xyj155/Currierbrother "悬停显示")  

#### 后台管理系统：[后台管理系统](https://github.com/xyj155/Administrator "悬停显示")  

#### PHP后端：[PHP后端](https://github.com/xyj155/CurrierBrotherPHP "悬停显示")  

# 快递小哥快递员版

#### 1.APP采用MVP架构的设计模式

#### 2.使用Rxjava+Retrifit框架进行网络请求和链式代码的设计

#### 3.采用高德API进行基础定位，实现地图展示

#### 4.调用MOB天气接口实现当地天气查询

#### 5.和后台进行对接，实现路劲规划算法结果在APP端的实现

#### 6.查询附近快递点，实现路径导航

#### 7.一键查询数据库里的订单数据

#### 8.接入极光IM，实现即时通讯

#### 9.接入图灵机器人，进行在线客户

#### 10.SharePreferenceUtil 工具类的封装

#### 11.自定义Toast封装

#### 12.自定义卡券View疯转

#### 13.高德路线规划算法实现

#### 14.自定义消息红点BottomBar实现

#### 15.SmartRefreshLayout的使用

#### 16.Zxing二维码扫描

#### 17.DrawerLayout抽屉布局的实现，并且伴有Map+RecycleView+DrawerLayout的代码和截图


## 项目截图
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/5c033a2f404c1e568056767f38018320.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/84ab34cc40e43f5a803849f614aa0e11.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/0724654f40c1f8dd8094cb828d548ebf.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/b40c08804068e7b68051aa8f61761e09.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/48b3ed36400a1660803eea75abe0fba2.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/75314f5d403ef1b480d5babe758526c6.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/139aa80e407b91b180b5b1b9517c0b8a.png)
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/f2c8109140524f8380cf44f58bff3c8c.png)


### 快递小哥架构图

![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/09/30/6f586dac40a74f1080a85e0eb83af56f.png)

### 快递小哥流程图
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/09/30/89a1dd62407e7db180f1b89ae1f8d53d.png)


### 本人博客：
#### CSDN：[CSDN博客](https://blog.csdn.net/qq_33163983 "悬停显示")  
#### 简书：[简书博客](https://www.jianshu.com/u/a09ef2f2fe67 "悬停显示")  


### 感谢开源框架
#### 自定义刷新布局： SmartRefreshLayout
#### 高德地图： Amap
#### Retrofit： 
               com.squareup.retrofit2:converter-gson:2.3.0
               com.squareup.retrofit2:adapter-rxjava:2.3.0
#### Rxjava：
               io.reactivex:rxjava:1.3.0
               io.reactivex:rxandroid:1.1.0
#### 极光：cn.jiguang.sdk:jpush:3.1.5
#### Volley ：com.mcxiaoke.volley:library:1.0.19
#### BaseRecyclerViewAdapterHelper：com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.4


### 个人联系方式：

#### 微信：
![image](http://bmob-cdn-20920.b0.upaiyun.com/2018/08/06/3248a1cc4075a1d7807afabf9210dca7.png)

#### QQ： 1789780841



