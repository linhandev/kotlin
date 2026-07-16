// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: default implicit receiver is available as this for implicit callable invocation
 */

class Holder1103 {
    fun mark(): String = "OK"
}

// TESTCASE NUMBER: 1
fun box(): String {
    val holder = Holder1103()
    return with(holder) {
        mark()
    }
}
