# 第1章 Android 初体验
# 1.1 Android开发概述
- Android是Google开发的操作系统
- Android开发是移动应用开发的表现形式之一(Android、IOS、H5 App、Native + H5、 RN、ionic、MUI...)

# 1.2 Android开发工具
- Android Studio
- 为什么使用Android Studio?
  -  Android Studio是Google自己推出的Android集成开发工具,且Google已经停止对Eclipse的支持.

# 1.3 第一个Android应用
- Everything begin with Hello World!
- Android Studio最大的特定是使用Gradle来构建项目...
[部分目录说明]
1. `res`: 存放资源
2. `drawable`: 图片
3. `layout`: 布局文件
4. `mipmap-hdpi`: logo图片
5. `values`: 颜色、文字
6. `AndroidMainfest.xml`: 应用里面用到的所有内容,都需要在这个里面注册一下

[部分代码说明]
- `src/main/java/com.skypan.helloworld/MainActivity`内的函数`setContentView(R.layout.activity_main)`:表示,使用了`activity_main布局`
- 打开`activity_main.xml`,将标签名改为如下:
````xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android" />
</LinearLayout>
````
- `TextView android:text="Hello World"`: 显示在手机上的值为Hello World

[提升]: 寻找一些文件,更深入了解Android的项目结构

# 第2章 UI组件
# 2-1 布局管理器
- 线性布局(LinearLayout)
- 相对布局(RelativeLayout)
- 以上两种占了接近百分之99

# LinearLayout(线性布局)
[最常用属性]:
- `android:id`: 控件的id
- `android:layout_width`: 布局的宽度
- `android:layout_height`: 布局的高度
- `android:background`: 布局的背景颜色
- `android:layout_margin`: 布局的左右边距
- `android:layout_padding`: 布局的内侧边距
- `android:orientation`: 布局的方向
- `dp`: 根据屏幕自己算出大小
- `match_parent`: 匹配父元素
- `<View />`: 是所有控件的父类,如`<Button />`
- `android: gravity= "bottom"`: 控件子元素的排列方式
- `weight`: 权重,占父元素宽度(剩余)的权重.相当于`flex布局`中子元素的属性`flex:1`; 把剩余内容按照权重去分配

[小结]:
- 通过Android Studio新建的项目,入口文件是`/app/src/main/java/com.skypan.helloworld/MainActivity`
````java
class MainActivity : AppCompatActivity() {
  override fun onCreate (saveInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
````
- 其中`setContentView`使用到了`activity_main`:总体的布局样式



# 2-1-2 RelativeLayout(相对布局)
[最常见属性]:
- `android:layout_toLeftOf`: 在谁左边
- `android:layout_toRightOf`: 在谁右边
- `android:layout_alignBottom`: 跟谁底部对齐
- `android:layout_alignParentBottom`: 跟父元素底部对齐
- `android:layout_below`: 在谁的下面

[栗子]:
- 靠父元素右下角对齐
````java
<RelativeLayout>
  <View
    android:id="@+id/view_1"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:background="#000000"
    android:layout_alignParentBottom="true"
    android:layout_alignParentRight="true"
  />
</RelativeLayout>
````

- View2相对于View1的右边对齐
````java
<RelativeLayout>
  <View
    android:id="@+id/view_1"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:background="#000000"
  />
  <View
    android:id="@+id/view_2"
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:background="#FF0033"
    android:layout_toRightOf="@id/view_1"
  />
</RelativeLayout>
````

[提升]: 在百度上面找一些Android设计稿,亲自实现

# 2-2 TextView
- 文字大小、颜色
- 显示不下使用...
- 文字 + icon
- 中划线、下划线

[栗子]:
- 1.写一个宽度随父元素,高度等于文本高度的按钮控件
````java
<LinearLayout
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:orientation="vertical">
  <Button
    android:id="@+id/btn_textview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="TextView"/>
</LinearLayout>
````



