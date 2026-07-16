// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: inner abstract class implemented via anonymous object, init block assignment, and nested inner class
 * HELPERS: checkType, functions
 */


// NOTE: attempt to implement inner abstract class as anonymous class
open class MainCase1() {
    private val priv = "privet"

    val implVal = object : MainCase1.InnerAbstractBase() {
        override fun foo(s: String) {
            println("object {$s}")
        }
    }

    abstract inner class InnerAbstractBase() {
        protected abstract fun foo(s: String)
        fun boo() {
            foo(priv)
        }
    }
}

// TESTCASE NUMBER: 1
fun case1() {
    val main = MainCase1()
    <!DEBUG_INFO_EXPRESSION_TYPE("MainCase1.InnerAbstractBase")!>main.implVal<!>.boo()
}



// NOTE: attempt to implement inner abstract class in init block
// TESTCASE NUMBER: 2
fun case2() {
    val main = MainCase2()
    <!DEBUG_INFO_EXPRESSION_TYPE("MainCase2.InnerAbstractBase")!>main.impl<!>.boo()
}


open class MainCase2() {
    private val priv = "privet"

    abstract inner class InnerAbstractBase() {
        protected abstract fun foo(s: String)
        fun boo() {
            foo(priv)
        }
    }

    var impl: InnerAbstractBase

    init {
        impl = object : MainCase2.InnerAbstractBase() {
            override fun foo(s: String) {
            }
        }
    }
}



// NOTE: attempt to inherit inner abstract class as another inner class
// TESTCASE NUMBER: 3
fun case3() {
    val main = MainCase3()
    <!DEBUG_INFO_EXPRESSION_TYPE("MainCase3.ImplInnerAbstractBase")!>main.ImplInnerAbstractBase()<!>.boo()
}

open class MainCase3() {
    private val priv = "privet"

    abstract inner class InnerAbstractBase() {
        protected abstract fun foo(s: String)
        fun boo() {
            foo(priv)
        }
    }

    inner class ImplInnerAbstractBase : MainCase3.InnerAbstractBase() {
        override fun foo(s: String) {
            println("ImplInnerAbstractBase {$s}")
        }
    }
}



// NOTE: attempt to inherit inner abstract class in a outer class function
// TESTCASE NUMBER: 4
fun case4() {
    val main = MainCase4()
    <!DEBUG_INFO_EXPRESSION_TYPE("MainCase4.InnerAbstractBase")!>main.zoo()<!>.boo()
}


open class MainCase4() {
    private val priv = "privet"

    abstract inner class InnerAbstractBase() {
        protected abstract fun foo(s: String)
        fun boo() {
            foo(priv)
        }
    }

    fun zoo(): MainCase4.InnerAbstractBase {
        class ImplInnerAbstractBaseZoo : MainCase4.InnerAbstractBase() {
            override fun foo(s: String) {
                println("zoo fun: {$s}")
            }
        }
        return ImplInnerAbstractBaseZoo()
    }
}
