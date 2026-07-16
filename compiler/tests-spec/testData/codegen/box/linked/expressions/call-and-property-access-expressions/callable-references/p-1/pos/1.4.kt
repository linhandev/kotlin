// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: A(3)::greet binds to greet on specific instance and invokes without receiver argument
 */

// TESTCASE NUMBER: 1

class A(val n: Int) {
    fun greet(): String = "hi$n"
}

fun box(): String {
    val ref: () -> String = A(3)::greet
    if (ref() != "hi3") return "NOK"
    return "OK"
}
