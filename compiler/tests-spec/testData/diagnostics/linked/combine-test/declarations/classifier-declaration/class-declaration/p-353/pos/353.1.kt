// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 353 -> sentence 353
 * declarations, declaration-visibility -> paragraph 353 -> sentence 353
 * declarations, property-declaration -> paragraph 353 -> sentence 353
 * NUMBER: 1
 * DESCRIPTION: private var in class body can be modified inside class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { private var n = 0; fun inc(): Int { n += 1; return n } }

// TESTCASE NUMBER: 1
fun test(): Int = C().inc()

fun case1() {
    checkSubtype<Int>(test())
}
