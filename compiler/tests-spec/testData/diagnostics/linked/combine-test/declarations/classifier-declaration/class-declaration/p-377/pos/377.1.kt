// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 377 -> sentence 377
 * declarations, declaration-visibility -> paragraph 377 -> sentence 377
 * declarations, property-declaration -> paragraph 377 -> sentence 377
 * declarations, classifier-declaration, companion-object -> paragraph 377 -> sentence 377
 * NUMBER: 1
 * DESCRIPTION: companion private val and const val have different outside-class access rules type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C { companion object { private const val K = 1; fun internalGet(): Int = K } }

// TESTCASE NUMBER: 1
fun test(): Int = C.internalGet()

fun case1() {
    checkSubtype<Int>(test())
}
