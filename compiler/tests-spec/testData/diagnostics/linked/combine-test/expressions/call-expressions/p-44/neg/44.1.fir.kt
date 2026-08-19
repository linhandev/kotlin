// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 44 -> sentence 44
 *                type-inference, introduction-1 -> paragraph 44 -> sentence 44
 *                type-system, upper-and-lower-bounds -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: argument violating upper bound causes compile error
 */

// TESTCASE NUMBER: 1
fun <T : Number> wrap(x: T): T = x

fun test() = <!CANNOT_INFER_PARAMETER_TYPE!>wrap<!>(<!ARGUMENT_TYPE_MISMATCH!>"s"<!>)
