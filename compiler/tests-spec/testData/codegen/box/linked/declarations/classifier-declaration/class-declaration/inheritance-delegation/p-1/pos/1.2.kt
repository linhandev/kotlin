// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: delegated property is evaluated once per instance at runtime
 */

// TESTCASE NUMBER: 1
interface Counter {
    val value: Int
}

var backing: Counter = object : Counter {
    override val value: Int = 1
}

class Delegated : Counter by backing

fun box(): String {
    val first = Delegated()
    backing = object : Counter {
        override val value: Int = 2
    }
    val second = Delegated()
    return if (first.value == 1 && second.value == 2) "OK" else "NOK"
}
