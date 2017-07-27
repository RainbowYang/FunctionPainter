# FunctionPainter2

[![Build Status](https://travis-ci.org/RainbowYang/FunctionPainter2.svg?branch=master)](https://travis-ci.org/RainbowYang/FunctionPainter2)

## 简介 | Introduction
一个用来绘画的数学空间。

## 设计 | Design
主要由坐标系(CoordinateSystem)和函数(CoordinateFunction)组成。

### 坐标系 | CoordinateSystem
坐标系是一个具有坐标转换功能（可以将一个坐标点转换为表示在屏幕上的位置）的对象。另外应负责绘图工作
#### 组成
1. 状态 | Condition ： 用于存贮其目前的状态
2. 坐标转换 | CoordinateChanger ： 用于实现坐标的双向转换。@ 1
3. 绘图 | Painter ： 用于对坐标系进行绘图。 @1 @2

### 函数 | CoordinateFunction
函数在这里指的是可以通过数学方式描述的一切几何图像。
+ 绘图 | Painter 


## 功能
### 坐标系
目前坐标系只有轴坐标系，但是是任意维度的。（高维空间看不懂怪我咯）
+ 移动 (左键拖动)
+ 缩放 (滚轮)
+ 旋转 (右键拖动)（针对三维的坐标系，实现的有立体感的旋转，其他维度只是平面旋转）

### 函数
这个其实不单是数学函数，还有其他的一些图形。但也就叫这个好了。

+ 幂函数(PowerFunction)
+ 指数函数(ExpFunction)
+ 对数函数(LogFunction
+ 三角函数(TrigonometricFunction)
+ 圆锥曲线(ConicSection)
+ 椭球(Ellipsoid)（三维才有用，不然就是个饼 0.0.）
+ 超方体(Hypercube)
+ 正多边形(RegularPolygon)、[芒星][] [（Wiki搜索结果）][] （比如[五角星][]，[六角星][]（这货不是魔法阵么）啥的）
+ 螺线
    + 费马螺线(Fermat spiral)
    + 等角螺线(Isometric spiral)
    + 双曲螺线(Hyperbolic spiral)
    + 阿基米德螺线(Archimedean spiral)
    + 连锁螺线(Lituus spiral)
    
+ 摆线
    + 摆线(Cycloid)
    + 外旋轮线(Epitrochoid)
    + 内旋轮线(Hypotrochoid)
    + 外摆线(Epicycloid)
    + 内摆线(Hypocycloid)
    
+ 利萨茹曲线(Lissajous)

## 预想功能（不局限但也不一定）

### 坐标系
二维的极坐标系。
+ 坐标轴单位长度可调 （或许还可以按指数增长）


### 函数
这个其实不单是数学函数，还有其他的一些图形。但也就叫这个好了。
+ 拉伸和压缩
+ 叠加
+ 旋转 
+ 翻转
+ 强行求导 （就是不通过理论计算，直接模拟很小的（dealt）x） <del>（或许会吧啊）</del>
+ 求定积分 （同上）
+ 分形
+ 迭代
+ 其他东西
    + 一些有趣的函数和图形
    + [贝塞尔曲线][]
    + 旋转的太极八卦图


### 点
关于点，在JavaFx是有Point2D和Point3D这两个类的。并且其中也实现了许多的点所需要的功能，比如加减，求距离等（当然不止，不一一列出）。同时也可以当做向量来用，也提供了一些向量运算的方法。

但是正如在坐标系里所提到的，坐标系不单单只是轴坐标系的，还有极坐标系。而无论作为一个点还是作为一个向量，那两个类都只能在轴坐标系中使用，并且作为官方给出的类，无法进行接口统一，所以打算自己实现。

+ 相加减
+ 单向叠加 （这个在函数的叠加中要用到，比如说x不变，y相加）
+ 旋转 
+ 翻折
+ 放大和缩小

本文博客地址 http://rainbow-yang.moe/201704/04/introduction-of-FunctionPainter.html
（未完）

[贝塞尔曲线]: https://zh.wikipedia.org/wiki/%E8%B2%9D%E8%8C%B2%E6%9B%B2%E7%B7%9A "Wiki链接"
[芒星]: http://baike.baidu.com/item/%E8%8A%92%E6%98%9F "这是百度百科的，Wiki居然没有专门的页面0.0."
[（Wiki搜索结果）]: https://zh.wikipedia.org/w/index.php?search=%E8%8A%92%E6%98%9F&title=Special:%E6%90%9C%E7%B4%A2&go=%E5%89%8D%E5%BE%80&searchToken=5jdycsxqxvlwdpupxwttj9jyb
[五角星]: https://zh.wikipedia.org/wiki/%E4%BA%94%E8%A7%92%E6%98%9F "Wiki链接"
[六角星]: https://zh.wikipedia.org/wiki/%E4%BA%94%E8%A7%92%E6%98%9F "Wiki链接"