/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 18
 * DESCRIPTION: implicit this() expands to this.invoke() as property-like callable
 */

class Self1154 {
    var invoked = false

    operator fun invoke() {
        invoked = true
    }

    fun trigger(): String {
        this()
        return if (invoked) "OK" else "NOK"
    }
}

// TESTCASE NUMBER: 1
fun box(): String = Self1154().trigger()
