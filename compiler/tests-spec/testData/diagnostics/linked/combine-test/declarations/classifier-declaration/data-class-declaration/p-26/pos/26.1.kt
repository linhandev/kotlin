// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: Map.Entry destructuring uses componentN like data classes
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val e: Map.Entry<String, Int> = mapOf("a" to 1).entries.first()
    val (k, v) = e
    checkSubtype<Int>(k.length + v)
}
