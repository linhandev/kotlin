// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: object declaration is property-like callable via invoke convention
 */

object Callable1123 {
    var invoked = false
    operator fun invoke() {
        invoked = true
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    Callable1123()
    return if (Callable1123.invoked) "OK" else "NOK"
}
