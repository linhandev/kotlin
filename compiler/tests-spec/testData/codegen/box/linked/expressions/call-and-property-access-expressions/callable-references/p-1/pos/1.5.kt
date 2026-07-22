// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, callable-references -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: A.Companion::tag invokes companion function returning "companion"
 */

// TESTCASE NUMBER: 1

class A {
    companion object {
        fun tag(): String = "companion"
    }
}

fun box(): String {
    val ref: () -> String = A.Companion::tag
    if (ref() != "companion") return "NOK"
    return "OK"
}
