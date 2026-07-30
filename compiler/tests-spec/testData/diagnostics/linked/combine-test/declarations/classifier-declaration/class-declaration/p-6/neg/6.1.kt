// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 6 -> sentence 6
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: argument violates Number upper bound
 */

// TESTCASE NUMBER: 1
class Holder<T : Number>(val v: T)

fun test() = Holder(<!TYPE_MISMATCH!>"s"<!>)
