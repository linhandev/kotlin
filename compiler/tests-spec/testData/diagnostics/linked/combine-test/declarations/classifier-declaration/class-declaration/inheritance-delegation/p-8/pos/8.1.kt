// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: observable property delegate increments change counter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
import kotlin.properties.Delegates

var changes = 0

class Box {
    var x: Int by Delegates.observable(0) { _, _, _ -> changes++ }
}

fun case_1() {
    checkSubtype<Int>(Box().apply { x = 1; x = 2 }.let { changes })
}
