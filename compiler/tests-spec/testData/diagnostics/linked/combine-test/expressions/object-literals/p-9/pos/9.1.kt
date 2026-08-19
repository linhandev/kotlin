// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: object literal must implement abstract property from interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Named {
    val name: String
}

fun case_1(): String = object : Named {
    override val name: String = "x"
}.name

fun case_1_check() {
    checkSubtype<String>(case_1())
}
