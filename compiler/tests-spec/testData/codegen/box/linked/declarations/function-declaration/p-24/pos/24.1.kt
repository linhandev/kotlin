// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: noinline lambda parameters may be passed to another function at runtime
 */

// TESTCASE NUMBER: 1
var executed = false

inline fun acceptNoinline(noinline block: () -> Unit) {
    block()
}

inline fun passNoinlineParameter(noinline block: () -> Unit) {
    acceptNoinline(block)
}

fun box(): String {
    passNoinlineParameter { executed = true }
    return if (executed == true) "OK" else "NOK"
}
