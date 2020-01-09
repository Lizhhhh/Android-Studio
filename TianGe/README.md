# 第1章 Android 初体验
## 1.1 Android开发概述
- Android是Google开发的操作系统
- Android开发是移动应用开发的表现形式之一(Android、IOS、H5 App、Native + H5、 RN、ionic、MUI...)

## 1.2 Android开发工具
- Android Studio
- 为什么使用Android Studio?
  -  Android Studio是Google自己推出的Android集成开发工具,且Google已经停止对Eclipse的支持.

## 1.3 第一个Android应用
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
## 2.1 布局管理器
- 线性布局(LinearLayout)
- 相对布局(RelativeLayout)
- 以上两种占了接近百分之99

### LinearLayout(线性布局)
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



### 2.1.2 RelativeLayout(相对布局)
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

## 2.2 TextView
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

- 2.点击Button类实现跳转
````java
// MainActivity.java
import andoridx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  // 声明 button
  private Button mBtnTextView;

  @override
  pretected void onCreate(Bundle saveInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBtnTextView = (Button) findViewById(R.id.btn_textview);
    mBtnTextView.setOutClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // 跳转到TextView演示界面
        Intent intent = new Intent(packageContext: MainActivity.this, TextViewActivity.class);
        startActivity(intent);
      }
    })
  }
}
````
- 3.能在MainActivity中使用TextViewActivity的原因
- `AndroidMainfest.xml`配置如下:
````xml
<application
  android:allowBckup="true"
  ...
  <activity android:name=".MainActivity">
    <intent-filter>
      <action android:name="android.intent.action.MAIN" />
      <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
  </activity>
>
````

- 4.Java操作的TextView: `android:id="@+id/tv_4"`
````java
// TextViewActivity.java
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;
public class TextViewActivity extends AppCompatActivity {

  private TextView mTv4;

  @override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_text_view);
    // 获取 id.tv_4
    mTv4 = (TextView) findViewById(R.id.tv_4);
    // 给 id.tv_4划线
    mTv4.getPaint().setFlags(Paint.STRING_THRU_TEXT_FLAG);
    // 去锯齿
    mTv4.getPaint().setAntiAlias(true)
  }
}
````

## 2.3 Button
- 文字大小、颜色
- 自定义背景形状
- 自定义按压效果
- 点击事件

### Button样式(简单)
- activity_main.xml
````xml
<Button
  android:id="@+id/btn_button"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:text="Button"
  android:layout_marginTop="10dp" />
````

### 使用java操作控件
````java
// MainActivity.java

// 声明控件
private Button mBtnButton
// 找到按钮 btn_button
mBtnButton = (Button) findViewById(R.id.btn_button);
// 给 mBtnButton 添加点击事件
mBtnButton.setOnClickListener(new View.OnClickListener(){
  // 点击按钮后执行的操作
})
````

### 实现跳转
````java
import andoirdx.appcompat.app.AppCompatActivity;
import andoird.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
  // 声明button
  private Button mBtnButton;

  @override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 找到按钮
    mBtnButton = (Button) findViewById(R.id.btn_button);
    mBtnButton.setOnClickListener(new View.OnClickListener() {
      @ovrride
      public void onClick(View v) {
        // 跳转到 Button 演示界面
        Intent intent = new Intent( packageContext: MainActivity.this, ButtonActivity.class);
        startActivity(intent);
      }
    })
  }
}
````

### 做一个矩形的按钮
- 1.在res/drawable -> New -> Drawable resource file -> btn_2.xml
- 代码如下:
````xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
  android:shape="rectangle">
  <solid
    android:color="#ffffff" />

  <corners
    android:radius="15dp" />
````

### 按压效果
- res/drawable/bg_btn4.xml
- 样式
````xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
  <item android:state_pressed="true">
    <shape>
      <solid android:color="#cc7c00" />
      <corners android:radius="15dp" />
    </shape>
  </item>
  <item android:state_pressed="false">
    <shape>
      <solid android:color="#ff9900">
      <corners android:radius="15dp" />
    </shape>
  </item>
</selector>
````

- 引用如下
- `activity_button.xml`
````xml
<Button
  android:id="@+id/btn_4"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:layout_below="@id/btn_3"
  android:layout_marginTop="5dp"
  android:text="Button 4"
  android:textSize="20sp"
  android:textColor="#0066ff"
  android:background="@drawable/bg_btn4" />
````

### 点击按钮,响应Toast
- 假设有按钮如下: `activity_button.xml`
````xml
<Button
 ...
 android:onClick="showToast" />
````
- 对应Java文件 `ButtonActivity.java` 中加入如下:
````java
public void showToast(View view) {
  Toast.makeText(context: this, text:'点击', Toast.LENGTH_SHORT).show();
}
````

[报错]:
- `Could not find method showToast(View) in a parent or ancestor Context for android`: 写按钮触发事件的时候,没有传入参数 `View view`,将`public void showToast()`改为`public void showToast(View view)`

### 点击按钮,响应Toast[方法2]
- [核心方法] :`Button.setOnClickListener()`
- 假设有按钮如下: `activity_button.xml`
````xml
<Button
  android:id="@+id/btn_3"
  ....
/>
````
- 对于的Java文件`ButtonActivity.java`中加入如下:
````java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

