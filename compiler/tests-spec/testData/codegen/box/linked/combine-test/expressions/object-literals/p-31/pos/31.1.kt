
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: state in object literal is not shared across distinct anonymous instances
 */

// TESTCASE NUMBER: 1
interface Counter {
    fun inc(): Int
}

fun test(): Int {
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

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
