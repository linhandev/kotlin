// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 135 -> sentence 135
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 135 -> sentence 135
 *                declarations, property-declaration, delegated-property-declaration -> paragraph 135 -> sentence 135
 * NUMBER: 1
 * DESCRIPTION: lazy-delegated property and init block yield constructible types without forcing lazy evaluation in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class LazyBox {
    val log = mutableListOf<String>()
    val v: Int by lazy {
        log += "lazy"
        1
    }

    init {
        log += "init"
    }
}

fun case1() {
    val box = LazyBox()
    box checkType { check<LazyBox>() }
    box.log checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 2
class StringLazyBox {
    val log = mutableListOf<String>()
    val tag: String by lazy {
        log += "lazy"
        "x"
    }

    init {
        log += "init"
    }
}

fun case2() {
    val box = StringLazyBox()
    box checkType { check<StringLazyBox>() }
    box.log checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 3
class DoubleLazyBox {
    val log = mutableListOf<String>()
    val value: Double by lazy {
        log += "lazy"
        2.0
    }

    init {
        log += "init"
    }
}

fun case3() {
    val box = DoubleLazyBox()
    box checkType { check<DoubleLazyBox>() }
    box.log checkType { check<MutableList<String>>() }
}