public class ButtonActivity extends AppCompatActivity {
  private Button mBtn3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBtn3 = (Button) findViewById(R.id.btn_3);
    mBtn3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(ButtonActivity.this, 'btn3被点击', Toast.LENGTH_SHORT).show();
      }
    })
  }
}
````

### 给TextView控件添加点击事件
- TextView控件`activity_button.xml`,如下:
````xml
<TextView
  android:id="@+id/tv_1"
  ...
/>
````
- 在Java中获取控件,并设置点击事件`ButtonActivity.java`
````java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class ButtonActivity extends AppCompatActivity {
  private TextView mTv1;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // tv_1的点击事件
    mTv1 = (TextView) findViewById(R.id.tv_1);
    mTv1.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        Toast.makeText(ButtonActivity.this, "TextView被点击", Toast.LENGTH_SHORT).show();
      }
    })
  }
}
````

## 2.4 EditText(输入控件)
- 常用属性
- 监听事件
- 制作登录界面

### 2.4.1 加一个跳转页面的按钮
- 按钮样式: `activity_main.xml`
````xml
<Button
  android:id="@+id/btn_edittext"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:text="EditText"
  android:textAllCaps="false"/>
````
- 按钮的点击事件: `MainActivity.java`
````java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.context.Intent;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  // 声明button
  private Button mBtnEditText;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // 找到 btn_edittext
    mBtnEditText = (Button) findViewById(R.id.btn_edittext);
    mBtnEditText.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // 跳转到 EditText 界面
        // 这里先假设有 EditTextActivity
        Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
        startActivity(intent);
      }
    });
  }
}
````
- 按钮的活动页面: `activity_edit_text.xml`
````xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android/apk/res/android"
  xmlns:app="http://schemas.android.com/apk/res-auto"
  xmlns:tools="http://schemas.android.com/tools"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:padding="15dp">

  <EditText
    android:id="@+id/et_1"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:textSize="16sp"
    android:textColor="#ffad33"
    android:hint="用户名"/>

  <EditText
    android:id="@+id/et_2"
    android:layout_width="match_parent"
    android:layout_height="50dp"
    android:layout_below="@id/et_1"
    android:hint="密码"
    android:inputType="textPassword"
    android:layout_marginTop="5dp"
  />
</RelativeLayout>
````

- 给用户名输入框`activity_edit_text.xml`加样式
- 输入控件如下
````xml
<EditText
  android:id="@+id/et_1"
  ...
  android:background="@drawable/bg_username"
/>
````
- 在res/drawable -> New -> Drawable resource file -> bg_username.xml(shape)
- 写入形状如下:
````xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
  android:shape="rectangle">
  <stroke
    android:width="1dp"
    andorid:color="#999999" />

  <corners
    android:radius="5dp"/>
````


## 2.5 RadioButton
- 常用属性
- 自定义样式
- 监听事件

### 2.5.1 新建按钮,并跳转到相应的活动页面
1.在`com.skypan.textview`下新建一个`RadioButtonActivity`活动
2.在主样式,新增一个 `RadioButton`按钮
````xml
<Button
  android:id="@+id/btn_radiobutton"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:text="Radio Button"
  android:textAllCaps="false"/>
````
3.在主活动中,添加按钮的跳转事件
````java
public class MainActivity extends AppCompatActivity {
  // 声明按钮
  private Button mBtnRadioButton;

  @override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mBtnRadioButton = (Button) findViewById(R.id.btn_radiobutton);
    mBtnRadioButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        // 跳转到 RadioButton 界面
        Intent intent = new Intent(MainActivity.this, RadioButtonActivity.class);
        startActivity(intent);
      }
    })
  }
}
````

### 2.5.2 封装View.OnClickListener
[说明] : 在主活动中,多次使用到这个方法,其中仅仅部分改变.因此将该方法提取出来.
1.提取出方法
````java
private class OnClick implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.btn_textview:
                // 跳转到 TextView 演示界面
                intent = new Intent(MainActivity.this, TextViewActivity.class);
                break;
            case R.id.btn_button:
                intent = new Intent(MainActivity.this, ButtonActivity.class);
                break;
            case R.id.btn_edittext:
                intent = new Intent(MainActivity.this, EditTextActivity.class);
                break;
            case R.id.btn_radiobutton:
                intent = new Intent(MainActivity.this, RadioButtonActivity.class);
                break;
        }
        startActivity(intent);
    }
}
````
2.设置启动函数
````java
private void setListeners(){
  Onclick onclick = new Onclick();
  mBtnTextView.setOnClickListener(onClick);
  mBtnButton.setOnClickListener(onClick);
  mBtnEditText.setOnClickListener(onClick);
  mBtnRadioButton.setOnClickListener(onClick);
}
````
### 2.5.3 单选按钮的监听事件:
- 按钮组的布局如下:
````xml
<RadioGroup
  android:id="@+id/rg_1"
  ...
/>
  <RadioButton
    android:id="@+id/rb_1"
    android:text="男"
  />
  <RadioButton
    android:id="@+id/rb_2"
    android:id="女"
  />
