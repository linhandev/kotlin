// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Trailing DOT without member name causes incomplete member access syntax error
 */

// TESTCASE NUMBER: 1
class Foo(val x: Int)

fun case1(): String {
    val foo = Foo(42)
    val y = foo.
    <!ILLEGAL_SELECTOR!>return "OK"<!>
<!NO_RETURN_IN_FUNCTION_WITH_BLOCK_BODY!>}<!>
