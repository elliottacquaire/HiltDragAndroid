package com.exae.proandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.exae.proandroid.animation.TokenAnno
import com.exae.proandroid.base.PosBaseActivity
import com.exae.proandroid.bean.TestBean
import com.exae.proandroid.bean.TestData
import com.exae.proandroid.common.ShareUtil
import com.exae.proandroid.coroutines.CoroutinesUtil
import com.exae.proandroid.utils.CommonUtils
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_launch.*
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class LaunchActivity : PosBaseActivity(R.layout.activity_launch), View.OnClickListener {

    @Inject
    lateinit var testBean: TestBean


    @Inject
    @TokenAnno
    lateinit var token: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.d(testBean.toString())



        globalScope.setOnClickListener(this)
        motionLayout.setOnClickListener(this)
        constraintLayout.setOnClickListener(this)
        main.setOnClickListener(this)
        rxjava.setOnClickListener(this)
        handler.setOnClickListener(this)
        shareElement.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.globalScope -> {
                /**
                 * 默认的协程调度器就是Dispatchers.Default，Default是一个协程调度器，其指定的线程为共有的线程池，线程数量至少为 2 最大与 CPU 数相同。
                 */
                GlobalScope.launch(Dispatchers.Main) { // 在 UI 线程创建一个新协程 // Dispatchers.Main可以省略
                    delay(3000)
                    val token = CoroutinesUtil.createToken("tokense")
                    Logger.d("=444=======$token=======")

                    //切换线程
                    //这块指定网络请求使用IO线程，withContext {}不会创建新的协程，在指定协程上运行挂起代码块，并挂起该协程直至代码块运行完成。
                    //withContext很适合需要直接获取协程结果但又不会短时间内重复调用的场景,比如网络请求.
                    //async适用于需要多次调用并且需要知道结果的场景,比如多任务下载.
                    // 如果我不需要多次调用只需要知道结果,还可以使用withContext,withContext的效果类似于上面代码的async{…}.await(),
                    // 不同的是withContext没有默认调度器必须要指定一个协程调度器,

                    val token1 = withContext(Dispatchers.IO) {
                        CoroutinesUtil.createToken("tokense")
                    }
                    Logger.d("=555=======$token1=======")


                }
                Logger.d("=333111=======${Thread.currentThread().name}=======")


                //是创建一个新的协程同时阻塞当前线程，直到协程结束。这个不应该在协程中使用，主要是为main函数和测试设计的。
                //但是launch不是suspend函数,runblocking不会等待非suspend函数),我该结束了,于是,它结束了程序,
                // 而launch闭包中的延时还没来得及执行,程序就被关闭了,所以two没有被打印出来
                runBlocking {
                    Logger.d("------runBlocking  start------------")
                    delay(5000)
                    Logger.d("------runBlocking  end------------")
                }
                Logger.d("=333=======${Thread.currentThread().name}=======")


                val defered = GlobalScope.async {
                    Logger.d("协程1开始执行")
                    delay(3000)
                    val token = CoroutinesUtil.createToken("tokense")
                    Logger.d("=async=======$token=======")
                }
                if (defered.isCompleted) {
                    val res = defered.getCompleted()
                    Logger.d("=async=======$res=======")
                } else {
                    Logger.d("=async=======no result=======")
                }

                /**
                 *
                CoroutineScope
                CoroutineScope 会跟踪它使用 launch 或 async 创建的所有协程。
                您可以随时调用 scope.cancel() 以取消正在进行的工作（即正在运行的协程）。
                在 Android 中，某些 KTX 库为某些生命周期类提供自己的 CoroutineScope。
                例如，ViewModel 有 viewModelScope，Lifecycle 有 lifecycleScope。不过，
                与调度程序不同，CoroutineScope 不运行协程

                使用 viewModelScope 时，ViewModel 类会在 ViewModel 的 onCleared() 方法中自动为您取消作用域。
                val scope = CoroutineScope(Job() + Dispatchers.Main)
                scope.launch {
                // New coroutine that can call suspend functions
                fetchDocs()
                }
                scope.cancel()

                Job 是协程的句柄。使用 launch 或 async 创建的每个协程都会返回一个 Job 实例，该实例是相应协程的唯一标识并管理其生命周期。

                CoroutineContext 使用以下元素集定义协程的行为：
                Job：控制协程的生命周期。
                CoroutineDispatcher：将工作分派到适当的线程。
                CoroutineName：协程的名称，可用于调试。
                CoroutineExceptionHandler：处理未捕获的异常。

                 */

            }
            R.id.motionLayout -> {
                //抽屉 效果
                startActivity(Intent(this,MotionActivity::class.java))
//                startActivity(Intent(this,MotionMoreActivity::class.java))
//                startActivity(Intent(this,CoordinatorLayoutActivity::class.java))
                //动画小球旋转滚动 效果
//                startActivity(Intent(this, MotionTestActivity::class.java))

            }
            R.id.constraintLayout -> {
                startActivity(Intent(this, ConstraintLayoutActivity::class.java))
            }
            R.id.main -> {
                startActivity(Intent(this, MainActivity::class.java))

                //kotlin 高阶函数
//                CommonUtils.tt()
//                CommonUtils.apply_Let_With_Run()


            }

            R.id.rxjava -> {
//                ShareUtil.putToken("tokkkkkkk")
//                val tok = ShareUtil.getToken(applicationContext)
                Logger.d(token)
            }

            R.id.handler -> {

            }
            R.id.shareElement -> {
                /**
                 * 多个元素共享
                 *  Pair pair1 = new Pair(gongxiang,"shareElement");
                Pair pair2 = new Pair(gongxiang2,"shareElement2");
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(Main8Activity.this,pair1,pair2).toBundle();
                Intent intent = new Intent(Main8Activity.this, Main7Activity.class);
                startActivity(intent,bundle);

                 */
                val bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(this,shareElement,"shareE").toBundle()
                startActivity(Intent(this, ShareElementActivity::class.java),bundle)
            }


        }
    }

}