// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: local function declared inside another function may capture values from the enclosing scope
 */

// TESTCASE NUMBER: 1
fun outer(): Int {
    var x = 2

    fun inner(): Int = x

    val before = inner()
    x = 42
    val after = inner()
    return before + after
}

// TESTCASE NUMBER: 2
fun outerWithParameter(base: Int): Int {
    fun inner(offset: Int): Int = base + offset
    return inner(8)
}
