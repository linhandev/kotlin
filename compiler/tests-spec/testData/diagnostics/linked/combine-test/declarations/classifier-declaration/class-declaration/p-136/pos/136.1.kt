// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 136 -> sentence 136
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 136 -> sentence 136
 *                declarations, property-declaration, delegated-property-declaration -> paragraph 136 -> sentence 136
 * NUMBER: 1
 * DESCRIPTION: accessing a lazy-delegated property after construction infers the property result type in class declaration
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
    val value = box.v
    box checkType { check<LazyBox>() }
    box.log checkType { check<MutableList<String>>() }
    value checkType { check<Int>() }
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
    val tag = box.tag
    box checkType { check<StringLazyBox>() }
    box.log checkType { check<MutableList<String>>() }
    tag checkType { check<String>() }
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
    val value = box.value
    box checkType { check<DoubleLazyBox>() }
    box.log checkType { check<MutableList<String>>() }
    value checkType { check<Double>() }
}