</RadioGroup>
````
- 监听函数如下:
````java
public class RadioButtonActivity extends AppCompatActivity {
  private RadioGroup mRg1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mRg1 = (RadioGroup) findViewById(R.id.rg_1);
    mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        Toast.makeText(RadioButtonActivity.this, radioButton.getText(),Toast.LENGTH.SHORT).show();
      }
    })
  }
}
````
[说明] :
1.一个活动对应的是一个类
2.所有活动都继承一个基类`AppCompatActiviry`
3.`protected`: 自己和子类都能使用.
4.`private`: 除了自己之外,其他类都无法使用


### 2.5.x 参数说明:
1.`android:checked`: 默认选中
2.`android:state_checked="true"`: 点击时显示的样式
3.`<solid android:color="#cc7a00">`: 一个矩形的颜色填充块
4.`<stroke android:width="1dp">`: 1个单位宽度的矩形线
5.`corners android:radius="15dp"`: 矩形的边角曲率15个单位

[报错]:
1.`Variable 'intent' might not have been initialized`: 变量`intent`没有初始化

## 2.6 复选框 CheckBox
- 常用属性
- 自定义样式
- 监听事件

### 2.6.1 线性垂直布局 + 复选框基本语法
````xml
<LinearLayout
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:orientation="vertical"
  android:layout_below="@id/cb_6"
  android:layout_marginTop="20dp">

  <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:textSize="20sp"
    android:text="你的兴趣:"
    android:textColor="#000"/>

  <CheckBox
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/cb_7"
    android:text="编程"
    android:textSize="20sp"
    android:layout_marginTop="5sp" />

  <CheckBox
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:id="@+id/cb_8"
    android:text="编程"
    android:textSize="20sp"
    android:layout_marginTop="5sp"/>

</LinearLayout>
````

### 2.6.2 制作带图标的复选框
1.准备好2个图标 `icon1` 和 `icon2`
2.将2个图标放入`res/drawable-xxhdpi`
3.准备选择器: `bg_check.xml`
````xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
  <item
    android:state_checked="false"
    android:drawable="@drawable/icon_checkbox_false" />
  <item
    android:state_checked="true"
    android:drawable="@drawable/icon_checkbox_true" />
</selector>
````
[说明] :
- (1)`android:state_checked='false'`: 未选中
- (2)`android:drawble='@drawable/icon_checkbox_false'`: 使用`icon_checkbox_false`图标

4.在CheckBox中使用 Selector: `bg_check.xml`
````xml
<CheckBox
  ...
  android:button="@drawable/bg_check"
>
````

### 2.6.3 给复选框添加事件
1.假设复选框的id为 `cb_1` 和 `cb_2`
2.编写复选框的活动如下: `CheckBoxActivity.java`
````java
public class CheckBoxActivity extends AppCompatActivity {
  // 声明控件
  private  CheckBox mCb1, mCb2;
  @override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    // 获取控件的视图
    mCb1 = (CheckBox) findViewById(R.id.cb_1);
    mCb2 = (CheckBox) findViewById(R.id.cb_2);

    mCb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
      @override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(CheckBoxActivity.this, isChecked? '1选中': '1取消', Toast.LENGTH_SHORT).show();
      }
    })
    mCb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
      @override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(CheckBoxActivity.this, isChecked? '2选中': '2取消', Toast.LENGTH_SHORT).show();
      }
    })
  }
}
````

## 2.7 ImageView
- Button的其他衍生控件: ToggleButton、Switch(略)
- 常用属性
- 加载网络图片

### 2.7.1 最基本的ImageView语法
- activity_image_view.xml
````xml
<ImageView
  android:layout_width="300dp"
  android:layout_height="200dp"
  android:background="#ff9900"
  android:src="@drawable/bg_icon_man"
  android:scaleType="fixXY"/>
````
[说明] :
1.`android:scaleType="fixXY"`: 撑满控件,宽高比可能发生变化
2.`android:scaleType="fitCenter"`: 保持宽高比缩放,直到能完全显示
3.`android:scaleType="centerCrop"`: 保持宽高比缩放,直至完全覆盖控件,裁剪显示


### 2.7.2 使用ImageView加载一张网络图片
1.写好控件: `activity_image_view.xml`
````xml
<ImageView
  android:id="@+id/iv_4"
  android:layout_width="200dp"
  android:layout_height="100dp"/>
````
2.配置glide: `/app/build.gradle`
````gradle
repositories {
    mavenCentral()
    google()
}
dependencies {
    implementation 'com.github.bumptech.glide:glide:4.10.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
}
````
[说明] :
- (1)使用glide进行网络资源请求
- (2)Android Studio编译器可以自动的按照 build.gradle 中的配置进行Jar包同步

3.获取控件`iv_4`,并使用glide往里面加资源
````java
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class ImageViewActivity extends AppCompatActivity {
  private ImageView mIv4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_view);
    mIv4 = (ImageView) findViewById(R.id.iv_4);
    Glide.with(this).load("https://www.baidu.com/img/bd_logo1.png").into(mIv4);
  }
}

````

[报错] :
- 1)`Request threw uncaught throwable java.lang.SecurityException: Permission denied (missing INTERNET permission?)`: 使用glide进行网络请求时,需要配置权限.在路径`/app/src/main/AndroidMainfest.xml`中添加网络权限如下:
````xml
<use-permission android:name="android.permission.INTERNET"  />
````

