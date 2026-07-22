// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, object-literals, functional-interface-lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: FI { s: String -> s } lambda with String param cannot implement bar(Int) reports TYPE_MISMATCH
 */

fun interface FI {
    fun bar(s: Int): String
}

// TESTCASE NUMBER: 1
fun case1() {
    val bad = FI <!TYPE_MISMATCH!>{ <!EXPECTED_PARAMETER_TYPE_MISMATCH!>s: String<!> -> s }<!>
}
