// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 63 -> sentence 63
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 63 -> sentence 63
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 63 -> sentence 63
 *                expressions, when-expressions -> paragraph 63 -> sentence 63
 * NUMBER: 1
 * DESCRIPTION: when with invoke condition infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class Callable(val x: Int) {
    operator fun invoke(): Int = x
}

fun case1(c: Callable) {
    checkSubtype<String>(
        when {
            c() > 0 -> "positive"
            else -> "non-positive"
        }
    )
}