## 2.8 列表视图ListView(知道,被RecyclerView替代)
- 常用属性
- Adapter接口
- Demo演示

### 2.8.1 创建一个ListViewActivity(手动导入依赖)
- 1)在`com.skypan.textview`包下,新建一个包`listview`
- 2)在`listview`下,新建一个Java类.注意: `Name: ListViewActivity` 和 `Superclass: android.app.Activity`
- 3)创建视图: `activity_listview.xml`, 在路径 `/app/src/main/res/layout`
- 4)完善`ListViewActivity.java`:
````java
package com.skypan.textview.listview;
import android.app.Activity;
import andoird.os.Bundle;
import androidx.annotation.Nullable;
import com.skypan.textview.R;

public class ListViewActivity extends Activity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listview);
  }
}
````
- 5)将`AndroidManifex.xml`声明`ListViewActivity.java`
````xml
<activity android:name=".listview.ListViewActivity" />
````

### 2.8.2 自定义颜色 + 使用
- 1)在`/app/src/main/res/values/colors.xml`中写入自定义颜色,如下
````xml
<resources>
  <color name="colorGray">#D5D5D5</color>
</resources>
````
- 2)使用
````xml
<TextView
  android:textColor="@color/colorGrayDark" />
````

### 2.8.3 自定义List View 按压样式
- 1)新建一个选择器(Selector): `/app/src/main/res/drawable` -> new Drawable Resource file -> `layout_list_item.xml`
- 2)`list_item.xml`
```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
  <item android:drawable="@color/colorAcecent" android:state_pressed="true" />
  <item android:drawable="@color/colorWhite" android:state_pressed="false" />
</selector>
```
- 3)在List View中使用: `activity_listview.xml`
````xml
<ListView
  android:id="@+id/lv_1"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:listSelector="@drawable/list_item" />
````

### 2.8.4 List View的点击事件
[参数]:
- 1)`setOnItemClickListener`: 点击事件
- 2)`setOnItemLongClickListener`: 长按事件

[栗子]: `ListViewActivity.java`
````java
import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.ListView

public class ListViewActivity extends Activity {

  // 声明 ListView控件
  private ListView mLv1;

  @Override
  protected void onCrete(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listview);
    mLv1 = (ListView) findViewById(R.id.lv_1);
    mLv1.setAdapter(new MyListAdapter(ListViewActivity.this));
    mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ListViewActivity.this, text:"点击了" + position, Toast.LENGTH_SHORT).show();
      }
    });
  }
}
````
[说明] :
- 1)公有类`ListViewActivity`继承安卓的app下面的公有类`Activity`
- 2)ListView来自`Android.widget.ListView`
- 3)适配器类`MyListAdapter`代码如下:`@/src/main/java/com.skypan.textview/listview/MyListAdapter.java`
````java
package com.skypan.textview.listview;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.skypan.textview.R;

public class MyListAdapter extends BaseAdapter {

  private Context mContext;
  private LayoutInflater mLayoutInflater;

  public MyListAdapter(Context context) {
    this.mContext = context;
    mLayoutInflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() { return 10; }

  @Override
  public Object getItem(int position) { return null; }

  @Override
  public long getItemId(int position) { return 0; }

  static class ViewHolders {
    public ImageView imageView;
    public TextView tvTitle, tvTime, tvContent;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder = null;
    if(convertView == null) {
      convertView = mLayoutInflater.inflate(R.layout.layout_list_item, null);
      holder = new ViewHolder();
      holder.imageView = (ImageView) convertView.findViewById(R.id.iv);
      holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
      hodler.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
      hodler.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
      convertView.setTag(holder);
    } else {
      holder = (ViewHolder) convertView.getTag();
    }
    // 给控件赋值
    holder.tvTitle.setText('这是标题');
    holder.tvTime.setText('2088-08-08');
    holder.tvContent.setText('这是内容呐~!');
    Glide.with(mContext).load("https://www.baidu.com/img/bd_logo1.png").into(holder.imageView);
    return convertView;
  }
}
````


## 2.x TCP的三次握手与四次挥手
- [原址](https://mp.weixin.qq.com/s/LyiwQuBQvtwV4RQCVsPQ7A)
- 1)请画出三次握手和四次挥手的示意图(略)
- 2)为什么连接的时候是三次握手?
[参考](https://www.zhihu.com/question/24853633)

[try]:
- (1)TCP作为一种可靠传输控制协议,其核心思想是: 既要保证数据可靠传输,又要提高传输的效率,而用三次恰恰可以满足以上两种方法.
- (2)TCP可靠传输的精髓,TCP连接的一方A,由操作系统动态随机选取一个32位长的序列号(Initial Sequelize Number),假设A的初始序列号为1000,以该序列号为原点,对自己将要发送的每个字节的数据进行编号,1001,1002,1003...,并把自己的初始序号ISN告诉B,告诉B什么样编号的数据是合法的,什么编号的数据是非合法的,同时B还可以对A每一个编号的字节数据进行确认。如果A收到B的确认编号2001,则意味着字节编号1001~2000,共1000个字节已经安全到达

- 3)什么是半连接队列?
[try]: 在TCP三次握手中的第一次握手,客户端向服务器发送SYN包,客户端将该连接保存在半连接队列中
- 4)ISN(Initial Sequence Number)是固定的吗?
[try]: 不是固定的,随机ISN能避免非同一网络的攻击
- 5)三次握手过程可以携带数据吗?
[try]: 根据RFC793标准,TCP的前2次握手不允许携带数据,但是第三次握手允许携带数据
- 6)如果第三次握手丢失了,客户端/服务端 会如何处理?
[参考](https://blog.csdn.net/gochenguowei/article/details/79649997)

[try]:
- (1)Server端: 此时Server端的状态为SYN_RECV,并且会根据TCP的超时重传机制,会等待3秒、6秒、12秒后重新发送 SYN + ACK 包,以便Client重新发送ACK包.而Server重发SYN + ACK包的次数,可以通过设置`/proc/sys/net/ipv4/tcp_synack_retries`修改,默认值为5.如果重发次数达到指定的次数仍未收到client的ACK应答,那么一段时间后,Server自动关闭这个连接.
- (2)Client端: Linux C中,client接收到 SYN + ACK包之后,它的TCP状态就为established,表示该连接已经建立.如果第三次握手中的ACK包丢失的情况下,Client向Server端发送数据,Server端将以RST包响应,方能感知Server的错误.
- 7)SYN攻击是什么?
[try]: TCP连接建立时,只发送 SYN包, 而不发送 ACK包.
- 8)挥手为什么需要四次? (后面解读)
- 9)四次挥手释放连接时,等待2MSL的意义? (后面解读)

