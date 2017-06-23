# FunctionPainter2 函数绘画师2

[![Build Status](https://travis-ci.org/RainbowYang/FunctionPainter2.svg?branch=master)](https://travis-ci.org/RainbowYang/FunctionPainter2)

## 简介
顾名思义，这是一个用来画函数的任意纬度的数学空间（已实现了三维旋转。其实还想可以画点别的好看的几何图形）。

从新开始的FunctionPainter。

代码会先更新在分支-[dev](https://github.com/RainbowYang/FunctionPainter2/tree/dev)!

Developing!

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

## 自制代码统计(2017-5-30 14:07:32)
    rainbow : 3302 lines in 63 files {
        outer : 64 lines in 2 files {
            frame : 64 lines in 2 files {
                MainFrame.java : 46(5)
                tool : 18 lines in 1 file {
                    FrameLocationSetter.java : 18(8)
                }
            }
        }
        Start.java : 59(41)
        inner : 2996 lines in 59 files {
            scalable : 28 lines in 2 files {
                ComponentScalable.java : 19(3)
                Component.java : 9(5)
            }
            view : 192 lines in 4 files {
                View.java : 10(5)
                CoordinateSystemView.java : 22(3)
                BackgroundView.java : 14(3)
                graphics : 146 lines in 1 file {
                    MyGraphics.java : 146(25)
                }
            }
            coordinate : 1385 lines in 21 files {
                system : 1026 lines in 16 files {
                    comp : 773 lines in 10 files {
                        special : 36 lines in 1 file {
                            Rotate3DEventListener.java : 36(3)
                        }
                        SystemPainter.java : 56(25)
                        Axes.java : 322(170)
                        EventListener.java : 58(10)
                        LocationChanger.java : 68(18)
                        Functions.java : 42(5)
                        Range.java : 29(5)
                        Mover.java : 84(40)
                        unused : 69 lines in 1 file {
                            Values.java : 69(15)
                        }
                        CoordinateSystemComponent.java : 9(5)
                    }
                    CoordinateSystemForAxes.java : 116(19)
                    CoordinateSystem.java : 58(6)
                    event : 79 lines in 4 files {
                        RotateEvent.java : 30(3)
                        CoordinateSystemEvent.java : 8(5)
                        MoveEvent.java : 19(3)
                        ZoomEvent.java : 22(5)
                    }
                }
                point : 359 lines in 5 files {
                    PointAxes2D.java : 77(21)
                    PointPolar2D.java : 77(24)
                    MyPoint.java : 43(26)
                    PointForAxes.java : 125(43)
                    PointDouble.java : 37(5)
                }
            }
            system : 283 lines in 3 files {
                comp : 83 lines in 1 file {
                    Colors.java : 83(37)
                }
                SystemComponent.java : 9(5)
                MySystem.java : 191(30)
            }
            function : 944 lines in 25 files {
                PointFunction.java : 56(23)
                mathfunction : 725 lines in 21 files {
                    special : 223 lines in 8 files {
                        _2D : 159 lines in 6 files {
                            cycloid : 103 lines in 5 files {
                                Epitrochoid.java : 26(8)
                                Hypotrochoid.java : 26(8)
                                Hypocycloid.java : 14(8)
                                Epicycloid.java : 17(11)
                                Cycloid.java : 20(8)
                            }
                            ConicSection.java : 56(19)
                        }
                        Lissajous.java : 23(8)
                        Net.java : 41(9)
                    }
                    MathFunction.java : 135(38)
                    simple : 367 lines in 12 files {
                        _2D : 284 lines in 10 files {
                            PowerFunction.java : 54(8)
                            TrigonometricFunction.java : 51(6)
                            ExpFunction.java : 22(6)
                            spiral : 135 lines in 6 files {
                                ArchimedeanSpiral.java : 21(7)
                                IsometricSpiral.java : 20(7)
                                LituusSpiral.java : 16(6)
                                FermatSpiral.java : 25(7)
                                HyperbolicSpiral.java : 23(9)
                                Spiral.java : 30(20)
                            }
                            LogFunction.java : 22(6)
                        }
                        SimpleMathFunction.java : 43(6)
                        _3D : 40 lines in 1 file {
                            Ellipsoid.java : 40(15)
                        }
                    }
                }
                MyFunction.java : 41(27)
                pointfunction : 122 lines in 2 files {
                    Hypercube.java : 21(5)
                    RegularPolygon.java : 101(10)
                }
            }
            listener : 65 lines in 2 files {
                CoordinateSystemListener.java : 34(5)
                Listeners.java : 31(5)
            }
            math : 99 lines in 2 files {
                MyMath.java : 30(20)
                Line.java : 69(21)
            }
        }
        tools : 183 lines in 1 file {
            CodeReader.java : 183(13)
        }
    }
    ---------------------------------------------------------
    Files : 63
    Code lines : 3302
    Blank lines : 644
    Annotation lines : 975
    Lines per file : 52.46

本文博客地址 http://rainbow-yang.moe/201704/04/introduction-of-FunctionPainter.html
（未完）

[贝塞尔曲线]: https://zh.wikipedia.org/wiki/%E8%B2%9D%E8%8C%B2%E6%9B%B2%E7%B7%9A "Wiki链接"
[芒星]: http://baike.baidu.com/item/%E8%8A%92%E6%98%9F "这是百度百科的，Wiki居然没有专门的页面0.0."
[（Wiki搜索结果）]: https://zh.wikipedia.org/w/index.php?search=%E8%8A%92%E6%98%9F&title=Special:%E6%90%9C%E7%B4%A2&go=%E5%89%8D%E5%BE%80&searchToken=5jdycsxqxvlwdpupxwttj9jyb
[五角星]: https://zh.wikipedia.org/wiki/%E4%BA%94%E8%A7%92%E6%98%9F "Wiki链接"
[六角星]: https://zh.wikipedia.org/wiki/%E4%BA%94%E8%A7%92%E6%98%9F "Wiki链接"