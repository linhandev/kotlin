// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 137 -> sentence 137
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 137 -> sentence 137
 *                declarations, classifier-declaration, object-declaration -> paragraph 137 -> sentence 137
 * NUMBER: 1
 * DESCRIPTION: companion object const val compile-time constant type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Limits {
    companion object {
        const val MAX = 10
        const val MIN = 0
    }

    var observed = 0

    init {
        observed = MAX + MIN
    }
}

fun case1() {
    Limits.MAX checkType { check<Int>() }
    Limits.MIN checkType { check<Int>() }
    val sum = Limits.MAX + Limits.MIN
    sum checkType { check<Int>() }
    val instance = Limits()
    instance checkType { check<Limits>() }
    instance.observed checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class Labels {
    companion object {
        const val TAG = "ready"
        const val CODE = 42
    }
}

fun case2() {
    Labels.TAG checkType { check<String>() }
    Labels.CODE checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class Flags {
    companion object {
        const val ENABLED = true
        const val MASK = 0xFF
    }

    val fromConst = MASK

    init {
        check(ENABLED)
    }
}

fun case3() {
    Flags.ENABLED checkType { check<Boolean>() }
    Flags.MASK checkType { check<Int>() }
    val instance = Flags()
    instance.fromConst checkType { check<Int>() }
}
