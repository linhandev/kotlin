/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: companion object of classifier type is property-like callable via invoke convention
 */

class Holder1135 private constructor() {
    companion object {
        var invoked = false
        operator fun invoke(): Holder1135 {
            invoked = true
            return Holder1135()
        }
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    Holder1135()
    return if (Holder1135.invoked) "OK" else "NOK"
}
