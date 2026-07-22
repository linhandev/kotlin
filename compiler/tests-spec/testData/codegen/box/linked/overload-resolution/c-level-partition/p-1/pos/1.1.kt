/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, c-level-partition -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: top-level function-like callable has higher priority than top-level property-like callable with invoke
 */

var funCalled1124 = false
var propCalled1124 = false

fun marker1124() {
    funCalled1124 = true
}

val marker1124 = object {
    operator fun invoke() {
        propCalled1124 = true
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    marker1124()
    return if (funCalled1124 && !propCalled1124) "OK" else "NOK"
}
