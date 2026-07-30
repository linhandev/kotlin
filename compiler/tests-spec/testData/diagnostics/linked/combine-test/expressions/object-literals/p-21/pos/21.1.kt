// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: anonymous object type is safely assignable to super interface
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface P {
    fun id(): String
}

fun test(p: P): String = p.id()

fun case_1(): String = test(object : P {
    override fun id(): String = "anon"
})

fun case_1_check() {
    checkSubtype<String>(case_1())
}
