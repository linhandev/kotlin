// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: JVM-forbidden dot in escaped identifier `a.b` as declaration name
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val <!INVALID_CHARACTERS!>`a.b`<!> = 3
    return "OK"
}
