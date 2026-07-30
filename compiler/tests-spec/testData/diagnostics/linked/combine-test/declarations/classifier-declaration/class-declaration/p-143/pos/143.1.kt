// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 143 -> sentence 143
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 143 -> sentence 143
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 143 -> sentence 143
 * NUMBER: 1
 * DESCRIPTION: enum class init block runs once for each enum constant type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Tracked {
    A, B, C;

    init {
    }
}

fun case1() {
    val a = Tracked.A
    a checkType { check<Tracked>() }
    a.name checkType { check<String>() }
    a.ordinal checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
enum class Numbered(val code: Int) {
    X(1), Y(2);

    val stamped: Int

    init {
        stamped = code * 10
    }
}

fun case2() {
    val x = Numbered.X
    x checkType { check<Numbered>() }
    x.code checkType { check<Int>() }
    x.stamped checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
enum class Phase {
    START {
        override fun tag(): String = "s"
    },
    END {
        override fun tag(): String = "e"
    };

    abstract fun tag(): String

    init {
    }
}

fun case3() {
    val start = Phase.START
    start checkType { check<Phase>() }
    start.tag() checkType { check<String>() }
}
