// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: kotlin.Nothing typing for TODO and elvis-throw expressions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(): Nothing = TODO()


// TESTCASE NUMBER: 2
fun case_2(): Nothing {
    throw IllegalArgumentException("failed")
}


// TESTCASE NUMBER: 3
fun case_3(): String {
    val value: String? = null
    return value ?: throw Exception()
}
