// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 17 -> sentence 17
 *                expressions, jump-expressions, return-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: bare return is not allowed in non-inline trailing lambda
 */

// TESTCASE NUMBER: 1
fun runBlock(block: () -> Unit): Unit = block()

fun test() = runBlock { <!RETURN_NOT_ALLOWED!>return<!> }
