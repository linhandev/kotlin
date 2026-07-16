// FIR_IDENTICAL
// LANGUAGE: +MixedNamedArgumentsInTheirOwnPosition
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: non-last vararg with default parameters on subsequent arguments
 */

// TESTCASE NUMBER: 1
fun collect(vararg items: Int, suffix: Int = 0): Int = items.sum() + suffix

fun useCollect() {
    collect(1, 2, 3)
    collect(1, 2, suffix = 3)
}

// TESTCASE NUMBER: 2
fun format(vararg parts: String, separator: String = ","): String = parts.joinToString(separator)

fun useFormat() {
    format("a", "b")
    format("a", separator = ";")
}
