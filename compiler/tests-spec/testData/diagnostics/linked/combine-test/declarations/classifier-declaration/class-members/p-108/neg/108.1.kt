// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 108 -> sentence 108
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 108 -> sentence 108
 * NUMBER: 1
 * DESCRIPTION: invoke with incompatible argument types fails at call site
 */

// TESTCASE NUMBER: 1
class Callable {
    operator fun invoke(x: Int): Int = x
}

fun test() = Callable()(<!TYPE_MISMATCH!>"x"<!>)
