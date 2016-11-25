# 培训范-Android版

## 项目架构
###JAVA编译版本
	java编译版本基于java8，使用java8中的lambda表达式。
	参考链接:
		http://www.liuhaihua.cn/archives/83170.html
		http://blog.csdn.net/telencool/article/details/51690505
    
###编程框架RxJava
	编程框架选用RxJava(响应式编程框架)，采用观察者模式。取缔之前的AsyncTask／Handler进行异步控制
	参考链接：
		http://gank.io/post/560e15be2dca930e00da1083

	扩展使用RxPermission,动态获取应用权限
	扩展使用RxBinding 响应控件的异步事件
 
###网络框架
	网络框架弃用之前的volly选用基于okHttp3的retrofit2，实现多线程可配置化网络请求。
	扩展使用GsonConvert进行序列化和反序列化Json
  
###图片加载框架
	图片加载框架弃用之前的ImageLoader，选择支持加载进度、webp格式、动图、渐进图加载功能的Fresco框架
	加油！




