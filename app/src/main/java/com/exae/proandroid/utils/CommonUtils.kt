package com.exae.proandroid.utils

import com.exae.proandroid.bean.PersonTest
import com.orhanobut.logger.Logger

object CommonUtils {

    fun mapUtils() {
        val list = mutableListOf<Int>()
        val list1 = listOf(1, 2, 3, 4, 5, 6)
        list1.forEach {
            Logger.d("list---$it")
        }

        for (i in 10..25) {
            list[i] = i
        }
        val stringList = list.map {
            //对集合中的数据进行操作，然后赋值给新的集合
            (it * 2).toString()
        }.forEach(::print)
        //函数作为参数的第二种方式调用 类名::方法名
        val doubleList2 = list.map(Int::toDouble).forEach(::print)

        val arrayList = arrayOf(1..5, 20..30) //集合内的集合 1..5 , 50..55
        val mergeArray = arrayList.flatMap { intRange ->
            intRange.map { intElement ->
                "${(intElement * 2)} -- pp"
            }
        }
        mergeArray.forEach { // //No.1 , No.2 , No.3 , No.4 , No.5 , No.50 , No.51 ,
            print(it)
        }

        //直接多个数组集合变换成一个结集合
        val newList = arrayList.flatMap {
            it
        }
        newList.forEach { print("$it , ") }
        //筛选 集合中数据 > 2的item
        val filterList = newList.filter { it > 2 }
        filterList.forEach(::print) //3453
        //筛选 集合中下标是奇数item
        val filterIndexList = newList.filterIndexed { index, i -> index % 2 == 0; }
        filterIndexList.forEach { print(it) } //1 3 5 3
        //返回包含 最后[n]元素 的列表。，包含最后2个元素
        newList.takeLast(2).forEach(::print) // 23


    }

    inline fun <T, R> T.letTest(block: (T) -> R): R = block(this)

    fun lockTest(parm:String,tt: () -> Int){
        print(parm)
        val ss = tt()
        print(ss)
    }

    //函数作为参数,此方法无返回值,函数参数也无参数
    fun <T> lock(lock: String,body: () -> T){
        // body为函数类型对象：() -> T。表示无参函数，并且返回值类型为T。
        val ss = body()
        Logger.d(ss)
    }
    //函数作为参数,此方法返回String类型,函数ttst 参数为Int，返回值为String
    fun locs(lock: String,ttst : (aa : Int) -> String ) : String {
        val ss = ttst(5)
        Logger.d("-----aa$ss")
        return "sss-$lock$ss"
    }

    fun locst(lock: String,ttst : (aa : Int,bb : String) -> String) : String {
        return "sss" + ttst.invoke(5,"ss")
    }
    fun locsta(lock: String,ttst : (aa : Int,bb : String) -> String) : String {
//        Logger.d("----")
        val ss = ttst(5,"3333") //"11----$aa===$bb"
        Logger.d("22-----aa$ss")
        return "sss--$lock" + ttst.invoke(5,"ss")
    }
    fun ttstssss(aa: Int) : String{
        Logger.d("$aa")
        return  aa.toString()
    }
    fun ttstsss(aa: Int,bb: String) : String{
        return  aa.toString() + bb
    }

    fun tt(){
//        lock("locktest") {
//            Logger.d("ssss--")
//        }

//        lock("lockd",::tt)//死循环
//        val sse = locs("sss1",(::ttstssss))
//        Logger.d("====$sse")
//        locs("sss",::ttstssss)
//        locs("sss") { //只有一个参数
//            Logger.d(it)
//            "sss" // 为返回值
//        }
//
//        locst("sss",::ttstsss)
//
       val ewe = locsta("ewewe") { aa : Int,bb : String ->
            Logger.d("sss123")
            "aaaa"
            "11----$aa$bb" //返回的结果
        }
        Logger.d("======$ewe")
    }

    fun apply_Let_With_Run(){
        val person : PersonTest? = PersonTest(20,"javal")
        /**
         * 最常用的场景就是使用let函数处理需要针对一个可null的对象统一做判空处理
         * 需要去明确一个变量所处特定的作用域范围内可以使用
         * it指代当前对象
         * 返回最后一行值
         */
        val ewewe = person?.let { //类作为参数 it
            println(it.name)
            println(it.age)
            it.name = "rtwr"
            "ewewewewe"
        }
        person?.apply {//类扩展 可以直接调用
            println(name)
            println(age)
        }
        person.letTest {
            println(it?.name)
        }
        Logger.d("-$person---$ewewe-----")
        Logger.d("--***********************************---")
        /**\
         * 适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，
         * 经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上
         * this指代当前对象
         * 返回最后一行值
         */
        val res = with(person){
            Logger.d("-----${this?.name}---${this?.age}")
            "3213"
        }
        Logger.d("-----$res")
        Logger.d("--***********************************---")
        /**
         * 适用于let,with函数任何场景。因为run函数是let,with两个函数结合体，
         * 准确来说它弥补了let函数在函数体内必须使用it参数替代对象，
         * 在run函数中可以像with函数一样可以省略，直接访问实例的公有属性和方法，
         * 另一方面它弥补了with函数传入对象判空问题，在run函数中可以像let函数一样做判空处理
         * this指代当前对象
         * 返回最后一行值
         */
        val re = person.run {
            Logger.d("-----${this?.name}---${this?.age}")
            3213
        }
        val re1 = person?.run {
            Logger.d("-----${name}---${age}")
            3213
        }
        Logger.d("-----$re1")
        Logger.d("--***********************************---")
        /**
         * 从结构上来看apply函数和run函数很像，
         * 唯一不同点就是它们各自返回的值不一样，run函数是以闭包形式返回最后一行代码的值，
         * 而apply函数的返回的是传入对象的本身。
         * apply一般用于一个对象实例初始化的时候，需要对对象中的属性进行赋值。
         * 或者动态inflate出一个XML的View的时候需要给View绑定数据也会用到，这种情景非常常见。
         * 特别是在我们开发中会有一些数据model向View model转化实例化的过程中需要用到。
         * this指代当前对象
         * 返回当前对象
         * 多层级判空问题
         */
        val per = person?.apply {
            this.name = "ee"
        }
        Logger.d("-----${per?.name}---${per?.age}")
        val pessr = person?.apply {
            this.name = "ee111"
        }?.age?.apply {
            Logger.d("----$this")
        }
        Logger.d("-----${pessr}---")
        Logger.d("--***********************************---")
        /**
         *also函数的结构实际上和let很像唯一的区别就是返回值的不一样，
         * let是以闭包的形式返回，返回函数体内最后一行的值，
         * 如果最后一行为空就返回一个Unit类型的默认值。而also函数返回的则是传入对象的本身
         *
         * 适用于let函数的任何场景，also函数和let很像，
         * 只是唯一的不同点就是let函数最后的返回值是最后一行的返回值而also函数的返回值是返回当前的这个对象。
         * 一般可用于多个扩展函数链式调用
         * it指代当前对象
         * 返回当前对象
         */
        val ee = person?.also {
            it.name = "wwww"
        }
        Logger.d("-----${ee?.name}---")
        Logger.d("--***********************************---")

    }


}