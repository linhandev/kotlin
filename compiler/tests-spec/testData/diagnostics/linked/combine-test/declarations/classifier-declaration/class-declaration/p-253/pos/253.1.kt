// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 253 -> sentence 253
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 253 -> sentence 253
 *                declarations, classifier-declaration, class-declaration, nested-and-inner-classifiers -> paragraph 253 -> sentence 253
 *                inheritance, inheriting -> paragraph 253 -> sentence 253
 * NUMBER: 1
 * DESCRIPTION: precise types when a nested class implements an outer nested generic interface with fixed type arguments
 * HELPERS: checkType
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

fun case1() {
    val x = Outer.Impl()
    x checkType { check<Outer.Impl>() }
    checkSubtype<Outer.Inner<Int>>(x)
    x.f() checkType { check<Int>() }
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

fun case2() {
    val b = Host.StringBox()
    checkSubtype<Host.Box<String>>(b)
    b.get() checkType { check<String>() }
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

fun case3() {
    val p = Nest.Mix()
    checkSubtype<Nest.PairLike<String, Int>>(p)
    p.left() checkType { check<String>() }
    p.right() checkType { check<Int>() }
}
