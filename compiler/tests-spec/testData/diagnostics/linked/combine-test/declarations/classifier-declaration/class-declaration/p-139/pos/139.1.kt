// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 139 -> sentence 139
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 139 -> sentence 139
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 139 -> sentence 139
 * NUMBER: 1
 * DESCRIPTION: all init blocks complete before secondary constructor body type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Seq() {
    val a = mutableListOf<Int>().apply { add(0) }

    init {
        a += 1
    }

    init {
        a += 2
    }

    constructor(tag: String) : this() {
        a += 3
    }
}

fun case1() {
    val viaSecondary = Seq("t")
    viaSecondary checkType { check<Seq>() }
    viaSecondary.a checkType { check<MutableList<Int>>() }
    val viaPrimary = Seq()
    viaPrimary checkType { check<Seq>() }
}

// TESTCASE NUMBER: 2
class Steps() {
    val log = mutableListOf<String>().apply { add("P") }

    init {
        log += "I1"
    }

    init {
        log += "I2"
    }

    constructor(tag: String) : this() {
        log += "S"
    }
}

fun case2() {
    val viaSecondary = Steps("u")
    viaSecondary checkType { check<Steps>() }
    viaSecondary.log checkType { check<MutableList<String>>() }
}

// TESTCASE NUMBER: 3
class Acc() {
    var total = 1

    init {
        total += 10
    }

    init {
        total *= 2
    }

    constructor(tag: String) : this() {
        total += 100
    }
}

fun case3() {
    val viaSecondary = Acc("v")
    viaSecondary checkType { check<Acc>() }
    viaSecondary.total checkType { check<Int>() }
}