### 2.x.x TCP全连接/半连接队列
- [原址](https://gameinstitute.qq.com/community/detail/125763)
- 1)什么是半连接队列,全连接队列?
[a] Linux内核协议栈为一个tcp连接管理使用两个队列,一个是半连接队列(用来保存SYN_SEN和SYN_RECV状态的请求),一个是全连接队列(acceptd队列)(用来保存处于established状态,但是应用层没有调用accept取走的请求.)
- 2)TCP连接基本概念
  - 三次握手
  (1)第一次握手: 客户端发送syn包(syn=i)到服务器,并进入SYN_SEND状态,并等待服务器确认;
  (2)第二次握手: 服务器收到syn包,必须确认客户的SYN(ack=j+1), 同时也发送一个SYN包(syn=k), 即SYN + ACK包, 此时服务器进入 SYN_RECV 状态.
  (3)第三次握手: 客户端收到服务器的 SYN + ACK包,向服务器发送ACK(ack=k+1),此包发送完毕,客户端和服务器进入ESTABLISHED状态,完成三次握手.
- 3)半连接队列(sync queue) 和 全连接队列(accept queue)
  - (1)sync queue: 是服务器接收到客户端的第一次握手请求SYN后,将该连接加入到队列中,当收到客户端的ACK后,从列表中移出
  - (2)accept queue: 是服务器收到客户端ACK后,将连接加入到的队列,在连接进行accept处理后,从队列中移出.

- 4)黑客攻击 - SYN洪水(SYN FLOOD)
  - SYN攻击属于DOS攻击的一种,它利用TCP协议缺陷,通过发送大量的SYN请求,而不回复ACK,占用大量服务器的半连接队列资源,进而导致队列溢出,无法响应正常的连接请求,耗费CPU和内存资源.



