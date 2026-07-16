// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, smart-casts, data-flow-framework -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: SmartCastType P component narrows expression type in each when is branch
 */

// TESTCASE NUMBER: 1
fun case_1(x: Any) {
    when (x) {
        is Int -> {
            val y = <!DEBUG_INFO_SMARTCAST!>x<!> + 1
            println(y)
        }
        is String -> {
            val y = <!DEBUG_INFO_SMARTCAST!>x<!>.length
            println(y)
        }
    }
}
