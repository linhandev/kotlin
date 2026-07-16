// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NO_VALUE_FOR_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: cannot skip middle default parameter with positional argument
 */

// TESTCASE NUMBER: 1
fun bar(x: Int = 1, y: Int = 1, z: String) {}

fun skipMiddleWithPositional() {
    bar(2, <!TYPE_MISMATCH!>"Me"<!>)
}
