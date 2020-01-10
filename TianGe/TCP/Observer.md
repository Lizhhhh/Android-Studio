# 1. 观察者模式
- 参考[JavaScript设计模式](https://detail.tmall.com/item.htm?spm=a230r.1.14.20.13cc5c79RxSDdw&id=560175810248&ns=1&abbucket=3)
## 1.1 消息注册方法
"将订阅者注册的消息推入到消息队列中"

[算法思路] :
1. 在推入到消息队列时,如果此消息不存在则应该创建一个该消息类型并将该消息放入消息队列中
2. 如果此消息存在则应该将消息执行方法推入该消息对应的执行方法队列中(保证多个模块注册一则消息时能顺利执行)

````javascript
regist: function(type, fn) {
  // 如果消息不存在则应该创建一个该消息类型
  if(typeof __message[type] === 'undefined') {
    // 将动作推入到该消息对应的动作执行队列中
    __message[type] = [fn];
  // 如果此消息存在
  } else {
    // 将动作方法推入该消息对应的动作执行序列中
    __message[type].push(fn);
  }
}
````

## 1.2 发布消息方法
"对于发布消息方法,其功能是当观察者发布一个消息时将所有订阅者的消息一次执行"

````javascript
fire: function(type, args) {
  // 如果该消息没有被注册,则返回
  if(!__message[type])
    return;
  // 定义消息信息
  var events = {
    type: type,
    args: args || {}
  },
  i = 0,
  len = __message[type].length;
  // 遍历消息动作
  for(; i < len; i++) {
    // 依次执行注册的消息对应的动作序列
    __messages[type][i].call(this, events);
  }
}
````

## 1.3 消息注销方法
"将订阅者注销的消息从消息队列中清除"
````javascript
remove: function(type, fn) {
  // 如果消息队列存在
  if(__messages[type] instanceof Array){
    // 从最后一个消息动作遍历
    var i = __message[type].length - 1;
    for(; i>= 0; i--) {
      // 如果存在该动作则在消息动作序列中移除相应动作
      __messages[type][i] === fn && __messages[type].splice(i, 1);
    }
  }
}
````

# 2.面向对象对象的程序设计
- 参考[JavaScript高级程序设计(第三版)](https://detail.tmall.com/item.htm?id=15137223953&ali_refid=a3_430583_1006:1102629117:N:UxZJiXb9HGvjaEwSsl47TYD7FWjT+KAxP8i1AY63ENk=:b6710647c4d135933a8ed27ade2675af&ali_trackid=1_b6710647c4d135933a8ed27ade2675af&spm=a230r.1.14.1)

面向对象(Object-Oriented, OO)的语言有一个标志,那就是它们都有类的概念,而通过类可以创建任意多个具有相同属性和方法的对象。ECMAScript中没有类的概念,因此它的对象也与类的语言的对象有所不同。

ECMA-262把对象定义为:"无序属性的集合,其属性可以包含基本值、对象或者函数"。严格来讲,这就相当于说对象是一组没有特定顺序的值。对象的每个属性和方法都有一个名字,而每个名字都映射到一个值。正因为这样,我们可以把ECMAScript的对象想象成散列表:无非就是一组名值对,其中值可以是数据或函数

## 2.1 理解对象
- 创建一个对象的最简单方式
- 创建一个Object的实例,然后再为它添加属性和方法
````javascript
var person = new Object();
person.name = "Nicholas";
person.age = 29;
person.job = "Software Engineer";
person.sayName = function() {
  alert(this.name);
};
````
早期的JavaScript开发人员使用上述方法创建对象。几年后,对象字面量成为创建这种对象的首选模式:
````javascript
var person = {
  name: "Nicholas",
  age: 29,
  job: "Software Engineer",

  sayName: function(){
    alert(this.name);
  }
};
````

### 2.1.1 属性类型
ECMA-262第5版在定义只有内部才用的特征(attribute)时,描述了属性(property)的各种特征。ECMA-262定义这些特性是为了给JavaScript引擎用的,因此在JavaScript中我们不能直接访问它们。为了表示特性是内部值,该规范把它们放在了两对儿方括号中,例如[ [Enumerable] ]

1. 数据属性
    数据属性包含一个数据值的位置。在这个位置可以读取和写入值。数据属性有4个描述其行为的特性
    - [ [Configurable]]: 表示能否通过delete删除属性从而重新定义属性,能否修改属性的特性,或者能否把属性修改为访问器属性。像前面例子中那样直接在对象上定义的属性,默认为true。
    - [ [Enumberable]]:表示能否通过for-in循环返回属性,默认为true。
    - [ [Writable]]:表示能否修改属性的值,默认为true。
    - [ [Value]]:属性的数据值,默认为undefined

    对于像前面栗子中的那样直接在对象上定义属性,它们的[ [Configurable]]、[ [Enumberable]]和[ [Writable]]特性都被设置为true,而[ [Value]]特性被设置为指定的值。
````javascript
var person = {
  name: "Nicholas"
}
````
要修改属性默认的特性,必须使用ECMAScript5的Object.defineProperty()方法。这个方法接收三个参数:属性所在的对象、属性的名字和一个描述符对象。其中,描述符(descriptor)对象的属性必须是:configurable、enumerable、writable和value。
````javascript
var person = {};
Object.defineProperty(person,"name", {
  writable: false,
  value: "Nicholas"
});
````
2. 访问器属性
访问器属性不包含数据值;它们包含一对儿getter和setter函数。在读取访问器属性时,会调用getter函数,这个函数负责返回有效的值;在写入访问器属性时,会调用setter函数并传入新值,这个函数负责决定如何处理数据。

- `[[Configurable]]`: 表示能否通过delete删除属性从而重新定义属性。
- `[[Enumerable]]`: 表示能否通过for-in循环返回属性。
- `[[Get]]`:在读取属性时调用的函数,默认值为undefined
- `[[Set]]`:在写入属性时调用的函数,默认值为undefined

访问器属性不能直接定义,必须使用Object.defineProperty()来定义。
````javascript
var book = {
  _year: 2004,
  edition: 1
};
Object.defineProperty(book, "year", {
  get: function() {
    return this._year;
  },
  set: function(newValue) {
    if(newValue > 2004){
      this._year = newValue;
      this.edition += newValue - 2004;
    }
  }
});
book.year = 2005;
alert(book.edition);
````
### 2.1.2 定义多个属性
ECAMAScript5 又定义了一个Object.defineProperties()方法。利用这个方法可以通过描述符一次定义多个属性
````javascript
var book = {};
Object.defineProperties(book, {
  _year: {
    writable: true,
    value: 2004
  },
  edition: {
    writable: true,
    value: 1
  },
  year: {
    get: function() {
      return this._year;
    },
    set: function(newValue) {
      if(newValue > 2004) {
        this._year = newValue;
        this.edition += newValue - 2004;
      }
    }
  }
});
````

### 2.1.3 读取属性的特性
使用ECMAScript5的Object.getOwnPropertyDescriptor()方法,可以取得给定属性的描述符。这个方法接收两个参数: 属性所在的对象和要读取其描述符的属性名称。返回值是一个对象,如果是访问器属性,这个对象的属性又configurable、enumerable、get和set;如果是数据属性,这个对象的属性又configurable、enumerable、writable和value。
````javascript
var book = {};
Object.defineProperties(book, {
  _year: {
    value: 2004
  },
  edition: {
    value: 1
  },
  year: {
    get: function(){
      return this._year;
    },
    set: function(newValue){
      if (newValue > 2004) {
        this._year = newValue;
        this.edition += newValue - 2004;
      }
    }
  }
});
var descriptor = Object.getOwnPropertyDescriptor(book, "_year");
alert(descriptor.value);    // 2004
alert(descriptor.configurable);   // false
alert(typeof descriptor.get);   // "undefined"

var descriptor = Object.getOwnPropertyDescriptor(book, "year");
alert(descriptor.value);
alert(descriptor.enumerable);
alert(typeof descriptor.get);
````

## 2.2 创建对象
- 对象字面量的缺点: 使用同一个接口创建很多对象,会产生大量的重复代码

### 2.2.1 工厂模式
- 抽象了创建具体对象的过程
- 考虑到ECMAScript中无法创建类,开发人员发明了如下函数:
````javascript
function createPerson(name, age, job) {
  var o = new Object();
  o.name = name;
  o.age = age;
  o.job = job;
  o.sayName = function(){
    alert(this.name);
  };
  return o;
}
var person1 = createPerson("Nicholas", 29, "Software Engineer");
var person2 = createPerson("Greg", 27, "Doctor");
````
- 工厂模式的缺点: 没有解决对象识别问题,即怎样知道一个对象的类型.(如上面应该可以检测出是一个createPerson类)
### 2.2.2 构造函数模式
- ECMAScript中的构造函数可用来创建特定类型的对象。
````javascript
function Person(name, age, job) {
  this.name = name;
  this.age = age;
  this.job = job;
  this.sayName = function(){
    alert(this.name);
  };
}
var person1 = new Person("Nicholas", 29, "Softwate Engineer");
var person2 = new Person("Greg", 27, "Doctor");
````
#### 2.2.2.1 构造函数和工厂模式的区别
- 没有显示地创建对象
- 直接将属性和方法赋给了this对象
- 没有return语句

#### 2.2.2.2 new构造函数
- 使用new操作符调用构造函数,实际上会经历以下4个步骤:
(1) 创建一个新对象;
(2) 将构造函数的作用域赋给新对象(this就指向了这个新对象);
(3) 指向构造函数中的代码(为这个新对象添加属性);
(4) 返回新对象


[栗子]:

 1. 检测person1是不是Person的实例
 ````javascript
 console.log(person1 instanceof Person);
 ````

创建自定义的构造函数意味着将来可以将它的实例标识为一种特定的类型。

#### 2.2.2.3 不使用new操作符调用构造函数
````javascript
function Person(name, age, job) {
  this.name = name;
  this.age = age;
  this.job = job;
  this.sayName = function(){
    alert(this.name);
  }
}
Person("Greg", 27, "Doctor");
alert(Window.name);     // "Greg"
````
- 将会在全局对象上挂载这些属性和方法.

#### 2.2.2.4 构造函数的问题
- 构造函数的主要问题是: "每个方法都要在每个实例上重新创建一遍"
````javascript
// 前面的构造函数等价于
function Person(name, age, job){
  this.name = name;
  this.age = age;
  this.job = job;
  this.sayName = new Function("alert(this.name)");
}
````
- 从以上角度来看,每一个Person实例都包含一个不同的Function实例
- 更确切的讲,以构造函数创建的函数,会导致不同的作用域链和标识符解析
- 以下代码可以证明函数的方法是不同的.
````javascript
console.log(person1.sayName === person2.sayName);   //false
````

### 2.2.3 原型模式
- 构造函数的缺点是,不同的实例的同一个方法是不相等的.
- 原型模式的引出就是为了解决上面的问题
- 具体做法是,不在构造函数中定义对象实例的信息,而是将这些信息直接添加到原型对象中
````javascript
function Person(){}
Person.prototype.name ="Nicholas";
Person.prototype.age =29;
Person.prototype.job ="Software Engineer";
Person.prototype.sayName = function () {
  alert(this.name);
};
var person1 = new Person();
person1.sayName();    // "Nicholas"

var person2 = new Person();
person2.sayName();    // "Nicholas"

console.log(person1.sayName == person2.sayName);    // true
````
#### 2.2.3.1 理解原型对象
- 通过以上方法创建的实例共享所有构造函数的属性和方法.
- 原型对象
(1) 新建一个函数Person.
(2) 根据规则为Person函数新建一个prototype属性: `Person -> Person.prototype`
(3) 在默认情况下,所有原型对象都会自动获得一个constructor属性: `xxx.prototype -> xxx.prototype.constructor`
(4) constructor属性是一个指向prototype原型(即,Person)的指针: `Person.prototype.constructor === Person`
(5) 当调用构造函数(Person)创建一个新实例(person1)后,该实例内部的一个指针(ECMA-262第5版称之为`[[Prototype]]`)指向构造函数的原型(Person.prototype).
(6) `[[Prototype]]`: 脚本中并无访问该指针的方法,但是浏览器厂商提供了访问这个指针的方法:`__proto__`
- 通过`isPrototypeOf()`方法来寻找对象之间是否存在原型关系
````javascript
console.log(Person.prototype.isPrototypeOf(person1));   // true
````
- `Object.getPrototypeOf()`: ECMAScript 5 新增的方法,用于返回实例的构造函数的原型.
````javascript
console.log(Object.getPrototypeOf(person1) === Person.prototype);
````
- 代码搜索某个属性的顺序:
(1) 首先从实例(person1)的本身开始获取,若找到则返回
(2) 否则顺着`[[Prototype]]`指针找到`Person.prototype`


# x.问题
## x.1 如何在继承的子类共享父类的数据

## x.2 浅谈原型链
## x.3 构造函数是什么、与普通函数的区别在哪里
## x.4 prototype、constructor是怎么来的
## x.5 实例与构造函数之间有直接联系吗



