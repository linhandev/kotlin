// WITH_STDLIB
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: non-last vararg with defaulted trailing parameter at runtime
 */

// TESTCASE NUMBER: 1
fun collect(vararg items: Int, suffix: Int = 10): Int = items.sum() + suffix

fun box(): String {
    val allPositional = collect(1, 2, 3) == 16
    val namedSuffix = collect(1, 2, suffix = 5) == 8
    return if (allPositional && namedSuffix) "OK" else "NOK"
}
