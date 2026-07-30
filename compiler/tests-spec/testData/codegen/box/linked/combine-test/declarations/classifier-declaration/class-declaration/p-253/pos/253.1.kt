// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 253 -> sentence 253
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 253 -> sentence 253
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 253 -> sentence 253
 *                inheritance, inheriting -> paragraph 253 -> sentence 253
 * NUMBER: 1
 * DESCRIPTION: a nested class may implement an outer nested generic interface by fixing type arguments; contrasts with p-43 nested non-generic inheritance and p-231 top-level generic interface producers
 */

// TESTCASE NUMBER: 1
class Outer {
    interface Inner<T> {
        fun f(): T
    }

    class Impl : Inner<Int> {
        override fun f(): Int = 1
    }
}

// TESTCASE NUMBER: 2
class Host {
    interface Box<T> {
        fun get(): T
    }

    class StringBox : Box<String> {
        override fun get(): String = "ok"
    }
}

// TESTCASE NUMBER: 3
class Nest {
    interface PairLike<A, B> {
        fun left(): A
        fun right(): B
    }

    class Mix : PairLike<String, Int> {
        override fun left(): String = "x"
        override fun right(): Int = 2
    }
}

fun box(): String {
    if (Outer.Impl().f() != 1) return "NOK: outer-impl"
    val asInner: Outer.Inner<Int> = Outer.Impl()
    if (asInner.f() != 1) return "NOK: via-inner"

    if (Host.StringBox().get() != "ok") return "NOK: string-box"
    val asBox: Host.Box<String> = Host.StringBox()
    if (asBox.get() != "ok") return "NOK: via-box"

    if (Nest.Mix().left() != "x") return "NOK: mix-left"
    if (Nest.Mix().right() != 2) return "NOK: mix-right"
    val asPair: Nest.PairLike<String, Int> = Nest.Mix()
    if (asPair.left() != "x" || asPair.right() != 2) return "NOK: via-pair"
    return "OK"
}
