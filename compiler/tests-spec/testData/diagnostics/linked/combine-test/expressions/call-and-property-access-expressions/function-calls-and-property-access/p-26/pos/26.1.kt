// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 26 -> sentence 26
 *                expressions, object-literals, functional-interface-lambda-literals -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: functional interface trailing lambda type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun interface Action {
    fun run()
}

fun exec(a: Action) {
    a.run()
}

fun case1() {
    exec { }
    checkSubtype<Unit>(Unit)
}
