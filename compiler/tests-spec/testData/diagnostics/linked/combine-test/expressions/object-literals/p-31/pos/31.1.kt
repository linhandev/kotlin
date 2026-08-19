// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: state in object literal is not shared across distinct anonymous instances
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Counter {
    fun inc(): Int
}

fun case_1(): Int {
    val c1 = object : Counter {
        var n = 0
        override fun inc(): Int = ++n
    }
    val c2 = object : Counter {
        var n = 0
        override fun inc(): Int = ++n
    }
    return c1.inc() + c2.inc()
}

fun case_1_check() {
    checkSubtype<Int>(case_1())
}
