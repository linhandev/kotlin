// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: notNull property delegate
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

class Box {
    var x: Int by Delegates.notNull()
}

fun case_1() {
    checkSubtype<Int>(Box().apply { x = 42 }.x)
}
