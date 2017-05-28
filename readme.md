# FunctionPainter2 函数绘画师2
从新开始的FunctionPainter

代码先更新在分支-[dev](https://github.com/RainbowYang/FunctionPainter2/tree/dev)!

Developing!

[来个GitHub的链接吧](https://github.com/RainbowYang/FunctionPainter2/)

## 简介
顾名思义，这是一个用来画函数的画板（其实还想可以画点别的好看的几何图形）。

## 功能
### 坐标系
目前坐标系只有轴坐标系，但是是任意维度的。（高维空间看不懂怪我咯）
+ 移动 (左键拖动)
+ 缩放 (滚轮)
+ 旋转 (右键拖动)（针对三维的坐标系，实现的有立体感的旋转，其他维度只是平面旋转）

### 函数
这个其实不单是数学函数，还有其他的一些图形。但也就叫这个好了。

+ 幂函数
+ 指（对）数函数
+ 三角函数
+ 圆锥曲线
+ 椭球（三维才有用，不然就是个饼 0.0.）
+ 正多边形、[芒星][] [（Wiki搜索结果）][] （比如[五角星][]，[六角星][]（这货不是魔法阵么）啥的）
+ 。。。
（。。。并不是说还有没写的，只是还有很多可以做的）

## 预想功能（不局限但也不一定）

### 坐标系
还有二维的极坐标系。

+ 坐标轴之间角度可调
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
    + 正多边形
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