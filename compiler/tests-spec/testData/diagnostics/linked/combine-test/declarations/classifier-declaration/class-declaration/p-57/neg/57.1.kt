// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 57 -> sentence 57
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 57 -> sentence 57
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 57 -> sentence 57
 * NUMBER: 1
 * DESCRIPTION: OutNum rejects argument violating Number upper bound
 */

// TESTCASE NUMBER: 1
class OutNum<out T : Number>(val v: T)

fun test() = OutNum(<!TYPE_MISMATCH!>"s"<!>)
