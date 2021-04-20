package com.exae.proandroid.coroutines

import com.orhanobut.logger.Logger
import kotlinx.coroutines.delay

/**
 * 协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大。而协程依赖于线程，但是协程挂起时不需要阻塞线程，
 * 几乎是无代价的，协程是由开发者控制的。所以协程也像用户态的线程，非常轻量级，一个线程中可以创建任意个协程。

 * 协程可以简化异步编程，可以顺序地表达程序，协程也提供了一种避免阻塞线程并用更廉价、更可控的操作替代线程阻塞的方法 -- 协程挂起。
 *
 * 协程scope
    协程代码都必须包含在一个范围内，就好比说：只有在这个圈里可以使用协程。这个scope用于跟踪管理其内部的协程。

    例如：协程标准库中的GlobalScope 、Android的viewModelScope

    协程builder
    其协程代码与非协程代码的桥梁。其负责在非协程代码里启动协程，即在协程scope里build一个协程代码块

    例如：launch与async

    协程dispatcher
    它负责具体执行线程。使用协程builder构建了协程后，总的有人去执行啊，这个任务就是由dispatcher完成的，它会调度线程来运行协程代码。

    例如：Dispatchers.Default、Dispatchers.IO，Dispatchers.Main

    CoroutineStart
    通过 launch{} 等创建一个协程的时候，你也可以传入一个 CoroutineStart 枚举值，这个枚举值参数定义了 CoroutineBuilder 的执行 Coroutine 的时机，具体的时机由以下几种：

    Kotlin 提供了三个调度程序，以用于指定应在何处运行协程：

    Dispatchers.Main - 使用此调度程序可在 Android 主线程上运行协程。此调度程序只能用于与界面交互和执行快速工作。示例包括调用 suspend 函数，运行 Android 界面框架操作，以及更新 LiveData 对象。
    Dispatchers.IO - 此调度程序经过了专门优化，适合在主线程之外执行磁盘或网络 I/O。示例包括使用 Room 组件、从文件中读取数据或向文件中写入数据，以及运行任何网络操作。
    Dispatchers.Default - 此调度程序经过了专门优化，适合在主线程之外执行占用大量 CPU 资源的工作。用例示例包括对列表排序和解析 JSON。

    DEFAULTE:会根据该 Coroutine 依赖的 Context 立刻执行该 Coroutine
    LAZY:按需执行 Coroutine，仅仅在你调用了 Job.start() 或者 Job.await() 之后会执行
    ATOMIC: 原子操作类型，也就是说会根据依赖的Context 执行 Coroutine，但是该 Coroutine 不可取消。对比 DEFAULT 类型，它不不可取消，不能通过 job.cancel() 去取消的。
    UNDISPATCHED:会在当前线程第一个挂起点执行 Coroutine

 */
object CoroutinesUtil {

    /**
     * 函数前面有suspend修饰符标记，这表示两个函数都是挂起函数。
     * 挂起函数能够以与普通函数相同的方式获取参数和返回值，但是调用函数可能挂起协程（如果相关调用的结果已经可用，
     * 库可以决定继续进行而不挂起），挂起函数挂起协程时，不会阻塞协程所在的线程。
     * 挂起函数执行完成后会恢复协程，后面的代码才会继续执行。但是挂起函数只能在协程中或其他挂起函数中调用。
     * 事实上，要启动协程，至少要有一个挂起函数，它通常是一个挂起 lambda 表达式。所以suspend修饰符可以标记普通函数、扩展函数和 lambda 表达式
     */
    suspend fun createToken(tt : String) : String{
        delay(2000)
        Logger.d("*111***$tt*********${Thread.currentThread().name}********")
        val  name = getName(30)
        return "*****$tt-----$name--"
    }

    suspend fun getName(age:Int) : String{
        Logger.d("**222*****$age******${Thread.currentThread().name}********")
        return if (age > 20){
            "jakc_20"
        }else {
            "jakc_10"
        }
    }

    fun testSS() : Int{
        return 30
    }

    /**
     * 我们使用内联修饰符时最常见的场景就是把函数作为另一个函数的参数时(高阶函数)。
     * 集合或字符串处理(如filter,map或者joinToString)或者一些独立的函数(如repeat)就是很好的例子。
    这就是为什么inline修饰符经常被库开发人员用来做一些重要优化的原因了。
    他们应该知道它是如何工作的，哪里还需要被改进以及使用成本是什么。当我们使用函数类型作为参数来定义自己的工具类函数时，
    我们也需要在项目中使用inline修饰符。当我们没有函数类型作为参数，没有reified实化类型参数并且也不需要非本地返回时，
    那么我们很可能不应该使用inline修饰符了。这就是为什么我们在非上述情况下使用inline修饰符会在Android Studio或IDEA IntelliJ得到一个警告原因。
     */
   inline fun testInline(){

    }
}