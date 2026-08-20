// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 366 -> sentence 366
 * declarations, declaration-visibility -> paragraph 366 -> sentence 366
 * declarations, property-declaration -> paragraph 366 -> sentence 366
 * NUMBER: 1
 * DESCRIPTION: public val with private set is readable but not writable outside class type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Counter { var count: Int = 0; private set; fun bump() { count++ } }

// TESTCASE NUMBER: 1
fun test(): Int { val c = Counter(); c.bump(); return c.count }

fun case1() {
    checkSubtype<Int>(test())
}