## 2.y TCP的三次握手和四次挥手(第2弹)
- [参考](https://mp.weixin.qq.com/s/LyiwQuBQvtwV4RQCVsPQ7A)
- 下面娓娓道来(逐字敲)[上一篇](https://blog.csdn.net/piano9425/article/details/103878287)提出问题的解析

### 2.y.1 三次握手
- 三次握手(Three-way Handshake)其实就是指建立一个TCP连接时,需要客户端和服务器总共发送3个包。进行三次握手的主要作用就是为了确认双方的接收能力和发送能力是否正常、指定自己的初始化序列号为后面的可靠传送做准备。实质上其实就是连接服务器指定端口,建立TCP连接,并同步连接双方的序列号和确认号,交换TCP窗口大小信息.

- 刚开始客户端处于Closed的状态,服务器处于Listen状态.进行三次握手:
  - (1)第一次握手: 客户端给服务端发一个SYN报文,并指明客户端的初始化序列号ISN(c).此时客户端处于SYN_SENT状态;首部的同步位SYN=1,初始序号seq=x,SYN=1的报文段不能携带数据,但要消耗掉一个序号
  - (2)第二次握手: 服务器收到客户端的SYN报文后,会以自己的SYN报文作为应答,并且也是指定了自己的初始化序列号ISN(s)。同时会把客户端的ISN+1作为ACK的值,表示自己已经收到了客户端的SYN,此时服务器处于SYN_RCVD的状态;在确认报文段中SYN=1,ACK=1,确认号ack=x+1(表示希望收到下一个的序号),初始序号seq=y
  - (3)第三次握手:客户端收到SYN报文之后,会发送一个ACK报文,当然,也是一样把服务器的ISN+1作为ACK的值,表示已经收到了服务端的SYN报文,此时客户端处于ESTABLISHED状态。服务器收到ACK报文之后,也处于ESTABLISHED状态,此时,双方已建立起了连接;确认报文段ACK=1,确认号ack=y+1,序号seq=x+1,ACK报文段可以携带数据,不携带数据则不消耗序号

- 发送第一个SYN的一端将执行主动打开(active open),接收这个SYN并发回下一个SYN的另一端执行被动打开(passive open).

- 在socket编程中,客户端执行connect()时,将触发三次握手。

### 2.y.2 为什么需要三次握手,两次不行吗?
- 为了弄清这个问题,我们需要先弄明白三次握手的目的是什么,能不能只用两次握手来达到同样的目的.

  - (1)第一次握手: 客户端发送网络包,服务端收到,这样服务端就能得出结论 -> 客户端的发送能力、服务端的接收能力是正常的
  - (2)第二次握手: 服务端发包,客户端收到了,这样客户端就能得出结论: 服务端的接收、发送能力,客户端的接收、发送能力是正常的。不过此时服务器并不能确认客户端的接收能力是否正常。
  - (3)第三次握手: 客户端发包,服务端收到,这样服务器就能得出结论: 客户端的接收、发送能力正常,服务器自己的发送、接收能力也正常。

- 因此,需要三次握手才能确认双方的接收与发送能力是否正常。

- 如果是用两次握手,则会出现下面这种情况:
> 如客户端发出连接请求,但因连接请求报文丢失而未收到确认,于是客户端再重传一次连接请求。后来收到了确认,建立了连接。数据传输完毕后,就释放了连接,客户端共发出了两个连接请求报文段,其中第一个丢失,第二个到达了服务端,但是第一个丢失的报文段只是再<font color=red size=4>某些网络结点长时间滞留了,延误到连接释放以后的某个时间才到达服务端</font>,此时服务端误认为客户端又发出了一次新的连接请求,于是就向客户端发出确认报文段,同意建立连接,就建立新的连接了,此时客户端忽略服务器发来的确认,也不发送数据,则服务端一致等待客户端发送数据,造成资源浪费.

### 2.y.3 什么是半连接队列?
- 服务器第一次收到客户端的SYN之后,就处于SYN_RCVD状态,此时双方还没有完全建立其连接,服务器会把此种状态下请求连接放在一个队列里,我们把这种队列称之为<font color=red>半连接队列</font>

- 当然还有一个<font color=red>全连接队列</font>,就是已经完成三次握手,建立起连接的就会放到全连接队列种。如果队列满了就有可能会出现丢包现象。

- <font color=red>SYN-ACK重传次数</font>:
> 服务器发送完SYN-ACK包,如果未收到客户确认包,服务器进行首次重传,等待一段时间仍未收到客户确认包,进行第二次重传。如果重传次数超过系统规定的最大重传次数,系统将该连接信息从半连接队列中删除。注意,每次重传等待的时间不一定相同,一般会是指数增长,例如间隔时间1s,2s,4s,8s......

### 2.y.4 ISN(Initial Sequence Number)是固定的吗?
- 当一端为建立连接而发送它的SYN时,它为连接选择一个初始序号。ISN随时间而变化,因此每个连接都将具有不同的ISN。ISN可以看作是一个32比特的计数器,每4ms加1.这样选择序号的目的在于防止在网络延迟的分组在以后被传送,而导致某个连接的一方对它做错误的解释。

- <font color=red>三次握手的其中一个重要功能是客户端和服务端交换ISN(Initial Sequence Number),以便让对方直到接下来接收数据的时候如何按序列号组装数据。</font>如果ISN是固定的,攻击者很容易猜出后续的确认号,因此ISN是动态生成的.

### 2.y.5 三次握手过程中可以携带数据吗?
- 其实第三次握手的时候,是可以携带数据的。但是,<font color=red>第一次、第二次握手不可以携带数据</font>
- 为什么这样呢? 假如第一个握手可以携带数据的话,如果有人要恶意攻击服务器,那他每次都在第一次握手中的SYN报文中放入大量的数据。因为攻击者根本就不理服务器的接收、发送能力是否正常,然后疯狂着重复发SYN报文的话,这会让服务器花费很多时间、内存控件来接收这些报文。
- 也就是说,<font color=red>第一次握手不可以放数据,其中一个简单的原因就是会让服务器更加容易受到攻击</font>。而对于第三次的话,此时客户端已经处于ESTABLISHED状态。对于客户端来说,他已经建立起连接了,并且也已经直到服务器的接收、发送能力是正常的了,所以能携带数据也没啥毛病。

### 2.y.6 SYN攻击是什么?

- <font color=red>服务器端的资源分配是在二次握手时分配的,而客户端的资源分配是在完成三次握手时分配的</font>,所以服务器容易收到SYN洪泛攻击。SYN攻击就是Client在短时间内伪造大量不存在的IP地址,并向Server不断地发送SYN包,Server则回复确认包,并等待Client确认,由于源地址不存在,因此Server需要不断重发至超时,这些伪造地SYN包将长时间占用未连接队列,导致正常的SYN请求因为队列满而被丢弃,从而引起网络拥塞甚至系统瘫痪。SYN攻击是一种典型的DoS/DDoS攻击。
- 检测SYN攻击非常的方便,当你在服务器上看到大量的半连接状态时,特别是源IP地址是随机的,基本上可以断定这是一次SYN攻击。在Linux/Unix上可以使用系统自带的netstats命令来检测SYN攻击。
> netstat -n -p TCP|grep SYN_RECV

- 常见的防御SYN攻击的方法有如下几种:
  - 缩短超时(SYN Timeout)时间
  - 增加最大半连接数
  - 过滤网关防护
  - SYN cookies技术

### 2.y.7 四次挥手
- 建立一个连接需要三次握手,而终止一个连接要经过四次挥手。这由TCP的半关闭(half-close)造成的。所谓的半关闭,其实就是TCP提供了连接的一端在结束它的发送后还能接收来自另一端数据的能力。
- TCP的连接的拆除需要发送四个包,因此成为四次挥手(Four-way handshake),客户端或服务器均可主动发送挥手动作。
- 刚开始双方都处于ESTABLISHED状态,假如是客户端先发起关闭请求。四次挥手的过程如下:
  - 第一次挥手: 客户端发送一个FIN报文,报文中会指定一个序列号。此时客户端处于`FIN_WAIT1`状态.

    即发出<font color=red>连接释放报文段</font>(FIN=1,序号seq=u),并停止发送数据,主动关闭TCP连接,进入FIN)WAIT1(终止等待1)状态,等待服务端的确认.

  - 第二次挥手: 服务端收到FIN之后,会发送ACK报文,且把客户端的序列号值+1作为ACK报文的序列号值,表明已经收到客户端的报文了,此时服务端处于CLOSE_WAIT状态

    即服务器收到连接释放报文段后即发出确认报文段(ACK=1,确认号ack=u+1,序号seq=v),服务器进入CLOST_WAIT(关闭等待)状态,此时的TCP处于半关闭状态,客户端到服务端连接释放。客户端收到服务端的确认后,进入FIN_WATI2(终止等待2)状态,等待服务端发出的连接释放报文段.

  - 第三次挥手: 如果服务端也想断开连接了,和客户端的第一次挥手一样,发送FIN报文,且指定一个序列号。此时服务器处于LAST_ACK状态

    即服务端没有要向客户端发送的数据,服务端发出<font color=red>连接释放报文段</font>(FIN=1, ACK=1, 序号 seq=w, 确认号 ack=u+1), 服务端进入LAST_ACK(最后确认)状态,等待客户端的确认.

  - 第四次挥手: 客户端收到FIN之后,一样发送一个ACK报文作为应答,且把服务器端的程序列号值+1作为自己ACK报文的序列号值,此时客户端处于TIME_WAIT状态。需要过一阵子以确保服务端收到自己的ACK报文之后才会进入CLOSED状态,服务端收到ACK报文之后,就处于关闭连接了,处于CLOSED状态

    即客户端收到服务器的连接释放报文后,对此发出<font color=red>确认报文段</font>(ACK=1,seq=u+1,ack=w+1),客户端进入TIME_WAIT(时间等待)状态.此时TCP未释放掉,需要经过时间等待计时器设置的时间2MSL后,客户端进入

- 收到一个FIN只意味着在这一方向上没有数据流动。<font color=red>客户端执行主动关闭并进入TIME_WAIT是正常的,服务端通常执行被动关闭,不会进入TIME_WAIT状态</font>

### 2.y.8 为什么需要四次
- 因为当服务端收到客户端的SYN连接请求报文后可以直接发送SYN+ACK报文。其中<font color=red>ACK报文是用来应答的,SYN报文是用来同步的。</font>但是关闭连接时,当服务器收到FIN报文时,很可能并不会立即关闭(因为还有数据要传输),所以只能先回复一个ACK报文,告诉客户端,"你发的FIN报文我收到了"。只有等到我服务端所有的报文都发送完了,我才能发送FIN报文,因此不能一起发送。故需要四次挥手。

### 2.y.9 2MSL等待状态

TIME_WAIT状态也成为了2MSL等待状态。每个具体TCP实现必须选择一个报文段最大生存时间(Maximum Segment Lifetime),它是任何报文段被丢弃前在网络内的最长时间。这个时间是有限的,因为TCP报文段以IP数据包在网络内传输,而IP数据报则有限制其生存时间的TTL字段。

对一个具体实现所给定的MSL值,处理的原则是: 当TCP执行一个主动关闭,并发回最后一个ACK,该连接必须在TIME_WATI状态停留的时间为2倍的MSL。这样可让TCP再次发送最好的ACK以防这个ACK丢失(另一端超时并重发最后的FIN)。

这种2MSL等待的另一个结果是这个TCP连接在2MSL等待期间,定义这个连接的插口(客户的IP地址和端口号,服务器的IP地址和端口号)不能再被使用。这个连接只能在2MSL结束后才能再被使用。


### 2.y.10 四次挥手释放连接时,等待2MSL的意义
><font color=red>MSL</font>是Maximum Segment Lifetime的英文缩写,可以译为"最长 报文段寿命",它是任何报文在网络上存在的最长的时间,超过这个时间报文将被丢弃。

为了保证客户端发送的最后一个ACK报文端报文段能够到达服务器。因为这个ACK有可能丢失,从而导致处在LAST-ACK状态的服务器收不到对FIN-ACK的确认报文。服务器会超时重传这个FIN-ACK,接着客户端再重传一次确认,重新启动时间等待计时器。最后客户端和服务器都能正常的关闭,一但这个ACK丢失的话,服务器就无法正常的进入关闭连接状态。


### 两个理由
1. 保证客户端发送的最后一个ACK报文段能够到达服务端。
   &nbsp;
   这个ACK报文段有可能丢失,使得处于LAST-ACK状态的B收不到已发送的FIN+ACK报文段的确认,服务端超时重传FIN+ACK报文段,而客户端能在2MSL时间内收到这个重传的FIN+ACK报文段,接着客户端重传一次确认,重新启动2MSL计时器,最后客户端和服务端都进入CLOSED状态,若客户端再TIME-WAIT状态不等待一段时间,而是发送完ACK报文段后立即释放连接,则无法收到服务端重传的FIN+ACK报文段,所以不会再发送一次确认报文段,则服务端无法正常进入到CLOSED状态。
2. 防止"已失效的连接请求报文段"出现在本连接中。
   &nbsp;
   客户端在发送完最后一个ACK报文段后,再经过2MSL,就可以使本连接持续的时间内所产生的所有报文段都从网络中消失,使下一个新的连接中不会出现这种旧的连接请求报文段。

### 2.y.11 为什么TIME_WAIT状态需要经过2MSL才能返回到CLOSE状态?

理论上,四个报文都发送完毕,就可以直接进入CLOSE状态了,但是可能网络是不可靠的,有可能最后一个ACK丢失。所以<font color=red>TIME_WAIT状态就是用来重发可能丢失的ACK报文</font>


## 2.9 网格视图GridView(略)
- 常用属性
- Adapter接口
- Demo演示

## 2.10 滚动视图ScrollView
- 垂直滚动: ScrollView
- 水平滚动: HorizontalScrollView
&nbsp;
> <font color=red>ScrollView的子元素</font>只允许有一个根结点

### 2.10.1 竖直方向滚动的基本使用
- `activity_main.xml`
````xml
<ScrollView xmlns:android:="http://schemas.android.com/apk/res/android"
  android:layout_width="match_parent"
  android:layout_height="match_parent">
  <LinearLayout>
      <!-- <Button
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:layout_marginTop="300dp"
          android:text="Test "
          android:textAllCaps="false" /> * N -->
  </LinearLayout>
</ScrollView>
````

### 2.10.2 水平方向的滚动
```xml
<HorizontalScroll>
  <LinearLayout>
    <!-- <Button
    android:layout_width="200dp"
    android:layout_height="300dp"
    android:text="Test"
    android:textAllCaps="false"/>  * N -->
  </LinearLayout>
</HorizontalScroll>
```
***
# 总结
## 1. TCP的实现

[说明] : 以下只是对上面的总结用代码的方式表述,并非是真实情况,仅供记忆和学习理解..

### 1.1 ISN生成器
````javascript
// 生成一个ISN(Initial Sequence Number)
// 32比特的计数器,每4ms加1
const ISN = (time) =>{
  return genISN(time);
}
// genISN传入时间,返回一个32比特位的计数器
````
### 1.2 报文
数据结构如下:
````javascript
// 用于建立连接请求
class SYN {
  constructor({SYN:1, seq: ISN(x)}){
    this.SYN = SYN;
    this.seq = seq;
  }
}
// 用于确认连接
class ACK {
  constructor({ACK:1, ack:'', data }){
    this.ACK = ACK;
    this.ack = ack;
    this.data = data;
  }
}
// 用于断开连接
class FIN {
  constructor({SYN: 1, seq: ISN(u)}){
    this.SYN = SYN;
    this.seq = seq;
  }
}
````
> u,x代表的是一个时间
### 1.3 Client结构
根据TCP的三次握手和四次挥手,客户端在进行连接和断开的过程有6个状态:如下
1. `CLOSED`: 初始关闭状态
2. `SYN_SEND`: TCP第一次握手,发送了SYN包之后的状态,此状态下等待服务端的SYN + ACK包
3. `ESTABLISHED`: TCP第三次握手,发送ACK包之后的状态,此状态下与服务器互相传递数据
4. `FIN_WAIT1`: TCP第一次挥手,发送FIN包之后的状态,此状态下等待服务器的ACK包
5. `FIN_WAIT2`: TCP第二次挥手,收到服务器的ACK包之后,此状态下等待服务器的FIN包
6. `TIME_WAIT`: 收到服务器第三次挥手的FIN包之后的状态,此状态下会发送一个ACK包给服务器,然后在等待2MSL后,将状态改为CLOSED.

两个函数:
1. `connect()`: 用于开启TCP连接
2. `disconnect()`: 主动关闭TCP连接
````javascript
// 假设有TCP的Client类
// TCP.Client类中有一个send方法,用于在网络中传递数据
class Client extends TCP.Client {
  constructor(){
    super();
    this.state = 'CLSOED';
  }
  async connect(){
    if(this.state === 'CLOSED') { this.handleCLOSED };
    if(this.state === 'SYN_SEND') {this.handleSYN_SEND };
  }
  handleCLOSED(){
      // 生成ack包
      const ack = new ACK({seq: ISN(new Date())});

      // 使用假设的send方法,发送TCP
      // send可以找到Server的端口,且能够告知Server端Client的端口
      const SynAck = await send(ack);

      this.state === 'SYN_SEND';
  }
  handleSYN_SEND(){
    if()
  }

}
````





