// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 *                overload-resolution, callables-and-invoke-convention -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: invoke operator trailing lambda type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Runner {
    operator fun invoke(block: () -> Unit) {
        block()
    }
}

fun case1() {
    (Runner()) { }
    checkSubtype<Unit>(Unit)
}
