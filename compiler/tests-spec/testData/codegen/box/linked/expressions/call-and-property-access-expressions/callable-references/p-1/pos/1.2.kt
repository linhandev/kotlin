// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: A::greet binds to function greet and invokes it on given A receiver
 */

// TESTCASE NUMBER: 1

class A(val n: Int) {
    fun greet(): String = "hi$n"
}

fun box(): String {
    val ref: (A) -> String = A::greet
    if (ref(A(3)) != "hi3") return "NOK"
    return "OK"
}
